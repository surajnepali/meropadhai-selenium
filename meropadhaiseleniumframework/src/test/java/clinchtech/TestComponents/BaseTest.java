package clinchtech.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import clinchtech.pageobjects.LandingPage;

public class BaseTest {
    
    public WebDriver driver;

    @FindBy(css="div.ct-text")
    WebElement toastMsg;

    public WebDriver initializeDriver() throws IOException{

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/clinchtech/resources/Global.properties");
        prop.load(fis);
        String browserName = prop.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        } else if(browserName.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        } else if(browserName.equalsIgnoreCase("edge")){
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    @BeforeMethod
    public LandingPage launchApplication() throws IOException{
        driver = initializeDriver();
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
    
    public WebElement toastMessage(){
        return toastMsg;
    }

    // Code that converts JSON file to HashMaps and add all of the HashMaps to List
    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException{

        // Read JSON to String
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

        //String to HashMap Jackson Datbind
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){

        });
        return data;

    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException{
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
    }

}