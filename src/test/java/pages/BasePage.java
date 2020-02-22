package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage extends Page {

    @Override
    public boolean isAt(){
        return this.zalogujButton.isDisplayed();
    }


    @FindBy(css = "#bestForm>button[type='submit']")
    private WebElement zalogujButton;

    @FindBy(css = "#bestForm>input[name='login']")
    private WebElement loginInput;

    @FindBy(css = "#bestForm>input[name='password']")
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
