package clinchtech.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import clinchtech.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
    
    WebDriver driver;

    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;   
        PageFactory.initElements(driver, this); 
    }

    @FindBy(css = "div.css-9dv6n")
    List<WebElement> cartCourses;

    @FindBy(css = "button.chakra-button.css-7ven0")
    WebElement grandTotalElement;

    @FindBy(css = "span.chakra-text.css-1izio3i")
    WebElement semiGrandPriceText;

    @FindBy(css = "div.css-urir3a")
    List<WebElement> checkoutCourseCards;

    @FindBy(css = "button.chakra-button.css-g3m7k4")
    WebElement checkoutButton;

    By cartCourseName = By.cssSelector("h2");
    
    By priceText = By.cssSelector("span.css-1k68b51:nth-of-type(1)");

    public boolean findNewCourse(String courseName){
        return cartCourses.get(0).findElement(By.cssSelector("h2")).getText().trim().equals(courseName);
    }

    public double coursesInCart(List<String> cartCourseNamesList, List<Double> cartCoursePricesList){
        double total = 0;
        for(WebElement cartCourse: cartCourses){
            String courseInCart = cartCourse.findElement(cartCourseName).getText();
            Double courseInCartPrice = Double.parseDouble(cartCourse.findElement(priceText).getText().split("s.")[1]);
            total = total + courseInCartPrice;
            cartCourseNamesList.add(courseInCart);
            cartCoursePricesList.add(courseInCartPrice);
        }
        return total;
    }

    public double compareTotal(List<String> cartCourseNamesList, List<Double> cartCoursePricesList){
        double stringifyTotal = coursesInCart(cartCourseNamesList, cartCoursePricesList);
        double grandTotal = Double.parseDouble(semiGrandPriceText.getText().split("s ")[1].trim());
        if(grandTotal == stringifyTotal){
            grandTotalElement.click();
        }
        return grandTotal;
    }

    public double checkout(List<String> cartCourseNamesList){
        double total = 0;
        int i = 0;
        for(WebElement checkoutCourseCard: checkoutCourseCards){
            String courseForCheckout = checkoutCourseCard.findElement(cartCourseName).getText();
            if(courseForCheckout.equals(cartCourseNamesList.get(i))){
                total = total + Double.parseDouble(checkoutCourseCard.findElement(priceText).getText().split("s.")[1]);
                i++;
            }else{
                System.out.println("The courses are not displayed in order that is displayed on the Cart Page");
                break;
            }
        }
        return total;
    }

    public void compareForCheckout(List<String> cartCourseNamesList, List<Double> cartCoursePricesList){
        double grandTotal = compareTotal(cartCourseNamesList, cartCoursePricesList);
        double checkoutTotal = checkout(cartCourseNamesList);
        if( checkoutTotal == grandTotal){
            System.out.println("Comparision is succesfully done");
            checkoutButton.click();
        }
    }

}