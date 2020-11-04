package Testway.test.Testway.test.cucumber;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class webdriverFuction {
	
	 WebDriver mdriver = null;
		
		public WebDriver launchApplicationAndGetDriver(String strBrowserName ,String strURL)
		 {
			try
			{
				 if(strBrowserName.equalsIgnoreCase("ie"))
				 {
					 System.setProperty("webdriver.ie.driver", globalDeclaration.ieExePath);
					 
					 mdriver = new InternetExplorerDriver();
				 }
				 else if (strBrowserName.equalsIgnoreCase("firefox"))
				 {
					 System.setProperty("webdriver.gecod.driver", globalDeclaration.fireFoxExePath);
					 
					 FirefoxOptions op = new FirefoxOptions(); 
					 mdriver = new FirefoxDriver(op);
					 
				 }
				 else if (strBrowserName.equalsIgnoreCase("chrome"))
				 {
					 System.setProperty("webdriver.chrome.driver", globalDeclaration.chromeExePath);
					 mdriver = new ChromeDriver();
				 }
				 //Launch the URL here 
				 mdriver.get(strURL);
				 
				 //Maximize the window
				 mdriver.manage().window().maximize();
				 globalDeclaration.errorOccured=false;
				  
				 System.out.println("Launch the application URL " + strURL + " on Browser " + strBrowserName);
			}
			catch(Exception e)
			{
				System.out.println("Application unable to launch successly. Please check");
				globalDeclaration.errorOccured=true;
			}
			return mdriver;
	}//public void launchBrowser(String strBrowserName)
		
	//-----------------------------------------------------------------
		
	// Apply wait statement
	 public void applyExplicitWait(WebDriver driver,String condition , By element , int waitingTime)
	 {
		 try
		 {
			 WebDriverWait wait = new WebDriverWait(driver,waitingTime);
			 if (condition.contains("visible"))
			 {
				wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			 }
			 else if (condition.contains("clickable"))
			 {
				 wait.until(ExpectedConditions.elementToBeClickable(element));
			 }
			 globalDeclaration.errorOccured=false;
		 }
		 catch (Exception e)
		 {
			 globalDeclaration.errorOccured=true;
			 System.out.println("Error occured in applyExplicitWait");
		 }
	 }// public void applyExplicitWait(St
	 
	//-----------------------------------------------------------------
	 
	 public void releaseResources()
	 {
		 mdriver.quit();
	 }

} //public class webdriverFuction {


