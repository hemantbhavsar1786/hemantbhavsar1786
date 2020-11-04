package Pages;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Testway.test.Testway.test.cucumber.commonFunction;
import Testway.test.Testway.test.cucumber.globalDeclaration;
import Testway.test.Testway.test.cucumber.webdriverFuction;



public class LocationSearch {
	
	private WebDriver driver = null;
	 
	private String searchAddressFromList="";
	//**** use other classes - start
	
	// Use webdriver methods
	webdriverFuction wdfunction = new webdriverFuction();
	// To use common methods
    commonFunction commonfunction = new commonFunction();
 
    
	// **** Page Element 
	//Enter address
	By searchAddressBy = By.id("imysearchstring");
	
	//Submit Button
	By submitElementBy = By.id("submit_deliveryarea");
		
	//---------------------------------------------
	
	//****  constructor
	public LocationSearch(WebDriver driver)
	{
		this.driver=driver;
		
	}//public LocationSearch(WebDriver driver)
	
	//-------------------------------------------------------------------------
	//Page Verification
	public Boolean pageVerification(String expectedPagetitle)
	{
		String actualPageTile = driver.getTitle();
		Boolean pageVerificationSuccessful = commonfunction.pageVerification(actualPageTile, expectedPagetitle);
		
		return pageVerificationSuccessful; 
		
	}
	//-------------------------------------------------------------------------
	//Enter address to Search
	public void enterLocationToSearch(String pAddress)
	{
		try
		{
			//Apply explicit waiting time
			wdfunction.applyExplicitWait(driver,globalDeclaration.visibleExpectedCondition, searchAddressBy, 10);
			
			WebElement searchAddressElement = driver.findElement(searchAddressBy);
			
			searchAddressElement.sendKeys(Keys.BACK_SPACE);
			searchAddressElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			searchAddressElement.sendKeys(Keys.DELETE);
			searchAddressElement.sendKeys(pAddress);
			 
			globalDeclaration.errorOccured=false;
		}
		catch(Exception e)
		{
			System.out.println("Error Occured while entering location to search");
			globalDeclaration.errorOccured=true;
		}
		
	}//public void enterLocationToSearch(String pAddress)
	//------------------------------------------------------------------------------------
	// Click on Submit Button
	public void clickSubmitButton()
	{
		try
		{
			//Apply explicit waiting time
			wdfunction.applyExplicitWait(driver,globalDeclaration.visibleExpectedCondition, submitElementBy, 25);
			WebElement submitElement = driver.findElement(submitElementBy);
			submitElement.click();
			globalDeclaration.errorOccured=false;
		}
		catch(Exception e)
		{
			System.out.println("Error Occured when click on  submit button");
			globalDeclaration.errorOccured=true;
		}
			 
	}//public void clickSubmitButton()
	//------------------------------------------------------------------------------------
	// Select the address from drop down
	public void SelectAddressFromList(String SelectAddress)
	{
		try
		{
			By SelectAddressFromListBy = By.linkText(SelectAddress);
			//Apply explicit waiting time
			wdfunction.applyExplicitWait(driver,globalDeclaration.visibleExpectedCondition, SelectAddressFromListBy, 25);
			WebElement SelectAddressFromListElement = driver.findElement(SelectAddressFromListBy);
			SelectAddressFromListElement.click();
			System.out.println("Selected address : "+SelectAddress);
			globalDeclaration.errorOccured=false;
		}
		catch(Exception e)
		{
			System.out.println("Error Occured when click on SelectAddressFromList");
			globalDeclaration.errorOccured=true;
		}
		
	}//public void SelectAddressFromList(String SelectAddress)
	//------------------------------------------------------------------------------------
}//public class LocationSearch {