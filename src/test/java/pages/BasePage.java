package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage extends Page {

    @Override
    public boolean isAt(){
        return this.foundationForm.isDisplayed();
    }



    @FindBy(id = "foundationForm")
    private WebElement foundationForm;

    @FindBy(css = "button.foundation-style.button.foundationButton[type='submit'][value='Login']")
    private WebElement zalogujButton;

    @FindBy(id = "login_section_btn")
    private WebElement loginButton;

    @FindBy(id = "registeredPlayerLogin")
    private WebElement loginInput;

    //"form > input.required.valid[name='password']"
    //<input type="password" class="required valid" style="font-size: 14px; margin-top: 1em; padding: 0.3em !important; width: 100% !important; border-radius: 3px;" placeholder="hasło" name="password">
    //"form#bestForm > input[name='password']"
    //css = "form > input.required.valid[name='password']"
    @FindBy(css = "form#bestForm > input[name='password']")
    private WebElement passwordInput;

    @FindBy(css = ".testDivred")
    private WebElement loginErrorText;


    //<form method="POST" action="login.html" class="foundation-style" id="bestForm" style="display:inline-block;width:auto;">
    //            <input id="registeredPlayerLogin" type="text" placeholder="login" name="login">
    //            <input type="password" placeholder="hasło" name="password">
    //            <button class="foundation-style button foundationButton" type="submit" value="Login">
    //                Zaloguj się
    //            </button>
    //            <input type="hidden" name="facebookAdId" value="">
    //        </form>

    public BasePage(WebDriver driver)
    {
        super(driver);
    }

    public void setZalogujButton()
    {
        clickElement(this.zalogujButton);
        new MainPage(driver);
    }

    public BasePage setLoginButton()
    {
        try {
            clickElement(this.loginButton);
        }catch (Exception e){
            System.out.println(">>>>>>> LOGIN button not displayed");
        }
        return this;
    }

    public BasePage setLoginInput(String text)
    {
        clickElement(this.loginInput);
        this.loginInput.clear();
        this.loginInput.sendKeys(text);
        return this;
    }

    public BasePage setPasswordInput(String text)
    {
        clickElement(this.passwordInput);
        this.passwordInput.clear();
        this.passwordInput.sendKeys(text);
        return this;
    }

    public boolean loginError()
    {
        try
        {
            wait.until(ExpectedConditions.elementToBeClickable(this.loginErrorText));
            return this.loginErrorText.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

}
