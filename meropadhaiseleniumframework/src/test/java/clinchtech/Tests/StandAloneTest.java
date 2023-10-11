package clinchtech.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {
    
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://demo.meropadhai.com");
        driver.findElement(By.cssSelector("button.chakra-button.css-1cvkl7w")).click();
        driver.findElement(By.name("email")).sendKeys("surajclinchtech@gmail.com");
        driver.findElement(By.id("field-3")).sendKeys("12345678");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.cssSelector("button.chakra-button.css-zdihdj")).click();
        WebElement titleText = driver.findElement(By.xpath("//h1/span[1]"));
        String actualText = titleText.getText();
        Assert.assertEquals(actualText, "Get set");
        driver.findElement(By.cssSelector("button.chakra-button.css-nv99c3")).click();
        
        Thread.sleep(2000);
        String courseCardCSS = "div.css-4o8san";
        List<WebElement> courseCards = driver.findElements(By.cssSelector(courseCardCSS));

        String courseName = "";
        for(WebElement courseCard: courseCards){
            courseName = courseCard.findElement(By.cssSelector("h2")).getText();
            if(courseName.equalsIgnoreCase("Network Programming")){
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0, 1500)");
                Thread.sleep(2000);
                courseCard.click();
                break;
            }
        }

        final String course = courseName;

        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
        w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='css-5irc85']/h1[@class='css-1pc3pal']")));
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='css-5irc85']/h1[@class='css-1pc3pal']")).getText(), courseName);

        driver.findElement(By.xpath("//button[@class='chakra-button css-1yrfiy8']")).click();

        WebElement staticDropdown = driver.findElement(By.xpath("//select[@class='chakra-select css-1e6c7l7']"));

        Select dropdown = new Select(staticDropdown);
        String option = "2";
        dropdown.selectByVisibleText(option);
        List<WebElement> prices = driver.findElements(By.xpath("//span[@class='chakra-text css-g2788h']"));
        if(prices.isEmpty()){
            WebElement resetPrice = driver.findElement((By.xpath("//span[@class='chakra-text css-10l964u']")));
            String[] price = resetPrice.getText().split("\\.");

            WebElement continueButton = driver.findElement(By.xpath("//button[@class='chakra-button css-1yxzgw8']"));
            String priceText = continueButton.getText().split("\\.")[1];
            Assert.assertEquals(priceText, price[1].trim());
            continueButton.click();
        }else{
            driver.findElement(By.xpath("//button[@class='chakra-button css-1yxzgw8']")).click();
        }

        List<WebElement> cartCourses = driver.findElements(By.cssSelector("div.css-9dv6n"));

        double total = cartCourses.stream()
            .map(cartCourse -> {
                String cartCourseName = cartCourse.findElement(By.cssSelector("h2")).getText();
                Assert.assertEquals(cartCourseName, course);
                String priceText = cartCourse.findElement(By.cssSelector("span.css-1k68b51:nth-of-type(1)"))
                    .getText()
                    .split("s.")[1]
                    .trim();
                return Double.parseDouble(priceText);
            })
            .reduce(0.0, Double::sum);

        String stringifyTotal = String.valueOf(total);


        String grandTotal = driver.findElement(By.cssSelector("span.chakra-text.css-1izio3i")).getText().split(" ")[1].trim();
        if(grandTotal.contains(stringifyTotal)){
            driver.findElement(By.cssSelector("button.chakra-button.css-7ven0")).click();
        }

        List<WebElement> checkoutCourses = driver.findElements(By.cssSelector("div.css-urir3a"));
        double checkoutTotal = checkoutCourses.stream()
            .map(checkoutCourse -> {
                String cartCourseName = checkoutCourse.findElement(By.cssSelector("h2")).getText();
                Assert.assertEquals(cartCourseName, course);
                String priceText = checkoutCourse.findElement(By.cssSelector("span.css-1k68b51")).getText().split("s.")[1].trim();
                return Double.parseDouble(priceText);
            })
            .reduce(0.0, Double::sum);

        String checkoutStringigyTotal = String.valueOf(checkoutTotal);


        String checkoutGrandTotal = driver.findElement(By.cssSelector("span.css-1k68b51")).getText().split("s.")[1].trim();
        if(checkoutStringigyTotal.equals(checkoutGrandTotal)){
            driver.findElement(By.cssSelector("button.chakra-button.css-g3m7k4")).click();
        }
    }

}