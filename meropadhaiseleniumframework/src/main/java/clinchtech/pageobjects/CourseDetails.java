package clinchtech.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import clinchtech.AbstractComponents.AbstractComponent;

public class CourseDetails extends AbstractComponent {
    
    WebDriver driver;

    public CourseDetails(WebDriver driver){
        super(driver);
        this.driver = driver;   
        PageFactory.initElements(driver, this); 
    }

    @FindBy(xpath = "//div[@class='css-5irc85']/h1[@class='css-1pc3pal']")
    WebElement courseName;
    
    @FindBy(xpath = "//button[@class='chakra-button css-1yrfiy8']")
    WebElement enrollNowButton;

    @FindBy(xpath = "//select[@class='chakra-select css-1e6c7l7']")
    WebElement staticDropdown;

    @FindBy(xpath = "//span[@class='chakra-text css-g2788h']")
    List<WebElement> prices;

    @FindBy(xpath = "//span[@class='chakra-text css-10l964u']")
    WebElement resetPrice;

    @FindBy(xpath = "//button[@class='chakra-button css-1yxzgw8']")
    WebElement continueButton;

    By findBy = By.xpath("//div[@class='css-5irc85']/h1[@class='css-1pc3pal']");

    public String getCourseTitle(int seconds){
        waitForElementToAppear(findBy, seconds);
        return courseName.getText();
    }

    public void clickEnrollNow(){
        enrollNowButton.click();
    }

    public void selectOpion(String option){
        Select dropdown = new Select(staticDropdown);
        dropdown.selectByVisibleText(option);
        if(prices.isEmpty()){
            String[] price = resetPrice.getText().trim().split(". ");
            System.out.println(price[1].trim());
            String priceText = continueButton.getText().split("Rs.")[1];
            System.out.println(priceText);
            if(priceText.equals(price[1].trim())){
                continueButton.click();
            }
        }else{
            continueButton.click();
        }
    }

}