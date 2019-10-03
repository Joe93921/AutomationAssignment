package Utility;

import org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.apache.log4j.Logger;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class BrowserDetails {

    public static final Logger logger = Logger.getLogger(BrowserDetails.class);
    public static WebDriver driver;

    FileInputStream obj;

    {
        try {
            obj = new FileInputStream("C:\\Users\\JyotiP\\IdeaProjects\\mavendemo\\configdetails\\config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    Properties properties1= new Properties();


    @Parameters({"appurl"})
    @BeforeMethod
    public void setup(@Optional String URL) throws IOException {

        properties1.load(obj);
        if(properties1.getProperty("Browser").equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(properties1.getProperty("url"));
        }
        else{
            System.out.println("No browser found");
        }

    }


    @AfterMethod
    public void close(){

        driver.quit();
        }


    public static void logBrowserConsoleLogs(){
        System.out.println("================== BROWSER LOGS =======================");
        LogEntries logEntries = driver.manage().logs().get("browser");
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
            Reporter.log(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }
        System.out.println("=======================================================");
    }

    public void takeScreenshot() {
        try {
            File destination;
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            destination = new File("target/TestResultScreenshot/"+timeStamp+".png");
            FileUtils.copyFile(scrFile, destination);
            String location =System.getProperty("user.dir")+"\\target\\TestResultScreenshot\\"+timeStamp+".png";
            String filePath = "<br><img src=\"file://" + location + "\" alt=\"\"/></br>";
            Reporter.log(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
