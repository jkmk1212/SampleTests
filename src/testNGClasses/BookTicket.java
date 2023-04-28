package testNGClasses;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BookTicket {

	WebDriver driver;

	private void screenShot() throws IOException {

		File ss = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(ss,
				new File("D:\\Selenium\\TechMasters\\ScreenShots\\BookTicket" + new Random().nextInt() + ".jpg"));
	}

	@BeforeTest

	public void login() {
		driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver.get("http://newtours.demoaut.com/mercurysignon.php");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test(priority = 1)
	public void bookTicket() throws IOException {
		driver.findElement(By.name("userName")).sendKeys("mercury");
		driver.findElement(By.name("password")).sendKeys("mercury");
		screenShot();
		driver.findElement(By.name("login")).click();
		driver.findElement(By.cssSelector(
				"body > div > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > b > font > input[type=radio]:nth-child(2)"))
				.click();
		driver.findElement(By.cssSelector(
				"body > div > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > form > table > tbody > tr:nth-child(9) > td:nth-child(2) > font > font > input[type=radio]:nth-child(2)"))
				.click();
		WebElement airline = driver.findElement(By.name("airline"));
		Select drpdwn = new Select(airline);
		drpdwn.selectByIndex(1);
		screenShot();
		driver.findElement(By.name("findFlights")).click();

		driver.findElement(By.cssSelector(
				"body > div > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > form > table:nth-child(9) > tbody > tr:nth-child(5) > td.frame_action_xrows > input[type=radio]"))
				.click();
		driver.findElement(By.cssSelector(
				"body > div > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > form > table:nth-child(10) > tbody > tr:nth-child(9) > td.frame_action_xrows > input[type=radio]"))
				.click();
		screenShot();
		driver.findElement(By.name("reserveFlights")).click();
		driver.findElement(By.name("passFirst0")).sendKeys("JK");
		driver.findElement(By.name("passLast0")).sendKeys("MK");
		driver.findElement(By.name("creditnumber")).sendKeys("1234567890");
		screenShot();
		driver.findElement(By.name("buyFlights")).click();

		String actmsg = driver.findElement(By.cssSelector(
				"body > div > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr:nth-child(1) > td:nth-child(2) > table > tbody > tr:nth-child(3) > td > p > font > b > font:nth-child(2)"))
				.getText();
		String expmsg = "Your itinerary has been booked!";
		screenShot();
		Assert.assertEquals(actmsg, expmsg);

	}

}
