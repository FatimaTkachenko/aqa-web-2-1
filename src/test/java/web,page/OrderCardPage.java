package ru.netology.web.page;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import static org.openqa.selenium.By.*;

public class OrderCardPage {
    private WebDriver driver;

    private By cityInput = cssSelector("[data-test-id='city'] input");
    private By nameInput = cssSelector("[data-test-id='name'] input");
    private By phoneInput = cssSelector("[data-test-id='phone'] input");
    private By agreeCheckbox = cssSelector("[data-test-id='agreement']");
    private By orderButton = cssSelector("button.button");
    private By successMessage = cssSelector("[data-test-id='order-notification'] .notification__title");

    public OrderCardPage() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    public void validOrder(String city, String name, String phone) {
        driver.findElement(cityInput).sendKeys(city);
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(phoneInput).sendKeys(phone);
        driver.findElement(agreeCheckbox).click();
        driver.findElement(orderButton).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
    }

    public void quit() {
        driver.quit();
    }
}
