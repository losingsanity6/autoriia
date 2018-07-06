package data_provider;

import org.testng.ITestContext;

public class DataProvider {

    public Object[][] getData(ITestContext context) {
        String parameter = context.getCurrentXmlTest().getLocalParameters().get("names");
        String[] names = parameter.split(",");
        Object[][] returnValues = new Object[names.length][1];
        int index = 0;
        for (Object[] each : returnValues) {
            each[0] = names[index++].trim();
        }
        return returnValues;
    }
}

