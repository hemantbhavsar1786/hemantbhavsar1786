package restAPITestFrameworkWithExcelTestCases.restAPITestFrameworkWithExcelTestCases.test;

public class globalDeclaration {
	
	public static String testSuiteInputFile = System.getProperty("user.dir")+"\\inputDataFiles\\TestSuite.xlsx";
	
	public static String testResultFile = System.getProperty("user.dir")+"\\TestResult\\TestResult.xlsx";
	
	// keywprd defined for restAPI Methods
	
	public static String restAPIGetMethodKeyword ="get";
	
	public static String restAPIPostMethodKeyword ="post";
	
	public static String restAPIPutMethodKeyword ="put";
	
	public static String restAPIDeleteMethodKeyword ="delete";
	
	//Track error occured in script
	public static Boolean errorOccured = false;
	
	//testexecution status
	public static String testExecutionPass = "Pass";
	public static String testExecutionFail = "Fail";
	public static String testExecutionBlock = "Block";
	public static String testExecutionNotCompleted = "Nor Completed";

	

}
