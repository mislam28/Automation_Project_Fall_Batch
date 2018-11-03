package Yahoo;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class Abstract_Class_Parallel {

    public static WebDriver driver = null;
    public static ExtentReports report = null;
    public static ExtentTest logger = null;
    public static JavascriptExecutor jse = null;


    @BeforeSuite
    public void openReport() {
        report = new ExtentReports("src\\main\\java\\Report_Folder\\ExtentReport.html", true);

        //define the javascript
//        logger.log(LogStatus.INFO,"Automation Test Suite started....");

    }

    @Parameters("browser")
    @BeforeMethod
    public void loggerSession(String browser, Method methodName) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        // System.setProperty("webdriver.ie.driver", "src\\main\\resources\\ie.exe");
        System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");
        if (browser.equalsIgnoreCase("googlechrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized", "incognito", "disable-infobars");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            driver.navigate().to("https://www.firefox.com");
            Thread.sleep(800);
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("ie")) {
            //this is where your ie driver would go
        } else if (browser.equalsIgnoreCase("safari")) {
            //this is where your safari driver would go
        }
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        jse = (JavascriptExecutor) driver;
        logger = report.startTest(methodName.getName());
        logger.log(LogStatus.INFO, "Automation Test Scenario started....");
    }

    @AfterMethod
    public void endLogger() {
        report.endTest(logger);
        logger.log(LogStatus.INFO, "Automation Test Scenario ended....");
    }

    @AfterSuite()
    public void closeBrowser() {
        report.flush();
        report.close();
        driver.quit();
        logger.log(LogStatus.INFO, "Automation Test Suite Ended....");
    }


}

