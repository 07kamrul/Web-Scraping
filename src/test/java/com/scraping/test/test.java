package com.scraping.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;

public class test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private ChromeDriver driver;

	private ChromeDriver getChromeDriver(boolean isHeadless) {
		ChromeDriverService service = new ChromeDriverService.Builder()
				.usingDriverExecutable(
						new File("C:\\Users\\kamrul.hasan\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe"))
				.usingAnyFreePort().build();

		ChromeOptions opts = new ChromeOptions().addArguments("--no-sandbox")
				.addArguments("--disable-dev-shm-usage", "--disable-extensions", "disable-infobars")
				.setHeadless(isHeadless).setExperimentalOption("useAutomationExtension", false);
		ChromeDriver driver = new ChromeDriver(service, opts);
		/*
		 * Developer should increase page load timeout in their scraper class when
		 * needed
		 */
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void test() throws InterruptedException {
		driver = getChromeDriver(false);
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.toString());
		driver.get("https://careers.unity.com/find-position");
		Thread.sleep(10 * 1000);
		List<WebElement> el = driver.findElements(
				By.xpath("//div[@class='component-open-positions']/div[@class='component-open-positions__position']"));
		System.out.println(el.size());

		for (WebElement webElement : el) {
			ScrapModel obj = new ScrapModel();
			obj.setTitle(webElement.findElement(By.xpath("//a/div/div[1]")).getText());
			obj.setLocation(webElement.findElement(By.xpath("//a/div/div[2]")).getText());
			obj.setType(webElement.findElement(By.xpath("//a/div/div[3]")).getText());
			System.out.println(webElement.findElement(By.xpath("//a/div/div[1]")).getText());
			System.out.println(webElement.findElement(By.xpath("//a/div/div[2]")).getText());
			System.out.println(webElement.findElement(By.xpath("//a/div/div[3]")).getText());
		}
	}

}
