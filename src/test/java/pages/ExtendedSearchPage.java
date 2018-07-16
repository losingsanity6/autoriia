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
    private final By showButton = By.xpath("//*[@id='results']/div/a[@class='button large indent_fes']");

    private final Logger log = Logger.getLogger(ExtendedSearchPage.class);
    Utils utils = new Utils(driver);

    public ExtendedSearchPage(WebDriver driver) {
        this.driver = driver;
    }


    public void clickShowButton() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(showButton));
        driver.findElement(showButton).click();


    }

    public void clickCheckboxes(String carType) {
        methodToChooseCheckboxes(carCheckbox, carType);
        //utils.ScrollWindow(originCheckbox);



    }
public void ckickOrigin(String country){
    methodToChooseCheckboxes(originCheckbox, country);
}

    public void methodToChooseCheckboxes(By element, String textToCompare) {

        List<WebElement> Checkbox = driver.findElements(element);
        for (int i = 0; i < Checkbox.size(); i++) {
            WebElement El = Checkbox.get(i);
            String textFromElement = El.getAttribute("innerHTML");
            if (textFromElement.equals(textToCompare)) {
                El.click();
            }
        }
    }
}