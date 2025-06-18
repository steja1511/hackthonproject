package Homepackage;
	import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
	 
	public class Baseclass {
		public static WebDriver driver;
	 
		//@Parameters("browser")
		@BeforeTest
	    @Parameters("browser")
	    public void setup( String browser) {
	        if (browser.equalsIgnoreCase("chrome")) {
	            driver = new ChromeDriver();
	        } else if (browser.equalsIgnoreCase("edge")) {
	            driver = new EdgeDriver();
	        } else {
	            System.out.println("Invalid browser. Defaulting to Chrome.");
	            driver = new ChromeDriver();
	        }
	        driver.manage().window().maximize();
	        driver.get("https://null-pointers-five.vercel.app/");
	    }
//	    @AfterSuite
//	    public void teardown() 
//	    {
//	        if (driver != null) {
//	            driver.quit();
//	        }
//	    }
	}
	
