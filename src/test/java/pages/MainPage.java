package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class MainPage extends Page {

    @Override
    public boolean isAt(){
        return this.fightTaskButton.isDisplayed();
    }


    @FindBy(css = "#taskButtonFight>a")
    private WebElement fightTaskButton;

    @FindBy(css = "#taskButtonTrain>a")
    private WebElement trainTaskButton;

    @FindBy(css = "#taskButtonWork>a")
    private WebElement workTaskButton;

    @FindBy(css = "a#myPlaces")
    private WebElement menuMyPlacesButton;

    @FindBy(css = "a[href*='work']")
    private WebElement menuWorkButton;

    @FindBy(css = "a[href*='train']")
    private WebElement menuTrainButton;

    @FindBy(css = "div.shoutContainer>div[id*='shoutCountry']")
    private List<WebElement> shoutContainerText;


    public void getShoutContainerText(int shoutNumber){

        try{
            if(!( (shoutNumber < this.shoutContainerText.size()) && (shoutNumber>0) )){
                Assert.fail(">>>>> Shout number out of range!!!");
            }
        }catch (Exception e){
            System.out.println("Something wrong with shoutNumber");
            e.getStackTrace();
        }

        try{
            System.out.println("shoutContainerText: >>>");
            System.out.println(this.shoutContainerText.get(0).getText());
            System.out.println("<<< End of shoutContainerText");

        }
        catch (Exception e)
        {
            System.out.println("Can not get shoutContainerText [NOK]");
            e.printStackTrace();
        }
    }

//    // boolean - to use with assert
//    public boolean checkTextVisibilityInPageSource(String text){
//        try
//        {
//            System.out.println("Searching... >>>" + text + "<<<");
//            String bodyContent = this.jobOfferDetailsList.getText();
//
//            return bodyContent.contains(text);
//
//        }
//        catch (Exception e)
//        {
//            System.out.println("Element >>>" + text + "<<< found: false [NOK]");
//            e.printStackTrace();
//            return false;
//        }
//
//    }


    public MainPage(WebDriver driver)
    {
        super(driver);
    }

    public void setTrainTaskButton()
    {
        clickElement(this.trainTaskButton);
    }

    public void setWorkTaskButton()
    {
        clickElement(this.workTaskButton);
    }

    public MainPage setMenuMyPlacesButton()
    {
        clickElement(this.menuMyPlacesButton);
        return new MainPage(driver);
    }

    public void setMenuWorkButton()
    {
        clickElement(this.menuWorkButton);
    }

    public void setMenuTrainButton()
    {
        clickElement(this.menuTrainButton);
    }

}
