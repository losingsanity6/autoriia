package pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewCarsPage {

    private final WebDriver driver;
    private final By carLink = By.xpath("//*[@id='marks-block']/a[1]");
    private final By golfLink = By.linkText("Golf");
    private final By firstImage = By.xpath("//*[@class = 'photo-294x190']");

    private final Logger log = Logger.getLogger(NewCarsPage.class);

    public NewCarsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnCarfindElementByLink(String linkText){
        driver.findElement(By.linkText(linkText)).click();
    }


    public String clickFirstImage() {
        driver.findElement(firstImage).click();
        log.info("Click on first image on Golf page was perfomed");
        return driver.getCurrentUrl();
    }


}
