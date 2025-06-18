package Homepackage;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Temp_Analysis {
    
    WebDriver driver;
    WebDriverWait wait;

    // Constructor to initialize the driver and wait
    public Temp_Analysis(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    By works=By.xpath("//a[text()='Works']");
    By temp=By.xpath("//*[text()='Temperature Analysis']");
    By celsius= By.id("celsius");
    By kelvin= By.id("kelvin");
    By fahrenheit= By.id("fahrenheit");
    By convertbutton=By.id("convertBtn");
    
    By calweather=By.id("getWeatherBtn");
    By tableview=By.xpath("//*[@id=\"toggleViewBtn\"]");
    By cardview=By.xpath("//*[@id=\"toggleViewBtn\"]");
    By selectcity=By.id("citySelect");
    By citycard=By.className("city-card");
    
    
    public void setWork() {
        driver.findElement(works).click();
    }

    public void settemp() {
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(temp));
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(temp));
    	//driver.findElement(temp).click();
    }

    public void enterCelsius(String value)
    { 
        driver.findElement(celsius).clear();
        driver.findElement(celsius).sendKeys(value);
    }
    
    public void enterkelvin(String value)
    {
        driver.findElement(kelvin).clear();
        driver.findElement(kelvin).sendKeys(value);
    }

    public void enterfahrenheit(String value)
    {
        driver.findElement(fahrenheit).clear();
        driver.findElement(fahrenheit).sendKeys(value);
    }

    public void clickConvertbtn()
    {
       driver.findElement(convertbutton).click();
    }

    public String getCelsius()
    {
       return driver.findElement(celsius).getAttribute("value");
    }

    public String getkelvin()
    {
       return driver.findElement(kelvin).getAttribute("value");
    }

    public String getfahrenheit()
    {
       return driver.findElement(fahrenheit).getAttribute("value");
    }


    public void clickCalweather()
    {
       driver.findElement(calweather).click();
    }

    public void clickTableviewAndTakeScreenshot(String fileName) {
        try {
            // Click the Table View button
            driver.findElement(tableview).click();

            // Wait until the table loads and is displayed
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("weatherTable"))); // Replace with the actual ID or locator of the table

            // Scroll to the table to ensure it's fully in view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("weatherTable"))); // Adjust the locator if necessary

            // Take the screenshot
            takeScreenshot(fileName);
        } catch (Exception e) {
            System.out.println("Failed to capture screenshot of table view: " + e.getMessage());
        }
    }

    public void clickCardview()
    {
       driver.findElement(cardview).click();
    }

    public WebElement selectCity()
    {
       return driver.findElement(selectcity);
    }

    public List<WebElement> cards()
    {
       return driver.findElements(citycard);
    }
   
    public void takeScreenshot(String fileName) {
        try {
            // Capture the screenshot as a file
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // file name and location
            String path = "screenshots/"+fileName;

            // Create the destination file
            File dest = new File(path);

            // Copy the screenshot to the destination
            FileUtils.copyFile(src, dest);

            System.out.println("Screenshot saved to: " + path);
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }
}
