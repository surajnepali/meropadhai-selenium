package clinchtech.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    
    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;   
        PageFactory.initElements(driver, this); 
    }

    @FindBy(css ="input[name='email']")
    WebElement emailField;

    @FindBy(id="field-3")
    WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitButton;

    public void login(String email, String password){
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        submitButton.click();
    }

}
