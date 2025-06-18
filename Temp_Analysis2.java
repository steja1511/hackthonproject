package Homepackage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Temp_Analysis2 {
    WebDriver driver;
    WebDriverWait wait;

    public Temp_Analysis2(WebDriver driver) throws InterruptedException {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        

    }

    By works = By.xpath("//a[text()='Works']");
    By temp = By.xpath("/html/body/section[4]/div/div[1]/a/div/div/img");
    By celsius = By.id("t_c");
    By kelvin = By.id("t_k");
    By fahrenheit = By.id("t_f");
    By calculatebutton = By.id("temp_calc");
    By flipbutton = By.xpath("//button[@class='flip-button']");
    By citydropdown = By.xpath("//*[@id='city']");
    By weatherbutton=By.xpath("/html/body/div[1]/div/div[2]/form/div/button[1]");
    By resetbutton=By.xpath("/html/body/div[1]/div/div[2]/form/div/button[2]");
    By citydisplay=By.xpath("/html/body/div[2]");
    By otherversion=By.xpath("//a[text()='Other Version']");
    
    public void clickotherversion()
    {
    	driver.findElement(otherversion).click();
    	}
    public void setWork() {
        driver.findElement(works).click();
    }

    public void setTemp() {
        driver.findElement(temp).click();
    }

    public void enterCelsius(String value) {
        driver.findElement(celsius).clear();
        driver.findElement(celsius).sendKeys(value);
    }

    public void enterKelvin(String value) {
        driver.findElement(kelvin).clear();
        driver.findElement(kelvin).sendKeys(value);
    }

    public void enterFahrenheit(String value) {
        driver.findElement(fahrenheit).clear();
        driver.findElement(fahrenheit).sendKeys(value);
    }

    public void clickConvertBtn() {
        driver.findElement(calculatebutton).click();
    }

    public String getCelsius() {
        return driver.findElement(celsius).getAttribute("value");
    }

    public String getKelvin() {
        return driver.findElement(kelvin).getAttribute("value");
    }

    public String getFahrenheit() {
        return driver.findElement(fahrenheit).getAttribute("value");
    }

    public void clickFlipBtn() {
        driver.findElement(flipbutton).click();
    }

    public void selectCity(String city) {
        Select dropdown = new Select(driver.findElement(citydropdown));
        dropdown.selectByVisibleText(city);
    }
    
    public void clickgetWeatherBtn() {
        driver.findElement(weatherbutton).click();
    }
    
    public void clickResetBtn() {
        driver.findElement(resetbutton).click();
    }
    
    public boolean checkcitydisplay(){
    	return driver.findElement(citydisplay).isDisplayed();
    }
}

 


