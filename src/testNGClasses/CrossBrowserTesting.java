package testNGClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrossBrowserTesting {

	WebDriver driver;

	@BeforeTest
	@Parameters("browser")
	public void openBrowser(String browser) {
		if (browser.equals("Chrome")) {

			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browser.equals("Internet Explorer")) {

			System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
			driver = new InternetExplorerDriver();

		} else if (browser.equals("Edge")) {

			System.setProperty("webdriver.edge.driver", "MicrosoftWebDriver.exe");
			driver = new EdgeDriver();

		}
		driver.get("https://www.facebook.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(priority = 1)

	public void verifyFBTitle() {

		String exptitle = "Facebook – log in or sign up";
		String acttitle = driver.getTitle();
		Assert.assertEquals(acttitle, exptitle);
	}

	@Test(priority = 2)

	public void createAccountText() {

		Boolean acc = driver.getPageSource().contains("Create an account");
		Assert.assertTrue(acc);
	}

	@Test(priority = 3)
	public void welcomeMessage() {
		String expmessage = "Facebook helps you connect and share with the people in your life.";
		String actmessage = driver
				.findElement(By.cssSelector("#content > div > div > div > div > div.lfloat._ohe > div > div"))
				.getText();
		Assert.assertEquals(expmessage, actmessage);

	}

	@Test(priority = 4)
	public void loginButton() {

		Boolean btn = driver.findElement(By.id("loginbutton")).isEnabled();
		Assert.assertTrue(btn);
	}

	@Test(priority = 5)
	public void femaleRadioButton() {

		Boolean femalebutton = driver.findElement(By.id("u_0_9")).isSelected();
		Assert.assertFalse(femalebutton);
	}

	@Test(priority = 6)
	public void logo() {

		Boolean logo = driver.findElement(By.cssSelector("#pageFooter > div.mvl.copyright > div > span")).isDisplayed();
		Assert.assertTrue(logo);
	}

	/*
	 * @AfterTest
	 * 
	 * public void closeBrowser() {
	 * 
	 * driver.close(); }
	 */
}
