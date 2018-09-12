package tests;


import cucumber.api.java.it.Ma;
import gherkin.lexer.He;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import tests.dataForTests.DataProviderSpecific;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.TestBase;
import utils.HelpersForTests;

import java.util.ArrayList;
import java.util.List;


public class FiltersTest extends TestBase {


    private final Logger log = org.apache.log4j.Logger.getLogger(FiltersTest.class);


    @Test(dataProvider = "NewCarsFilters", dataProviderClass = DataProviderSpecific.class)
    public void newCarsFilerTestNoResultMessege(String carBrand, String carModel, String region, String yearFrom, String yearTo, String priceFrom, String priceTo, String message) {
        MainPage mainPage = new MainPage();
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
        MainPage mainPage = new MainPage();
        mainPage
                .chooseCarBrand(carBrand)
                .clickModel(carModel)
                .clickRegion(region)
                .selectYearFrom(yearFrom, yearTo)
                .enterPriceToPriceField(priceFrom, priceTo);
        ResultPage resultPage = mainPage.clickSearchButton();
        Assert.assertTrue(resultPage.getTextFromNoResultsMessage().contains(message), "The message does not contain defined text");

    }

    @Test(dataProvider = "Data for used car filters for carbrand", dataProviderClass = DataProviderSpecific.class)
    public void usedCarBrandFilterTest(String carBrand) {
        MainPage mainPage = new MainPage();
        mainPage
                .chooseCarBrand(carBrand);
        ResultPage resultPage = mainPage.clickSearchButton();
        Assert.assertTrue(resultPage.methodToObtainListOfElements().contains(carBrand) && resultPage.textFromHeader().contains(carBrand), "Assertation failed");
    }


    @Test(description = "Check extended search", dataProvider = "checkboxes", dataProviderClass = DataProviderSpecific.class)
    public void extendedSearchCheckBoxes(String carType, String country) {
        MainPage mainPage = new MainPage();
        log.info("TC checkboxes in extended search started");
        ExtendedSearchPage extendedSearchPage = mainPage.clickExtendedSearchButton()
                .clickCheckboxes(carType)
                .ckickOrigin(country);
        ResultPage resultPage = extendedSearchPage.clickShowButton();
        Assert.assertTrue(resultPage.getH1Text().contains(carType), "The header contains other text");
    }


    @Test(description = "Check work of price fields", dataProvider = "boundariesForPriceField", dataProviderClass = DataProviderSpecific.class)
    public void priceFieldTest(String priceFrom, String priceTo, String resultTestForAssert) {
        log.info("TC price field started");
        MainPage mainPage = new MainPage();
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
        HelpersForTests helpersForTests = new HelpersForTests();
        log.info("TC check links started");
        MainPage mainPage = new MainPage();
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

    @Test(dataProvider = "Data to check languages", dataProviderClass = DataProviderSpecific.class)
    public void checkLanguages(String lang, String title, String url) {
        MainPage mainPage = new MainPage();
        HelpersForTests helpersForTests = new HelpersForTests();
        mainPage.clickOnElementByLinkText(lang);
        Assert.assertTrue(helpersForTests.getTitle().contains(title) && helpersForTests.getUrl().contains(url));
    }

    @Test(dataProvider = "Data for other Ria services", dataProviderClass = DataProviderSpecific.class)
    public void checkOtherRiaServices(String linkName, String url, String title) {
        MainPage mainPage = new MainPage();
        mainPage.clickOnElementByLinkText(linkName);
        HelpersForTests helpers = new HelpersForTests();
        helpers.switchBetweenWindows(1);
        Assert.assertTrue(driver.getCurrentUrl().equals(url) && driver.getTitle().equals(title), "Assertation failed");

    }

    @Test(dataProvider = "links names for clickOncarTypeDropdowns", dataProviderClass = DataProviderSpecific.class)
    public void clickOncarTypeDropdowns(String usedCarType, String newCarType) {
        MainPage mainPage = new MainPage();
        int links = mainPage.getListSize();
        for (int i = 0; i < links; i++) {
            List<WebElement> linksList = mainPage.getListOfElements();
            WebElement item = linksList.get(i);
            if (item.getText().equals(usedCarType) || item.getText().equals(newCarType)) {
                item.click();
                Assert.assertTrue(mainPage.getContainerContent().isDisplayed());
            } else {
                item.click();
                Assert.assertTrue(mainPage.getOtherContainer().isDisplayed());
            }

        }


    }
    @Test(description="test sourcetree")
public void testSourceTree(){
        //
    }
}



