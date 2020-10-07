package solar_Battery_Charger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.NgWebDriver;




public class loginTest{
	
	@Test(dataProvider="LoginData")
	public void LoginTest(String name,String Pass,String bool) throws InterruptedException
	{
		System.out.println(name);
		System.out.println(Pass);
		
		boolean isValid = Boolean.valueOf(bool);
		
		System.setProperty("webdriver.gecko.driver","C:\\Users\\ADMIN\\Tenxer\\AutoTesting\\Selenium_Jar_Files\\geckodriver.exe");
		WebDriver driver=new FirefoxDriver();
		JavascriptExecutor jsDriver=(JavascriptExecutor) driver;
		NgWebDriver ngDriver=new NgWebDriver(jsDriver);
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		String pageurl = "https://renesas.evmlabs.com/#!/";
		driver.get(pageurl);
		ngDriver.waitForAngularRequestsToFinish();
		
		//Login Page
		driver.manage().window().maximize();
		driver.findElement(ByAngular.model("username")).sendKeys(name);
		driver.findElement(ByAngular.model("password")).sendKeys(Pass);
		driver.findElement(ByAngular.buttonText("Login")).click();
		Thread.sleep(4000);
		
		String ExpectedTitle="Tenxer - Login";
		String ActualTitle = driver.getTitle();
		System.out.println("title" + ActualTitle);
		/*wait.until(ExpectedConditions.elementToBeClickable(ByAngular.repeater("form in Forms")));
		if(driver.findElement(ByAngular.repeater("form in Forms")) != null)
		{
			System.out.println("1");
		}
		else
		{
			System.out.println("0");
		}*/
		//Alert alert = driver.switchTo().alert();
		//System.out.println(alert.getText();
		if(isValid)
		{
			System.out.println("success");
			Assert.assertNotEquals(ExpectedTitle, ActualTitle);
			
		}
		else
		{
			Assert.assertEquals(ExpectedTitle, ActualTitle);
		}
		driver.quit();
	}
	
	
	@DataProvider(name="LoginData")
	public Object[][] UsernamAndPassword() throws IOException
	{
		File loginData=new File("C:\\Users\\ADMIN\\Tenxer\\loginData.xlsx");
		FileInputStream fis=new FileInputStream(loginData);
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet FirstSheet= wb.getSheetAt(0);
		
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
		return data;
	}
	
}
