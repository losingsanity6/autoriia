package pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ExtendedSearchPage {
    private WebDriver driver;

    private final By carCheckbox = By.xpath("//*[@id='bodies']/ul/li[@class='item-checkbox']/label/span[@class='name']");
    private final By originCheckbox = By.xpath("//*[@class='result-section mhide']//ul/li[@class='rubric']/label");
    private final By originContainer = By.xpath("//*[@id='originContainer']");
    private final By showButton = By.xpath("//*[@id='results']/div/a[1]");

    private final Logger log = Logger.getLogger(ExtendedSearchPage.class);
    Utils utils = new Utils(driver);

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
        methodToChooseCheckboxes(carCheckbox, carType);
        log.info("click on car type checkbox was perfomed");
        return this;


    }

    public ExtendedSearchPage ckickOrigin(String country) {
        methodToChooseCheckboxes(originCheckbox, country);
        log.info("click on country check box was perfomed");
        return this;
    }

    public void methodToChooseCheckboxes(By element, String textToCompare) {


        List<WebElement> Checkbox = driver.findElements(element);
        for (int i = 0; i < Checkbox.size(); i++) {
            WebElement El = Checkbox.get(i);
            String textFromElement = El.getAttribute("innerHTML");
            System.out.println(textFromElement);
            if (textFromElement.equals(textToCompare)) {
                El.click();
                return;
            }
        }
    }
}