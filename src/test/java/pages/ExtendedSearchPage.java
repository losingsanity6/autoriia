package pages;

import org.apache.log4j.Logger;
import utils.HelpersForTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExtendedSearchPage {
    private WebDriver driver;

    private final By carCheckbox = By.xpath("//*[@id='bodies']/ul/li[@class='item-checkbox']/label/span[@class='name']");
    private final By originCheckbox = By.xpath("//*[@class='result-section mhide']//ul/li[@class='rubric']/label");
    private final By showButton = By.xpath("//*[@id='results']/div/a[@class='button large indent_fes']");

    private final Logger log = Logger.getLogger(ExtendedSearchPage.class);
    HelpersForTests helpersForTests = new HelpersForTests();

    public ExtendedSearchPage(WebDriver driver) {
        this.driver = driver;
    }


    public ResultPage clickShowButton() {

        driver.findElement(showButton).click();
        //  driver.findElement(showButton).click();
        log.info("Click on show button was perfomed");

        return new ResultPage(driver);
    }

    public ExtendedSearchPage clickCheckboxes(String carType) {
        helpersForTests.methodToChooseCheckboxes(carCheckbox, carType);
        log.info("click on car type checkbox was perfomed");
        return this;


    }

    public ExtendedSearchPage ckickOrigin(String country) {
        HelpersForTests helpersForTests = new HelpersForTests();
        helpersForTests.ScrollWindow(originCheckbox);
        helpersForTests.methodToChooseCheckboxes(originCheckbox, country);
        log.info("click on country check box was perfomed");
        return this;
    }


}