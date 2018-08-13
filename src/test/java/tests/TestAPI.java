package tests;

import com.google.gson.Gson;
import data_provider.ConfigFileReader;
import data_provider.DataProviderApi;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.StringContains.containsString;


public class TestAPI {
    //  public final String apiKey = "0SW6PYn2So4FiFdxIL5HWdKa0rdQiAdZzb6AwIZK";
    ConfigFileReader configFileReader = new ConfigFileReader();

    @BeforeTest
    public void setUp() {
        RestAssured.baseURI = "https://developers.ria.com/auto/";
    }

    @Test(dataProvider = "Status code", dataProviderClass = DataProviderApi.class)
    public void checkStatusCode(String carId, int statusCode) {
        given().queryParam("api_key",configFileReader.getApiKey() ).queryParam("marka_id[1]", carId).
                when().
                get("search").
                then().
                statusCode(statusCode);


    }

    @Test(dataProvider = "Auto Id and user ID", dataProviderClass = DataProviderApi.class)
    public void bodyTest(String autoId, int userId) {
        given().
                queryParam("api_key", configFileReader.getApiKey()).
                queryParam("auto_id", autoId).
                when().
                get("/info").
                then().contentType(ContentType.JSON).and().body("userId", equalTo(userId));

    }

    @Test(dataProviderClass = DataProviderApi.class, dataProvider = "contentType")
    public void headerTest(String carId, String contentType) {
        io.restassured.response.Response response = given().
                queryParam("api_key", configFileReader.getApiKey()).
                queryParam("marka_id[1]", carId)
                .get("search").
                        andReturn();
        String headerValueContentType = response.getHeader("Content-type");
        Assert.assertTrue(headerValueContentType.contains(contentType));
    }

    @Test(dataProvider = "data for Client error message", dataProviderClass = DataProviderApi.class)
    public void checkClientErrorMessage(String wrongApiKey, String message) {
        given().
                queryParam("api_key", wrongApiKey).
                post("info").
                then().statusCode(403).and().body("error.code", containsString(message));
    }


    @Test(dataProviderClass = DataProviderApi.class, dataProvider = "doGetRequest")
    public void doGetRequest(String autoId) {
        io.restassured.response.Response response =
                given().
                        queryParam("api_key", configFileReader.getApiKey()).
                        queryParam("auto_id", autoId).
                        when().
                        get("info").
                        then().extract().response();

        System.out.println(response.asString());
    }
   @Test(description = "implementation through httpClient")
    public void httpClientStatusCode() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://developers.ria.com/auto/info?api_key=0SW6PYn2So4FiFdxIL5HWdKa0rdQiAdZzb6AwIZK&auto_id=19050985");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }
    @Test
    public void httpClientBody()throws IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://developers.ria.com/auto/info?api_key=0SW6PYn2So4FiFdxIL5HWdKa0rdQiAdZzb6AwIZK&auto_id=19050985");
        CloseableHttpResponse response = httpClient.execute(httpGet);
      HttpEntity entity =  response.getEntity();
      entity.getContent();

    }
}

