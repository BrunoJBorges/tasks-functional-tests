package br.pr.brunoborges.tasks.functional;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		
		//Navegar até a página
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		return driver;
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
			//Navegar até a página
			driver.navigate().to("http://localhost:8001/tasks");
			
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
			//Navegar até a página
			driver.navigate().to("http://localhost:8001/tasks");
			
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
