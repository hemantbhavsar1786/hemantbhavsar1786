package Pages;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Testway.test.Testway.test.cucumber.commonFunction;
import Testway.test.Testway.test.cucumber.globalDeclaration;
import Testway.test.Testway.test.cucumber.webdriverFuction;


public class OrderAndPay {
	
	
	//**** user other classes
	// To Use webDriver methods
	webdriverFuction wdfunction = new webdriverFuction();
	// To use common methods
    commonFunction commonfunction = new commonFunction();
  //-------------------------------------------------------------------------
    
    //**** Page Elements
	//addres
	By addressBy = By.id("iaddress");
	
	//postcode
	By postcodeBy = By.id("ipostcode");
	//City
	By cityBy = By.id("itown");
	//email
	By emailBy = By.id("iemail");
	//Surname
	By surnameBy = By.id("isurname");
	//iphonenumber
	By phonenumberBy = By.id("iphonenumber");
	//-------------------------------------------------------------------------
	//**** Variables
	WebDriver driver=null;
	String Address ="";
	String PostCode ="";
	String City ="";
	String Name ="";
	double PhoneNum = 0;
	String eMail ="";
	
	
	//-------------------------------------------------------------------------
	
	public OrderAndPay(WebDriver driver)
	{
		this.driver=driver;
	}
	//-------------------------------------------------------------------------
	//Page Verification
	public Boolean pageVerification()
	{
		try
		{
			driver.findElement(addressBy).isDisplayed();
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	//-------------------------------------------------------------------------
		
	 public void FillFormDetailsAndOrderPay()
	 {
		 try
		 {
			 //Read input data
			 readInputDataFromExcel();
			 // Address
			 WebElement AddressEle= driver.findElement(addressBy);
			 AddressEle.sendKeys(Address);
			 
			 //PostCode
			 WebElement PostCodeEle= driver.findElement(postcodeBy);
			 
			 PostCodeEle.sendKeys(Keys.BACK_SPACE);
			 PostCodeEle.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			 PostCodeEle.sendKeys(Keys.DELETE);
			 PostCodeEle.sendKeys(PostCode); // 
			 
			 //City
			 WebElement CityEle= driver.findElement(cityBy);
			 CityEle.sendKeys(City);
			 
			 //email
			 WebElement emailEle= driver.findElement(emailBy);
			 emailEle.sendKeys(eMail);
			 
			 // Name
			 WebElement nameEle= driver.findElement(surnameBy);
			 nameEle.sendKeys(Name);
			 
			 //PhoneNumber
			 WebElement PhoneNumberEle= driver.findElement(phonenumberBy);
			 PhoneNumberEle.sendKeys(Double.toString(PhoneNum));
			 
			 // check box
			 //OrderAndPayBy button
			 By OrderAndPayBy = By.cssSelector("input[class='button_form cartbutton-button']");
			 
			 wdfunction.applyExplicitWait(driver,globalDeclaration.clickableExpectedCondition,OrderAndPayBy, 20);
			 
			 WebElement OrderAndPayEle= driver.findElement(OrderAndPayBy);
			 
			 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", OrderAndPayEle);
			 
			 OrderAndPayEle.click();
			 globalDeclaration.errorOccured=false;
			 
			 System.out.println("Form detailed filled successfully");
		 }
		 catch(Exception e)
		 {
			 globalDeclaration.errorOccured=true;
			 System.out.println("Error occured in method FillFormDetailsAndOrderPay");
		 }
		 
	 }// public void FillFormDetails()
	 //-----------------------------------------------------------------------------
	 public void readInputDataFromExcel()
	 {
		 XSSFSheet globalDeclarationSheet = null;
		
		try
		{
				globalDeclarationSheet=commonfunction.ReadExcelFile(globalDeclaration.globalDeclarationFile);
				
				int intTotalRows = globalDeclarationSheet.getLastRowNum();
				
				// iterate through execel  file to find execution flag
				
				for (int intRow=1;intRow<=intTotalRows;intRow++)
				{
					//Check execution flag
					String ExecutionFlag = globalDeclarationSheet.getRow(intRow).getCell(16).getStringCellValue();
					
					if (ExecutionFlag.equalsIgnoreCase("Yes"))
					{
						//Address		10
						Address  = globalDeclarationSheet.getRow(intRow).getCell(10).getStringCellValue();
						//PostCode
						PostCode  = globalDeclarationSheet.getRow(intRow).getCell(11).getStringCellValue();
						//City			11
						City  = globalDeclarationSheet.getRow(intRow).getCell(12).getStringCellValue();
						//Name			12
						Name  = globalDeclarationSheet.getRow(intRow).getCell(13).getStringCellValue();
						//PhoneNum		13	
						PhoneNum  = (int) globalDeclarationSheet.getRow(intRow).getCell(14).getNumericCellValue();
						//e-mail			14
						eMail  = globalDeclarationSheet.getRow(intRow).getCell(15).getStringCellValue();
						
					}//if (globalDeclarationSheet.getLastRowNum()>0)
				}//for (int intRow=1;intRow<=intTotalRows;intRow++)
			}
			catch(Exception e)
			{
				System.out.println("Please set global declaraton file to submit personal details");
			}
	 }// public void readInputDataFromExcel()
}//public class OrderAndPay {
