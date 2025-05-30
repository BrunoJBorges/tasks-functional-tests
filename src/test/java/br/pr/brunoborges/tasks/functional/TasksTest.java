package br.pr.brunoborges.tasks.functional;

import java.net.URL;
import java.time.Duration;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() {
		try {
	        ChromeOptions options = new ChromeOptions();
	        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.4.95:4444/wd/hub"), options); // URL do Selenium Grid

	        driver.navigate().to("http://192.168.4.95:8001/tasks");
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	        return driver;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarAplicacao();
		
		try {
			//Clicar no botão para adicionar uma nova task
			driver.findElement(By.id("addTodo")).click();
			
			//Escrever a descrição e a data
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			//Clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Validar a mensagem de sucesso
			String mensagemSucesso = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", mensagemSucesso);
		} finally {
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		WebDriver driver = acessarAplicacao();
		
		try {
			
			//Clicar no botão para adicionar uma nova task
			driver.findElement(By.id("addTodo")).click();
			
			//Escrever a descrição e a data
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");
			
			//Clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Validar a mensagem de erro
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", mensagem);
		} finally {
			driver.quit();
		}
		
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		WebDriver driver = acessarAplicacao();
		
		try {
			
			//Clicar no botão para adicionar uma nova task
			driver.findElement(By.id("addTodo")).click();
			
			//Escrever a descrição e a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			//Clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Validar a mensagem de erro
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", mensagem);
		} finally {
			driver.quit();
		}		
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		WebDriver driver = acessarAplicacao();
		
		try {
			//Clicar no botão para adicionar uma nova task
			driver.findElement(By.id("addTodo")).click();
			
			//Escrever a descrição e a data
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//Clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Validar a mensagem de sucesso
			String mensagemSucesso = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", mensagemSucesso);
		} finally {
			driver.quit();
		}
	}

}
