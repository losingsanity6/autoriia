package Pages;

import Utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExtendedSearchPageObject {
    private WebDriver driver;

    private By checkboxSedan = By.xpath("//*[@id=\"bodies\"]/ul/li[1]/label");
    private By checkboxEngland = By.xpath("//*[@id=\"originContainer\"]/div[2]/ul/li[1]/label");
    private By showButton = By.xpath("//*[@id=\"results\"]/div/a[1]");


    public ExtendedSearchPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickShowButton() {
        driver.findElement(showButton).click();
    }

    public void clickCheckBoxes() {
        Utils utils = new Utils(driver);
        utils.ScrollWindow(checkboxSedan);
        driver.findElement(checkboxSedan).click();
        driver.findElement(checkboxEngland).click();
    }
}