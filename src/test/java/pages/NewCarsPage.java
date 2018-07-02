package pages;

import utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewCarsPage {

    private WebDriver driver;
    private final By carLink = By.xpath("//*[@id=\"marks-block\"]/a[1]");
    private final By golfLink = By.linkText("Golf");
    private final By firstImage = By.xpath("//*[@class = 'photo-294x190']");


    public NewCarsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String clickCar() {
        Utils utils = new Utils(driver);
        utils.ScrollWindow(carLink);
        driver.findElement(carLink).click();
        return driver.getCurrentUrl();

    }

    public String clickGolf() {
        driver.findElement(golfLink).click();
        return driver.getCurrentUrl();
    }

    public String clickFirstImage() {
        driver.findElement(firstImage).click();
        return driver.getCurrentUrl();
    }


}
