package Testcases.StepDefinition;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;

import Pages.LocationSearch;
import Pages.OrderAndPay;
import Pages.OrderReferenceNumberPage;
import Pages.RestaurantSelection;
import Pages.orderCreation;
import Testway.test.Testway.test.cucumber.commonFunction;
import Testway.test.Testway.test.cucumber.globalDeclaration;
import Testway.test.Testway.test.cucumber.webdriverFuction;
import io.cucumber.java.en.*;

public class CreateOrderStepDef {
	
	// To use common function
    commonFunction commonfunction = new commonFunction();
    
    // To use WebDriver function
    webdriverFuction wdfunction = new webdriverFuction();
    
    WebDriver driver = null;
    
    Boolean pageVerificationSuccessful=false;
    
 // Page varification 
	String pageTitle="";
	String addressSearch="";
	String subAddressToSelect="";
	// This variable used to check whether restaurant is exist
	Boolean restaurantExist = false;
	String  RestaurantName = "";
		
	// following variable can be use to order menu items
	String menuItem1 = "";
	String menuItem2 = "";
	String menuItem3 ="";
	String menuItem4 = "";
	
	@Given("Takeway website is launched")
	public void Takeway_website_is_launched() {
		
        XSSFSheet globalDeclarationSheet = null;
         
       
          
        String URL = "";
        String browser = "";
       
  		
  		
  		
  		
  		
  		
  		
  		
  		
		
  		
       
        
        // First read global declaration file and collect input data
		
		try
		{
			globalDeclarationSheet=commonfunction.ReadExcelFile(globalDeclaration.globalDeclarationFile);
			
			int intTotalRows = globalDeclarationSheet.getLastRowNum();
			
			// iterate through excel  file to find execution flag
			
			for (int intRow=1;intRow<=intTotalRows;intRow++)
			{
				//Check execution flag
				String ExecutionFlag = globalDeclarationSheet.getRow(intRow).getCell(16).getStringCellValue();
				
				if (ExecutionFlag.equalsIgnoreCase("Yes"))
				{
					// First check which scenario from Global declaration need to execute
					// We need to make execution Flag cilumn in gloaldeclaration file to Yes. All other columns should be No
					
					// set the url					
					URL  = globalDeclarationSheet.getRow(intRow).getCell(0).getStringCellValue();
					System.out.println("URL " + URL);
					
					
					// set Browser
					browser  = globalDeclarationSheet.getRow(intRow).getCell(1).getStringCellValue();
					// Restaurant and menu items
					RestaurantName  = globalDeclarationSheet.getRow(intRow).getCell(2).getStringCellValue();
					
					menuItem1  = globalDeclarationSheet.getRow(intRow).getCell(3).getStringCellValue();
					menuItem2  = globalDeclarationSheet.getRow(intRow).getCell(4).getStringCellValue();
					menuItem3  = globalDeclarationSheet.getRow(intRow).getCell(5).getStringCellValue();
					menuItem4  = globalDeclarationSheet.getRow(intRow).getCell(6).getStringCellValue();
					
					//Location to Search
					
					addressSearch=globalDeclarationSheet.getRow(intRow).getCell(17).getStringCellValue();
					
					
					
					subAddressToSelect=globalDeclarationSheet.getRow(intRow).getCell(18).getStringCellValue();
					
					//Location to Search
					
					globalDeclaration.errorOccured=false;
					
										
				}//if (globalDeclarationSheet.getLastRowNum()>0)
			}//for (int intRow=1;intRow<=intTotalRows;intRow++)
		}
		catch(Exception e)
		{
			globalDeclaration.errorOccured=true;
			System.out.println("Error while reading global declaration file. Please set global declaraton file properly");
		}
		
		// Launch the application
		if(!globalDeclaration.errorOccured)
		{
			driver= wdfunction.launchApplicationAndGetDriver(browser, URL);
			commonfunction.updateTestResult("Test_Case_Number_1", globalDeclaration.testExecutionPass, "Application launch successfully");
			
			
		}
		else
		{
			commonfunction.updateTestResult("Test_Case_Number_1", globalDeclaration.testExecutionFail, "Unable to launch application");
			commonfunction.ErrorOccuredStatement();
		}
		
	   
	}

	@When("Display the all restaurants by selecting the Location and sublocation")
	public void Display_the_all_restaurants_by_selecting_the_Location_and_sublocation() {
	    // Write code here that turns the phrase above into concrete actions
		LocationSearch locsearch = new LocationSearch(driver);
		
		// Verify Page title verification -- start
		
		if(!globalDeclaration.errorOccured)
		{
			
			
			pageTitle="Thuisbezorgd.nl | Food delivery | Easily order pizza, sushi and other food online";
			
			pageVerificationSuccessful=locsearch.pageVerification(pageTitle);
			if (pageVerificationSuccessful)
			{
				System.out.println("LocationSearch page title " + pageTitle + " is displayed successfully");
				globalDeclaration.errorOccured=false;
			}
			else
			{
				commonfunction.updateTestResult("Test_Case_Number_2", globalDeclaration.testExecutionNotCompleted, "page verification is not successful");
				System.out.println("LocationSearch page title " + pageTitle + " is not displayed successfully");
				globalDeclaration.errorOccured=true;
			}
		}
		else
		{
			commonfunction.ErrorOccuredStatement();
		}
		
		// Verify Page title verification -- end
		
		if(!globalDeclaration.errorOccured)
		{
			locsearch.enterLocationToSearch(addressSearch);
			//click on cookie button if displayed screen
			commonfunction.clickCookieOKButton(driver);
			
			System.out.println("Searching Location successful");
		}
		else
		{
			commonfunction.updateTestResult("Test_Case_Number_2", globalDeclaration.testExecutionNotCompleted, "Ubanle to enter sub location to search");
			commonfunction.ErrorOccuredStatement();
		}
		// click on cookie displayed on screen
		
		// Click on submit button
		if(!globalDeclaration.errorOccured)
		{
			locsearch.clickSubmitButton();
		}
		else
		{
			commonfunction.updateTestResult("Test_Case_Number_2", globalDeclaration.testExecutionNotCompleted, "Search using sublocation is not successful");
			commonfunction.ErrorOccuredStatement();
		}
		// Select Address from List
		if(!globalDeclaration.errorOccured)
		{
			locsearch.SelectAddressFromList(subAddressToSelect);
			System.out.println("Selected sub Location successfully");
			commonfunction.updateTestResult("Test_Case_Number_2", globalDeclaration.testExecutionPass, "Location and sublocation is successful");
		}
		else
		{
			commonfunction.updateTestResult("Test_Case_Number_2", globalDeclaration.testExecutionFail, "Not able to select sub location is not successful");
			commonfunction.ErrorOccuredStatement();
		}
		
		//****** LocationSearch page End 
		
	}

	@Then("Select the particular restaurant and display its menus")
	public void Select_the_particular_restaurant_and_display_its_menus() {
	    // Write code here that turns the phrase above into concrete actions
		
		//***** RestaurantSelection Page - Start
		
				RestaurantSelection restaurantselection = new RestaurantSelection(driver);
				
				// Verify Page title verification -- start
				
				if(!globalDeclaration.errorOccured)
				{
					pageVerificationSuccessful=restaurantselection.pageVerification();
					if (pageVerificationSuccessful)
					{
						System.out.println("RestaurantSelection page Verification : RestaurantSelection page is displayed successful");
						globalDeclaration.errorOccured=false;
					}
					else
					{
						commonfunction.updateTestResult("Test_Case_Number_3", globalDeclaration.testExecutionFail, "Restaurant page is not displayed correctly");
						globalDeclaration.errorOccured=true;
						System.out.println("RestaurantSelection page Verification : RestaurantSelection page is not  displayed successful");
					}
				}
				else
				{
					commonfunction.updateTestResult("Test_Case_Number_3", globalDeclaration.testExecutionFail, "Restaurant page is not displayed correctly");
					commonfunction.ErrorOccuredStatement();
				}
				
				// Verify Page title verification -- end
				
				// restaurant having different type -- display by particular Type
				if(!globalDeclaration.errorOccured)
				{
					restaurantselection.DisplayRestaurantByType("All");
					
				}
				else
				{
					commonfunction.ErrorOccuredStatement();
				}
				
				// Check Whether restaurent is exist at entered location -- validation
				if(!globalDeclaration.errorOccured)
				{
					restaurantExist = restaurantselection.checkAnyRestaurantExistForSelectedLocation();
					System.out.println("Test Case : Select location having restaurant and displayed properly");
					commonfunction.updateTestResult("Test_Case_Number_3", globalDeclaration.testExecutionPass, "Restaurant are displayed correctly");
				}
				else
				{
					commonfunction.updateTestResult("Test_Case_Number_3", globalDeclaration.testExecutionFail, "Restaurant are not displayed correctly");
					commonfunction.ErrorOccuredStatement();
				}
				
				//Select Restaurent and display its menu item
				if(!globalDeclaration.errorOccured)
				{
					try
					{
						restaurantselection.SelectRestaurantAndDisplayMenu(RestaurantName);
						System.out.println("Test case : Displayed the menu Item successfully");
						commonfunction.updateTestResult("Test_Case_Number_4", globalDeclaration.testExecutionPass, "Menu Items are displayed correctly");
					}
					catch(Exception e)
					{
						commonfunction.updateTestResult("Test_Case_Number_4", globalDeclaration.testExecutionFail, "Menu Items are not displayed correctly");
						System.out.println("Error Occured in dispaying menu Item");
						commonfunction.ErrorOccuredStatement();
					}
				}
				else
				{
					commonfunction.updateTestResult("Test_Case_Number_4", globalDeclaration.testExecutionFail, "Menu Items are not displayed correctly");
					commonfunction.ErrorOccuredStatement();
				}
				
				//***** RestaurantSelection Page - End
				
	   
	}

	@Then("Place the order by selecting the menu items")
	public void Place_the_order_by_selecting_the_menu_items() {
	    // Write code here that turns the phrase above into concrete actions
		

		// Order Creation page - start
		orderCreation ordercreation = new orderCreation(driver);
		
		
		
		if(!globalDeclaration.errorOccured)
		{
			try
			{
		
				if(!menuItem1.equals(""))
				{
					ordercreation.ItemSelection(menuItem1);
				}
				if(!menuItem2.equals(""))
				{
					ordercreation.ItemSelection(menuItem2);
				}
				if(!menuItem3.equals(""))
				{
					ordercreation.ItemSelection(menuItem3);
				}
				if(!menuItem4.equals(""))
				{
					ordercreation.ItemSelection(menuItem4);
				}
				ordercreation.priceSummary();
				System.out.println("Test case : menu Item selected successfully" );
				ordercreation.PlaceOrder();
				System.out.println("Test case : Order created  successfully" );
				commonfunction.updateTestResult("Test_Case_Number_5", globalDeclaration.testExecutionPass, "Selected and order the menu item successfully");
			}
			catch(Exception e)
			{
				commonfunction.updateTestResult("Test_Case_Number_5", globalDeclaration.testExecutionFail, "Getting problem toplace an order");
				System.out.println("Error Occured in selecting the menu Item");
				commonfunction.ErrorOccuredStatement();
			}
		}
		else
		{
			commonfunction.updateTestResult("Test_Case_Number_5", globalDeclaration.testExecutionFail, "Getting problem toplace an order");
			commonfunction.ErrorOccuredStatement();
		}
		//place the order
		
		
		// Order Creation page - End
	   
	}

	@Then("pay the amount by filling up personal details")
	public void pay_the_amount_by_filling_up_personal_details() {
	    
		//***** Order and pay page -start
		
			if(!globalDeclaration.errorOccured)
			{
				OrderAndPay orderandpay=new OrderAndPay(driver);
				// Verify Page title verification -- start
				
				if(!globalDeclaration.errorOccured)
				{
					pageTitle="Thuisbezorgd.nl";
					
					pageVerificationSuccessful=orderandpay.pageVerification();
					if (pageVerificationSuccessful)
					{
						
						globalDeclaration.errorOccured=false;
					}
					else
					{
						globalDeclaration.errorOccured=true;
						System.out.println("orderandpay page verification : orderandpay page is not displayed successful");
					}
				}
				
				// Verify Page title verification -- end
				try
				{
					orderandpay.FillFormDetailsAndOrderPay();
					System.out.println("Personal details are provided successfully");
					commonfunction.updateTestResult("Test_Case_Number_6", globalDeclaration.testExecutionPass, "Personal details entered successfully and order palced");
				}
				catch(Exception e)
				{
					commonfunction.updateTestResult("Test_Case_Number_6", globalDeclaration.testExecutionFail, "Personal details not entered successfully and order palced is not");
					System.out.println("Error Occured while filling up the form details");
					commonfunction.ErrorOccuredStatement();
				}
				
			}
			else
			{
				commonfunction.updateTestResult("Test_Case_Number_6", globalDeclaration.testExecutionFail, "Personal details not entered successfully and order palced is not");
				commonfunction.ErrorOccuredStatement();
			}
	    
	}

	@Then("get a order reference number")
	public void get_a_order_reference_number() {
		if(!globalDeclaration.errorOccured)
		{
			OrderReferenceNumberPage oderreferencenumberpage = new OrderReferenceNumberPage(driver);
			try
			{
				oderreferencenumberpage.getOrderReference();
				commonfunction.updateTestResult("Test_Case_Number_7", globalDeclaration.testExecutionPass, "Order reference number generated successfully");
				
			}
			catch(Exception e)
			{
				commonfunction.updateTestResult("Test_Case_Number_7", globalDeclaration.testExecutionFail, "Order reference number is not generated successfully");
				System.out.println("Error Occured generating order reference number");
				commonfunction.ErrorOccuredStatement();
			}
		}
		else
		{
			commonfunction.ErrorOccuredStatement();
		}
		//**********OrderReferenceNumberPage start
		
		//Close the browser
		wdfunction.releaseResources();
	    
	}




}
