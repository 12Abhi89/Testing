package solar_Battery_Charger;

import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.paulhammant.ngwebdriver.ByAngular;
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
		driver.manage().window().maximize();
		ngDriver.waitForAngularRequestsToFinish();
	}
	
	@AfterMethod
	public void destroy()
	{
		//driver.quit();
	}
	
	public Float[] SystemStatus()
	{
		//waits till system status is visible in page
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ByAngular.repeater("value in outputTrans track by $index")));
				
				//prints System status data
				//String obj;
				
				String[] obj,obj2;
				List<WebElement> SystemStatus=driver.findElements(ByAngular.repeater("value in outputTrans track by $index"));
				Float[] values= new Float[SystemStatus.size()];
				int i=0;
				for(WebElement x : SystemStatus)
				{
					obj=x.getText().split("\n");
					obj2=obj[1].split(" ");
					if(obj2[0].contains("%"))
					{
						//obj2[0].substring(0, (obj2[0].length())-2);
						//System.out.println("sub "+obj2[0].substring(0, (obj2[0].length())-1));
						values[i] = Float.parseFloat(obj2[0].substring(0, (obj2[0].length())-1));
						
						
					}
					else
					{
						values[i] = Float.parseFloat(obj2[0]);
					}
					i++;
				}
				return values;

	}
	
	public String[] Console()
	{
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ByAngular.repeater("op in outputTrans track by $index")));
		List<WebElement> Console=driver.findElements(ByAngular.repeater("op in outputTrans track by $index"));
		String[] con=new String[Console.size()];
		int i=0;
		for(WebElement c:Console)
		{
			//System.out.println("##############################\n"+c.getText());
			con[i]=c.getText().toString();
			i++;
		}
		return con;
	}
	
	@Test()
	public void configTest() throws InterruptedException {
		
		
		//Login Page
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
	
		//Waits Till User guide close button is available
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='wmClose notop']")));
		//Minimizes user guide pop up
		driver.findElement(By.xpath("//button[@class='wmClose notop']")).click();
		
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
		Irradiance.selectByVisibleText("100 %");
		
		//Temp
		dropdown.get(3).click();
		Thread.sleep(1000);
		Select Temp=new Select(dropdown.get(3));
		Temp.selectByVisibleText("322");
		
		
		//press the configure button
		driver.findElement(By.xpath(".//button[@ng-class=\"getComToArr(data.class)\" and @ng-click=\"submitAll($event,data.allattrib)\" and @class=\"btn btn-primary btn-element  fat-btn\"]")).click();
		
		//waits till top right status bar show Ready
		wait.until(ExpectedConditions.textToBe(By.xpath(".//li[@class=\"nav-item\"]/div[@class=\"nav-link active\"]/span[@class=\"ng-scope\"]"), ". Ready"));
		
		//waits till system status is visible in page
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ByAngular.repeater("value in outputTrans track by $index")));
		
		//print Rounded values of System status
		Float[] SystemData;
		SystemData=SystemStatus();
		for(float i:SystemData)
		{
			System.out.println("=====================================\n"+ Math.round(i));
		}
		
		//Console data is displayed
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ByAngular.repeater("op in outputTrans track by $index")));
		String[] console;
		console=Console();
		for(String i:console)
		{
			System.out.println("----------------------------------------\n"+i);
		}
		//System.out.println("c1="+console[console.length-3]);
		Assert.assertEquals(console[console.length-3], "Solar Panel Configuration Complete");
		
		
		
		
		//wait until MPPT Button is available to press
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class=\"btn btn-primary btn-element  fat-btn\" and @ng-click=\"formsubmit($event,data.allattrib)\"]")));
		
		//This list contains MPPT button and Battery Discharge button
		List<WebElement> MPPTandBattery=driver.findElements(By.xpath("//button[@class=\"btn btn-primary btn-element  fat-btn\" and @ng-click=\"formsubmit($event,data.allattrib)\"]"));
		System.out.print("\nMPPT= "+MPPTandBattery.size());
		
		
				
		//MPPT On--------------------------------------------------------------------------
		
		//MPPT Button will be pressed
		MPPTandBattery.get(0).click();
		
		//Condition 1
		//waits till top right status bar show Ready
		wait.until(ExpectedConditions.textToBe(By.xpath(".//li[@class=\"nav-item\"]/div[@class=\"nav-link active\"]/span[@class=\"ng-scope\"]"), ". Ready"));
		
		//Press Battery Discharge Button
		//MPPTandBattery.get(1).click();
		
		Thread.sleep(5000);
		
		//Condition 2
		//MPPT Button Status
		String ActualMPPTStatus,ExpectedMPPTStatus="MPPT ON";
		ActualMPPTStatus=driver.findElement(By.id("tab_12_21_41")).findElement(By.xpath(".//div[@ng-if=\"value.label\" and @class=\"led-label ng-binding ng-scope\" and @ng-bind-html=\"value.label | newlines\"]")).getText();
		System.out.println("\nMPPTStatus= " + ActualMPPTStatus);
		Assert.assertEquals(ActualMPPTStatus, ExpectedMPPTStatus);
		
		//Condition 3
		//MPPT Status in console
		String ActualConsoleMPPTStatus="";
		String ExpectedConsoleMPPTStatus="MPPT is ON";
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ByAngular.repeater("op in outputTrans track by $index")));
		console=Console();
		Assert.assertEquals(ActualConsoleMPPTStatus,console[console.length-1]);
		
		//End MPPT On--------------------------------------------------------------------------------------
		
		
		//MPPT off
		//MPPTandBattery.get(0).click();
		//Press Battery Discharge Button
		//MPPTandBattery.get(1).click();
		
		
		System.out.println("\n-----------------------------------------\n");
		
	}

}
