package solar_Battery_Charger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.NgWebDriver;




public class loginTest{
	
	protected WebDriver driver;
	protected JavascriptExecutor jsDriver;
	protected NgWebDriver ngDriver;
	protected WebDriverWait wait;
	protected String pageurl = "https://renesas.evmlabs.com/#!/";
	
	@BeforeMethod
	public void setup()
	{
		System.setProperty("webdriver.gecko.driver","C:\\Users\\ADMIN\\Tenxer\\AutoTesting\\Selenium_Jar_Files\\geckodriver.exe");
		driver=new FirefoxDriver();
		jsDriver=(JavascriptExecutor) driver;
		ngDriver=new NgWebDriver(jsDriver);
		wait = new WebDriverWait(driver,30);
		
		
		driver.get(pageurl);
		ngDriver.waitForAngularRequestsToFinish();
	}
	
	@AfterMethod
	public void destroy()
	{
		driver.quit();
	}
	
	@Test(dataProvider="LoginData")
	public void LoginTest(String name,String Pass,String bool) throws InterruptedException
	{
		
		boolean isValid = Boolean.valueOf(bool);
		
		
		
		//Login Page
		driver.manage().window().maximize();
		driver.findElement(ByAngular.model("username")).sendKeys(name);
		driver.findElement(ByAngular.model("password")).sendKeys(Pass);
		driver.findElement(ByAngular.buttonText("Login")).click();
		Thread.sleep(4000);
		
		//Collecting page title
		String ExpectedTitle="Tenxer - Login";//login page title
		String ActualTitle = driver.getTitle();//current page title
		System.out.println("title" + ActualTitle);
		
		
		if(isValid)
		{
			Assert.assertNotEquals(ExpectedTitle, ActualTitle);//login Successfully
			
		}
		else
		{
			Assert.assertEquals(ExpectedTitle, ActualTitle);//login unsuccessfully
		}
	}
	
	
	@DataProvider(name="LoginData")
	public Object[][] UsernamAndPassword() throws IOException
	{
		File loginData=new File("C:\\Users\\ADMIN\\Tenxer\\TestingData\\loginData.xlsx");
		FileInputStream fis=new FileInputStream(loginData);
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		XSSFSheet FirstSheet= workbook.getSheetAt(0);
		
		int row=FirstSheet.getPhysicalNumberOfRows();
		int col =FirstSheet.getRow(0).getPhysicalNumberOfCells();
		
		Object[][] data=new Object[row][col];
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				data[i][j]=FirstSheet.getRow(i).getCell(j).toString();
			}
		}
		workbook.close();
		return data;
	}
	
}
