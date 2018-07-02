package tests;

import pages.LoggedInPage;
import pages.LoginPage;
import pages.MainPage;
import utils.Annotations;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class LoginTest extends Annotations {

    @Test
    public void validLoginTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        driver.switchTo().frame("login_frame");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.LoginInput("iamonria", "380637017113");
        loginPage.clickLoginButton();
        try {
            LoggedInPage loggedInPage = new LoggedInPage(driver);
            // System.out.println(loggedInPage.getCurrentUrl());
            System.out.println("!!" + loggedInPage.getTextFromLoggedUser());
            Assert.assertTrue(loggedInPage.getTextFromLoggedUser().contains("Личный кабинет"), "The link does not contain text");
        } catch (Exception e) {

            System.out.println("Whoops! Captcha has appeared");
        }


    }

    @Test
    public void invalidLoginTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        driver.switchTo().frame("login_frame");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.LoginInput("invalidPassword", "948594835-03-04930459430589458");
        loginPage.clickLoginButton();
        try {
            Assert.assertTrue(loginPage.invalidPhoneMessage().contains("неверный мобильный номер телефона"), "The message does not contain that text");
        } catch (Exception e) {
            System.out.println("Whoops! Captcha has appeared");
        }

    }

    @Test
    public void loginViaFacebook() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.switchBetweenFrame();
        loginPage.loginViaFacebook();
        ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
        System.out.println(windowHandles);
        driver.switchTo().window(windowHandles.get(1));
        loginPage.LoginFacebook("tanyalondon1@mail.ru", "donotusethispassword");
        driver.switchTo().window(windowHandles.get(0));
        loginPage.switchBetweenFrame();
        System.out.println("!!" + loginPage.Message());
        Assert.assertTrue(loginPage.Message().contains("Не удалось"), " The message does not contain text");

    }


}
