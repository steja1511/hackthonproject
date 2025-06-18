package Homepackage;

import java.util.List;

import java.util.Map;
 
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
 
public class ContactTest extends Baseclass {
	
	
    @DataProvider(name = "contactFormData")
    public Object[][] contactFormDataProvider() {
        // Convert test data to a 2D Object array (rows of Name, Email, Message)
        List<Map<String, String>> testData = ExcelUtil.getTestData(
            "C:\\Users\\2388974\\eclipse-workspace\\Temp_analysis\\src\\test\\resources\\ContactData.xlsx",
            "Sheet1"
        );
 
        // Transform List<Map<String, String>> into Object[][] for DataProvider
        Object[][] dataArray = new Object[testData.size()][3];
        for (int i = 0; i < testData.size(); i++) {
            Map<String, String> row = testData.get(i);
            dataArray[i][0] = row.get("Name");
            dataArray[i][1] = row.get("Email");
            dataArray[i][2] = row.get("Message");
        }
        return dataArray;
    }
    @Test(dataProvider = "contactFormData",priority=6)
    public void testContactForm(String name, String email, String message) throws InterruptedException {
        ContactPage contactPage = new ContactPage(driver);
 
        contactPage.openContactSection();
        contactPage.enterName(name);
        contactPage.enterEmail(email);
        contactPage.enterMessage(message);
        Thread.sleep(3000);
        
        if (!name.matches("^[a-zA-Z ]+") && !name.isEmpty()) { // Regex to check if name contains invalid characters
            System.out.println("Validation Failed: " + name);
            Assert.fail("Test failed: Name contains numbers or invalid characters.");
        } else {
            // Proceed with form submission only if all inputs are valid
            contactPage.submitForm();
 
            
            Thread.sleep(2000);
            // Take a screenshot with a unique filename for each submission
            String screenshotName = "ContactForm_Submission_" + System.currentTimeMillis() + ".png";
            contactPage.takeScreenshot(screenshotName);
        }
    }
}