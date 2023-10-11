package clinchtech.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
    
    WebDriver driver;

    public AbstractComponent(WebDriver driver){
        this.driver = driver;
    }

    public void JavascriptExecutor(int x, int y){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(" + x + ", " +  y + ")");
    }

    public void waitForElementToAppear(By findBy, int seconds){
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        w.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

}
