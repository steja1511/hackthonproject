package Homepackage;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class topFrame {
	
	WebDriver driver;
	WebDriverWait wait;
 
    // Constructor to initialize the driver and wait
    public topFrame(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }
 
	By Home=By.linkText("Home");
	By About=By.linkText("About");
	By Team=By.linkText("Team");
	By Works=By.xpath("//a[text()='Works']");
	By Contact=By.xpath("/html/body/header/nav/ul/li[5]/a");
	
	public void setHome() {
		driver.findElement(Home).click();
	}
	public void setAbout() {
		driver.findElement(About).click();
	}
	public void setTeam() {
		driver.findElement(Team).click();
	}
	public void setWorks() {
		driver.findElement(Works).click();
	}
	public void setContact() {
		driver.findElement(Contact).click();
	}
}
