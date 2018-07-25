package pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewCarsPage {

    private final WebDriver driver;
    private final By firstImage = By.xpath("//*[@class='gallery-ticket-item']/a[contains(@href, 'vii-pokolenie-restajling-khetchbek')]");
    private final By newCarLink = By.xpath("//*[@id='marks-block']/a[@title='Volkswagen']");
    private final Logger log = Logger.getLogger(NewCarsPage.class);

     public NewCarsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnCarfindElementByLink(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }

public void clickElementByPartialLinkText(String carBrand){
        driver.findElement(By.partialLinkText(carBrand)).click();
}

    public String clickFirstImage() {
        driver.findElement(firstImage).click();
        log.info("Click on first image on Golf page was perfomed");
        return driver.getCurrentUrl();
    }


}
