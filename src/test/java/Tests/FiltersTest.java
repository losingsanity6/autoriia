package Tests;


import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;
import org.apache.log4j.BasicConfigurator;

import java.util.concurrent.TimeUnit;

public class FiltersTest{
private WebDriver driver;
    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "browsers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        String baseUrl = "https://auto.ria.com/";
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);



    }

    //@AfterTest
    //public void close() {
    //  driver.close();
    // }


    @Test
    public void selectCarNew() {
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.selectCarNew();
        mainPageObject.clickSearchButton();
        ResultPageObject resultPageObject = new ResultPageObject(driver);
        String carName = resultPageObject.dropDownCar();
        System.out.println(carName);
        Assert.assertEquals("Audi", carName);
    }

    @Test
    public void UsedCarsFilterTest() {
        String yearFrom = "2000";
        String yearTo = "2018";
        String priceTo = "19000";
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.carBrandDropdownUsedInput("Daewoo");
        mainPageObject.clickCarInAutocompletedSearch();
        mainPageObject.Model("Korando");
        mainPageObject.clickModelInAutocompletedSearch();
        mainPageObject.clickRegion("Киев");
        mainPageObject.clickAutocompletedRegion();
        mainPageObject.selectYearFrom(yearFrom, yearTo);
        mainPageObject.priceFieldInput("", priceTo);
        mainPageObject.clickSearchButton();
        ResultPageObject resultPageObject = new ResultPageObject(driver);
        Assert.assertTrue(resultPageObject.noResultsMessage().contains("Объявлений не найдено"));

    }

    @Test
    public void extendedSearchCheckBoxes() {
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.ExtendedSearchButtonClick();
        ExtendedSearchPageObject extendedSearchPageObject = new ExtendedSearchPageObject(driver);
        extendedSearchPageObject.Checkbox();
        extendedSearchPageObject.clickShowButton();
        ResultPageObject resultPageObject = new ResultPageObject(driver);
        System.out.println(resultPageObject.getH1Text());
        Assert.assertTrue(resultPageObject.getH1Text().contains("Седан"));
    }


    @Test
    public void priceFieldTestFromLowestToHighest() {
        BasicConfigurator.configure();
        String priceFrom = "1";
        String priceTo = "100000000000";
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.priceFieldInput(priceFrom, priceTo);
        mainPageObject.clickSearchButton();
        ResultPageObject resultPageObject = new ResultPageObject(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        resultPageObject.ScrollWindow();
        System.out.println("" + resultPageObject.getTextFromPriceInputFrom() + resultPageObject.getInputFromPriceFieldTo());
        Assert.assertEquals("1", resultPageObject.getTextFromPriceInputFrom());
        //   String h1 = resultPageObject.getH1Text();

    }


    @Test
    public void InvalidDataForPriceField() {
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.priceFieldInput("gggggggg", "wqwqwqw");
        mainPageObject.clickSearchButton();
        ResultPageObject resultPageObject = new ResultPageObject(driver);
        Assert.assertEquals("", resultPageObject.getTextFromPriceInputFrom());

    }

    @Test
    public void checkPages() {
        MainPageLinks mainPageLinks = new MainPageLinks(driver);
        mainPageLinks.clickNewCars();
        NewCarsPAge newCarsPAge = new NewCarsPAge(driver);
        newCarsPAge.clickCar();
        System.out.println(driver.getCurrentUrl());
        newCarsPAge.golfClick();
        System.out.println(driver.getCurrentUrl());
    }
}

