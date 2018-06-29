package Tests;

import Pages.LoggedInPage;
import Pages.LoginPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "browsers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://auto.ria.com/");


    }
    @AfterTest
    public void close() {
        driver.close();
    }
    @Test
    public void validLoginTest() {
        driver.get("https://auto.ria.com/login.html");
        driver.switchTo().frame("login_frame");
        LoginPageObject loginPageObject = new LoginPageObject(driver);
        loginPageObject.LoginInput("iamonria", "380637017113");
        loginPageObject.clickLoginButton();
        LoggedInPage loggedInPage = new LoggedInPage(driver);
        System.out.println("!!" +loggedInPage.getTextFromLoggedUser());


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
