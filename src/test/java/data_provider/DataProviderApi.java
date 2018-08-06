package data_provider;

import org.testng.annotations.DataProvider;

public class DataProviderApi {
    @DataProvider(name = "Status code")
    public static Object[][] statusCode()

    {
        return new Object[][]{
                {"84", 200}
        };
    }

    @DataProvider(name = "Auto Id and user ID")
    public static Object[][] autoId(){
        return new Object[][]{
                {"19050985",489269}
        };
    }

        @DataProvider(name = "contentType")
        public static Object[][] contentType()

        {
            return new Object[][]{
                    {"84", "text/html"}
            };
        }
    @DataProvider(name = "data for Client error message")
    public static Object[][] dataForClientErrorMessage()

    {
        return new Object[][]{
                {"akdsjadksjad", "API_KEY_INVALID"}
        };
    }

    @DataProvider(name = "doGetRequest")
    public static Object[][] doGetRequest()

    {
        return new Object[][]{
                {" 12132398"}
        };
    }
    }
