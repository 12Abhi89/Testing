package solar_Battery_Charger;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.NgWebDriver;

public class SolarBatteryCharger {
	
	
	public static void main(String[] args) throws InterruptedException, IOException {
		   
		
		
		//loginTest obj=new loginTest();
		
		//obj.UsernamAndPassword();
		
		
		
		
		
		
		
		
			// TODO Auto-generated method stub
			System.setProperty("webdriver.gecko.driver","C:\\Users\\ADMIN\\Tenxer\\AutoTesting\\Selenium_Jar_Files\\geckodriver.exe");
			WebDriver driver=new FirefoxDriver();
			JavascriptExecutor jsDriver=(JavascriptExecutor) driver;
			NgWebDriver ngDriver=new NgWebDriver(jsDriver);
			WebDriverWait wait = new WebDriverWait(driver,60);
			
			String pageurl = "https://renesas.evmlabs.com/#!/";
			driver.get(pageurl);
			ngDriver.waitForAngularRequestsToFinish();
			
			//Login Page
			driver.manage().window().maximize();
			driver.findElement(ByAngular.model("username")).sendKeys("abhishek@tenxertech.com");
			driver.findElement(ByAngular.model("password")).sendKeys("4KSVHCgxc6p7dV2");
			driver.findElement(ByAngular.buttonText("Login")).click();
			//Thread.sleep(10000);
			
			//EVM Selecting page
			wait.until(ExpectedConditions.elementToBeClickable(ByAngular.repeater("form in Forms")));
			driver.findElement(ByAngular.repeater("form in Forms")).findElement(By.className("material-icons")).click();
			
			
			//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
			//ISL81601-US011REFZ- Solar Battery Charger Page
			Thread.sleep(13000);
			driver.switchTo().frame(0);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/div/div/div/nav/div/button/div/i")));
			driver.findElement(By.xpath("//html/body/div/div/div/nav/div/button[@class='min-max-toggle btn btn--icon' and @aria-label='minimize chat window toggle']")).click();
			driver.switchTo().parentFrame();
			driver.findElement(By.xpath("//button[@class='wmClose notop']")).click();
			
			//drop down
			

			//driver.findElement(By.xpath(xpathExpression));
			List<WebElement> dropdown=driver.findElements(ByAngular.model("tnxmodel"));
			
			//Voc
			dropdown.get(0).click();
			Thread.sleep(2000);
			Select Voc=new Select(dropdown.get(0));
			Voc.selectByVisibleText("21 V");
			
			//Isc
			dropdown.get(1).click();
			Thread.sleep(2000);
			Select Isc=new Select(dropdown.get(1));
			Isc.selectByVisibleText("3 A");
			
			//Irradiance
			dropdown.get(2).click();
			Thread.sleep(2000);
			Select Irradiance=new Select(dropdown.get(2));
			Irradiance.selectByVisibleText("75 %");
			
			//Temp
			dropdown.get(3).click();
			Thread.sleep(2000);
			Select Temp=new Select(dropdown.get(3));
			Temp.selectByVisibleText("322");
			
			driver.findElement(By.xpath(".//button[@ng-class=\"getComToArr(data.class)\" and @ng-click=\"submitAll($event,data.allattrib)\" and @class=\"btn btn-primary btn-element  fat-btn\"]")).click();
			
			/*
			//Voc
			driver.findElement(ByAngular.model("tnxmodel")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//option[@ng-repeat=\"num in data | numtoarr track by $index\" and @value=30]")));
			driver.findElement(By.xpath(".//option[@ng-repeat=\"num in data | numtoarr track by $index\" and @value=30]")).click();
		    */
			//Thread.sleep(25000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//label[@ng-click=\"disconnectBlaze()\"]/input[@type=\"checkbox\"]")));
		    //driver.findElement(By.xpath(".//label[@ng-click=\"disconnectBlaze()\"]/input[@type=\"checkbox\"]")).click();
			
			System.out.println("jgh");
			
		}
			}


