package com.scraping.test;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UnityCareers {
	private ChromeDriver driver;

	@Autowired
	ScrapRepo scrapRepo;

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

	@GetMapping(value = "/unity")
	private boolean unityScrapper() throws InterruptedException {
		driver = getChromeDriver(false);
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.toString());
		driver.get("https://careers.unity.com/find-position");
		Thread.sleep(10 * 1000);
//		List<WebElement> el = driver.findElements(
//				By.xpath("//div[@class='component-open-positions']/div[@class='component-open-positions__position']"));
		List<WebElement> el = driver.findElements(
				By.xpath("//a[@class='component-open-positions__list-position']"));

		System.out.println(el.size());
		ScrapModel obj = new ScrapModel();

		for (WebElement webElement : el) {
//			obj.setTitle(webElement.findElement(By.xpath("//a/div/div[1]")).getText());
//			obj.setLocation(webElement.findElement(By.xpath("//a/div/div[2]")).getText());
//			obj.setType(webElement.findElement(By.xpath("//a/div/div[3]")).getText());
//			scrapRepo.save(obj);
//			System.out.println(webElement.findElement(By.xpath("//a/div/div[1]")).getText());
//			System.out.println(webElement.findElement(By.xpath("//a/div/div[2]")).getText());
//			System.out.println(webElement.findElement(By.xpath("//a/div/div[3]")).getText());
//			
			System.out.println(webElement.findElement(By.xpath("//div/div[1]/p[@class='large']")).getText());
			System.out.println(webElement.findElement(By.xpath("//div/div[2]/p[@class='large']")).getText());
			System.out.println(webElement.findElement(By.xpath("//div/div[3]/p[@class='large']")).getText());
		}
		return true;

	}
}
