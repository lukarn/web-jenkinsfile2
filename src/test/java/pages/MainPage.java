package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends Page {

    @Override
    public boolean isAt(){
        return this.fightButton.isDisplayed();
    }


    @FindBy(css = "#taskButtonFight>a")
    private WebElement fightButton;

//    @FindBy(xpath = "//*[contains(text(), 'hasło traci ważność')]")
//    private WebElement needNewPassText;

    public MainPage(WebDriver driver)
    {
        super(driver);
    }

//    public boolean needNewPass()
//    {
//        try
//        {
//            wait.until(ExpectedConditions.elementToBeClickable(this.needNewPassText));
//            return this.needNewPassText.isDisplayed();
//        }
//        catch (Exception e)
//        {
//            return false;
//        }
//    }

}
