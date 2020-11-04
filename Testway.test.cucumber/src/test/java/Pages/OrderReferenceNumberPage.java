package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Testway.test.Testway.test.cucumber.commonFunction;
import Testway.test.Testway.test.cucumber.globalDeclaration;
import Testway.test.Testway.test.cucumber.webdriverFuction;


public class OrderReferenceNumberPage {
	
	//****User other classes 
	// To Use webDriver methods
	webdriverFuction wdfunction = new webdriverFuction();
	// To Use commonFunction methods
	commonFunction commonfunction = new commonFunction();
	
	//**** Page Element
	
	By OrderReferenceBy = By.cssSelector("div[class='order-reference']");
	
	//**** varibale
	WebDriver driver =null;
	
	//--------------------------------------------------------------------------
	//Constructor
	public OrderReferenceNumberPage(WebDriver driver)
	{
		this.driver = driver;
	}
	 //-------------------------------------------------------------------------
	
	 public void getOrderReference()
	 {
		 try
		 {
			 //div[class='order-reference']
			 //By OrderReferenceBy = By.cssSelector("div[class='order-reference']");
			 
			 wdfunction.applyExplicitWait(driver,globalDeclaration.visibleExpectedCondition, OrderReferenceBy, 10);
			 
			 WebElement OrderReferenceEle = driver.findElement(OrderReferenceBy);
			 
			 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", OrderReferenceEle);
			 String strOrderReference = OrderReferenceEle.getText();
			 
			 System.out.println(strOrderReference);
		 }
		 catch(Exception e)
		 {
			 globalDeclaration.errorOccured=false;
			 System.out.println("Error occured in method getOrderReference");
		 }
		 
	 }// public void getOrderReference()
	 //-------------------------------------------------------------------------

}
