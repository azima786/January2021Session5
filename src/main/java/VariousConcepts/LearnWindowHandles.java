package VariousConcepts;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LearnWindowHandles {
	WebDriver driver;

	@Before
	public void init() {
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.get("https://www.yahoo.com/");
	}

	@Test
	public void WindowHandle() throws InterruptedException {
		String handle = driver.getWindowHandle();
//		System.out.println(handle);
//		System.out.println(driver.getTitle());
		driver.findElement(By.xpath("//input[@id='ybar-sbq']")).sendKeys("xpath");
		driver.findElement(By.xpath("//input[@id='ybar-search']")).click();

//		String handle2 = driver.getWindowHandle();
//		System.out.println(handle2);

		driver.findElement(By.linkText("XPath Tool for XML & JSON - Start Your Free, 30-Day Trial")).click();
		Set<String> handles = driver.getWindowHandles();
//		System.out.println(handles);
		System.out.println(driver.getTitle());

		for (String I : handles) {
			System.out.println(I);
			driver.switchTo().window(I);
		}

		System.out.println(driver.getTitle());
		driver.switchTo().window(handle);
		System.out.println(driver.getTitle());
	}

	@After
	public void teardown() {
		driver.close();
		driver.quit();
	}
}
