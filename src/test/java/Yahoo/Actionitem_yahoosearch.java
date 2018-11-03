package Yahoo;

import ResuableObjects.Reusable_Methods_loggers;
import Utilities.Abstract_class;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;



public class Actionitem_yahoosearch extends Abstract_Class_Parallel {


    @Test
    public void yahooSearchResult() throws IOException, InterruptedException {

        Reusable_Methods_loggers.navigate(logger, driver, "https://www.yahoo.com");

            String title = driver.getTitle();

            if (title.equals("Yahoo")) {

                logger.log(LogStatus.PASS, "Title matches");

            } else {

                logger.log(LogStatus.FAIL, "The title doesn't match " + title);

            }

            List<WebElement> Elements = driver.findElements(By.xpath("//*[contains(@class,'D(ib) Mstart(21px) Mend(13px)')]"));
            logger.log(LogStatus.INFO,"link count is "+ Elements.size());

            System.out.println("Number of elements " + Elements.size());



            Reusable_Methods_loggers.sendKeysMethod(logger, driver, "//*[@name='p']", "Nutrition", "Search Bar");

            Thread.sleep(1500);

            Reusable_Methods_loggers.clickMethod(logger, driver, "//*[@id='uh-search-button']", "Yahoo Search");
            Thread.sleep(1500);

            jse.executeScript("scroll(0,5000");

            String searchresult = Reusable_Methods_loggers.captureText(driver,"//*[@class='compPagination",0,"search result");
            String[] arraySearch = searchresult.split("Next");
            logger.log(LogStatus.INFO,"search count is"+arraySearch[1].trim());
            // Thread.sleep(1500);

            Thread.sleep(1500);
            jse.executeScript("scroll(-5000,0");
            Reusable_Methods_loggers.clickMethod(logger, driver, "//*[@id='yucs-login_signIn']", "Sign In");
            Thread.sleep(2000);
            boolean checkBoxState = driver.findElement(By.xpath("")).isSelected();

            if(checkBoxState == true){
                logger.log(LogStatus.PASS,"Checkbox is selected by default");
            }else {
                logger.log(LogStatus.FAIL,"CheckBox is not selected by defult");
                Reusable_Methods_loggers.getScreenshot(driver,logger,"check Box state");
            }

            Reusable_Methods_loggers.sendKeysMethod(logger, driver, "//*[@id='login-username']", "FalseEMAIL", "Username");
            Thread.sleep(1500);
            Reusable_Methods_loggers.clickMethod(logger, driver, "//*[@id='login-signin']", "Next Button");
            //error messege compare
            Thread.sleep(2000);
            Reusable_Methods_loggers.compareMessages(logger, driver, "//*[@id='username-error']"Email not Recognized", "//*[@class='row error']", "Error Message");


        }






    }//end of parent class

