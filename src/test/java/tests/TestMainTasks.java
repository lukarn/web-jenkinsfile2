package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.MainPage;
import pages.TrainPage;
import pages.WorkPage;
import utilities.DriverManager;


public class TestMainTasks
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
    private WorkPage workPage;
    private TrainPage trainPage;


    @DataProvider
    public Object[][] getData()
    {
        return new Object[][]{
                {1, envLoginPage, "chrome"},
                {2, envLoginPage, "firefox"},
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

    //  , dependsOnMethods = { "loginCorrect" }
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
        workPage = new WorkPage(driver);
        trainPage = new TrainPage(driver);

        Assert.assertTrue(basePage.isAt(envTimeoutIsAt), "----------BasePage not loaded!");
    }

    @Test(dataProvider="getData")
    public void loginCorrect(int p1, String p2, String p3) {
        launch(p1, p2, p3);

        basePage.setLoginInput(envLoginLogin)
                .setPasswordInput(envLoginPassword)
                .setZalogujButton();

        Assert.assertTrue(mainPage.isAt(envTimeoutIsAt), "----------Log in fail - you are not on MainPage");

    }

//    @Test(dataProvider="getData", dependsOnMethods = { "loginCorrect" })
//    public void work(int p1, String p2, String p3) {
//        loginCorrect(p1, p2, p3);
//
//        try {
//            mainPage.setWorkTaskButton();
//        }
//        catch (Exception e)
//        {
//            mainPage.setMenuMyPlacesButton()
//                    .setMenuWorkButton();
//        }
//
//
//        Assert.assertTrue(workPage.isAt(envTimeoutIsAt), "----------Error - you are not on WorkPage");
//
//        if(!workPage.workCheck())
//        {
//            workPage.setWorkButton();
//            Assert.assertTrue(workPage.isAt(envTimeoutIsAt), "----------Error - you are not on WorkPage");
//        }
//
//        Assert.assertTrue(workPage.workCheck(), "----------Error - Can not read production results after work");
//
//
//    }
//
//    @Test(dataProvider="getData", dependsOnMethods = { "loginCorrect" })
//    public void train(int p1, String p2, String p3) {
//        loginCorrect(p1, p2, p3);
//
//        try {
//            mainPage.setTrainTaskButton();
//        }
//        catch (Exception e)
//        {
//            mainPage.setMenuMyPlacesButton()
//                    .setMenuTrainButton();
//        }
//
//
//        Assert.assertTrue(trainPage.isAt(envTimeoutIsAt), "----------Error - you are not on TrainPage");
//
//
//
//        if(!trainPage.trainCheck())
//        {
//            trainPage.setTrainButton();
//            Assert.assertTrue(trainPage.isAt(envTimeoutIsAt), "----------Error - you are not on TrainPage");
//        }
//
//        Assert.assertTrue(trainPage.trainCheck(), "----------Error - Can not read train countdown after training");
//
//
//    }


}
