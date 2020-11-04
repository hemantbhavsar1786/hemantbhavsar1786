package restAPITestSuiteExecution;

import java.io.File;

import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import restAPITestFrameworkWithExcelTestCases.restAPITestFrameworkWithExcelTestCases.test.CommonFunction;
import restAPITestFrameworkWithExcelTestCases.restAPITestFrameworkWithExcelTestCases.test.globalDeclaration;

public class restAPITestSuiteExecution { 
	
	 static CommonFunction commonfunction = new CommonFunction();
	
	// input file data variable
	
	
	static String test_Case_Number ="";

		
	static String test_Case_Name ="";

		
	static String executionFlag ="";
	
	
	static String restAPIMethodToTest ="";
	
		
	static String baseURI ="";

	
	static String methodURL ="";

	
	static String reqest_Header_Name ="";

	
	static String request_Header_Value =null;

	
	static int responceCodeToValidate =0;

	
	static String validate_Response_JSON_Using ="";

		
	static String input_Field_Name_1 ="";

		
	static String input_Field_Value_1 ="";

		
	static String input_Field_Name_2 ="";

		
	static int input_Field_Value_2 =0;

		
	static String input_Field_Name_3 ="";

		
	static int input_Field_Value_3 =0;

		
	static String input_Field_Name_4 ="";

		
	static String input_Field_Value_4 ="";

		
	static String input_Field_Name_5 ="";

		
	static String input_Field_Value_5 ="";

		
	static String input_Field_Name_6 ="";

		
	static String input_Field_Value_6 ="";

	
	static String remark ="";
	
	static String methodURI="";
	
	static int requestExecutionStatusCode=0;

	
	public static void main(String[] args) {
		
	
	
		XSSFSheet testSuiteSheet=null;
		
		File testSuiteFile = new File(globalDeclaration.testSuiteInputFile);
		
		if(testSuiteFile.exists())
		{
			//try
			//{
				testSuiteSheet=commonfunction.ReadExcelFile(globalDeclaration.testSuiteInputFile);
			
				int intTotalTestSuiteRows = testSuiteSheet.getLastRowNum();
				
				if(intTotalTestSuiteRows>0)
				{
					// iterate through excel  file to find execution flag
					
					for (int intRow=1;intRow<=intTotalTestSuiteRows;intRow++)
					{
						//Check execution flag
						String ExecutionFlag = testSuiteSheet.getRow(intRow).getCell(2).getStringCellValue();
						
						if (ExecutionFlag.equalsIgnoreCase("Yes"))
						{
							test_Case_Number =testSuiteSheet.getRow(intRow).getCell(0).getStringCellValue();
							
							test_Case_Name =testSuiteSheet.getRow(intRow).getCell(1).getStringCellValue();
							
								
							//executionFlag =testSuiteSheet.getRow(intRow).getCell(2).getStringCellValue();;
							
							
							restAPIMethodToTest =testSuiteSheet.getRow(intRow).getCell(3).getStringCellValue();
							
								
							baseURI =testSuiteSheet.getRow(intRow).getCell(4).getStringCellValue();
							
							
							methodURL =testSuiteSheet.getRow(intRow).getCell(5).getStringCellValue();
						
							
							reqest_Header_Name =testSuiteSheet.getRow(intRow).getCell(6).getStringCellValue();
							
							request_Header_Value =testSuiteSheet.getRow(intRow).getCell(7).getStringCellValue();
							
							
							
							
							 responceCodeToValidate = (int) testSuiteSheet.getRow(intRow).getCell(8).getNumericCellValue();
							 
							
							 validate_Response_JSON_Using =testSuiteSheet.getRow(intRow).getCell(9).getStringCellValue();
							 
							 
							 if (restAPIMethodToTest.equalsIgnoreCase(globalDeclaration.restAPIPostMethodKeyword) ||restAPIMethodToTest.equalsIgnoreCase(globalDeclaration.restAPIPutMethodKeyword))
							{
								 input_Field_Name_1 =testSuiteSheet.getRow(intRow).getCell(10).getStringCellValue();
								 
									
								 input_Field_Value_1 =testSuiteSheet.getRow(intRow).getCell(11).getStringCellValue();
								
									
								 input_Field_Name_2 =testSuiteSheet.getRow(intRow).getCell(12).getStringCellValue();
								
								
								 //salary
								 input_Field_Value_2 =(int) testSuiteSheet.getRow(intRow).getCell(13).getNumericCellValue();
								
									
								 //input_Field_Name_3 =testSuiteSheet.getRow(intRow).getCell(14).getStringCellValue();
								 input_Field_Name_3 =testSuiteSheet.getRow(intRow).getCell(14).getStringCellValue();
								 
									
								 input_Field_Value_3 =(int)testSuiteSheet.getRow(intRow).getCell(15).getNumericCellValue();
								
									
								 input_Field_Name_4 =testSuiteSheet.getRow(intRow).getCell(16).getStringCellValue();
								
									
								 input_Field_Value_4 =testSuiteSheet.getRow(intRow).getCell(17).getStringCellValue();
								
									
								 input_Field_Name_5 =testSuiteSheet.getRow(intRow).getCell(18).getStringCellValue();
									
									
								 input_Field_Value_5 =testSuiteSheet.getRow(intRow).getCell(19).getStringCellValue();
								
									
								 input_Field_Name_6 =testSuiteSheet.getRow(intRow).getCell(20).getStringCellValue();
								 
									
								 input_Field_Value_6 =testSuiteSheet.getRow(intRow).getCell(21).getStringCellValue();
								
								
								 remark =testSuiteSheet.getRow(intRow).getCell(22).getStringCellValue();
							}
							 
							//check rest API Method
							//String restAPIMethodToTest = testSuiteSheet.getRow(intRow).getCell(2).getStringCellValue();
							
							if (restAPIMethodToTest.equalsIgnoreCase(globalDeclaration.restAPIGetMethodKeyword))
							{
								// call get Method
								executeGetMethod();
							}
							else if (restAPIMethodToTest.equalsIgnoreCase(globalDeclaration.restAPIPostMethodKeyword))
							{
								// call get Method
								executePostMethod();
							}
							else if (restAPIMethodToTest.equalsIgnoreCase(globalDeclaration.restAPIPutMethodKeyword))
							{
								executePutMethod();
							}
							else if (restAPIMethodToTest.equalsIgnoreCase(globalDeclaration.restAPIDeleteMethodKeyword))
							{
								// Call Delete Method
								executeDeleteMethod();
							}
							
							// First check which scenario from Global declaration need to execute
							// We need to make execution Flag cilumn in gloaldeclaration file to Yes. All other columns should be No
							
							
							globalDeclaration.errorOccured=false;
							
												
						}//if (globalDeclarationSheet.getLastRowNum()>0)
					}//for (int intRow=1;intRow<=intTotalRows;intRow++)
				} // if totalrows
				else
				{
					System.out.println("No test cases exist in the test suite file");
				}
			
		}//if file exist
		else
		{
			System.out.println("Test SUite File is not exist");
		}
				
	}//main
	
	public static void executeGetMethod()
	{
		System.out.println();
		System.out.println();
		System.out.println("Get method execution");
		
		System.out.println();
		System.out.println();
		// Execute Get Method
		
		// Create Request Specification

		commonfunction.requestSpecification();
			
		// add header

		commonfunction.addRequestHeader(reqest_Header_Name,request_Header_Value);
		
		// Create full URL based on Method name and its paramter
		
		methodURI = baseURI+methodURL;
		
		System.out.println("get method " + methodURI + " is getting executed");
		
		 requestExecutionStatusCode = commonfunction.ExecuteMethodAndGetStatusCode(globalDeclaration.restAPIGetMethodKeyword, methodURI);
		
		System.out.println("get method is executed with status code "+requestExecutionStatusCode);
		
		if (requestExecutionStatusCode==responceCodeToValidate)
		{
			ValidateResponse();
			
		}
		else
		{
			commonfunction.updateTestResult(test_Case_Number,globalDeclaration.testExecutionFail ,"Test execution is completed with status code "+requestExecutionStatusCode);
			System.out.println("Get Method execution is failed");
		}
		
	}//public static void executeGetMethod()
		//----------------------------------------------------------------------------------
		
		public static void executePostMethod()
		{
			// Execution of post Method
			System.out.println();
			System.out.println();
			System.out.println("Post method execution");
			
			System.out.println();
			System.out.println();
			
			commonfunction.requestSpecification();
			//add headers
			commonfunction.addRequestHeader(reqest_Header_Name,request_Header_Value);
			
			
			methodURI = baseURI+methodURL;
			
			System.out.println("post method " + methodURI + " is getting executed");
			
			// Create request JSON
			prepareRequestJSEONFieldsAndValues();
			
			// Add request JSON to request
			
			commonfunction.addResuestJSONToRequest();
			
			// Execute the post request
			
			int requestExecutionStatusCode = commonfunction.ExecuteMethodAndGetStatusCode(globalDeclaration.restAPIPostMethodKeyword, methodURI);
			System.out.println("post method is executed with status code "+requestExecutionStatusCode);
			
			if (requestExecutionStatusCode==responceCodeToValidate)
			{
				
				ValidateResponse();
				
			}
			else
			{
				commonfunction.updateTestResult(test_Case_Number,globalDeclaration.testExecutionFail ," Execution completed wuth status code " +requestExecutionStatusCode);
				System.out.println("Post messagee executioon is failed");
			}
		}//postmethod end
		//----------------------------------------------------------------
		public static void ValidateResponse()
		{
			String responseBody = commonfunction.getResponseBody();
			
			System.out.println();
			System.out.println("please find below  the response body");
			System.out.println();
			System.out.println(responseBody);
			
			
			
			Boolean validateResponse = commonfunction.validateResponse(validate_Response_JSON_Using ,responseBody );
			
			if (validateResponse)
			{
				System.out.println();	
				System.out.println("Validation string  "+ validate_Response_JSON_Using + "displayed successfully is respoance body");
				System.out.println();
				
				// get response header and display headers
				
				Headers headers = commonfunction.displayResponseHeader();
				
				// display
				System.out.println("Response header are :");
				System.out.println();
				for (Header header:headers)
				{
					System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
				}
				System.out.println();
				
				commonfunction.updateTestResult(test_Case_Number,globalDeclaration.testExecutionPass ,"Response body validation is successful with searchstring " +validate_Response_JSON_Using);
			}
			
			else
			{
				commonfunction.updateTestResult(test_Case_Number,globalDeclaration.testExecutionFail ,"Response body validation is unsuccessful with searchstring " +validate_Response_JSON_Using);
				System.out.println("Validation string  "+ validate_Response_JSON_Using + "displayed successfully is respoance body");
			}
		}
		// put method
		public static void executePutMethod()
		{
			// Execution of put Method
			
			System.out.println();
			System.out.println("Put method execution");
			
			System.out.println();
			
			
			commonfunction.requestSpecification();
			//add headers
			commonfunction.addRequestHeader(reqest_Header_Name,request_Header_Value);
		
			methodURI = baseURI+methodURL;
			
			System.out.println("put method " + methodURI + " is getting executed");
			
			// Create request JSON
			prepareRequestJSEONFieldsAndValues();
			
						
			// Add request JSON to request
			commonfunction.addResuestJSONToRequest();
			
			// Execute the post request
			
			int requestExecutionStatusCode = commonfunction.ExecuteMethodAndGetStatusCode(globalDeclaration.restAPIPostMethodKeyword, methodURI);
			System.out.println("put method is executed with status code "+requestExecutionStatusCode);
			
			if (requestExecutionStatusCode==responceCodeToValidate)
			{
				
				ValidateResponse();
				
			}
			else
			{
				commonfunction.updateTestResult(test_Case_Number,globalDeclaration.testExecutionFail ," Execution completed wuth status code " +validate_Response_JSON_Using);
				System.out.println("Put messagee executioon is failed");
			}
		}//put method
		
		// execute delete method
		public static void executeDeleteMethod()
		{
			System.out.println();
			System.out.println();
			System.out.println("Delete method execution");
			
			System.out.println();
			System.out.println();
			// Execute Get Method
			
			// Create Request Specification

			commonfunction.requestSpecification();
				
			// add header

			commonfunction.addRequestHeader(reqest_Header_Name,request_Header_Value);
			
			// Create full URL based on Method name and its paramter
			methodURI = baseURI+methodURL;
			
			System.out.println("delete method " + methodURI + " is getting executed");
			
			
			
			int requestExecutionStatusCode = commonfunction.ExecuteMethodAndGetStatusCode(globalDeclaration.restAPIGetMethodKeyword, methodURI);
			
			System.out.println("delete method is executed with status code "+requestExecutionStatusCode);
			
			if (requestExecutionStatusCode==responceCodeToValidate)
			{
				ValidateResponse();
				
			}
			else
			{
				commonfunction.updateTestResult(test_Case_Number,globalDeclaration.testExecutionFail ,"Test execution is completed with status code "+requestExecutionStatusCode);

				System.out.println("Delete Method execution is failed");
			}
			
		}//public static void executeDeleteMethod()
		
		public static void prepareRequestJSEONFieldsAndValues()
		{
			// field 1
			if(!input_Field_Name_1.equals(""))
			{
				commonfunction.prepareRequestJSON(input_Field_Name_1, input_Field_Value_1);
			}
			// field 2
			if(!input_Field_Name_2.equals(""))
			{
				commonfunction.prepareRequestJSON(input_Field_Name_2, input_Field_Value_2);
			}

			// field 3
			if(!input_Field_Name_3.equals(""))
			{
				commonfunction.prepareRequestJSON(input_Field_Name_3, input_Field_Value_3);
			}

			// field 4
			if(!input_Field_Name_4.equals(""))
			{
				commonfunction.prepareRequestJSON(input_Field_Name_4, input_Field_Value_4);
			}

			// field 5
			if(!input_Field_Name_5.equals(""))
			{
				commonfunction.prepareRequestJSON(input_Field_Name_5, input_Field_Value_5);
			}

			// field 6
			if(!input_Field_Name_6.equals(""))
			{
				commonfunction.prepareRequestJSON(input_Field_Name_6, input_Field_Value_6);
			}
		}
		
		
		
}//class
