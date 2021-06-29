package VariousConcepts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class LearnTestNG {
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

	@Test(priority = 2)
	public void learnTestNG() throws InterruptedException {

		Assert.assertEquals(driver.getTitle(), "Login - iBilling");

		// Login Data
		String LoginID = "demo@techfios.com";
		String Password = "abc123";

		WebElement UserName = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		UserName.sendKeys(LoginID);
		password.sendKeys(Password);
		driver.findElement(By.name("login")).click();
		WebElement Dashboard = driver.findElement(By.xpath("//h2[contains(text(),' Dashboard ')]"));
		Assert.assertEquals(Dashboard.getText(), "Dashboard");

	}

	@Test(priority = 1)
	public void addCustomer() {
		Assert.assertEquals(driver.getTitle(), "Login - iBilling");

		// Login Data
		String LoginID = "demo@techfios.com";
		String Password = "abc123";

		WebElement UserName = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		UserName.sendKeys(LoginID);
		password.sendKeys(Password);
		driver.findElement(By.name("login")).click();
		WebElement Dashboard = driver.findElement(By.xpath("//h2[contains(text(),' Dashboard ')]"));
		Assert.assertEquals(Dashboard.getText(), "Dashboard");
		String FullName = "Techfios";
		String Email = "techfios@techfios.com";
		String Phone = "5258956";
		String address = "152 Techfios Rd";
		String State = "TX";
		String Zip = "56856";
		String Country = "United States";
		WebElement Customers = driver.findElement(By.xpath("//span[contains(text(),'Customers')]"));
		Customers.click();
		WebElement AddCustomer = driver.findElement(By.xpath("//a[contains(text(),'Add Customer')]"));
		WebDriverWait Wait = new WebDriverWait(driver, 10);
		Wait.until(ExpectedConditions.visibilityOf(AddCustomer));
		AddCustomer.click();
		WebElement fullname = driver.findElement(By.xpath("//input[@id='account']"));
		WebElement Contacts = driver.findElement(By.xpath("//select[@id='cid']"));
		fullname.sendKeys(FullName);

		Select sel = new Select(Contacts);

		// To get all the options
		List<WebElement> ListContacts = sel.getOptions();
		for (int i = 0; i < ListContacts.size(); i++) {
			System.out.println(ListContacts.get(i).getText());
			if (ListContacts.get(i).getText().equals("Jezzy604")) {
				ListContacts.get(i).click();
				break;
			}
		}

		Random rnd = new Random();
		int Random = rnd.nextInt(999);
		WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
		WebElement phone = driver.findElement(By.xpath("//input[@id='phone']"));

		email.sendKeys(Random +Email);
		phone.sendKeys(Random +Phone);
		driver.findElement(By.xpath("//input[@id='address']")).sendKeys("5421 Techfios Dr");
		driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Texan City");
		driver.findElement(By.xpath("//input[@id='state']")).sendKeys("TX");
		driver.findElement(By.xpath("//input[@id='zip']")).sendKeys("75010");
		WebElement Country1 = driver.findElement(By.xpath("//select[@id='country']"));
		Select Sel = new Select(Country1);
		Sel.selectByVisibleText("Afghanistan");
		driver.findElement(By.xpath("//button[@id='submit']")).click();
	}

	@AfterMethod
	public void teardown() {
		driver.close();
		driver.quit();
	}

}
