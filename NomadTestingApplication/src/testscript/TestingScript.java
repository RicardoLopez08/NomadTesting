package testscript;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Testing Script -
 * This script runs all the testing functions used in the NomadTest application.
 * @author Mitchell Ungar
 */
public class TestingScript {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  
  String strReport = "Sent";
  
  String COMMA_DELIMITER = ",";
  String NEW_LINE_SEPARATOR = "\n";
  String pattern = "yyyy-MM-dd";
  SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
  String date = simpleDateFormat.format(new Date());
	
  String FILE_HEADER = "REPORT:" + " Page Test" + ", " + ", " +"Tester: " + "Mitchell Ungar" +"," + "Date: " + date;
  String FILE_SUB_HEADER = "Subject:" + "," + "Status:";
  String strLoginName = "ceniuk@aim.com";
  String strPassword = "NomadUAT!1";
  String strAccountEmail = "testemail@test.com";
/**
 * Empty Constructor  
 */
public TestingScript() {
	super();
}
  
/**
 * Non-Empty Constructor
 * @param strLoginName
 * @param strPassword
 */
public TestingScript(String strLoginName, String strPassword) {
	super();
	this.strLoginName = strLoginName;
	this.strPassword = strPassword;
}



public String getStrReport() {
	return strReport;
}

public void setStrReport(String strReport) {
	this.strReport = strReport;
}

/**
 * Get Login Name - 
 * This function is used to get the Login Name from the test object.
 * @return strLoginName
 */
public String getStrLoginName() {
	return strLoginName;
}

/**
 * Get Account Email - 
 * This function is used to get the account email from the test object.
 * @return strAccountEmail
 */
public String getStrAccountEmail() {
	return strAccountEmail;
}
/**
 * Set Account Email - 
 * This function is used to set the test objects account email.
 * @param strAccountEmail
 */
public void setStrAccountEmail(String strAccountEmail) {
	this.strAccountEmail = strAccountEmail;
}
/**
 * Set Login Name - 
 * This function is used to set the test objects login name.
 * @param strLoginName
 */
public void setStrLoginName(String strLoginName) {
	this.strLoginName = strLoginName;
}
/**
 * Get Password - 
 * This function is used to get the password from the test object.
 * @return strPassword
 */
public String getStrPassword() {
	return strPassword;
}
/**
 * Set Password - 
 * This function is used to set the test objects password.
 * @param strPassword
 */
public void setStrPassword(String strPassword) {
	this.strPassword = strPassword;
}
/**
 * Set Up - 
 * Used to begin each test. Signs into Nomad using the e-mail and password. 
 * @throws Exception
 */
@Before
public void setUp() {
	strReport = "";
	FileWriter fileWriter;
	try {
		fileWriter = new FileWriter("Reports/SetUpTest.csv");
		fileWriter.append(FILE_HEADER);
		fileWriter.append(NEW_LINE_SEPARATOR);
		fileWriter.append(FILE_SUB_HEADER + COMMA_DELIMITER + "input: ");
		fileWriter.append(NEW_LINE_SEPARATOR);
		fileWriter.append("Running Resources Page Test");
		fileWriter.append(COMMA_DELIMITER);
		strReport = FILE_HEADER + NEW_LINE_SEPARATOR + FILE_SUB_HEADER + COMMA_DELIMITER + "input: " + NEW_LINE_SEPARATOR + "Running SetUp: " + COMMA_DELIMITER;
	    driver = new FirefoxDriver();
	    baseUrl = "https://bbd-nomad-alpha.herokuapp.com/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    //To login before every test
	    System.out.println("SIGNING IN:..........................");
	    driver.get(baseUrl + "/");
	    strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	    fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	    strReport = strReport + "Click Login Button" + COMMA_DELIMITER;
	    fileWriter.append("Click Login Button" + COMMA_DELIMITER);
	    driver.findElement(By.cssSelector("strong")).click();
	    strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	    fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	    fileWriter.append("User E-mail Address" + COMMA_DELIMITER);
	    strReport = strReport + "User E-mail Address" + COMMA_DELIMITER;
	    driver.findElement(By.id("user_email_address")).clear();
	    driver.findElement(By.id("user_email_address")).sendKeys(strLoginName);
	    strReport = strReport + "SUCCESS" + COMMA_DELIMITER + strLoginName + NEW_LINE_SEPARATOR;
	    fileWriter.append("SUCCESS" + COMMA_DELIMITER + strLoginName + NEW_LINE_SEPARATOR);
	    fileWriter.append("User Password" + COMMA_DELIMITER);
	    strReport = strReport + "User Password" + COMMA_DELIMITER;
	    driver.findElement(By.id("user_password")).clear();
	    driver.findElement(By.id("user_password")).sendKeys(strPassword);
	    strReport = strReport + "SUCCESS" + COMMA_DELIMITER + strPassword + NEW_LINE_SEPARATOR;
	    fileWriter.append("SUCCESS" + COMMA_DELIMITER + strPassword + NEW_LINE_SEPARATOR);
	    fileWriter.append("Click Login Button" + COMMA_DELIMITER);
	    strReport = strReport + "Click Login Button" + COMMA_DELIMITER;
	    driver.findElement(By.id("submit")).click();
	    strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	    fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	    fileWriter.append("Click Button Close" + COMMA_DELIMITER);
	    strReport = strReport + "Click Button Close" + COMMA_DELIMITER;
	    driver.findElement(By.cssSelector("button.close")).click();
	    strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	    fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	    strReport = strReport + "SetUp Completed";
	    fileWriter.append("SetUp Completed");
	    fileWriter.flush();
	    fileWriter.close();
	} catch (IOException e) {
		try {
			FileWriter fileWriter2 = new FileWriter("Reports/SetUpTest.csv");
			fileWriter2.append(strReport + "FAILURE," + " \n " + "SetUP Test Failure");
			fileWriter2.flush();
			fileWriter2.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	
  }
/**
 * Test Resources - 
 * Tests the resource page. 
 * @throws Exception
 */
@Test
public void testResources() throws Exception {
	strReport = "";
	FileWriter fileWriter = new FileWriter("Reports/ResourcesTest.csv");
	fileWriter.append(FILE_HEADER);
	fileWriter.append(NEW_LINE_SEPARATOR);
	fileWriter.append(FILE_SUB_HEADER);
	fileWriter.append(NEW_LINE_SEPARATOR);
	fileWriter.append("Running Resources Page Test");
	fileWriter.append(COMMA_DELIMITER);
	strReport = FILE_HEADER + NEW_LINE_SEPARATOR + FILE_SUB_HEADER + NEW_LINE_SEPARATOR + "Running Benefits Details Test: " + COMMA_DELIMITER;
  	driver.manage().window().maximize();
  	fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
  	strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
  	fileWriter.append("Clicking Resources Link" + COMMA_DELIMITER);
  	strReport = strReport + "Clicking Resources Link" + COMMA_DELIMITER;
  	driver.findElement(By.linkText("Resources")).click();
  	fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
  	strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
  	fileWriter.append("Clicking Admin Forums" + COMMA_DELIMITER);
  	strReport = strReport + "Clicking Admin Forums" + COMMA_DELIMITER;
  	driver.findElement(By.linkText("Admin Forms")).click();
  	fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
  	strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
  	fileWriter.append("Clicking FAQ Link" + COMMA_DELIMITER);
  	strReport = strReport + "Clicking FAQ Link" + COMMA_DELIMITER;
  	driver.findElement(By.linkText("FAQ")).click();
  	fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
  	strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
  	fileWriter.append("Clicking Glossary Link" + COMMA_DELIMITER);
  	strReport = strReport + "Clicking Glossary Link" + COMMA_DELIMITER;
  	driver.findElement(By.linkText("Glossary")).click();
  	fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
  	strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
  	fileWriter.append("Clicking Links Link" + COMMA_DELIMITER);
  	strReport = strReport + "Clicking Links Link" + COMMA_DELIMITER;
  	driver.findElement(By.linkText("Links")).click();
  	fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
  	strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
  	fileWriter.append("Resource Test Successful");
  	fileWriter.flush();
  	fileWriter.close();
  try {
    assertEquals("Announcements", driver.findElement(By.cssSelector("th")).getText());
  } catch (Error e) {
    verificationErrors.append(e.toString());
  }
  System.out.println("End");
}
/**
 * Edit/Terminate Employee - 
 * Edits and Terminates the employee in the third row.
 * @throws Exception
 */
@Test
public void testEmployees4() throws Exception {
	strReport = "";
	FileWriter fileWriter = new FileWriter("Reports/EditTerminateEmployeeTest.csv");
	fileWriter.append(FILE_HEADER);
	fileWriter.append(NEW_LINE_SEPARATOR);
	fileWriter.append(FILE_SUB_HEADER + COMMA_DELIMITER + "Input:");
	fileWriter.append(NEW_LINE_SEPARATOR);
	fileWriter.append("Running Edit Beneficiaries Page Test");
	fileWriter.append(COMMA_DELIMITER);
	strReport = FILE_HEADER + NEW_LINE_SEPARATOR + FILE_SUB_HEADER + COMMA_DELIMITER + "Input:" + NEW_LINE_SEPARATOR + "Running Edit/Terminate Employee Test: " + COMMA_DELIMITER;
	  System.out.println("Run Employees Test  4...............");	
	  driver.get(baseUrl + "/plan_administrator/company/3022425/dashboard");
	  driver.manage().window().maximize();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  System.out.println("Going back to Employees Page");
	  fileWriter.append("Clicking Employees Link" + COMMA_DELIMITER);
	  strReport = strReport + "Clicking Employees Link" + COMMA_DELIMITER;
	  driver.findElement(By.linkText("Employees")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Clicking View Button 3rd Row Down" + COMMA_DELIMITER);
	  strReport = strReport + "Clicking View Button 3rd Row Down" + COMMA_DELIMITER;
	  driver.findElement(By.xpath("(//a[contains(text(),'View')])[5]")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Editing Employee" + COMMA_DELIMITER);
	  strReport = strReport + "Editing Employee" + COMMA_DELIMITER;
	  driver.findElement(By.xpath("//a[contains(text(),'Edit')]")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Marital Status" + COMMA_DELIMITER);
	  strReport = strReport + "Marital Status" + COMMA_DELIMITER;
	  new Select(driver.findElement(By.id("plan_administrator_our_plan_members_employee_marital_status"))).selectByVisibleText("Single");
	  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "Single" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "Single" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Marital Status Effective Date" + COMMA_DELIMITER);
	  strReport = strReport + "Marital Status Effective Date" + COMMA_DELIMITER;
	  driver.findElement(By.id("plan_administrator_our_plan_members_employee_marital_status_effective_date")).clear();
	  driver.findElement(By.id("plan_administrator_our_plan_members_employee_marital_status_effective_date")).sendKeys("2016-05-01");
	  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "2016-05-01" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "2016-05-01" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Employee Change Effective Date" + COMMA_DELIMITER);
	  strReport = strReport + "Employee Change Effect Date" + COMMA_DELIMITER;
	  driver.findElement(By.id("plan_administrator_our_plan_members_employee_change_effective_date")).clear();
	  driver.findElement(By.id("plan_administrator_our_plan_members_employee_change_effective_date")).sendKeys("2016-04-07");
	  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "2016-04-07" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "2016-04-07" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Clicking Terms and Conditions" + COMMA_DELIMITER);
	  strReport = strReport + "Clicking Terms and Conditions" + COMMA_DELIMITER;
	  driver.findElement(By.id("terms_and_conditions")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Click Save Button" + COMMA_DELIMITER);
	  strReport = strReport + "Click Save Button" + COMMA_DELIMITER;
	  driver.findElement(By.name("commit")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("View Employee Button Click" + COMMA_DELIMITER);
	  strReport = strReport + "View Employee Button Click" + COMMA_DELIMITER;
	  driver.findElement(By.xpath("//div[@id='modal-confirm']/div/div/div[2]/button")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Terminate Button Click" + COMMA_DELIMITER);
	  strReport = strReport + "Terminate Button Click" + COMMA_DELIMITER;
	  driver.findElement(By.linkText("Terminate")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Employee Termination Date" + COMMA_DELIMITER);
	  strReport = strReport + "Employee Termination Date" + COMMA_DELIMITER;
	  driver.findElement(By.id("plan_administrator_our_plan_members_employee_termination_date")).clear();
	  driver.findElement(By.id("plan_administrator_our_plan_members_employee_termination_date")).sendKeys("2016-04-07");
	  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "2016-04-07" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "2016-04-07" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Termination Reason" + COMMA_DELIMITER);
	  strReport = strReport + "Termination Reason" + COMMA_DELIMITER;
	  new Select(driver.findElement(By.id("plan_administrator_our_plan_members_employee_termination_reason"))).selectByVisibleText("Other");
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Selecting Text Box" + COMMA_DELIMITER);
	  strReport = strReport + "Selecting Text Box" + COMMA_DELIMITER;
	  driver.findElement(By.cssSelector("option[value=\"6\"]")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Reason for Termination" + COMMA_DELIMITER);
	  strReport = strReport + "Reason for Termination" + COMMA_DELIMITER;
	  driver.findElement(By.id("plan_administrator_our_plan_members_employee_termination_reason_other")).clear();
	  driver.findElement(By.id("plan_administrator_our_plan_members_employee_termination_reason_other")).sendKeys("This is a test");
	  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "This is a test"  + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "This is a test" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Click Terminate Employee Button" + COMMA_DELIMITER);
	  strReport = strReport + "Click Terminate Employee Button" + COMMA_DELIMITER;
	  driver.findElement(By.name("commit")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Clicking Home Link" + COMMA_DELIMITER);
	  strReport = strReport + "Clicking Home Link" + COMMA_DELIMITER;
	  System.out.println("Clicking Home Link");
	  driver.findElement(By.linkText("Home")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Edit/Terminate Employee Test Successful");
	  strReport = strReport + "Edit/Terminate Employee Test Successful" + COMMA_DELIMITER;
	  System.out.println("End");
	  fileWriter.flush();
	  fileWriter.close();
}
/**
 * Edit Dependent - 
 * Goes into employee, and edits a dependent.
 * @throws Exception
 */
@Test
public void testEmployees3() throws Exception {
	strReport = "";
	FileWriter fileWriter = new FileWriter("Reports/EditDependentsTest.csv");
	fileWriter.append(FILE_HEADER);
	fileWriter.append(NEW_LINE_SEPARATOR);
	fileWriter.append(FILE_SUB_HEADER + COMMA_DELIMITER + "Input:");
	fileWriter.append(NEW_LINE_SEPARATOR);
	fileWriter.append("Running Edit Beneficiaries Page Test");
	fileWriter.append(COMMA_DELIMITER);
	strReport = FILE_HEADER + NEW_LINE_SEPARATOR + FILE_SUB_HEADER + COMMA_DELIMITER + "Input:" + NEW_LINE_SEPARATOR + "Running Edit Dependents Test: " + COMMA_DELIMITER;
	  System.out.println("Run Employees Test 3...............");	
	  driver.get(baseUrl + "/plan_administrator/company/3022425/dashboard");
	  driver.manage().window().maximize();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Clicking Employees Link" + COMMA_DELIMITER);
	  strReport = strReport + "Clicking Employees Link" + COMMA_DELIMITER;
	  driver.findElement(By.linkText("Employees")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Clicking View Button 3rd Row Down" + COMMA_DELIMITER);
	  strReport = strReport + "Clicking View Button 3rd Row Down" + COMMA_DELIMITER;
	  driver.findElement(By.xpath("(//a[contains(text(),'View')])[5]")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Clicking Dependents Link" + COMMA_DELIMITER);
	  strReport = strReport + "Clicking Dependents Link" + COMMA_DELIMITER;
	  driver.findElement(By.linkText("Dependents")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Viewing Item in Dependents" + COMMA_DELIMITER);
	  strReport = strReport + "Viewing Item in Dependents" + COMMA_DELIMITER;
	  driver.findElement(By.xpath("//table[@id='dependents-list']/tbody/tr/td[5]/div/button")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Opening Group" + COMMA_DELIMITER);
	  strReport = strReport + "Opening Group" + COMMA_DELIMITER;
	  driver.findElement(By.cssSelector("div.btn-group.open > ul.dropdown-menu > li > a")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Editing Group" + COMMA_DELIMITER);
	  strReport = strReport + "Editing Group" + COMMA_DELIMITER;
	  driver.findElement(By.linkText("Edit")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Gender" + COMMA_DELIMITER);
	  strReport = strReport + "Gender" + COMMA_DELIMITER;
	  new Select(driver.findElement(By.id("plan_administrator_our_plan_members_dependent_gender"))).selectByVisibleText("Female");
	  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "Female" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "Female"  + NEW_LINE_SEPARATOR;
	  fileWriter.append("Effective Change Date" + COMMA_DELIMITER);
	  strReport = strReport + "Effective Change Date" + COMMA_DELIMITER;
	  driver.findElement(By.id("plan_administrator_our_plan_members_dependent_change_effective_date")).clear();
	  driver.findElement(By.id("plan_administrator_our_plan_members_dependent_change_effective_date")).sendKeys("2016-04-07");
	  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "2016-04-07" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "2016-04-07" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Clicking Terms and Condition" + COMMA_DELIMITER);
	  strReport = strReport + "Clicking Terms and Conditions" + COMMA_DELIMITER;
	  driver.findElement(By.id("terms_and_conditions")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Saving Changes" + COMMA_DELIMITER);
	  strReport = strReport + "Saving Changes" + COMMA_DELIMITER;
	  driver.findElement(By.name("commit")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Clicking Benefit Coverage Link" + COMMA_DELIMITER);
	  strReport = strReport + "Clicking Benefit Coverage Link" + COMMA_DELIMITER;
	  driver.findElement(By.linkText("Benefit Coverage")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Clicking Dependents Link" + COMMA_DELIMITER);
	  strReport = strReport + "Clicking Dependents Link" + COMMA_DELIMITER;
	  driver.findElement(By.linkText("Dependents")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Viewing Dependent" + COMMA_DELIMITER);
	  strReport = strReport + "Viewing Dependent" + COMMA_DELIMITER;
	  driver.findElement(By.xpath("//table[@id='dependents-list']/tbody/tr/td[5]/div/button")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Terminating Dependent" + COMMA_DELIMITER);
	  strReport = strReport + "Terminating Dependent" + COMMA_DELIMITER;
	  driver.findElement(By.linkText("Terminate")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Setting Termination Date" + COMMA_DELIMITER);
	  strReport = strReport + "Setting Termination Date" + COMMA_DELIMITER;
	  driver.findElement(By.id("plan_administrator_our_plan_members_dependent_termination_date")).clear();
	  driver.findElement(By.id("plan_administrator_our_plan_members_dependent_termination_date")).sendKeys("2016-04-07");
	  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "2016-04-07" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "2016-04-07" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Termination Reason" + COMMA_DELIMITER);
	  strReport = strReport + "Termination Reason" + COMMA_DELIMITER;
	  new Select(driver.findElement(By.id("plan_administrator_our_plan_members_dependent_termination_reason"))).selectByVisibleText("Other");
	  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "Other" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "Other" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Terminate Dependent" + COMMA_DELIMITER);
	  strReport = strReport + "Terminate Dependent" + COMMA_DELIMITER;
	  driver.findElement(By.name("commit")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Edit Dependent Success");
	  strReport = strReport + "Edit Dependent Success";
	  fileWriter.flush();
	  fileWriter.close();
}
/**
 * Edits Beneficiaries - 
 * Goes into employee, and edits the beneficiaries. 
 * @throws Exception
 */
@Test
public void testEmployees2() throws Exception {
	strReport = "";
	FileWriter fileWriter = new FileWriter("Reports/EditBeneficiariesTest.csv");
	fileWriter.append(FILE_HEADER);
	fileWriter.append(NEW_LINE_SEPARATOR);
	fileWriter.append(FILE_SUB_HEADER + COMMA_DELIMITER + "Input:");
	fileWriter.append(NEW_LINE_SEPARATOR);
	fileWriter.append("Running Edit Beneficiaries Page Test");
	fileWriter.append(COMMA_DELIMITER);
	strReport = FILE_HEADER + NEW_LINE_SEPARATOR + FILE_SUB_HEADER + COMMA_DELIMITER + "Input:" + NEW_LINE_SEPARATOR + "Running Edit Beneficiaries Test: " + COMMA_DELIMITER;
	  driver.get(baseUrl + "/plan_administrator/company/3022425/dashboard");
	  driver.manage().window().maximize();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Clicking Employees Link" + COMMA_DELIMITER);
	  strReport = strReport + "Clicking Employees Link" + COMMA_DELIMITER;
	  driver.findElement(By.linkText("Employees")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Click View 3rd Column Down" + COMMA_DELIMITER);
	  strReport = strReport + "Click View 3rd Column Down" + COMMA_DELIMITER;
	  driver.findElement(By.xpath("(//a[contains(text(),'View')])[5]")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Clicking Beneficiaries Link" + COMMA_DELIMITER);
	  strReport = strReport + "Clicking Beneficiaries Link" + COMMA_DELIMITER;
	  driver.findElement(By.linkText("Beneficiaries")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Clicking Beneficiaries Button" + COMMA_DELIMITER);
	  strReport = strReport + "Clicking Beneficiaries Button" + COMMA_DELIMITER;
	  driver.findElement(By.xpath("//div[@id='beneficiaries']/table/tbody/tr[2]/td[4]/div/button")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Clicking Edit Button" + COMMA_DELIMITER);
	  strReport = strReport + "Clicking Edit Button" + COMMA_DELIMITER;
	  driver.findElement(By.xpath("(//a[contains(text(),'Edit')])[4]")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Filling out Beneficiary Information" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "Filling out Beneficiary Information" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Beneficiary Full Name" + COMMA_DELIMITER);
	  strReport = strReport + "Beneficiary Full Name" + COMMA_DELIMITER;
	  driver.findElement(By.id("beneficiary_full_name")).clear();
	  driver.findElement(By.id("beneficiary_full_name")).sendKeys("TestName");
	  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "TestName" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "TestName" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Saving Beneficiary" + COMMA_DELIMITER);
	  strReport = strReport + "Saving Beneficiary" + COMMA_DELIMITER;
	  driver.findElement(By.xpath("//div[@id='beneficiaries']/div[2]/div[2]/button")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Terms and Conditions Click" + COMMA_DELIMITER);
	  strReport = strReport + "Terms and Conditions Click" + COMMA_DELIMITER;
	  driver.findElement(By.id("terms_and_conditions")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Changing Effective Date" + COMMA_DELIMITER);
	  strReport = strReport + "Changing Effective Date" + COMMA_DELIMITER;
	  driver.findElement(By.id("plan_administrator_our_plan_members_beneficiary_change_effective_date")).clear();
	  driver.findElement(By.id("plan_administrator_our_plan_members_beneficiary_change_effective_date")).sendKeys("2016-04-07");
	  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "2016-04-07" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "2016-04-07" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Save Button Click" + COMMA_DELIMITER);
	  strReport = strReport + "Save Button Click" + COMMA_DELIMITER;
	  driver.findElement(By.xpath("//input[@name='commit']")).click();
	  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	  fileWriter.append("Edit Beneficiary Test Success");
	  strReport = strReport + "Edit Beneficiary Test Success";
	  fileWriter.flush();
	  fileWriter.close();
}
/**
 * Create a New Employee - 
 * Goes into employee and creates a new employee.
 * @throws Exception
 */
@Test
public void testEmployees() throws Exception {
	strReport = "";
	FileWriter fileWriter = new FileWriter("Reports/CreateEmployeesTest.csv");
	fileWriter.append(FILE_HEADER);
	fileWriter.append(NEW_LINE_SEPARATOR);
	fileWriter.append(FILE_SUB_HEADER + COMMA_DELIMITER + "Input:");
	fileWriter.append(NEW_LINE_SEPARATOR);
	fileWriter.append("Running Resources Page Test");
	fileWriter.append(COMMA_DELIMITER);
	strReport = FILE_HEADER + NEW_LINE_SEPARATOR + FILE_SUB_HEADER + COMMA_DELIMITER + "Input:" + NEW_LINE_SEPARATOR + "Running Create Employees Test: " + COMMA_DELIMITER;
  System.out.println("Run Employees Test  1...............");	
  driver.get(baseUrl + "/plan_administrator/company/3022425/dashboard");
  driver.manage().window().maximize();
  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
  fileWriter.append("Clicking Employees Link" + COMMA_DELIMITER);
  strReport = strReport + "Clicking Employess Link" + COMMA_DELIMITER;
  driver.findElement(By.linkText("Employees")).click();
  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
  fileWriter.append("Clicking New Employee Button" + COMMA_DELIMITER);
  strReport = strReport + "Clicking New Employee Button" + COMMA_DELIMITER;
  driver.findElement(By.linkText("New")).click();
  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
  fileWriter.append("First Name" + COMMA_DELIMITER);
  strReport = strReport + "First Name" + COMMA_DELIMITER;
  driver.findElement(By.id("plan_administrator_our_plan_members_employee_first_name")).clear();
  driver.findElement(By.id("plan_administrator_our_plan_members_employee_first_name")).sendKeys("TestFirstName");
  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "TestFirstName" +NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "TestFirstName" +NEW_LINE_SEPARATOR;
  fileWriter.append("Last Name" + COMMA_DELIMITER);
  strReport = strReport + "Last Name" + COMMA_DELIMITER;
  driver.findElement(By.id("plan_administrator_our_plan_members_employee_last_name")).clear();
  driver.findElement(By.id("plan_administrator_our_plan_members_employee_last_name")).sendKeys("TestLastName");
  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "TestLastName" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "TestLastName" +NEW_LINE_SEPARATOR;
  fileWriter.append("Middle Initial" + COMMA_DELIMITER);
  strReport = strReport + "Middle Initial" + COMMA_DELIMITER;
  driver.findElement(By.id("plan_administrator_our_plan_members_employee_middle_initial")).clear();
  driver.findElement(By.id("plan_administrator_our_plan_members_employee_middle_initial")).sendKeys("T");
  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "T" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "T" +NEW_LINE_SEPARATOR;
  fileWriter.append("Gender" + COMMA_DELIMITER);
  strReport = strReport + "Gender" + COMMA_DELIMITER;
  new Select(driver.findElement(By.id("plan_administrator_our_plan_members_employee_gender"))).selectByVisibleText("Male");
  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "Male" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "Male" +NEW_LINE_SEPARATOR;
  fileWriter.append("Marital Status" + COMMA_DELIMITER);
  strReport = strReport + "Marital Status" + COMMA_DELIMITER;
  new Select(driver.findElement(By.id("plan_administrator_our_plan_members_employee_marital_status"))).selectByVisibleText("Married");
  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "Married" +NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "Married" +NEW_LINE_SEPARATOR;
  fileWriter.append("Birthdate" + COMMA_DELIMITER);
  strReport = strReport + "Birthdate" + COMMA_DELIMITER;
  driver.findElement(By.id("plan_administrator_our_plan_members_employee_birth_date")).clear();
  driver.findElement(By.id("plan_administrator_our_plan_members_employee_birth_date")).sendKeys("1981-05-02");
  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "1981-05-02" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "1981-05-02" +NEW_LINE_SEPARATOR;
  fileWriter.append("E-mail Address" + COMMA_DELIMITER);
  strReport = strReport + "E-mail Address" + COMMA_DELIMITER;
  driver.findElement(By.id("plan_administrator_our_plan_members_employee_email_address")).clear();
  driver.findElement(By.id("plan_administrator_our_plan_members_employee_email_address")).sendKeys("testemail@test.ca");
  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "testemail@test.ca" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "testemail@test.ca" +NEW_LINE_SEPARATOR;
  fileWriter.append("Address" + COMMA_DELIMITER);
  strReport = strReport + "Address" + COMMA_DELIMITER;
  driver.findElement(By.id("plan_administrator_our_plan_members_employee_address_line_one")).clear();
  driver.findElement(By.id("plan_administrator_our_plan_members_employee_address_line_one")).sendKeys("1234 Something St");
  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "1234 Something St" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "1234 Something St" +NEW_LINE_SEPARATOR;
  fileWriter.append("City" + COMMA_DELIMITER);
  strReport = strReport + "City" + COMMA_DELIMITER;
  driver.findElement(By.id("plan_administrator_our_plan_members_employee_address_city")).clear();
  driver.findElement(By.id("plan_administrator_our_plan_members_employee_address_city")).sendKeys("TestCity");
  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "TestCity" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "TestCity" +NEW_LINE_SEPARATOR;
  fileWriter.append("Province" + COMMA_DELIMITER);
  strReport = strReport + "Province" + COMMA_DELIMITER;
  new Select(driver.findElement(By.id("plan_administrator_our_plan_members_employee_address_province"))).selectByVisibleText("Ontario");
  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "Ontario" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "Ontario" +NEW_LINE_SEPARATOR;
  fileWriter.append("Postal Code" + COMMA_DELIMITER);
  strReport = strReport + "Postal Code" + COMMA_DELIMITER;
  driver.findElement(By.id("plan_administrator_our_plan_members_employee_address_postal_code")).clear();
  driver.findElement(By.id("plan_administrator_our_plan_members_employee_address_postal_code")).sendKeys("K7M 7H4");
  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "K7M 7H4" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "K7M 7H4" +NEW_LINE_SEPARATOR;
  fileWriter.append("Group Class" + COMMA_DELIMITER);
  strReport = strReport + "Group Class" + COMMA_DELIMITER;
  new Select(driver.findElement(By.id("plan_administrator_our_plan_members_employee_group_class"))).selectByVisibleText("A - All Employees");
  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "A - All Employees" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "A - All Employees" +NEW_LINE_SEPARATOR;
  fileWriter.append("Occupation" + COMMA_DELIMITER);
  strReport = strReport + "Occupation" + COMMA_DELIMITER;
  driver.findElement(By.id("plan_administrator_our_plan_members_employee_occupation")).clear();
  driver.findElement(By.id("plan_administrator_our_plan_members_employee_occupation")).sendKeys("Tester");
  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "Tester" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "Tester" +NEW_LINE_SEPARATOR;
  fileWriter.append("Employees Earnings" + COMMA_DELIMITER);
  strReport = strReport + "Employees Earnings" + COMMA_DELIMITER;
  driver.findElement(By.id("plan_administrator_our_plan_members_employee_earnings")).clear();
  driver.findElement(By.id("plan_administrator_our_plan_members_employee_earnings")).sendKeys("23.00");
  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "23.00" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "23.00" +NEW_LINE_SEPARATOR;
  fileWriter.append("Employee Earning Frequency" + COMMA_DELIMITER);
  strReport = strReport + "Employee Earning Frequency" + COMMA_DELIMITER;
  new Select(driver.findElement(By.id("plan_administrator_our_plan_members_employee_earnings_frequency"))).selectByVisibleText("Hourly");
  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "Hourly" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "Hourly" +NEW_LINE_SEPARATOR;
  fileWriter.append("Weekly Work Hours" + COMMA_DELIMITER);
  strReport = strReport + "Weekly Work Hours" + COMMA_DELIMITER;
  driver.findElement(By.id("plan_administrator_our_plan_members_employee_weekly_work_hours")).clear();
  driver.findElement(By.id("plan_administrator_our_plan_members_employee_weekly_work_hours")).sendKeys("35");
  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "35" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "35" +NEW_LINE_SEPARATOR;
  fileWriter.append("Employee Employment Date" + COMMA_DELIMITER);
  strReport = strReport + "Employee Employment Date" + COMMA_DELIMITER;
  driver.findElement(By.id("plan_administrator_our_plan_members_employee_employment_date")).clear();
  driver.findElement(By.id("plan_administrator_our_plan_members_employee_employment_date")).sendKeys("2016-05-01");
  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "2016-05-01" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "2016-05-01" +NEW_LINE_SEPARATOR;
  fileWriter.append("Beneficiary Full Name" + COMMA_DELIMITER);
  strReport = strReport + "Beneficiary Full Name" + COMMA_DELIMITER;
  driver.findElement(By.id("beneficiary_full_name")).clear();
  driver.findElement(By.id("beneficiary_full_name")).sendKeys("TestName2");
  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "TestName2" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "TestName2" +NEW_LINE_SEPARATOR;
  fileWriter.append("Beneficiary Relationship Type" + COMMA_DELIMITER);
  strReport = strReport + "Beneficiary Relationship Type" + COMMA_DELIMITER;
  new Select(driver.findElement(By.id("beneficiary_relationship_type"))).selectByVisibleText("Spouse");
  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "Spouse" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "Spouse" +NEW_LINE_SEPARATOR;
  fileWriter.append("Beneficiary Share Percentage" + COMMA_DELIMITER);
  strReport = strReport + "Beneficiary Share Percentage" + COMMA_DELIMITER;
  driver.findElement(By.id("beneficiary_share_percentage")).clear();
  driver.findElement(By.id("beneficiary_share_percentage")).sendKeys("100");
  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "%100" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "%100" +NEW_LINE_SEPARATOR;
  fileWriter.append("Click Save Button" + COMMA_DELIMITER);
  strReport = strReport + "Click Save Button" + COMMA_DELIMITER;
  driver.findElement(By.xpath("//div[@id='beneficiaries']/div[2]/div[2]/button")).click();
  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
  fileWriter.append("Beneficiary Created" + NEW_LINE_SEPARATOR);
  strReport = strReport + "Beneficiary Created" + NEW_LINE_SEPARATOR;
  fileWriter.append("Spouse Dependent Being Created" + COMMA_DELIMITER);
  strReport = strReport + "Spouse Dependent Being Created" + COMMA_DELIMITER;
  driver.findElement(By.xpath("(//button[@name='button'])[6]")).click();
  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
  fileWriter.append("Dependent Firt Name" + COMMA_DELIMITER);
  strReport = strReport + "Dependent First Name" + COMMA_DELIMITER;
  driver.findElement(By.id("dependent_first_name")).clear();
  driver.findElement(By.id("dependent_first_name")).sendKeys("TestWife");
  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "TestWife" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "TestWife" +NEW_LINE_SEPARATOR;
  fileWriter.append("Dependent Last Name" + COMMA_DELIMITER);
  strReport = strReport + "Dependent Last Name" + COMMA_DELIMITER;
  driver.findElement(By.id("dependent_last_name")).clear();
  driver.findElement(By.id("dependent_last_name")).sendKeys("TestLastName");
  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "TestLastName" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "TestLastName" +NEW_LINE_SEPARATOR;
  fileWriter.append("Dependent Gender" + COMMA_DELIMITER);
  strReport = strReport + "Dependent Gender" + COMMA_DELIMITER;
  new Select(driver.findElement(By.id("dependent_gender"))).selectByVisibleText("Female");
  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "Female" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "Female" +NEW_LINE_SEPARATOR;
  fileWriter.append("Dependent Relationship" + COMMA_DELIMITER);
  strReport = strReport + "Dependent Relationship" + COMMA_DELIMITER;
  new Select(driver.findElement(By.id("dependent_relationship"))).selectByVisibleText("Spouse");
  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "Spouse" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "Spouse" +NEW_LINE_SEPARATOR;
  fileWriter.append("Dependent Date of Birth" + COMMA_DELIMITER);
  strReport = strReport + "Dependent Date of Birth" + COMMA_DELIMITER;
  driver.findElement(By.id("dependent_date_of_birth")).clear();
  driver.findElement(By.id("dependent_date_of_birth")).sendKeys("1983-05-02");
  fileWriter.append("SUCCESS" + COMMA_DELIMITER + "1983-05-02" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + COMMA_DELIMITER + "1983-05-02" +NEW_LINE_SEPARATOR;
  fileWriter.append("Dependent Created" + COMMA_DELIMITER);
  strReport = strReport + "Dependent Created" + COMMA_DELIMITER;
  driver.findElement(By.cssSelector("#dependents > div.table-footer.table-form-edit > div.form-actions > button[name=\"button\"]")).click();
  fileWriter.append("SUCCESS" +  NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
  fileWriter.append("Terms and Conditions Clicked" + COMMA_DELIMITER);
  strReport = strReport + "Terms and Conditions Clicked" + COMMA_DELIMITER;
  driver.findElement(By.id("terms_and_conditions")).click();
  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
  fileWriter.append("Save Button Clicked" + COMMA_DELIMITER);
  strReport = strReport + "Save Button Clicked" + COMMA_DELIMITER;
  driver.findElement(By.name("commit")).click();
  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
  fileWriter.append("Employee Added" + COMMA_DELIMITER);
  strReport = strReport + "Employee Added" + COMMA_DELIMITER;
  driver.findElement(By.xpath("//div[@id='modal-employee-beneficiary-designation-notice']/div/div/div[3]/button")).click();
  fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
  strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
  fileWriter.append("Clicking Beneficiaries Link" + NEW_LINE_SEPARATOR);
  strReport = strReport + "Clicking Beneficiaries Link" + NEW_LINE_SEPARATOR;
  fileWriter.append("Create Employee Successful");
  strReport = strReport + "Create Employee Successful";
  
  fileWriter.flush();
  fileWriter.close();
  
}
/**
 * Benefits Learn More - 
 * Goes into Benefits and clicks every "Learn More" button.
 * @throws IOException 
 */
@Test
public void testBenefitsLearnMore() throws IOException{
	try {
		strReport = "";
		FileWriter fileWriter = new FileWriter("Reports/BenefitsLearnMoreTest.csv");
		fileWriter.append(FILE_HEADER);
		fileWriter.append(NEW_LINE_SEPARATOR);
		fileWriter.append(FILE_SUB_HEADER);
		fileWriter.append(NEW_LINE_SEPARATOR);
		fileWriter.append("Running Resources Page Test");
		fileWriter.append(COMMA_DELIMITER);
		strReport = FILE_HEADER + NEW_LINE_SEPARATOR + FILE_SUB_HEADER + NEW_LINE_SEPARATOR + "Running Benefits Learn More Test: " + COMMA_DELIMITER;
		driver.manage().window().maximize();
		fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
		strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
		fileWriter.append("Clicking Benefits Link" + COMMA_DELIMITER);
		strReport = strReport + "Clicking Benefits Link" + COMMA_DELIMITER;
		driver.findElement(By.linkText("Benefits")).click();
		fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
		strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
		fileWriter.append("Learn More Links:" + NEW_LINE_SEPARATOR);
		strReport = strReport + "Learn More Links" + NEW_LINE_SEPARATOR;
		fileWriter.append("Learn More 1" + COMMA_DELIMITER);
		strReport = strReport + "Learn More 1" + COMMA_DELIMITER;
		driver.findElement(By.xpath("(//a[contains(text(),'Learn More')])[1]")).click();
		fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
		strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
		fileWriter.append("Learn More 2" + COMMA_DELIMITER);
		strReport = strReport + "Learn More 2" + COMMA_DELIMITER;
		driver.findElement(By.xpath("(//a[contains(text(),'Learn More')])[2]")).click();
		fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
		strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
		fileWriter.append("Learn More 3" + COMMA_DELIMITER);
		strReport = strReport + "Learn More 3" + COMMA_DELIMITER;
		driver.findElement(By.xpath("(//a[contains(text(),'Learn More')])[3]")).click();
		fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
		strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
		fileWriter.append("Learn More 4" + COMMA_DELIMITER);
		strReport = strReport + "Learn More 4" + COMMA_DELIMITER;
		driver.findElement(By.xpath("(//a[contains(text(),'Learn More')])[4]")).click();
		fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
		strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
		fileWriter.append("Learn More 5" + COMMA_DELIMITER);
		strReport = strReport + "Learn More 5" + COMMA_DELIMITER;
		driver.findElement(By.xpath("(//a[contains(text(),'Learn More')])[5]")).click();
		fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
		strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
		fileWriter.append("Learn More 6" + COMMA_DELIMITER);
		strReport = strReport + "Learn More 6" + COMMA_DELIMITER;
		driver.findElement(By.xpath("(//a[contains(text(),'Learn More')])[6]")).click();
		fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
		strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
		fileWriter.append("Learn More 7" + COMMA_DELIMITER);
		strReport = strReport + "Learn More 7" + COMMA_DELIMITER;
		driver.findElement(By.xpath("(//a[contains(text(),'Learn More')])[7]")).click();
		fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
		strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
		fileWriter.append("Learn More 8" + COMMA_DELIMITER);
		strReport = strReport + "Learn More 8" + COMMA_DELIMITER;
		driver.findElement(By.xpath("(//a[contains(text(),'Learn More')])[8]")).click();
		fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
		strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
		fileWriter.append("Learn More 9" + COMMA_DELIMITER);
		strReport = strReport + "Learn More 9" + COMMA_DELIMITER;
		driver.findElement(By.xpath("(//a[contains(text(),'Learn More')])[9]")).click();
		fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
		strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
		fileWriter.append("Learn More 10" + COMMA_DELIMITER);
		strReport = strReport + "Learn More 10" + COMMA_DELIMITER;
		driver.findElement(By.xpath("(//a[contains(text(),'Learn More')])[10]")).click();
		fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
		strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
		fileWriter.append("Learn More 11" + COMMA_DELIMITER);
		strReport = strReport + "Learn More 11" + COMMA_DELIMITER;
		driver.findElement(By.xpath("(//a[contains(text(),'Learn More')])[11]")).click();
		fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
		strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
		fileWriter.append("Learn More 12" + COMMA_DELIMITER);
		strReport = strReport + "Learn More 12" + COMMA_DELIMITER;
		driver.findElement(By.xpath("(//a[contains(text(),'Learn More')])[12]")).click();
		fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
		strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR ;
		fileWriter.append("Learn More 13" + COMMA_DELIMITER);
		strReport = strReport + "Learn More 13" + COMMA_DELIMITER;
		driver.findElement(By.xpath("(//a[contains(text(),'Learn More')])[13]")).click();
		fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
		strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR ;
		fileWriter.append("Going Home" + COMMA_DELIMITER);
		strReport = strReport + "Going Home" + COMMA_DELIMITER;
		driver.findElement(By.linkText("Home")).click();
		fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
		fileWriter.append("Test Successful" + COMMA_DELIMITER);
		strReport = strReport + "Test Successful" + COMMA_DELIMITER;
		System.out.println("End");
		strReport = strReport + "SUCCESS" ;
		fileWriter.flush();
		fileWriter.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	
}
/**
 * Benefit Details - 
 * Goes into benefits and clicks all "Details" buttons. 
 * @throws Exception
 */
@Test
public void testBenefitsDetails() throws Exception {
	strReport = "";
	FileWriter fileWriter = new FileWriter("Reports/BenefitDetailsTest.csv");
	fileWriter.append(FILE_HEADER);
	fileWriter.append(NEW_LINE_SEPARATOR);
	fileWriter.append(FILE_SUB_HEADER);
	fileWriter.append(NEW_LINE_SEPARATOR);
	fileWriter.append("Running Benefit Details Test");
	fileWriter.append(COMMA_DELIMITER);
	strReport = FILE_HEADER + NEW_LINE_SEPARATOR + FILE_SUB_HEADER + NEW_LINE_SEPARATOR + "Running Benefits Details Test: " + COMMA_DELIMITER;
	System.out.println("RUNNING BENEFITS TEST...................");
	driver.manage().window().maximize();
	fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	fileWriter.append("Clicking Benefits Link" + COMMA_DELIMITER);
	strReport = strReport + "Clicking Benefits Link" + COMMA_DELIMITER;
	driver.findElement(By.linkText("Benefits")).click();
	fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	fileWriter.append("Detail Link 1" + COMMA_DELIMITER);
	strReport = strReport + "Detail Link 1" + COMMA_DELIMITER;
	driver.findElement(By.xpath("//p/a")).click();
	driver.findElement(By.linkText("Back")).click();
	fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	fileWriter.append("Detail Link 2" + COMMA_DELIMITER);
	strReport = strReport + "Detail Link 2" + COMMA_DELIMITER;
	driver.findElement(By.xpath("//div[2]/div/p/a")).click();
	driver.findElement(By.linkText("Back")).click();
	fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	fileWriter.append("Detail Link 3" + COMMA_DELIMITER);
	strReport = strReport + "Detail Link 3" + COMMA_DELIMITER;
	driver.findElement(By.xpath("//div[3]/div/p/a")).click();
	driver.findElement(By.linkText("Back")).click();
	fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	fileWriter.append("Detail Link 4" + COMMA_DELIMITER);
	strReport = strReport + "Detail Link 4" + COMMA_DELIMITER;
	driver.findElement(By.xpath("(//a[contains(text(),'Details')])[4]")).click();
	driver.findElement(By.linkText("Back")).click();
	fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	fileWriter.append("Detail Link 5" + COMMA_DELIMITER);
	strReport = strReport + "Detail Link 5" + COMMA_DELIMITER;
	driver.findElement(By.xpath("//div[@id='benefit_summary']/div[2]/div/div[2]/div/p/a")).click();
	driver.findElement(By.linkText("Back")).click();
	fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	fileWriter.append("Detail Link 6" + COMMA_DELIMITER);
	strReport = strReport + "Detail Link 6" + COMMA_DELIMITER;
	driver.findElement(By.xpath("//div[2]/div/div[3]/div/p/a")).click();
	driver.findElement(By.linkText("Back")).click();
	fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	fileWriter.append("Detail Link 7" + COMMA_DELIMITER);
	strReport = strReport + "Detail Link 7" + COMMA_DELIMITER;
	driver.findElement(By.xpath("(//a[contains(text(),'Details')])[7]")).click();
	driver.findElement(By.linkText("Back")).click();
	fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	fileWriter.append("Detail Link 8" + COMMA_DELIMITER);
	strReport = strReport + "Detail Link 8" + COMMA_DELIMITER;
	driver.findElement(By.xpath("(//a[contains(text(),'Details')])[8]")).click();
	driver.findElement(By.linkText("Back")).click();
	fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	fileWriter.append("Detail Link 9" + COMMA_DELIMITER);
	strReport = strReport + "Detail Link 9" + COMMA_DELIMITER;
	driver.findElement(By.xpath("(//a[contains(text(),'Details')])[9]")).click();
	driver.findElement(By.linkText("Back")).click();
	fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	fileWriter.append("Detail Link 10" + COMMA_DELIMITER);
	strReport = strReport + "Detail Link 10" + COMMA_DELIMITER;
	driver.findElement(By.xpath("(//a[contains(text(),'Details')])[10]")).click();
	driver.findElement(By.linkText("Back")).click();
	fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	fileWriter.append("Detail Link 11" + COMMA_DELIMITER);
	strReport = strReport + "Detail Link 11" + COMMA_DELIMITER;
	driver.findElement(By.xpath("(//a[contains(text(),'Details')])[11]")).click();
	driver.findElement(By.linkText("Back")).click();
	fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	fileWriter.append("Detail Link 12" + COMMA_DELIMITER);
	strReport = strReport + "Detail Link 12" + COMMA_DELIMITER;
	driver.findElement(By.xpath("(//a[contains(text(),'Details')])[12]")).click();
	driver.findElement(By.linkText("Back")).click();
	fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	fileWriter.append("Detail Link 13" + COMMA_DELIMITER);
	strReport = strReport + "Detail Link 13" + COMMA_DELIMITER;
	driver.findElement(By.xpath("(//a[contains(text(),'Details')])[13]")).click();
	driver.findElement(By.linkText("Back")).click();
	fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	fileWriter.append("Detail Link 14" + COMMA_DELIMITER);
	strReport = strReport + "Detail Link 14" + COMMA_DELIMITER;
	driver.findElement(By.xpath("(//a[contains(text(),'Details')])[14]")).click();
	driver.findElement(By.linkText("Back")).click();
	fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
	strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
	fileWriter.append("Benefit Details Test Complete");
	strReport = strReport + "Benefit Details Test Complete";
	fileWriter.flush();
	fileWriter.close();
	
}

/**
 * Home Test - 
 * Goes into home page and tests filters.
 * @throws IOException 
 */
@Test
public void homePageTest() throws IOException{
	try {
		strReport = "";
		FileWriter fileWriter = new FileWriter("Reports/HomePageTest.csv");
		fileWriter.append(FILE_HEADER);
		fileWriter.append(NEW_LINE_SEPARATOR);
		fileWriter.append(FILE_SUB_HEADER);
		fileWriter.append(NEW_LINE_SEPARATOR);
		fileWriter.append("Running Home Page Test");
		fileWriter.append(COMMA_DELIMITER);
		strReport = FILE_HEADER + NEW_LINE_SEPARATOR + FILE_SUB_HEADER + NEW_LINE_SEPARATOR + "Running Home Page Test: " + COMMA_DELIMITER;
		driver.manage().window().maximize();
		fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
		strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
		try {
			fileWriter.append("Platform Tutorial Text Check" + COMMA_DELIMITER);
			strReport = strReport + "Platform Tutorial Text Check" + COMMA_DELIMITER;
			assertEquals("Platform Tutorial", driver.findElement(By.cssSelector("div.widget.widget-platform-tutorial > div.header")).getText());
			fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
			strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
			fileWriter.append("Clicking Activities Button" + COMMA_DELIMITER);
			strReport = strReport + "Clicking Activities Button" + COMMA_DELIMITER;
			driver.findElement(By.linkText("Click here to see all Activities")).click();
			fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
			strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
			fileWriter.append("Testing Filters:" + NEW_LINE_SEPARATOR);
			strReport = strReport + "Testing Filters" + NEW_LINE_SEPARATOR;
			driver.findElement(By.xpath("//table[@id='activity-stream']/thead/tr/th[7]/div/div/button")).click();
			strReport = strReport + "Filter 5" + COMMA_DELIMITER;
			fileWriter.append("Filter 5" + COMMA_DELIMITER);
			driver.findElement(By.linkText("5")).click();
			fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
			strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
			driver.findElement(By.id("apply-filter")).click();
			driver.findElement(By.xpath("//table[@id='activity-stream']/thead/tr/th[7]/div/div/button")).click();
			fileWriter.append("Filter 10" + COMMA_DELIMITER);
			strReport = strReport + "Filter 10" + COMMA_DELIMITER;
			driver.findElement(By.linkText("10")).click();
			fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
			strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
			driver.findElement(By.id("apply-filter")).click();
			driver.findElement(By.xpath("//table[@id='activity-stream']/thead/tr/th[7]/div/div/button")).click();
			fileWriter.append("Filter 25" + COMMA_DELIMITER);
			strReport = strReport + "Filter 25" + COMMA_DELIMITER;
			driver.findElement(By.linkText("25")).click();
			fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
			strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
			driver.findElement(By.id("apply-filter")).click();
			driver.findElement(By.xpath("//table[@id='activity-stream']/thead/tr/th[7]/div/div/button")).click();
			fileWriter.append("Filter 50" + COMMA_DELIMITER);
			strReport = strReport + "Filter 50" + COMMA_DELIMITER;
			driver.findElement(By.linkText("50")).click();
			fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
			strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
			driver.findElement(By.id("apply-filter")).click();
			driver.findElement(By.xpath("//table[@id='activity-stream']/thead/tr/th[7]/div/div/button")).click();
			fileWriter.append("Filter 100" + COMMA_DELIMITER);
			strReport = strReport + "Filter 100" + COMMA_DELIMITER;
			driver.findElement(By.linkText("100")).click();
			fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
			strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
			driver.findElement(By.id("apply-filter")).click();
			fileWriter.append("Filter 'Christine'" + COMMA_DELIMITER);
			strReport = strReport + "Filter 'Christine'" + COMMA_DELIMITER;
			driver.findElement(By.xpath("//table[@id='activity-stream']/thead/tr/th[7]/div/div/button")).click();
			driver.findElement(By.cssSelector("input.form-control.control_size_md")).clear();
			driver.findElement(By.cssSelector("input.form-control.control_size_md")).sendKeys("Christine");
			driver.findElement(By.id("apply-filter")).click();
			fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
			strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
			driver.findElement(By.xpath("//table[@id='activity-stream']/thead/tr/th[7]/div/div/button[2]")).click();
			driver.findElement(By.cssSelector("input.form-control.control_size_md")).clear();
			driver.findElement(By.cssSelector("input.form-control.control_size_md")).sendKeys("");
			driver.findElement(By.id("apply-filter")).click();
			fileWriter.append("Filter 'Rasmussen'" + COMMA_DELIMITER);
			strReport = strReport + "Filter 'Rasmussen'" + COMMA_DELIMITER;
			driver.findElement(By.xpath("//table[@id='activity-stream']/thead/tr/th[7]/div/div/button")).click();
			driver.findElement(By.cssSelector("#filter-employee_last_name > input.form-control.control_size_md")).clear();
			driver.findElement(By.cssSelector("#filter-employee_last_name > input.form-control.control_size_md")).sendKeys("Rasmussen");
			driver.findElement(By.id("apply-filter")).click();
			fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
			strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
			driver.findElement(By.xpath("//table[@id='activity-stream']/thead/tr/th[7]/div/div/button[2]")).click();
			driver.findElement(By.cssSelector("#filter-employee_last_name > input.form-control.control_size_md")).clear();
			driver.findElement(By.cssSelector("#filter-employee_last_name > input.form-control.control_size_md")).sendKeys("");
			driver.findElement(By.id("apply-filter")).click();
			fileWriter.append("Home Link Click" + COMMA_DELIMITER);
			strReport = strReport + "Home Link Click" + COMMA_DELIMITER;
			driver.findElement(By.linkText("Home")).click();
			fileWriter.append("SUCCESS" + NEW_LINE_SEPARATOR);
			strReport = strReport + "SUCCESS" + NEW_LINE_SEPARATOR;
			fileWriter.append("TEST FINISHED");
			strReport = strReport + "TEST FINISHED";
	        fileWriter.flush();
	        fileWriter.close();
	      } catch (Error e) {
	      		verificationErrors.append(e.toString());
	      		fileWriter.append("FAILURE" + NEW_LINE_SEPARATOR + " ERROR!!!");
	      		fileWriter.flush();
		        fileWriter.close();
	      	}
		} catch (FileNotFoundException e1) {
			  e1.printStackTrace();
			  
		  	}
  }
/**
 * My Account Test - 
 * Goes into my account using the parameter account-email and creates a new account holder.
 * Account holders cannot be terminated once created. 
 * @throws Exception
 */
@Test 
public void myAccountPageTest() throws Exception {
	//TODO Create Report
	    System.out.println("ACCOUNT PAGE TEST:........................");
	    driver.manage().window().maximize();
	  	System.out.println("Clicking My Account Link");
	    driver.findElement(By.linkText("My Account")).click();
	    System.out.println("Editing Account");
	    driver.findElement(By.linkText("Edit")).click();
	    driver.findElement(By.id("plan_administrator_my_account_company_profile_admin_contact_full_name")).clear();
	    driver.findElement(By.id("plan_administrator_my_account_company_profile_admin_contact_full_name")).sendKeys("Miss. Christine Rasmussen");
	    System.out.println("Saving Edit");
	    driver.findElement(By.name("commit")).click();
	    try {
	      System.out.println("Checking Edit if Change happened");
	      assertEquals("Miss. Christine Rasmussen", driver.findElement(By.xpath("//div[@id='container']/div[3]/div[2]/fieldset/div/div/div[2]/div")).getText());
	    } catch (Error e) {
	      System.out.println("Edit Fail");
	      verificationErrors.append(e.toString());
	    }
	    System.out.println("Edit Back to Original");
	    driver.findElement(By.linkText("Edit")).click();
	    driver.findElement(By.id("plan_administrator_my_account_company_profile_admin_contact_full_name")).clear();
	    driver.findElement(By.id("plan_administrator_my_account_company_profile_admin_contact_full_name")).sendKeys("Ms. Christine Rasmussen");
	    System.out.println("Saving second Edit");
	    driver.findElement(By.name("commit")).click();
	    try {
	      System.out.println("Confirming Edit is Successful");
	      assertEquals("Ms. Christine Rasmussen", driver.findElement(By.xpath("//div[@id='container']/div[3]/div[2]/fieldset/div/div/div[2]/div")).getText());
	    } catch (Error e) {
	      System.out.println("Second edit Fail");
	      verificationErrors.append(e.toString());
	    }
	    
	    System.out.println("Clicking Administrative Assistants Link");
	    driver.findElement(By.linkText("Administrative Assistants")).click();
	    System.out.println("Clicking New Button");
	    driver.findElement(By.linkText("New")).click();
	    System.out.println("First Name Enter");
	    driver.findElement(By.id("plan_administrator_my_account_administrative_assistant_first_name")).clear();
	    driver.findElement(By.id("plan_administrator_my_account_administrative_assistant_first_name")).sendKeys("TestAssist");
	    System.out.println("Last Name Enter");
	    driver.findElement(By.id("plan_administrator_my_account_administrative_assistant_last_name")).clear();
	    driver.findElement(By.id("plan_administrator_my_account_administrative_assistant_last_name")).sendKeys("TestAssist");
	    System.out.println("Email Enter");
	    driver.findElement(By.id("plan_administrator_my_account_administrative_assistant_email_address")).clear();
	    driver.findElement(By.id("plan_administrator_my_account_administrative_assistant_email_address")).sendKeys(strAccountEmail);
	    System.out.println("Area Code Enter");
	    driver.findElement(By.id("plan_administrator_my_account_administrative_assistant_phone_area_code")).clear();
	    driver.findElement(By.id("plan_administrator_my_account_administrative_assistant_phone_area_code")).sendKeys("613");
	    System.out.println("Phone Number Enter");
	    driver.findElement(By.id("plan_administrator_my_account_administrative_assistant_phone_number")).clear();
	    driver.findElement(By.id("plan_administrator_my_account_administrative_assistant_phone_number")).sendKeys("5555555");
	    System.out.println("Phone Number Extension Enter");
	    driver.findElement(By.id("plan_administrator_my_account_administrative_assistant_phone_extension")).clear();
	    driver.findElement(By.id("plan_administrator_my_account_administrative_assistant_phone_extension")).sendKeys("1");
	    System.out.println("Switch");
	    driver.findElement(By.cssSelector("span.bootstrap-switch-handle-on.bootstrap-switch-primary")).click();
	    System.out.println("Save Button Click");
	    driver.findElement(By.name("commit")).click();
	    System.out.println("Viewing Change");
	    driver.findElement(By.xpath("//div[@id='container']/div[3]/div[2]/table/tbody/tr[2]/td[3]/div/button")).click();
	    driver.findElement(By.xpath("//div[@id='container']/div[3]/div[2]/table/tbody/tr/td[3]/div/button")).click();
	    driver.findElement(By.cssSelector("div.btn-group.open > ul.dropdown-menu > li > a")).click();
	    System.out.println("Going Back to Account Page");
	    driver.findElement(By.linkText("Back")).click();
	    System.out.println("View original");
	    driver.findElement(By.xpath("//div[@id='container']/div[3]/div[2]/table/tbody/tr[2]/td[3]/div/button")).click();
	    driver.findElement(By.cssSelector("div.btn-group.open > ul.dropdown-menu > li > a")).click();
	    System.out.println("Going Back to Account Page");
	    driver.findElement(By.linkText("Back")).click();
	    System.out.println("User Prefences Page");
	    driver.findElement(By.xpath("(//a[contains(text(),'User Preferences')])[2]")).click();
	    System.out.println("Entering Incorrect Password");
	    driver.findElement(By.id("user_preference_confirm_current_password")).clear();
	    driver.findElement(By.id("user_preference_confirm_current_password")).sendKeys("NomadUAT");
	    System.out.println("Entering New Password");
	    driver.findElement(By.id("user_preference_new_password")).clear();
	    driver.findElement(By.id("user_preference_new_password")).sendKeys("NomadUAT!1");
	    System.out.println("Entering Confirmed New Password");
	    driver.findElement(By.id("user_preference_confirm_password")).clear();
	    driver.findElement(By.id("user_preference_confirm_password")).sendKeys("NomadUAT!1");
	    System.out.println("Selecting Question");
	    new Select(driver.findElement(By.id("user_preference_security_question"))).selectByVisibleText("What is your mother's maiden name?");
	    new Select(driver.findElement(By.id("user_preference_security_question"))).selectByVisibleText("What is the name of your family pet?");
	    System.out.println("Creating Answer");
	    driver.findElement(By.id("user_preference_security_answer")).clear();
	    driver.findElement(By.id("user_preference_security_answer")).sendKeys("moj");
	    driver.findElement(By.id("user_preference_security_answer")).clear();
	    driver.findElement(By.id("user_preference_security_answer")).sendKeys("mojo");
	    System.out.println("Save");
	    driver.findElement(By.name("commit")).click();
	    System.out.println("Back to Home");
	    driver.findElement(By.linkText("Home")).click();
	    System.out.println("Back to My Account Page");
	    driver.findElement(By.linkText("My Account")).click();
	    System.out.println("Back to Preferences");
	    driver.findElement(By.xpath("(//a[contains(text(),'User Preferences')])[2]")).click();
	    System.out.println("Enter Correct Password");
	    driver.findElement(By.id("user_preference_confirm_current_password")).clear();
	    driver.findElement(By.id("user_preference_confirm_current_password")).sendKeys("NomadUAT!1");
	    System.out.println("Change Password");
	    driver.findElement(By.id("user_preference_new_password")).clear();
	    driver.findElement(By.id("user_preference_new_password")).sendKeys("NomadUAT!1");
	    System.out.println("Confirm New Password");
	    driver.findElement(By.id("user_preference_confirm_password")).clear();
	    driver.findElement(By.id("user_preference_confirm_password")).sendKeys("NomadUAT!1");
	    System.out.println("Save");
	    driver.findElement(By.name("commit")).click();
	    System.out.println("Home");
	    driver.findElement(By.linkText("Home")).click();
	    System.out.println("End");
  }

  /**
   * Tear Down - 
   * When test is successful Tear Down exits the browser. 
   * @throws Exception
   */
  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
