package Pages;

import org.openqa.selenium.By;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Testway.test.Testway.test.cucumber.commonFunction;
import Testway.test.Testway.test.cucumber.globalDeclaration;
import Testway.test.Testway.test.cucumber.webdriverFuction;



public class RestaurantSelection {
	//**** user other classes
	// To use  common function
	commonFunction commonfunction = new commonFunction();
	// To Use webDriver function
	webdriverFuction wdfunction = new webdriverFuction();
		
	//**** Page Elements
	By checkRestaurantExistBy = By.cssSelector("h2[class='restaurantname']");
	//check link "All" is displayed
	By checkAllLink = By.linkText("All");
	//----------------------------------------------------------------------
	
	//****variables
	WebDriver driver = null;
	
	//----------------------------------------------------------------------
	//Contructor
	public RestaurantSelection(WebDriver driver)
	{
		this.driver=driver;
	}
	//----------------------------------------------------------------------
	
	// Page Verification
	
	public Boolean pageVerification()
	{
		try
		{
			wdfunction.applyExplicitWait(driver,globalDeclaration.clickableExpectedCondition, checkAllLink,20);
			driver.findElement(checkAllLink).isDisplayed();
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
		
	}//public Boolean pageVerification()
	//----------------------------------------------------------------------
	
	// Display the restaurent by its type
	 public void DisplayRestaurantByType(String RestaurantType)
	 {
		 //String pRestaurantType = RestaurantType;
		// commonfunction.clickCookieOKButton(driver);
		 try
		 {
			 By searchTextBy = By.linkText(RestaurantType);
			 
			 wdfunction.applyExplicitWait(driver,globalDeclaration.clickableExpectedCondition, searchTextBy,20);
			 
			 driver.findElement(searchTextBy).click();
			 System.out.println("Selected Restaurant Type as : " + RestaurantType);
			 globalDeclaration.errorOccured=false;
			// System.out.println("Display the restaurent by type " + RestaurantType	 );
		 }
		 catch (Exception e)
		 {
			 globalDeclaration.errorOccured=true;
			 System.out.println("Getting to problem to search a location in method DisplayRestaurantByType");
			// e.printStackTrace();
			 
		 }
		 
	 }// public void DisplayRestaurantByType(String pRestaurantType)
	 //-------------------------------------------------------------------------
	 
	 // Select the particular restaurant and display the menu items
	 public void SelectRestaurantAndDisplayMenu(String RestaurantName)
	 {
		 try
		 {
			//System.out.println("RestaurantName : "+ RestaurantName);
				
			By RestaurantNameBy = By.xpath("//a[contains(.,'"+ RestaurantName +"')]");
			 
			wdfunction.applyExplicitWait(driver,globalDeclaration.visibleExpectedCondition, RestaurantNameBy, 30);
			 
			WebElement	RestaurantNameByElement = driver.findElement(RestaurantNameBy);
			
			RestaurantNameByElement.click();
			
			System.out.println("Selected restaurant named as : " +RestaurantName);
			
			globalDeclaration.errorOccured=false;
			 
		 }
		 catch (Exception e)
		 {
			 globalDeclaration.errorOccured=true;
			 System.out.println("Getting to problem in method SelectRestaurantAndDisplayMenu");
			// e.printStackTrace();
			 
		 }
	 }//public void SelectRestaurantAndDisplayMenu(String pRestaurantName)
	 
	 //-------------------------------------------------------------------------
	 
	 // Validation to check whether Restaurant exist for given location
	 public Boolean checkAnyRestaurantExistForSelectedLocation()
	 {
		 Boolean blnRestaurantExist=false;
		 try
		 {
			//Check whether restaurant is exist
			//By checkRestaurantExistBy = By.cssSelector("h2[class='restaurantname']");
			wdfunction.applyExplicitWait(driver,globalDeclaration.clickableExpectedCondition, checkRestaurantExistBy,30);
			//h2[class='restaurantname'] - all restaurant are displayed here in a tag
			WebElement checkRestaurantExist = driver.findElement(checkRestaurantExistBy);
			
			List<WebElement> checkAnyLinkRestaurant =  checkRestaurantExist.findElements(By.tagName("a"));
			int intnumberRestaurantElement = checkAnyLinkRestaurant.size();
			
			//System.out.println("intnumberRestaurantElement "+ intnumberRestaurantElement);
			
			if(intnumberRestaurantElement>0)
			{
				blnRestaurantExist= true;
				System.out.println("Restaurant verified :Restaurant are available for search location");
			}
			else
			{
				System.out.println("Restaurant Not Exist");
				blnRestaurantExist= false;
			}
			globalDeclaration.errorOccured=false;
		 }
		 catch(Exception e)
		 {
			 globalDeclaration.errorOccured=true;
			 blnRestaurantExist= false;
			 System.out.println("Error occured in method checkAnyRestaurantExistForSelectedLocation");
		 }
		
		 return blnRestaurantExist;
	 }//public void checkAnyRestaurantExistForSelectedLocation()

}//class
