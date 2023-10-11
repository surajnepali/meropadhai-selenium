package clinchtech.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import clinchtech.AbstractComponents.AbstractComponent;

public class ExplorePage extends AbstractComponent {
    
    WebDriver driver;

    public ExplorePage(WebDriver driver){
        super(driver);
        this.driver = driver;   
        PageFactory.initElements(driver, this); 
    }

    @FindBy(css ="div.css-4o8san")
    List<WebElement> courseCards;

    public List<WebElement> getCourses(){
        return courseCards;
    }

    public WebElement getCourseByName(String courseName, int x, int y) throws InterruptedException{
        Thread.sleep(1000);
        WebElement course = getCourses()
            .stream()
            .filter(courseElement ->  courseElement.findElement(By.cssSelector("h2"))
            .getText().equals(courseName))
            .findFirst()
            .orElse(null);
            JavascriptExecutor(x, y);
            Thread.sleep(2000);
        return course;
    }

    public String seeCourseDetails(String courseName, int x, int y) throws InterruptedException{
        WebElement course = getCourseByName(courseName, x, y);
        String courseNameText = course.findElement(By.cssSelector("h2")).getText();
        course.click();
        return courseNameText;
    }

}