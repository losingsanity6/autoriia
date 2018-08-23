package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import static configs.DriverProvider.driver;

public class AllForAutoPage {
    private final By carBrandLocator = By.id("select_category");
    private final By carModer = By.id("select_model");
    private final By searchButton = By.xpath("//*[@id='search_form']/div/div/div[@class='footbar-search__main']/button");


    public AllForAutoPage clickOnLinkText(String linkText) {
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
