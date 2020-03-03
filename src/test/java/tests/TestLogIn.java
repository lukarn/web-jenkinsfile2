package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.BasePage;

import utilities.DriverManager;


public class TestLogIn
{
    public static WebDriver driver;

    //from Jenkins
//    private long timeoutIsAt = 20;

//    private String envLoginPage = System.getenv("LOGIN_PAGE");
//    private String envLoginLogin = System.getenv("LOGIN_LOGIN");
//    private String envLoginPassword = System.getenv("LOGIN_PASSWORD");

//    private int timeoutIsAt = Integer.parseInt(System.getenv("TEST_TIMEOUT"));

//    //from here
    private String envLoginPage = "https://primera.e-sim.org/";
    private String envLoginLogin = "sledzik";
    private String envLoginPassword = "luk@rn0220";
    private int envTimeoutIsAt = 60;

    // Page Objects
    private BasePage basePage;
    private MainPage mainPage;


    @DataProvider
    public Object[][] getData()
    {
        return new Object[][]{
                {1, envLoginPage, "chrome"},
//                {2, envLoginPage, "firefox"},
        };
    }

    @BeforeSuite()
    public void BeforeSuite()
    {
        System.out.println("==============================================");
        System.out.println("=Test suite parameters(env. variables)       =");
        System.out.println("==============================================");
        System.out.println("=envLoginPage: " + envLoginPage);
        System.out.println("=envLoginLogin: " + envLoginLogin);
        System.out.println("=envLoginPassword: " + envLoginPassword);
        System.out.println("=timeoutIsAt: " + envTimeoutIsAt);
        System.out.println("==============================================");
    }

    @AfterMethod()
    public void AfterMethod()
    {
        if(driver != null)
        {
            driver.quit();
        }
        else
        {
            System.out.println("Something is wrong ---> driver = null in AfterMethod");
        }
    }

    //  , priority=1
    //  , enabled = false
    @Test(dataProvider="getData", enabled = false)
    public void launch(int p1, String p2, String p3) {
        // setup driver
        DriverManager driverManager = new DriverManager(driver);
        //usingBrowser=p3;
        driver = driverManager.getDriver(p3);

        //get to base page (from data provider)
        driver.get(p2);
        System.out.println("-------testing www no. " + p1 + " : " + p2 + " on " + p3);

        // Page Object - assign
        basePage = new BasePage(driver);
        mainPage = new MainPage(driver);

        Assert.assertTrue(basePage.isAt(envTimeoutIsAt), "----------BasePage not loaded!");
    }

    @Test(dataProvider="getData")
    public void loginIncorrectUser(int p1, String p2, String p3) {
        launch(p1, p2, p3);


        basePage.setLoginInput("IncorrectUser")
                .setPasswordInput(envLoginPassword)
                .setZalogujButton();

        Assert.assertTrue(basePage.loginError(), "----------Can not see login error info after incorrect login data");
    }

    @Test(dataProvider="getData")
    public void loginIncorrectUserAndPassword(int p1, String p2, String p3) {
        launch(p1, p2, p3);

        basePage.setLoginInput("IncorrectUser")
                .setPasswordInput("IncorrectPassword")
                .setZalogujButton();

        Assert.assertTrue(basePage.loginError(), "----------Can not see login error info after incorrect login data");
    }

    @Test(dataProvider="getData")
    public void loginNoUserAndPassword(int p1, String p2, String p3) {
        launch(p1, p2, p3);

        basePage.setLoginInput("")
                .setPasswordInput("")
                .setZalogujButton();

        Assert.assertTrue(basePage.loginError(), "----------Can not see login error info after incorrect login data");
    }

    @Test(dataProvider="getData")
    public void loginNoPassword(int p1, String p2, String p3) {
        launch(p1, p2, p3);

        basePage.setLoginInput(envLoginLogin)
                .setPasswordInput("")
                .setZalogujButton();

        Assert.assertTrue(basePage.loginError(), "----------Can not see login error info after incorrect login data");
    }

    @Test(dataProvider="getData")
    public void loginIncorrectPassword(int p1, String p2, String p3) {
        launch(p1, p2, p3);

        basePage.setLoginInput(envLoginLogin)
                .setPasswordInput("IncorrectPassword")
                .setZalogujButton();

        Assert.assertTrue(basePage.loginError(), "----------Can not see login error info after incorrect login data");
    }

    @Test(dataProvider="getData")
    public void loginCorrect(int p1, String p2, String p3) {
        launch(p1, p2, p3);

        basePage.setLoginInput(envLoginLogin)
                .setPasswordInput(envLoginPassword)
                .setZalogujButton();

        Assert.assertTrue(mainPage.isAt(envTimeoutIsAt), "----------Log in fail - you are not on main page");

//        Assert.fail("---------->>>>>>>>>>>>Fail test<<<<<<<<<<<<<");

    }




}
