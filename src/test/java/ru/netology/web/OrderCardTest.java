package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderCardTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String BASE_URL = "http://localhost:9999";

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    void testMainPageLoads() {
        driver.get(BASE_URL);
        assertThat(driver.getTitle()).isNotEmpty();
        assertThat(driver.findElements(By.tagName("input")).size()).isGreaterThan(0);
    }

    @Test
    void testOrderFormExists() {
        driver.get(BASE_URL);
        wait.until(d -> driver.findElements(By.tagName("input")).size() > 0);

        assertThat(driver.findElement(By.name("name")).getAttribute("type")).isEqualTo("text");
        assertThat(driver.findElement(By.name("phone")).getAttribute("type")).isEqualTo("tel");
        assertThat(driver.findElements(By.name("agreement")).size()).isEqualTo(1);
    }

    @Test
    void testOrderFormCanBeFilled() {
        driver.get(BASE_URL);

        driver.findElement(By.name("name")).sendKeys("Иван Иванов");
        driver.findElement(By.name("phone")).sendKeys("+79991234567");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.name("agreement")));

        assertThat(driver.findElement(By.name("name")).getAttribute("value")).isEqualTo("Иван Иванов");
        assertThat(driver.findElement(By.name("phone")).getAttribute("value")).isEqualTo("+79991234567");
        assertThat(driver.findElement(By.name("agreement")).isSelected()).isTrue();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
