package Tests;

import Pages.LoginPageObject;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    private WebDriver driver;

    @Before
    public void setUP() {
        System.setProperty("webdriver.chrome.driver", "H:\\Testing\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://auto.ria.com/");


    }

    @Test
    public void validLoginTest() {
        driver.get("https://auto.ria.com/login.html");
        driver.switchTo().frame("login_frame");
        LoginPageObject loginPageObject = new LoginPageObject(driver);
        loginPageObject.LoginInput("iamonria", "380637017113");
        loginPageObject.clickLoginButton();

    }

    @Test
    public void InvalidLoginTest() {
        driver.get("https://auto.ria.com/login.html");
        driver.switchTo().frame("login_frame");
        LoginPageObject loginPageObject = new LoginPageObject(driver);
        loginPageObject.LoginInput("invalidPassword", "thisisinvalidnumber");
        loginPageObject.clickLoginButton();

    }

    @Test
    public void loginViaFacebook() {
        driver.get("https://auto.ria.com/login.html");
        driver.switchTo().frame("login_frame");
        LoginPageObject loginPageObject = new LoginPageObject(driver);
        loginPageObject.loginViaFacebook();
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        driver.findElement(By.id("email")).sendKeys("tanyalondon1@mail.ru");
        driver.findElement(By.id("pass")).sendKeys("iamsherlocked");
        driver.findElement(By.id("loginbutton")).click();
       // driver.switchTo().window();
        System.out.println("!!" +loginPageObject.Message());

        // driver.switchTo().window();


    }
}
