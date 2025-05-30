package br.pr.brunoborges.tasks.prod;

import java.net.URL;
import java.time.Duration;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HealthCheckIT {
	
	@Test
	public void healtCheck() {
		try {
	        ChromeOptions options = new ChromeOptions();
	        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.4.95:4444/wd/hub"), options); // URL do Selenium Grid

	        driver.navigate().to("http://192.168.4.95:9999/tasks");
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        
	        String versao = driver.findElement(By.id("version")).getText();
	        Assert.assertTrue(versao.startsWith("build_"));
	        
	        driver.quit();
	        
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
