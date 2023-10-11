package clinchtech.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
    
    WebDriver driver;

    public LandingPage(WebDriver driver){
        this.driver = driver;   
        PageFactory.initElements(driver, this); 
    }

    @FindBy(css ="button.chakra-button.css-1cvkl7w")
    WebElement loginButton;

    public void goTo(){
        driver.get("https://demo.meropadhai.com");
    }

    public void goToLoginPage(){
        loginButton.click();
        System.out.println(driver.getCurrentUrl());
    }

}
