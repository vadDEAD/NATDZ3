package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallBackService {
    private static WebDriver driver;
    private static ChromeOptions options;


    @BeforeAll
    static  void setUpAll() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        System.setProperty("webdriver.chrome.driver", "./drivers/win/chromedriver.exe");
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver(options);
    }
    @AfterEach
    void tearDown(){
        driver.quit();
        driver = null;
    }
    @Test
    void Test1AllInput(){
        driver.get("http://localhost:9999/");
        driver.findElements(By.className("input__control")).get(0).sendKeys("Текст");
        driver.findElements(By.className("input__control")).get(1).sendKeys("+79998887766");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.tagName("p")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void Test2NameWithSpace(){
        driver.get("http://localhost:9999/");
        driver.findElements(By.className("input__control")).get(0).sendKeys("текст текст");
        driver.findElements(By.className("input__control")).get(1).sendKeys("+79998887766");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.tagName("p")).getText().trim();
        assertEquals(expected, actual);
    }
    @Test
    void Test3NameWithHyphen(){
        driver.get("http://localhost:9999/");
        driver.findElements(By.className("input__control")).get(0).sendKeys("текст-текст");
        driver.findElements(By.className("input__control")).get(1).sendKeys("+79998887766");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.tagName("p")).getText().trim();
        assertEquals(expected, actual);
    }


}
