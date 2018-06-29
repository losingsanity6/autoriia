package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewCarsPAge {
    private WebDriver driver;
    private By carLink = By.xpath("//*[@id=\"marks-block\"]/a[1]");

    public NewCarsPAge(WebDriver driver) {
        this.driver = driver;
    }
    public  void clickCar(){
        driver.findElement(carLink).click();
    }
    public void golfClick(){
        driver.findElement(By.xpath("/html/body/div[2]/section/ol/li[12]/a")).click();
    }

}
