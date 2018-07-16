package tests;


import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ExtendedSearchPage;
import pages.MainPage;
import pages.NewCarsPage;
import pages.ResultPage;
import utils.Annotations;
import utils.Utils;

import java.util.concurrent.TimeUnit;


public class FiltersTest extends Annotations {


    private final Logger log = org.apache.log4j.Logger.getLogger(FiltersTest.class);

    protected MainPage openMainPage() {
        driver.get("https://auto.ria.com");
        return new MainPage(driver);
    }

    //TODO: rewrite test for new cars filter


    @Test(description = "Check work of used cars filter", dataProvider = "TesForUsedFilters", dataProviderClass = data_provider.Data_Provider.class)
    public void usedCarsFilterTest(String carBrand, String carModel, String region, String yearFrom, String yearTo, String priceFrom, String priceTo, String message) {
        log.info("TC Used cars filters started");
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseCarBrand(carBrand)
                .clickModel(carModel)
                .clickRegion(region)
                .selectYearFrom(yearFrom, yearTo)
                .enterPriceToPriceField(priceFrom, priceTo);
        ResultPage resultPage = mainPage.clickSearchButton();
        Assert.assertTrue(resultPage.getTextFromNoResultsMessage().contains(message), "The message does not contain defined text");


    }

    @Test(description = "Check extended search", dataProvider = "checkboxes", dataProviderClass = data_provider.Data_Provider.class)
    public void extendedSearchCheckBoxes(String carType, String country) {
        log.info("TC checkboxes in extended search started");
        MainPage mainPage = new MainPage(driver);
        ExtendedSearchPage extendedSearchPage = mainPage.clickExtendedSearchButton()
                .clickCheckboxes(carType)
                .ckickOrigin(country);
        ResultPage resultPage = extendedSearchPage.clickShowButton();
        Assert.assertTrue(resultPage.getH1Text().contains(carType), "The header contains other text");
    }

//TODO rewrite test for price field

    @Test(description = "Check work of price fields", dataProvider = "boundariesForPriceField", dataProviderClass = data_provider.Data_Provider.class)
    public void priceFieldTest(String priceFrom, String priceTo, String resultTestForAssert) {
        log.info("TC price field started");
        MainPage mainPage = new MainPage(driver);
        mainPage.enterPriceToPriceField(priceFrom, priceTo);
        ResultPage resultPage = mainPage.clickSearchButton();
        Assert.assertEquals(resultTestForAssert, resultPage.getTextFromPriceInputFrom(), "The price field does not contain parameter");
        log.info("Assertation passed");


    }

    @Test(description = "invalidPriceFieldTest", dataProvider = "invalidDataForPriceField", dataProviderClass = data_provider.Data_Provider.class)
    public void invalidPriceFieldTest(String priceFrom, String priceTo, String resultTestForAssert) {
        priceFieldTest(priceFrom, priceTo, resultTestForAssert);
    }


    @Test(description = "Check opening links", dataProvider = "linkNames", dataProviderClass = data_provider.Data_Provider.class)
    public void checkPages(String firstPageLink, String linkOnSecondPage, String linkOnThirdPage) {
        Utils utils = new Utils(driver);
        log.info("TC check links started");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnElementByLinkText(firstPageLink);
        NewCarsPage newCarsPage = new NewCarsPage(driver);
        newCarsPage.clickOnCarfindElementByLink(linkOnSecondPage);
        String secondPageUrl = driver.getCurrentUrl();
        newCarsPage.clickOnCarfindElementByLink(linkOnThirdPage);
        String thirdPage = driver.getCurrentUrl();
        newCarsPage.clickFirstImage();
        Assert.assertTrue(secondPageUrl.contains(linkOnSecondPage) && thirdPage.contains(linkOnThirdPage));


    }

   /* @Test
    public void detailsAuto() {
        MainPage mainPageObject = new MainPage(driver);
        mainPageObject.allForCarsDropDown();
        AllForAutoPage allForAutoPage = new AllForAutoPage(driver);
        allForAutoPage.clickDetailsForCars();
        allForAutoPage.selectCarBrand();
        allForAutoPage.selectCarModel();
        allForAutoPage.clickSearchButton();

    }*/

}

