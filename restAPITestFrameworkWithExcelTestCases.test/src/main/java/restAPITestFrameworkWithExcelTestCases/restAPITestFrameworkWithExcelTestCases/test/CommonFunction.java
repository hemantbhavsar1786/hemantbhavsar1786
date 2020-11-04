package restAPITestFrameworkWithExcelTestCases.restAPITestFrameworkWithExcelTestCases.test;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CommonFunction {
	
	public  RequestSpecification request = null; 
	public  Response  response= null;
	JSONObject json = new JSONObject();
	
	
	
	//Create request specification
	public void requestSpecification()
	{
		request= RestAssured.given();
		//request.baseUri(baseUri)
		//System.out.println("Hi");
	}
	//-----------------------------------------------------------------------------------
	// Add requestHeader
	public void addRequestHeader(String key , String request_Header_Value)
	{
		System.out.println("inside method key "+key);
		
		System.out.println("inside method request_Header_Value "+request_Header_Value);
		request.header(key, request_Header_Value);
	}
	//-----------------------------------------------------------------------------------
	public int ExecuteMethodAndGetStatusCode(String restMethodType,String methodURL)
	{
		if(restMethodType.equalsIgnoreCase(globalDeclaration.restAPIGetMethodKeyword))
		{
			System.out.println("get methods get executed");
			
			response = request.get(methodURL);
		}
		else if (restMethodType.equalsIgnoreCase(globalDeclaration.restAPIPostMethodKeyword))
		{
			System.out.println("Post methods get executed");
			
			response = request.post(methodURL);
		}
		else if (restMethodType.equalsIgnoreCase(globalDeclaration.restAPIPostMethodKeyword))
		{
			System.out.println("put methods get executed");
			
			response = request.put(methodURL);
		}
		else if (restMethodType.equalsIgnoreCase(globalDeclaration.restAPIDeleteMethodKeyword))
		{
			System.out.println("Delete methods get executed");
			
			response = request.delete(methodURL);
		}
		else
		{
			System.out.println("No Methods get called");
		}
		
		
		int intStatusCode = response.getStatusCode();
		
		return intStatusCode;
	}//public int ExecuteMethodAndGetStatusCode(String restMethodType,String methodURL)
	//-----------------------------------------------------------------------------------
	public String getResponseBody()
	{
		String responseJSONString = response.getBody().asString();
		
		 return responseJSONString;
		
	}
	//-----------------------------------------------------------------------------------
	//Validate the Response with string
	
		public Boolean validateResponse(String whatToValidate , String ResponseBody)
		{
			Boolean validationSuccessful = false;
			
			if(ResponseBody.contains(whatToValidate))
			{
				validationSuccessful=true;
			}
			else
			{
				validationSuccessful=false;
			}
			
			return validationSuccessful;
			
		}
		//-----------------------------------------------------------------------------------
		
		//Validate the Response with string
		
		public Boolean validateResponse(int whatToValidate , String ResponseBody)
		{
			Boolean validationSuccessful = false;
			
			if(ResponseBody.contains(String.valueOf(whatToValidate)))
			{
				validationSuccessful=true;
			}
			else
			{
				validationSuccessful=false;
			}
			
			return validationSuccessful;
			
		}
		//-----------------------------------------------------------------------------------
		
		public Headers displayResponseHeader()
		{
			Headers headers = response.getHeaders();
			
			return headers;
		}
		//-----------------------------------------------------------------------------------
		
		public void prepareRequestJSON(String key ,String strvalue)
		{
			json.put(key, strvalue);
		}
		
		
		public void prepareRequestJSON(String key ,int intvalue)
		{
			json.put(key, intvalue);
		}
	
		//-----------------------------------------------------------------------------------
		
		public void addResuestJSONToRequest()
		{
			request.body(json.toJSONString());
		
		}
		
		//----------------------------------------------------
		// Read Excel file
				 public XSSFSheet ReadExcelFile(String strExcelFilePath)
				 {
					 
					 File file =null;
					 XSSFWorkbook workbook = null;
					 try
					 {
						 file = new File(strExcelFilePath);
					 
					 //
						 FileInputStream fis = null;
					 
							 try {
								 fis = new FileInputStream(file);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							 
							
							try {
								workbook = new XSSFWorkbook(fis);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					 }
					 catch(Exception e)
					 {
						System.out.println("Problem in reading the global declaration file");
						globalDeclaration.errorOccured=true;
					 }
					
					XSSFSheet sheet = workbook.getSheetAt(0);
					
					return sheet;
				 } // public XSSFSheet ReadExcelFile(String strExcelFilePath)
				 
				 //------------------------------------------------------------------
				 public void updateTestResult(String testcaseNumber, String testcaseStatus ,String remark)
				 {
					 XSSFWorkbook workbookupdate = null;
					 File excelFileToUpdate =null;
					 XSSFSheet testResultSheet = null;
					 Boolean testResultUpdateSuccessful=false;
						 
						 try
						 {
							 excelFileToUpdate = new File(globalDeclaration.testResultFile);
							 
							 if(excelFileToUpdate.exists() || excelFileToUpdate.canRead())
							 {
						 
								 //
									 FileInputStream fisUpdateExcel = null;
								 
										 try {
											 fisUpdateExcel = new FileInputStream(excelFileToUpdate);
										} catch (FileNotFoundException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										 
										
										try {
											workbookupdate = new XSSFWorkbook(fisUpdateExcel);
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									
									
									testResultSheet = workbookupdate.getSheetAt(0);
									
									int updateExcelFileTotalRecord = testResultSheet.getLastRowNum();
									
									if(updateExcelFileTotalRecord>0)
									{
										for(int row=1;row<=updateExcelFileTotalRecord;row++)
										{
											String testcaseNumberFromFile = testResultSheet.getRow(row).getCell(0).getStringCellValue();
											
											
											if(testcaseNumberFromFile.equalsIgnoreCase(testcaseNumber))
											{
												 testResultSheet.getRow(row).getCell(2).setCellValue(testcaseStatus);
												 testResultSheet.getRow(row).getCell(3).setCellValue(remark);
												 testResultUpdateSuccessful=true;
												 break;
											}
										}
									}
									else
									{
										System.out.println("Test Result file is not created");
								}//for
									//exit for loop
									if(testResultUpdateSuccessful)
									{
															
										FileOutputStream fos = null;
										try {
											fos = new FileOutputStream(excelFileToUpdate);
										} catch (FileNotFoundException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										try {
											workbookupdate.write(fos);
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										try {
											workbookupdate.close();
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									else
									{
										System.out.println("Test case is not found to update");
									}
						 }//if
							 else
							 {
								 System.out.println("Test Result file is not exist or it is open. Please check");
								 globalDeclaration.errorOccured=false;
							 }
									
						 }
						 catch(Exception e)
						 {
							System.out.println("Problem in reading Test Result File");
							globalDeclaration.errorOccured=true;
						 }
								
			}// public void updateTestResult(String testcaseNumber, String testcaseStatus ,String remark)
				

}//public class CommonFunction {
