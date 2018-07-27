package tests;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
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

    @Test
    public void checkClientErrorMessage() {
        given().
                queryParam("wrongParam", "akdsjadksjad").
                get("info").
                then().statusCode(403).and().body("error.code", containsString("API_KEY_MISSING"));
    }


    @Test
    public void doGetRequest() {
        io.restassured.response.Response response =
                given().
                        queryParam("api_key", "0SW6PYn2So4FiFdxIL5HWdKa0rdQiAdZzb6AwIZK").
                        queryParam("auto_id", "12132398").
        when().
                get("info").
                then().extract().response();

        System.out.println(response.asString());
    }
    @Test(description = "implementation through httpClient")
    public void httpClientStatusCode() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://developers.ria.com/auto/info?api_key=0SW6PYn2So4FiFdxIL5HWdKa0rdQiAdZzb6AwIZK&auto_id=19050985");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }
    @Test
    public void httpClientBody(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://developers.ria.com/auto/info?api_key=0SW6PYn2So4FiFdxIL5HWdKa0rdQiAdZzb6AwIZK&auto_id=19050985");
        CloseableHttpResponse response = httpClient.execute(httpGet);
      HttpEntity entity =  response.getEntity();
      entity.getContent();

    }
}

