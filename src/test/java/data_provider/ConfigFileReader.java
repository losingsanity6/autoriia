package data_provider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private Properties properties;
    private final String propertyFilePath = "src\\main\\resources\\configs\\configuration.properties";


    public ConfigFileReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }


    public long getImplicitlyWait() {
        String implicitlyWait = properties.getProperty("pageWaitTimeout");
        if (implicitlyWait != null)
            return Long.parseLong(implicitlyWait);
        else
            throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("baseURL");
        if (url != null)
            return url;
        else
            throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    public String getApiKey() {
        String apiKey = properties.getProperty("Api_Key");
        if (apiKey != null)
            return apiKey;
        else
            throw new RuntimeException("apiKey not specified in the Configuration.properties file.");
    }
    public String getChromePath(){
        String chromePath=properties.getProperty("chromePath");
        if(chromePath!=null)
            return chromePath;
        else
            throw  new RuntimeException("Chrome path was not found in Configuration properties file");
    }
    public String getGeckoDriverPath(){
        String geckoDriver=properties.getProperty("geckDriverPath");
        if(geckoDriver!=null)
            return geckoDriver;
        else
            throw  new RuntimeException("Chrome path was not found in Configuration properties file");
    }
    public DriverType getBrowser() {
        String browserName = properties.getProperty("browser");
        if(browserName == null || browserName.equals("chrome")) return DriverType.CHROME;
        else if(browserName.equalsIgnoreCase("firefox")) return DriverType.FIREFOX;
        else throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
    }
}