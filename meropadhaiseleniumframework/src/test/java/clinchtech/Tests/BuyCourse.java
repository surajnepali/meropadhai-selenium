package clinchtech.Tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

import clinchtech.TestComponents.BaseTest;
import clinchtech.pageobjects.CartPage;
import clinchtech.pageobjects.CourseDetails;
import clinchtech.pageobjects.ExplorePage;
import clinchtech.pageobjects.HomePage;
import clinchtech.pageobjects.LandingPage;
import clinchtech.pageobjects.LoginPage;

public class BuyCourse extends BaseTest{
    
    @Test
    public void buyCourse() throws IOException, InterruptedException {
        
        LandingPage landingPage = launchApplication();
        landingPage.goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("surajclinchtech@gmail.com", "12345678");
        
        HomePage homePage = new HomePage(driver);
        String actualText = homePage.verifyPage();
        Assert.assertEquals(actualText, "Get set");
        homePage.goToExplorePage();
        
        Thread.sleep(2000);
        
        ExplorePage explorePage = new ExplorePage(driver);

        final String course = explorePage.seeCourseDetails("Network Programming", 0, 1600);

        CourseDetails courseDetails = new CourseDetails(driver);
        Assert.assertEquals(courseDetails.getCourseTitle(5), course);
        courseDetails.clickEnrollNow();
        courseDetails.selectOpion("2");

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.findNewCourse(course));

        List<String> cartCourseNamesList = new ArrayList<>();
        List<Double> cartCoursePricesList = new ArrayList<>();

        cartPage.compareForCheckout(cartCourseNamesList, cartCoursePricesList);

        System.out.println(cartCourseNamesList);
        System.out.println(cartCoursePricesList);

    }

}