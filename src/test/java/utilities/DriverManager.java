package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.net.InetAddress;
import java.net.URL;
import java.util.HashMap;

public class DriverManager {

    private WebDriver driver;

    private void getLocalHost() {
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            System.out.println(">>>>>>>>>>Your current IP address : " + ip.getHostAddress());
            System.out.println(">>>>>>>>>>Your current Hostname : " + ip.getHostName());
            //return ip.getHostAddress();

        } catch (Exception e) {
            e.printStackTrace();
            //return null;
        }
    }

    public DriverManager(WebDriver driver)
    {
        this.driver = driver;
    }

    public WebDriver getDriver(String usingBrowser)
    {
        if(usingBrowser.equalsIgnoreCase("chrome"))
        {
            //run chromedriver
            driver = getChromeDriver();
            System.out.println("start with chromedriver :)");
        }
        else if(usingBrowser.equalsIgnoreCase("firefox"))
        {
            //run firefox
            driver = getFirefoxDriver();
            System.out.println("start with firefoxdriver :)");
        }
        else {
            //other drivers to implement!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            System.out.println("---------other driver to implement");
        }

        return driver;
    }

    private WebDriver getChromeDriver()
    {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", System.getProperty("user.dir") + "\\screenShots");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--headless");


        WebDriver driver = null;

        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
            getLocalHost();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("---------Driver = null - problem during chrome init in DriverManager");
        }

        return driver;
    }

    private WebDriver getFirefoxDriver()
    {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile myProfile = new FirefoxProfile();
        myProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
        myProfile.setPreference("pdfjs.disabled", true);
        myProfile.setPreference("browser.download.folderList", 2);
        myProfile.setPreference("browser.download.manager.showWhenStarting", false);
        myProfile.setPreference("browser.download.dir", System.getProperty("user.dir") + "\\screenShots");
        options.setProfile(myProfile);

        options.addArguments("--width=1920");
        options.addArguments("--height=1080");
        options.setHeadless(true);

        WebDriver driver = null;

        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
            getLocalHost();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("---------Driver = null - problem during firefox init in DriverManager");
        }

        return driver;
    }




}
