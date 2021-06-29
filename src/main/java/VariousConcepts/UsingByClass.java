package VariousConcepts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UsingByClass {
	WebDriver driver;
	String browser = null;
	String url;

	@BeforeClass
	public void readconfig() {
		Properties prop = new Properties();
		try {
			InputStream input = new FileInputStream(".\\src\\main\\java\\config.properties");
			prop.load(input);
			browser = prop.getProperty("browser");
			System.out.println("Browser Used " + browser);
			url = prop.getProperty("url");
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	@BeforeMethod
	public void init() {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.get(url);
	}

	@Test(priority = 1)
	public void addCustomer() {
		Assert.assertEquals(driver.getTitle(), "Login - iBilling");

		// Login Data
		String LoginID = "demo@techfios.com";
		String Password = "abc123";
		String FullName = "Techfios";
		String Email = "techfios@techfios.com";
		String Phone = "5258956";
		String address = "152 Techfios Rd";
		String State = "TX";
		String Zip = "56856";
		String Country = "United States";
		String company = "BIG FEET RUNNIN";

		By UserName = By.id("username");
		By password = (By.id("password"));
		By Login = (By.name("login"));
		By Dashboard = (By.xpath("//h2[contains(text(),' Dashboard ')]"));
		By Customers = (By.xpath("//span[contains(text(),'Customers')]"));
		By AddCustomer = (By.xpath("//a[contains(text(),'Add Customer')]"));
		By fullname = (By.xpath("//input[@id='account']"));
		By Company = (By.xpath("//select[@id='cid']"));
		By email = (By.xpath("//input[@id='email']"));
		By phone = (By.xpath("//input[@id='phone']"));
		By Address = (By.xpath("//input[@id='address']"));
		By City = (By.xpath("//input[@id='city']"));
		By state = (By.xpath("//input[@id='state']"));
		By zip = (By.xpath("//input[@id='zip']"));
		By country = (By.xpath("//select[@id='country']"));
		By Submit = (By.xpath("//button[@id='submit']"));

		driver.findElement(UserName).sendKeys(LoginID);
		driver.findElement(password).sendKeys(Password);
		driver.findElement(Login).click();
		driver.findElement(Customers).click();
		driver.findElement(AddCustomer).click();
		Waitforelement(driver, 5, fullname);
		driver.findElement(fullname).sendKeys(FullName);

		driver.findElement(Company);
		Select(company, Company);
		int number = Random();
		driver.findElement(email).sendKeys(number + Email);
		driver.findElement(phone).sendKeys(number + Phone);
		
		driver.findElement(Submit).click();
		
	}

	public int Random() {
		Random random = new Random();
		return random.nextInt(999);
	}

	public void Select(String data, By element) {
		Select sel = new Select(driver.findElement(element));
		sel.selectByVisibleText(data);

	}

	public void Waitforelement(WebDriver driver, int waittime, By element) {
		WebDriverWait wait = new WebDriverWait(driver, waittime);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
	}

	@AfterMethod
	public void teardown() {
		driver.close();
		driver.quit();
	}

}
