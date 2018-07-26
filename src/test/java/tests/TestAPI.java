package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.xml.ws.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.head;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.StringContains.containsString;


public class TestAPI {
    @BeforeTest
    public void setUp() {
        RestAssured.baseURI = "https://developers.ria.com/auto/";
    }

    @Test
    public void checkStatusCode() {
        when().
                get("search?api_key=0SW6PYn2So4FiFdxIL5HWdKa0rdQiAdZzb6AwIZK&marka_id[1]=84").
                then().
                statusCode(200);


    }

    @Test
    public void bodyTest() {
        given().
                queryParam("api_key", "0SW6PYn2So4FiFdxIL5HWdKa0rdQiAdZzb6AwIZK").
                queryParam("auto_id", "19050985").
                when().
                get("/info").
                then().contentType(ContentType.JSON).and().body("userId", equalTo(489269));

    }

    @Test
    public void headerTest() {
        io.restassured.response.Response response = given().
                queryParam("api_key", "0SW6PYn2So4FiFdxIL5HWdKa0rdQiAdZzb6AwIZK").
                queryParam("marka_id[1]", "84")
                .get("search").
                        andReturn();
        String headerValueContentType = response.getHeader("Content-type");
        Assert.assertTrue(headerValueContentType.contains("html\text"));
    }
}

