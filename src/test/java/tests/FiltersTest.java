package tests;


import data_provider.DataProviderSpecific;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.Annotations;
import utils.Utils;


public class FiltersTest extends Annotations {


    private final Logger log = org.apache.log4j.Logger.getLogger(FiltersTest.class);



    @Test(dataProvider = "NewCarsFilters", dataProviderClass = DataProviderSpecific.class)
    public void newCarsFilerTest(String carBrand, String carModel, String region, String yearFrom, String yearTo, String priceFrom, String priceTo, String message) {
        MainPage mainPage = new MainPage(driver);
        mainPage
                .clickOnradioButton()
                .chooseCarBrand(carBrand)
                .clickModel(carModel)
                .selectRegion(region)
                .selectYearFrom(yearFrom, yearTo)
                .enterPriceToPriceField(priceFrom, priceTo);
        ResultPage resultPage = mainPage.clickSearchButton();
        Assert.assertTrue(resultPage.getTextFromNewCarsMessge().contains(message), "The message does not contain defined text");
    }

    @Test(description = "Check work of used cars filter", dataProvider = "TesForUsedFilters", dataProviderClass = DataProviderSpecific.class)
    public void usedCarsFilterTest(String carBrand, String carModel, String region, String yearFrom, String yearTo, String priceFrom, String priceTo, String message) {
        log.info("TC Used cars filters started");
        MainPage mainPage = new MainPage(driver);
        mainPage
                .chooseCarBrand(carBrand)
                .clickModel(carModel)
                .clickRegion(region)
                .selectYearFrom(yearFrom, yearTo)
                .enterPriceToPriceField(priceFrom, priceTo);
        ResultPage resultPage = mainPage.clickSearchButton();
        Assert.assertTrue(resultPage.getTextFromNoResultsMessage().contains(message), "The message does not contain defined text");


    }

    @Test(description = "Check extended search", dataProvider = "checkboxes", dataProviderClass = DataProviderSpecific.class)
    public void extendedSearchCheckBoxes(String carType, String country) {
        MainPage mainPage = new MainPage(driver);
        log.info("TC checkboxes in extended search started");
        //   MainPage mainPage = new MainPage(driver);
        ExtendedSearchPage extendedSearchPage = mainPage.clickExtendedSearchButton()
                .clickCheckboxes(carType)
                .ckickOrigin(country);
        ResultPage resultPage = extendedSearchPage.clickShowButton();
        Assert.assertTrue(resultPage.getH1Text().contains(carType), "The header contains other text");
    }


    @Test(description = "Check work of price fields", dataProvider = "boundariesForPriceField", dataProviderClass = DataProviderSpecific.class)
    public void priceFieldTest(String priceFrom, String priceTo, String resultTestForAssert) {
        log.info("TC price field started");
        MainPage mainPage = new MainPage(driver);
        mainPage
                .enterPriceToPriceField(priceFrom, priceTo);
        ResultPage resultPage = mainPage.clickSearchButton();
        Assert.assertEquals(resultTestForAssert, resultPage.getTextFromPriceInputFrom(), "The price field does not contain parameter");
        log.info("Assertation passed");


    }

    @Test(description = "invalidPriceFieldTest", dataProvider = "invalidDataForPriceField", dataProviderClass = DataProviderSpecific.class)
    public void invalidPriceFieldTest(String priceFrom, String priceTo, String resultTestForAssert) {
        priceFieldTest(priceFrom, priceTo, resultTestForAssert);
    }


    @Test(description = "Check opening links", dataProvider = "linkNames", dataProviderClass = DataProviderSpecific.class)
    public void checkPages(String firstPageLink, String linkOnSecondPage, String linkOnThirdPage) {
        Utils utils = new Utils(driver);
        log.info("TC check links started");
        MainPage mainPage = new MainPage(driver);
        NewCarsPage newCarsPage = mainPage.clickOnElementByLinkText(firstPageLink);
        newCarsPage
                .clickElementByPartialLinkText(linkOnSecondPage);
        String secondPageUrl = driver.getCurrentUrl();
        newCarsPage
                .clickOnCarfindElementByLink(linkOnThirdPage);
        String thirdPage = driver.getCurrentUrl();
        newCarsPage
                .clickFirstImage();
        Assert.assertTrue(secondPageUrl.contains(linkOnSecondPage.toLowerCase()) && thirdPage.contains(linkOnThirdPage.toLowerCase()));


    }

    @Test(dataProvider = "Detailsforautopage", dataProviderClass = DataProviderSpecific.class)
    public void detailsAuto(String linkText) {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickAllForAutoDropdown();
        AllForAutoPage allForAutoPage = new AllForAutoPage(driver);
        allForAutoPage
                .clickOnLinkText(linkText)
                .selectCarBrand(linkText);
                //.selectCarModel(linkText)
              //  .clickSearchButton();

    }

}

