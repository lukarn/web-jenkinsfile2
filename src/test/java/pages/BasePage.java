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


    @FindBy(xpath = "//*[contains(@value, 'Zaloguj')]")
    private WebElement zalogujButton;

    @FindBy(id = "inp_log")
    private WebElement loginInput;

    @FindBy(id = "inp_pass")
    private WebElement passwordInput;

    @FindBy(xpath = "//*[contains(@id, 'blad_logowanie') and contains(text(), 'Błędne dane logowania')]")
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
        loginInput.sendKeys(text);
        return this;
    }

    public BasePage setPasswordInput(String text)
    {
        clickElement(this.passwordInput);
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
