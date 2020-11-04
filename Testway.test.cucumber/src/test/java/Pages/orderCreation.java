package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Testway.test.Testway.test.cucumber.commonFunction;
import Testway.test.Testway.test.cucumber.globalDeclaration;
import Testway.test.Testway.test.cucumber.webdriverFuction;


public class orderCreation {
	
	
	//**** User other classes
	// To Use webDriver methods
	webdriverFuction wdfunction = new webdriverFuction();
	// To Use commonFunction methods
	commonFunction commonfunction = new commonFunction();
	
	//**** Page WebElement
	
	By OrderButtonBy = By.cssSelector("button[class='basket__order-button cartbutton-button']");
	//SubTotal
	//span[class='cart-sum-price grey notranslate']
	By subTotalBy = By.cssSelector("span[class='cart-sum-price grey notranslate']");

	//Delivery cost 
	//span[class='cart-sum-price grey notranslate']
	By deliveryCostBy = By.cssSelector("span[class='cart-sum-price grey notranslate']");

//	Total
//	span[class='cart-sum-price notranslate']
	By totalCostBy = By.cssSelector("span[class='cart-sum-price notranslate']");

	
	//**** Variable
	WebDriver driver = null;
	
	
	
	//----------------------------------------------------------------------------------------
	
	public orderCreation(WebDriver driver)
	{
		this.driver=driver;
	}
	//-------------------------------------------------------------------------
		
	public void ItemSelection(String strMenuName)
	{ 
		Boolean itemSelectedWithPrice=false;
		Boolean menuItemSelected=false;
		try
		{
			List<WebElement> allItems = driver.findElements(By.tagName("span"));
			 
			WebElement ItemElementFound = null;
			String ItemPrice = "";
			
			 
			 for(WebElement singleItem:allItems)
			 {
				 String singleItemText = singleItem.getText();
				 if(singleItemText.equalsIgnoreCase(strMenuName))
				 {
					 						 
					ItemElementFound = singleItem;
					
					WebElement parentElement = singleItem.findElement(By.xpath("./.."));
					 
					 /// find span tag from parent elemet -- start
					List<WebElement>  parentElementspans = parentElement.findElements(By.tagName("span"));
					  
					/// find span tag from parent element -- end
					 
					WebElement SecondparentElement = parentElement.findElement(By.xpath("./.."));
					 				  
					WebElement ThirdparentElement = SecondparentElement.findElement(By.xpath("./.."));
						
					WebElement FourparentElement = ThirdparentElement.findElement(By.xpath("./.."));
						 
					List<WebElement> fourparentElementspans = FourparentElement.findElements(By.tagName("span"));
						  
					// click on particular items
					singleItem.click();
					menuItemSelected=true;
					
					
					// wait fot some particular time as sometime getting an error
					
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 
				 // click on price button
					List<WebElement> priceButtons =  FourparentElement.findElements(By.tagName("button"));
					 
					 for(WebElement button:priceButtons)
					 {
						 
						 if(button.getText().contains("€"))
						 {
							 ItemPrice = button.getText();
							 
							 button.click();
							 itemSelectedWithPrice=true;
							 break;
						 }
					
					 }// for(WebElement button:priceButtons)
					 
					 System.out.println(" Menu item " + strMenuName + " got selected ");
	
				 }// if(singleItemText.equalsIgnoreCase(pItemName))
				 if(itemSelectedWithPrice || menuItemSelected)
				 {
					 break;
				 }
			 
		 } //for(WebElement singleItem:allItems)
		}
		 catch(Exception e)
		 {
			 globalDeclaration.errorOccured=true;
			 System.out.println("Error occured in method checkAnyRestaurantExistForSelectedLocation");
		 }
		 
	}//public void ItemSelection(String strMenuName)
	//-----------------------------------------------
	public void PlaceOrder()
	 {
		 try
		 {
			 wdfunction.applyExplicitWait(driver,globalDeclaration.clickableExpectedCondition, OrderButtonBy, 20);
			 WebElement OrderButton = driver.findElement(OrderButtonBy);
			
			 OrderButton.click();
			 System.out.println("Order placed successfully");
			 globalDeclaration.errorOccured=false;
			 
		 }
		 catch(Exception e)
		 {
			 globalDeclaration.errorOccured=true;
			 System.out.println("Getting to problem to search a location in method PlaceOrder");
		 }
	 }// public void PlaceOrder()
	 //-------------------------------------------------------------------------
	
	public void priceSummary()
	{
		wdfunction.applyExplicitWait(driver,globalDeclaration.clickableExpectedCondition, subTotalBy, 20);
		
		String subTotal = driver.findElement(subTotalBy).getText();
		String deliveryCost = driver.findElement(deliveryCostBy).getText();
		String totalCost = driver.findElement(totalCostBy).getText();
		
		System.out.println("subTotal cost" + subTotal);
		
		System.out.println("deliveryCost " + deliveryCost);
		
		System.out.println("totalCost" + totalCost);
		
	}
	 
}//public class orderCreation {
