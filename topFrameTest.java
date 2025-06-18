package Homepackage;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class topFrameTest extends Baseclass{
	//WebDriver driver;
    topFrame topFramePage;
 
    @BeforeClass
    public void setup() {
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("https://null-pointers-five.vercel.app/");
        topFramePage = new topFrame(driver);
    }
 
    @Test(priority=1)
    public void testHome() {
        topFramePage.setHome();
        // Add assertions to verify the expected behavior
        boolean homeStatus = driver.findElement(By.id("home")).isDisplayed();
        Assert.assertTrue(homeStatus, "Home section is not displayed");
        System.out.println("Home section displayed");
    }
 
    @Test(priority=2)
    public void testAbout() {
        topFramePage.setAbout();
        // Add assertions to verify the expected behavior
        boolean aboutStatus = driver.findElement(By.id("about")).isDisplayed();
        Assert.assertTrue(aboutStatus, "About section is not displayed");
        System.out.println("About section displayed");
    }
 
    @Test(priority=3)
    public void testTeam() {
        topFramePage.setTeam();
        // Add assertions to verify the expected behavior
        boolean teamStatus = driver.findElement(By.id("team")).isDisplayed();
        Assert.assertTrue(teamStatus, "Team section is not displayed");
        System.out.println("Team section displayed");
    }
 
    @Test(priority=4)
    public void testWorks() {
        topFramePage.setWorks();
        // Add assertions to verify the expected behavior
        boolean workStatus = driver.findElement(By.id("works")).isDisplayed();
        Assert.assertTrue(workStatus, "Works section is not displayed");
        System.out.println("Works section displayed");
    }
 
    @Test(priority=5)
    public void testContact() {
        topFramePage.setContact();
        // Add assertions to verify the expected behavior
        boolean contactStatus = driver.findElement(By.xpath("/html/body/footer")).isDisplayed();
        Assert.assertTrue(contactStatus, "Contact section is not displayed");
        System.out.println("Contact section displayed");
    }
 
//    @AfterClass
//    public void teardown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}
