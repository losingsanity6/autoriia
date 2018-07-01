package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AllForAutoPage {
    WebDriver driver;
    private By detailsForCar = By.linkText("Каталог авторазборок");
    private By carBrand = By.id("select_category");
    private By carModer = By.id("select_model");
    private  By searchButton = By.xpath("//*[@id=\"search_form\"]/div/div/div[3]/button");
    public AllForAutoPage(WebDriver driver){
         this.driver=driver;
    }
    public void clickDetailsForCars(){
        driver.findElement(detailsForCar).click();
    }
    public  void selectCarBrand(){
        Select brandCar = new Select(driver.findElement(carBrand));
        brandCar.selectByIndex(5);
    }
    public void selectCarModel(){
        Select modelCar = new Select(driver.findElement(carModer));
        modelCar.selectByIndex(6);
    }
    public void clickSearchButton(){
        driver.findElement(searchButton).click();
    }
}
