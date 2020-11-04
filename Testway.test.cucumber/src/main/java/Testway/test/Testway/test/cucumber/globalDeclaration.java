package Testway.test.Testway.test.cucumber;



public class globalDeclaration {
	
	// Public global declaration file
	public static String globalDeclarationFile = System.getProperty("user.dir")+"\\inputDataFiles\\globalvariable.xlsx";
	
	//Test Result File
	public static String testResultFile = System.getProperty("user.dir")+"\\inputDataFiles\\TestResult.xlsx";
	
	// chromeExePath
	public static String chromeExePath = System.getProperty("user.dir")+"\\driverExe\\chrome\\chromedriver.exe";
			
	// IEExePath
	public static String ieExePath = System.getProperty("user.dir")+"\\driverExe\\ie\\IEDriverServer.exe";
	
	// FirefoxExePath
	public static String fireFoxExePath = System.getProperty("user.dir")+"\\driverExe\\firefox\\geckodriver.exe";
	
	// Check Whether error Occur
	public static boolean errorOccured = true;
	//Explicit wait value
	public static String visibleExpectedCondition = "visible";
	
	public static String clickableExpectedCondition = "clickable";
	
	// Test Execution related variable
	
	public static String testExecutionPass = "Pass";
	public static String testExecutionFail = "Fail";
	public static String testExecutionBlock = "Block";
	public static String testExecutionNotCompleted = "Nor Completed";


}//public class globalDeclaration {
