import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LoginPageObject{

    private WebDriver driver;
    private By numberInput = By.id("emailloginform-email");
    private By passwordInput = By.id("emailloginform-password");
    private By LoginBtn = By.xpath("//*[@id=\"login-form\"]/div[4]/button");
    private By FacebookLocator = By.xpath("/html/body/div[1]/div[1]/a[1]");

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

     public void LoginInput(String password, String number){
        driver.findElement(numberInput).sendKeys(number);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(passwordInput).sendKeys(password);
     }
     public void loginViaFacebook(){
        driver.findElement(FacebookLocator).click();

     }
     public void clickLoginBtn(){
        driver.findElement(LoginBtn).click();
     }

     }

