package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WorkPage extends Page {

    @Override
    public boolean isAt(){
        return this.activatePushesButton.isDisplayed();
    }


    @FindBy(css = "#activatePushes")
    private WebElement activatePushesButton;

    @FindBy(css = "button#workButton")
    private WebElement workButton;

    @FindBy(css = "#productionReportTable>tbody>tr>#productionDisplayInTable")
    private WebElement workProductionResult;

    public WorkPage(WebDriver driver)
    {
        super(driver);
    }


    public void setWorkButton()
    {
        clickElement(this.workButton);
    }


    private String getWorkProductionResult()
    {
        return this.workProductionResult.getText();
    }

    public boolean workCheck()
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(this.workProductionResult));
            System.out.println("Production result is: " + getWorkProductionResult() + " [OK]");
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }



//    public void getJobOfferDetailsList(){
//        try{
//            System.out.println("Job Offer Details List: >>>");
//            System.out.println(this.jobOfferDetailsList.getText());
//            System.out.println("<<< End of Job Offer Details List");
//
//        }
//        catch (Exception e)
//        {
//            System.out.println("Can not get Job Offer Details List [NOK]");
//            e.printStackTrace();
//        }
//    }
//
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

}
