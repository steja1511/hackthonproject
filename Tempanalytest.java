package Homepackage;

import java.io.FileInputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tempanalytest extends Baseclass {
    Temp_Analysis tempanlysis;

    @Test(priority = 8,dependsOnMethods="version2")
    public void fromExcel() throws Exception {
        tempanlysis = new Temp_Analysis(driver);
        //tempanlysis.setWork();
        //tempanlysis.settemp();
        FileInputStream fis=new FileInputStream("src/test/resources/testdata.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet s1=wb.getSheetAt(0);
		for(int i=1;i<=s1.getLastRowNum();i++)
		{
			XSSFRow row=s1.getRow(i);
			if(row==null)
				continue;
			String cel="";
			String fahr="";
			String kelvin="";
			if(row.getCell(0)!=null)
				cel=row.getCell(0).toString();
			    tempanlysis.enterCelsius(cel);
			if(row.getCell(2)!=null)
				fahr=row.getCell(2).toString();
			tempanlysis.enterfahrenheit(fahr);
			if(row.getCell(1)!=null)
				kelvin=row.getCell(1).toString();
			    tempanlysis.enterkelvin(kelvin);
			
			double inputvalue=0;
			String inputtype="";
			if(!cel.isEmpty())
			{
				inputvalue=Double.parseDouble(cel);
				inputtype="c";
			}
			else if(!kelvin.isEmpty())
			{
				inputvalue=Double.parseDouble(kelvin);
				inputtype="k";
			}
			else if(!fahr.isEmpty())
			{
				inputvalue=Double.parseDouble(fahr);
				inputtype="f";
			}
			
			tempanlysis.clickConvertbtn();
			String outc=tempanlysis.getCelsius();
			String outk=tempanlysis.getkelvin();
			String outf=tempanlysis.getfahrenheit();
			
			double expectedc=0,expectedf=0,expectedk=0;
			switch(inputtype)
			{
			case "c":
				expectedc=inputvalue;
				expectedf=(inputvalue*9/5)+32;
				expectedk=inputvalue+273.15;
				break;
				
			case "f":
			   expectedc=(inputvalue-32)*5/9;
			   expectedf=inputvalue;
			   expectedk=((inputvalue-32)*5/9)+273.15;
			   break;
			   
			case"k":
				expectedc=inputvalue-273.15;
				expectedf=((inputvalue-273.15)*9/5)+32;
				expectedk=inputvalue;
				break;
			}
			
			Assert.assertEquals(Double.parseDouble(outc), expectedc,0.01);
			Assert.assertEquals(Double.parseDouble(outf), expectedf,0.01);
			Assert.assertEquals(Double.parseDouble(outk), expectedk,0.01);
		}
		wb.close();
		fis.close();
		
    }

    @Test(priority = 9)
    public void calculateWeather() throws Exception {
        
        Select city = new Select(tempanlysis.selectCity());
        List<WebElement> options = city.getOptions();
        Assert.assertEquals(options.size(), 11);
        for (int i = 0; i < options.size(); i++) {
            city.selectByIndex(i);
        }
        tempanlysis.clickCalweather();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("cityCards")));
        List<WebElement> cards = tempanlysis.cards();
        System.out.println(cards.get(0).getText());
        Assert.assertEquals(cards.size(), 1);
    }

    @Test (priority = 10)
    public void handleTableView() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("temperatureChart")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("temperatureChart")));
        tempanlysis.takeScreenshot("temp1.png");

        tempanlysis.clickTableviewAndTakeScreenshot("TableViewScreenshot.png"); 
        tempanlysis.clickCardview();
    }
}