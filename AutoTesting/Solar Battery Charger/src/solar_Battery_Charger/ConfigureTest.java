package solar_Battery_Charger;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.ByAngularRepeater;
import com.paulhammant.ngwebdriver.NgWebDriver;

public class ConfigureTest {
	
	protected WebDriver driver;
	protected JavascriptExecutor jsDriver;
	protected NgWebDriver ngDriver;
	protected String pageurl = "https://renesas.evmlabs.com/#!/";
	protected WebDriverWait wait;
	
	@BeforeMethod
	public void setup()
	{
		System.setProperty("webdriver.gecko.driver","C:\\Users\\ADMIN\\Tenxer\\AutoTesting\\Selenium_Jar_Files\\geckodriver.exe");
		driver=new FirefoxDriver();
		jsDriver=(JavascriptExecutor) driver;
		ngDriver=new NgWebDriver(jsDriver);
		wait = new WebDriverWait(driver,60);
		
		
		driver.get(pageurl);
		ngDriver.waitForAngularRequestsToFinish();
	}
	
	@AfterMethod
	public void destroy()
	{
		//driver.quit();
	}
	
	@Test()
	public void configTest() throws InterruptedException {
		
		
		//Login Page
		driver.manage().window().maximize();
		driver.findElement(ByAngular.model("username")).sendKeys("abhishek@tenxertech.com");
		driver.findElement(ByAngular.model("password")).sendKeys("4KSVHCgxc6p7dV2");
		driver.findElement(ByAngular.buttonText("Login")).click();
		
		//EVM Selecting page
		wait.until(ExpectedConditions.elementToBeClickable(ByAngular.repeater("form in Forms")));
		driver.findElement(ByAngular.repeater("form in Forms")).findElement(By.className("material-icons")).click();
		
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		//ISL81601-US011REFZ- Solar Battery Charger Page
		
		//Switch to Eva chat bot
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		
		//waits to load Eva Chat bot
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/div/div/div/nav/div/button[@class='min-max-toggle btn btn--icon' and @aria-label='minimize chat window toggle']")));
		Thread.sleep(1000);
		
		//minimizes Eva Chat bot
		driver.findElement(By.xpath("//html/body/div/div/div/nav/div/button[@class='min-max-toggle btn btn--icon' and @aria-label='minimize chat window toggle']")).click();
		
		//switch to parrent frame
		driver.switchTo().parentFrame();
		try
		{
		//Minimizes user guide pop up
		driver.findElement(By.xpath("//button[@class='wmClose notop']")).click();
		}catch(Exception E)
		{
			//sometime user guide won't popup
		}
		
		//input configure value
		List<WebElement> dropdown=driver.findElements(ByAngular.model("tnxmodel"));
		
		//Voc
		dropdown.get(0).click();
		Thread.sleep(1000);
		Select Voc=new Select(dropdown.get(0));
		Voc.selectByVisibleText("21 V");
		
		//Isc
		dropdown.get(1).click();
		Thread.sleep(1000);
		Select Isc=new Select(dropdown.get(1));
		Isc.selectByVisibleText("3 A");
		
		//Irradiance
		dropdown.get(2).click();
		Thread.sleep(1000);
		Select Irradiance=new Select(dropdown.get(2));
		Irradiance.selectByVisibleText("75 %");
		
		//Temp
		dropdown.get(3).click();
		Thread.sleep(1000);
		Select Temp=new Select(dropdown.get(3));
		Temp.selectByVisibleText("322");
		
		
		//press the configure button
		driver.findElement(By.xpath(".//button[@ng-class=\"getComToArr(data.class)\" and @ng-click=\"submitAll($event,data.allattrib)\" and @class=\"btn btn-primary btn-element  fat-btn\"]")).click();
		
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//li[@class=\"nav-item\"]/div[@class=\"nav-link active\"]/span[@class=\"ng-scope\"]")));
		
		Boolean Status=true;
		String ActualString="";
		
		//waits till top right status bar show Ready
		wait.until(ExpectedConditions.textToBe(By.xpath(".//li[@class=\"nav-item\"]/div[@class=\"nav-link active\"]/span[@class=\"ng-scope\"]"), ". Ready"));
		
		//waits till system status is visible in page
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ByAngular.repeater("value in outputTrans track by $index")));
		
		//prints System status data
		List<WebElement> z=driver.findElements(ByAngular.repeater("value in outputTrans track by $index"));
		for(WebElement x : z)
		{
			System.out.println("==========================================\n"+x.getText());
		}
		
		
		//System.out.println("st ="+ st);
		/*WebElement i=driver.findElement(By.xpath("//div[@ng-if=\"options\" and @class=\"ng-scope\"]"));
		System.out.println("gh");
		wait.until(ExpectedConditions.invisibilityOf(i));
		System.out.println("hg");
		if(!i.getText().isEmpty())
		{
			System.out.println("Graph Exist");
		}
		else
		{
			System.out.println("Graph Doesn't Exist");
		}
		*/
	}

}
