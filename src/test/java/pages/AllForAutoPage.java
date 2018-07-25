package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AllForAutoPage {
    private final WebDriver driver;
    private final By carBrandLocator = By.id("select_category");
    private final By carModer = By.id("select_model");
    private final By searchButton = By.xpath("//*[@id='search_form']/div/div/div[@class='footbar-search__main']/button");

    public AllForAutoPage(WebDriver driver) {
        this.driver = driver;
    }

   public AllForAutoPage clickOnLinkText(String linkText){
        driver.findElement(By.linkText(linkText));
        return this;
   }

    public final void selectCarBrand(String carBrand) {
        Select brandCar = new Select(driver.findElement(carBrandLocator));
        brandCar.selectByVisibleText(carBrand);
    }

    public void selectCarModel(String carModel) {
        Select modelCar = new Select(driver.findElement(carModer));
        modelCar.selectByVisibleText(carModel);
    }

    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }
}
