package ResuableObjects;


import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;

    public class reuse_method_logger {

        public static void navigate(ExtentTest logger, WebDriver driver, String url) throws IOException {

            try{
                logger.log(LogStatus.INFO,"navigating to url "+url);
                driver.navigate().to(url);
            }catch(Exception err){
                logger.log(LogStatus.FAIL,"unable to navigate to url "+err);
                getScreenshot(driver,logger,"URL Error");
            }
        }
        public static void sendKey(ExtentTest logger, WebDriver driver, String locator, String userInput, String element) throws IOException {
            try {
                logger.log(LogStatus.INFO,"Entering " + userInput + " in element " + element);

                WebElement input = driver.findElement(By.xpath(locator));
                input.clear();
                input.sendKeys(userInput);
            } catch (Exception e1) {
                logger.log(LogStatus.FAIL,"unable to send info on element = " + element);

                getScreenshot(driver,logger,element);
            }
        }//end of sendKeys method

        public static void selectByVisibleText(ExtentTest logger, WebDriver wDriver, String locator,String visibleText, String elementName)  {
            try {
                logger.log(LogStatus.INFO,"Selecting from the element " + elementName);
                WebElement roomSelection = wDriver.findElement(By.xpath(locator));
                Select num_room = new Select(roomSelection);
                num_room.selectByVisibleText(visibleText);
            }catch (Exception err0){
                logger.log(LogStatus.FAIL,"unable to make selection from element "+ elementName);

            }
        }//end of selectByVisibleText method

        public static void mouseHoverClick(ExtentTest logger, WebDriver wDriver, String locator, String elementName) throws IOException {
            try {
                logger.log(LogStatus.INFO,"hovering over and clicking in " + elementName);
                Actions mouseAction = new Actions(wDriver);
                WebElement signIn = wDriver.findElement(By.xpath(locator));
                mouseAction.moveToElement(signIn).click().build().perform();

            }catch (Exception err1){
                logger.log(LogStatus.FAIL,"unable to hover and click on element "+elementName);
                // System.out.println("unable to hover and click on element "+elementName);
                getScreenshot(wDriver,logger,elementName);
            }

        }//end of mouseHoverClick

        public static String getWebText(ExtentTest logger, WebDriver wDrive, String locator,int index, String elementName){
            String textCaptured=null;
            try {
                logger.log(LogStatus.INFO,"Getting the text from element " + elementName);
                //System.out.println("Getting the text from element " + elementName);

                WebElement webText = wDrive.findElements(By.xpath(locator)).get(index);
                textCaptured = webText.getText();

                //System.out.println("Header of the page is " + textCaptured);
            }catch(Exception err){
                logger.log(LogStatus.FAIL,"unable to get the text from element "+elementName);
                //System.out.println("unable to get the text from element "+elementName);
            }

            return textCaptured;

        }//end of getWebText method

        public static String getPayOffDate(WebDriver wDrive, String locator,int index, String elementName){

            String textCaptured=null;

            try {
                System.out.println("Getting the text from element " + elementName);
                WebElement webText;
                WebElement myPayOff=wDrive.findElements(By.xpath("//*[@nowrap='nowrap']")).get(index);

                if(myPayOff.getText().equals("Mortgage payoff date")){
                    webText=wDrive.findElements(By.xpath(locator)).get(index);
                    textCaptured = webText.getText(); }
                else
                { webText = wDrive.findElements(By.xpath(locator)).get(index+1);
                    textCaptured = webText.getText(); }

            }catch(Exception err){
                System.out.println("unable to get the text from element "+elementName);
            }
            return textCaptured;
        }

        public static void getScreenshot(WebDriver driver, ExtentTest logger, String screenshotName) throws IOException {
            // String path = "C:\\Users\\sumon.kashem\\Desktop\\Screenshots\\";
            String path = "src/main/java/Report_Folder/ExtentReport/screenShot/";
            String fileName = screenshotName + ".png";
            File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            //Now you can do whatever you need to do with, for example copy somewhere
            FileUtils.copyFile(sourceFile, new File(path + fileName));
            //String imgPath = directory + fileName;
            String image = logger.addScreenCapture("ScreenShots\\" + fileName);
            logger.log(LogStatus.FAIL, "", image);
        }

    }
