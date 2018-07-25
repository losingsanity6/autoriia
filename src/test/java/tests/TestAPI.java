package tests;

import data_provider.ConfigFileReader;
import io.restassured.RestAssured;
import org.aspectj.lang.annotation.Before;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.xml.ws.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.IsEqual.equalTo;

public class TestAPI {
    @BeforeTest
    public void setUp(){
        RestAssured.baseURI = "https://developers.ria.com/auto/";
    }

    @Test
    public void responseCode1() {
        when().
                get("search?api_key=0SW6PYn2So4FiFdxIL5HWdKa0rdQiAdZzb6AwIZK&marka_id[1]=84").
                then().
                statusCode(200);



    }
    @Test
    public void bodyTest() {

        when().
                get("search?api_key=0SW6PYn2So4FiFdxIL5HWdKa0rdQiAdZzb6AwIZK&marka_id[1]=84").
                then().
                body("additional_params.lang_id", equalTo("2"));


    }
}

