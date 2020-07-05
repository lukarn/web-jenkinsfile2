package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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


    public int getShoutContainerText(){

        try {
            int iii = 0;
            for(WebElement elementFromList : this.shoutContainerText) {
                iii++;
                System.out.println("shoutContainerText no." + iii + " : >>>");
                System.out.println(elementFromList.getText());
                System.out.println("<<< End of shoutContainerText");
            }
            return this.shoutContainerText.size();

        } catch (Exception e) {
            e.printStackTrace();
            return this.shoutContainerText.size();
        }

    }


    public boolean printShoutContainerWithText(String text){

        boolean matchFound = false;
        try {
            for(WebElement elementFromList : this.shoutContainerText) {

                System.out.println("Searching in shouts... >>>" + text + "<<<");
                String bodyContent = elementFromList.getText();

                if(bodyContent.contains(text)){

                    System.out.println("shoutContainerText with text " + text + ": >>>");
                    System.out.println(elementFromList.getText());
                    System.out.println("<<< End of shoutContainerText");
                    matchFound = true;
                }
            }
            return matchFound;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


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
