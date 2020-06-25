package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage extends Page {

    @Override
    public boolean isAt(){
        return this.loginInput.isDisplayed();
    }



    @FindBy(id = "foundationForm")
    private WebElement foundationForm;

    @FindBy(css = "button.foundation-style.button.foundationButton[type='submit'][value='Login']")
    private WebElement zalogujButton;

    @FindBy(id = "login_section_btn")
    private WebElement loginButton;

    @FindBy(id = "registeredPlayerLogin")
    private WebElement loginInput;

    @FindAll({
            @FindBy(css = "form > input.required.valid[name='password']"),
            @FindBy(css = "form#bestForm > input[name='password']")
    })
    private WebElement passwordInput;

    @FindBy(css = ".testDivred")
    private WebElement loginErrorText;


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
