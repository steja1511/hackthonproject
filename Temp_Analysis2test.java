package Homepackage;
import java.io.FileInputStream;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.Test;
public class Temp_Analysis2test extends Baseclass{
	private static final String getRow = null;
	Temp_Analysis2 tempanlysis;
	//tempanlysis.settemp();
	@Test(priority=7)
	public void version2( )throws Exception
	{
		tempanlysis=new Temp_Analysis2(driver);
		tempanlysis.setWork();
		Thread.sleep(2000);
		tempanlysis.setTemp();
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
			tempanlysis.enterFahrenheit(fahr);
			if(row.getCell(1)!=null)
				kelvin=row.getCell(1).toString();
			    tempanlysis.enterKelvin(kelvin);
			    	Thread.sleep(2000);
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
			tempanlysis.clickConvertBtn();
			String outc=tempanlysis.getCelsius();
			String outk=tempanlysis.getKelvin();
			String outf=tempanlysis.getFahrenheit();
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
		Thread.sleep(2000);
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		Thread.sleep(2000);
		tempanlysis.clickFlipBtn();
		  // Loop for city data
		XSSFSheet s2=wb.getSheetAt(1);
        for (int j = 1; j <= s2.getLastRowNum(); j++) {
            XSSFRow cityrow = s2.getRow(j);
            if (cityrow == null)
                continue;
            String city = "";
            if (cityrow.getCell(0) != null)
                city = cityrow.getCell(0).toString();
            tempanlysis.selectCity(city);
            Thread.sleep(2000);
            tempanlysis.clickgetWeatherBtn();
            Thread.sleep(2000);
            tempanlysis.clickResetBtn();
        }
        	wb.close();
    		fis.close();
    		Thread.sleep(2000);
    		if(tempanlysis.checkcitydisplay()==true)
    		{
    			System.out.println("Weather output container is visible");
    		}
    		else
    		{
    			System.out.println("Weather output container is not visible");
    		}
    		
    		tempanlysis.clickgetWeatherBtn();
    		try
            {
          	  Alert alert=driver.switchTo().alert();
          	  System.out.println("Alert is present");
          	  alert.accept();
          	 System.out.println("Alert is handled");
            }
            catch(NoAlertPresentException e)
            {
          	  System.out.println("No alert present");
            }
    		tempanlysis.clickotherversion();
	}	
}
 

