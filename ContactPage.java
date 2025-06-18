package Homepackage;

import java.io.File;

import java.io.IOException;
 
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class ContactPage {
    WebDriver driver;
    WebDriverWait wait;
 
    public ContactPage(WebDriver driver) {
        this.driver = driver;
    }
 
    By contactNav = By.linkText("Contact");
    By nameField = By.xpath("//*[@id='contact']/form/input[1]");
    By emailField = By.xpath("//*[@id='contact']/form/input[2]");
    By messageField = By.xpath("//*[@id='contact']/form/textarea");
    By sendBtn = By.xpath("//*[@id='contact']/form/button");
 
   
    public void openContactSection() {
        driver.findElement(contactNav).click();
    }
 
    public void enterName(String name) {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
        
    }
 
    public void enterEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }
 
    public void enterMessage(String message) {
        driver.findElement(messageField).clear();
        driver.findElement(messageField).sendKeys(message);
    }
 
    public void submitForm() {
        WebElement send = driver.findElement(sendBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", send);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", send);
    }
    public void takeScreenshot(String fileName) {
        try {
            // Capture the screenshot as a file
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
 
            // Ensure the filename and path are properly constructed
            String path = "screenshot/"+fileName;
 
            // Create the destination file
            File dest = new File(path);
 
            // Copy the screenshot to the destination
            FileUtils.copyFile(src, dest);
 
            System.out.println("Screenshot saved to: " + path);
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }
    }
 
}
