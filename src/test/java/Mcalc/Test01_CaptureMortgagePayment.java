package Mcalc;

import ResuableObjects.Reusable_Methods;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Test01_CaptureMortgagePayment {
    //declare webdriver here since it's a global variable
    WebDriver driver;
    //declare all readable & writable excel workbook and worksheet here since it's global
    Workbook readable;
    Sheet readableSheet;
    WritableWorkbook writable;
    WritableSheet writableSheet;
    int rows;

    @BeforeSuite
    public void openBrowser() throws IOException, BiffException {
        //define the driver path
        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--incognito");
        driver = new ChromeDriver(options);
        //timeout using implicit wait
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);

        //defining excel readable path
        readable = Workbook.getWorkbook(new File("src\\main\\resources\\Mlc Excel.xls"));
        //defining readable sheet
        readableSheet = readable.getSheet(0);
        //defining writable excel path
        writable = Workbook.createWorkbook(new File("src\\main\\resources\\Mlc Excel.xls"),readable);
        //defining the writable excel sheet
        writableSheet = writable.getSheet(0);
        //define the row count
        rows = readableSheet.getRows();

    }//end of beforesuite

    /*@AfterSuite
    public void closeBrowser() throws IOException, WriteException {
        writable.write();
        writable.close();
        readable.close();
        driver.quit();
    }//end of aftersuite

    @Test
    public void testScenario() throws WriteException {
        for(int i =1;i < rows; i++) {
            String purchasePrice = readableSheet.getCell(0,i).getContents();
            String downPayment = readableSheet.getCell(1,i).getContents();
            String interestRate = readableSheet.getCell(2,i).getContents();
            String zipCode = readableSheet.getCell(3,i).getContents();
            String payMonth = readableSheet.getCell(4,i).getContents();
            String payYear = readableSheet.getCell(5,i).getContents();

            //navigating to mlcalc
            Reusable_Methods.navigate(driver, "https://www.mlcalc.com");

            //clear purchase field
            Reusable_Methods.clearMethod(driver, "//*[@name='ma']", "Purchase Price");
            //enter information to purchase price
            Reusable_Methods.sendKeysMethod(driver, "//*[@name='ma']", purchasePrice, "Purhcase Price");

            //clear down payment field
            Reusable_Methods.clearMethod(driver, "//*[@name='dp']", "Down Payment");
            //enter information to purchase price
            Reusable_Methods.sendKeysMethod(driver, "//*[@name='dp']", downPayment, "Down Payment");

            //clear Interest Rate field
            Reusable_Methods.clearMethod(driver, "//*[@name='ir']", "Interest Rate");
            //enter information to purchase price
            Reusable_Methods.sendKeysMethod(driver, "//*[@name='ir']", interestRate, "Interest Rate");

            //clear Zip Code field
            Reusable_Methods.clearMethod(driver, "//*[@name='zipCode']", "Zip Code");
            //enter information to purchase price
            Reusable_Methods.sendKeysMethod(driver, "//*[@name='zipCode']", zipCode, "Zip Code");

            //select pay month
            Reusable_Methods.selectByText(driver,"//*[@name='sm']",payMonth,"Month");

            //select pay year
            Reusable_Methods.selectByText(driver,"//*[@name='sy']",payYear,"Year");

            //click on Calculate button
            Reusable_Methods.clickMethod(driver,"//*[@alt='Calculate']","Calculate");

            //capture monthly payment
            String mntPayment = Reusable_Methods.captureText(driver,"//*[@class='big']",0,"Monthly Payment");
            //add label for payoff date
            Label label1 = new Label(6,i,mntPayment);
            writableSheet.addCell(label1);

            //mortgage title
            boolean trueFalse = driver.findElements(By.xpath("//*[@class='nowrap']")).get(2).isDisplayed();
            if(trueFalse == true){
                //capture payoff date
                String payOffDate = Reusable_Methods.captureText(driver,"//*[@class='big']",2,"Payoff Date");
                //add label for monthly payment
                //add label for payoff date
                Label label2 = new Label(7,i,payOffDate);
                writableSheet.addCell(label2);
            } else {
                String payOffDate2 = Reusable_Methods.captureText(driver,"//*[@class='big']",3,"Payoff Date");
                //add label for payoff date
                Label label2 = new Label(7,i,payOffDate2);
                writableSheet.addCell(label2);
            }



        }//end of for loop

    }//end of test execution




*/
}

