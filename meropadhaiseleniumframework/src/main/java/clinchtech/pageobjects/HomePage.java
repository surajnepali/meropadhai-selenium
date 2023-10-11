package clinchtech.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    
    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;   
        PageFactory.initElements(driver, this); 
    }

    @FindBy(css ="button.chakra-button.css-zdihdj")
    WebElement skipButton;

    @FindBy(xpath = "//h1/span[1]")
    WebElement titleText;

    @FindBy(css =  "button.chakra-button.css-nv99c3")
    WebElement exploreButton;

    public String verifyPage(){
        skipButton.click();
        return titleText.getText();
    }

    public void goToExplorePage(){
        exploreButton.click();
    }

}
