package tests;


import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.ExtendedSearchPage;
import pages.MainPage;
import pages.NewCarsPage;
import pages.ResultPage;
import utils.Annotations;


public class FiltersTest extends Annotations {


    private final Logger log = org.apache.log4j.Logger.getLogger(FiltersTest.class);

  @Test(description = "Check work of new cars filter")
    public void checkWorkOfNewCarsFilter() {
        log.info("TC check of new Cars Filters started");
        MainPage mainPage = new MainPage(driver);
        mainPage.selectCarNew();
        mainPage.clickSearchButton();
        ResultPage resultPage = new ResultPage(driver);
        String carName = resultPage.findCarDropdown();
        Assert.assertEquals("BMW", carName, "The car dropdown was not found");
        log.info("Assertation passed");

    }

    @Test(description = "Check work of used cars filter")
    @Parameters({"carBrand", "yearFrom", "yearTo", "priceTO", "carModel","region", "priceFrom"})//TODO: add params
    public void usedCarsFilterTest(String carBrand,  String yearFrom, String yearTo, String carModel, String region, String priceFrom,String priceTo ) {
        log.info("TC Used cars filters started");
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseCarBrand(carBrand);
        mainPage.clickModel(carModel);
        mainPage.clickRegion(region);
        mainPage.selectYearFrom(yearFrom, yearTo);
        mainPage.enterPriceToPriceField(priceFrom);
        mainPage.clickSearchButton();
        ResultPage resultPage = new ResultPage(driver);
        Assert.assertTrue(resultPage.getTextFromNoResultsMessage().contains("Объявлений не найдено"));//TODO:add errors
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
        System.out.println(resultPage.getH1Text());
        Assert.assertTrue(resultPage.getH1Text().contains("Седан"), "The header contains other text");
    }


    @Test(description = "Check work of price fields")
    public void priceFieldTestFromLowestToHighest() {
        log.info("TC price field started");
        String priceFrom = "1";
        String priceTo = "100000000000";
        MainPage mainPage = new MainPage(driver);
        mainPage.enterPriceToPriceField("1");
        mainPage.clickSearchButton();
        ResultPage resultPage = new ResultPage(driver);
        Assert.assertEquals("1", resultPage.getTextFromPriceInputFrom(), "The price field does not contain parameter");
        log.info("Assertation passed");


    }


    @Test(description = "Invalid price to price input")
    @Parameters({"text"})
    public void invalidDataForPriceField(@Optional("wqewe")String text){
        log.info("TC Invalid data for price field started");
        MainPage mainPage = new MainPage(driver);
        mainPage.enterPriceToPriceField(text);
        mainPage.clickSearchButton();
        ResultPage resultPage = new ResultPage(driver);
        Assert.assertEquals("", resultPage.getTextFromPriceInputFrom(), "The price field does not contain the parameter");
        log.info("Assertation passed");

    }

   @Test(description = "Check opening links")
    public void checkPages() {
        log.info("TC check links started");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickNewCars();
        NewCarsPage newCarsPage = new NewCarsPage(driver);
        Assert.assertTrue(newCarsPage.clickCar().contains("volkswagen") && newCarsPage.clickGolf().contains("golf") && newCarsPage.clickFirstImage().contains("khetchbek"), "The urls don't contain defined words ");


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

