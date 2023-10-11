package clinchtech.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

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

}
