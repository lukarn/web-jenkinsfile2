package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IClass;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import tests.*;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ListenerManager extends TestListenerAdapter {

    @Override
    public void onTestStart(ITestResult tr) {
        log("======================> Lets get test " + tr.getName() + tr.getTestClass() + " started....");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        log("Test '" + tr.getName() + "'-------------------------------------------------------------------->>>>> PASSED");
        log(tr.getTestClass());
        log("Priority of this method is " + tr.getMethod().getPriority());
        System.out.println(".....");
    }


    @Override
    public void onTestFailure(ITestResult tr) {

        log("Test '" + tr.getName() + "'-------------------------------------------------------------------->>>>> FAILED");
        log("Priority of this method is " + tr.getMethod().getPriority());

        String testClassSelect = tr.getTestClass().toString();

        //select Test from package tests (webdriver)
        TakesScreenshot ts;
        if(testClassSelect.equalsIgnoreCase("[TestClass name=class tests.TestLogIn]"))
        {
            ts = (TakesScreenshot) TestLogIn.driver;
        }
        else if (testClassSelect.equalsIgnoreCase("[TestClass name=class tests.TestMainTasks]"))
        {
            ts = (TakesScreenshot) TestMainTasks.driver;
        }

        else
        {
            System.out.println("Test method not recognized in ListenerManager screenShot- should be : " + testClassSelect);
            ts = null;   //just for wrong init - correct to your method
        }

        if(ts != null) {
            File srcFile = ts.getScreenshotAs(OutputType.FILE);

            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                Date date = new Date();

                //ScreenShot
                FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "\\screenShots\\NOK_" + dateFormat.format(date) + tr.getName() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("No driver - no photo.");
        }

        System.out.println(".....");

    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log("Test '" + tr.getName() + "'-------------------------------------------------------------------->>>>> SKIPPED");
        System.out.println(".....");
    }

    private void log(String methodName) {
        System.out.println(methodName);
    }

    private void log(IClass testClass) {
        System.out.println(testClass);// + "using browser: " + TestTerminarz.usingBrowser
    }

}