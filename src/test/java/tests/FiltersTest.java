package tests;


import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ExtendedSearchPage;
import pages.MainPage;
import pages.NewCarsPage;
import pages.ResultPage;
import utils.Annotations;
import utils.Utils;


public class FiltersTest extends Annotations {


    private final Logger log = org.apache.log4j.Logger.getLogger(FiltersTest.class);


    //TODO: rewrite test for new cars filter


    @Test(description = "Check work of used cars filter", dataProvider = "TesForUsedFilters", dataProviderClass = data_provider.Data_Provider.class)
    public void usedCarsFilterTest(String carBrand, String carModel, String region, String yearFrom, String yearTo, String priceFrom, String priceTo, String message) {
        log.info("TC Used cars filters started");
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseCarBrand(carBrand);
        mainPage.clickModel(carModel);
        mainPage.clickRegion(region);
        mainPage.selectYearFrom(yearFrom, yearTo);
        mainPage.enterPriceToPriceField(priceFrom, priceTo);
        mainPage.clickSearchButton();
        ResultPage resultPage = new ResultPage(driver);
        Assert.assertTrue(resultPage.getTextFromNoResultsMessage().contains(message), "The message does not contain defined text");//TODO:add errors
        //TODO: add logs

    }

    @Test(description = "Check extended search")
    public void extendedSearchCheckBoxes() {
        log.info("TC checkboxes in extended search started");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickExtendedSearchButton();
        ExtendedSearchPage extendedSearchPage = new ExtendedSearchPage(driver);
        extendedSearchPage.clickCheckBoxes();
        extendedSearchPage.clickShowButton();
        ResultPage resultPage = new ResultPage(driver);
        Assert.assertTrue(resultPage.getH1Text().contains("Седан"), "The header contains other text");
    }


    @Test(description = "Check work of price fields", dataProvider = "boundariesForPriceField", dataProviderClass = data_provider.Data_Provider.class)
    public void priceFieldTestFromLowestToHighest(String priceFrom, String priceTo) {
        log.info("TC price field started");
        MainPage mainPage = new MainPage(driver);
        mainPage.enterPriceToPriceField(priceFrom, priceTo);
        mainPage.clickSearchButton();
        ResultPage resultPage = new ResultPage(driver);
        Assert.assertEquals(priceFrom, resultPage.getTextFromPriceInputFrom(), "The price field does not contain parameter");
        log.info("Assertation passed");


    }


    @Test(description = "Invalid price to price input", dataProvider = "invalidDataForPriceField", dataProviderClass = data_provider.Data_Provider.class )
    public void invalidDataForPriceField(String priceFrom, String priceTo) {
        log.info("TC Invalid data for price field started");
        MainPage mainPage = new MainPage(driver);
        mainPage.enterPriceToPriceField(priceFrom, priceTo);
        mainPage.clickSearchButton();
        ResultPage resultPage = new ResultPage(driver);
        Assert.assertEquals("", resultPage.getTextFromPriceInputFrom(), "The price field does not contain the parameter");
        log.info("Assertation passed");

    }

    @Test(description = "Check opening links", dataProvider = "linkNames", dataProviderClass =  data_provider.Data_Provider.class )
    public void checkPages(String firstPageLink,String linkOnSecondPage, String linkOnThirdPage ) {
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
        Assert.assertTrue( secondPageUrl.contains(linkOnSecondPage)&&thirdPage.contains(linkOnThirdPage));


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

