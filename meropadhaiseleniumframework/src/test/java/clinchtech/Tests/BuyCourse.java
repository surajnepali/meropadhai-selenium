package clinchtech.Tests;

import java.io.IOException;
import java.util.ArrayList;
// import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import clinchtech.TestComponents.BaseTest;
import clinchtech.pageobjects.CartPage;
import clinchtech.pageobjects.CourseDetails;
import clinchtech.pageobjects.ExplorePage;
import clinchtech.pageobjects.HomePage;
import clinchtech.pageobjects.LandingPage;
import clinchtech.pageobjects.LoginPage;

public class BuyCourse extends BaseTest{
    
    @Test(dataProvider = "getData")
    // While using HashMap, it should be
    // public void buyCourse(HashMap<String, String>) throws IOException, InterruptedException{
    public void buyCourse(String email, String password, String courseString) throws IOException, InterruptedException {
        
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);
        
        HomePage homePage = new HomePage(driver);
        String actualText = homePage.verifyPage();
        Assert.assertEquals(actualText, "Get set");
        homePage.goToExplorePage();
        
        Thread.sleep(2000);
        
        ExplorePage explorePage = new ExplorePage(driver);

        final String course = explorePage.seeCourseDetails(courseString, 0, 1600);

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

    @DataProvider
    public Object[][] getData(){

        // This his how Hashing is done in Java (This HashMap was created manually)

        // HashMap<String, String> map = new HashMap<String, String>();
        // map.put("email", "surajnepali7896@gmail.com");
        // map.put("password", "12345678");
        // map.put("courseStrings", "Network Programming");

        // HashMap<String, String> map1 = new HashMap<String, String>();
        // map1.put("email", "surajclinchtech@gmail.com");
        // map1.put("password", "12345678");
        // map1.put("courseStrings", "Network Programming");

        // While using HashMap, it should return
        // return new Object[][] {{map}, {map1}};

        return new Object[][] {{"surajnepali7896@gmail.com", "12345678", "Network Programming"}, {"surajclinchtech@gmail.com", "12345678", "Network Programming"}};
    }

    // @DataProvider
    // public Object[][] getData1() throws IOException{
    //     List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "///src//test//java//clinchtech//Data//buyCourse.json");
    //     return new Object[][] {{data.get(0)}, {data.get(1)}};
    // }

}