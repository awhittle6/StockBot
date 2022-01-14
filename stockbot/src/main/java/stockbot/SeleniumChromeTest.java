package stockbot;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumChromeTest {
    public static void main(String[] args){
        java.util.logging.Logger.getLogger("org.slf4j.impl.StaticLoggerBinder").setLevel(java.util.logging.Level.OFF);
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless","--disable-gpu");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.microcenter.com/product/621439/raspberry-pi-4-model-b-2gb-ddr4");
        String inti = (String) ((JavascriptExecutor)driver).executeScript("document.querySelector(\"head > script:nth-child(68)\");");
        System.out.print(inti);
        //WebElement element = driver.findElement(By.cssSelector("head > script:nth-child(68)"));
        //WebElement element = driver.findElement(By.xpath("/html/head/script[28]"));
        //System.out.println(driver.findElement(By.xpath("//*[@id=\"details\"]/h1/span/span")).getAttribute("innerHTML"));
        //System.out.println(element.getAttribute("innerHTML"));
        //System.out.println(driver instanceof JavascriptExecutor);
    }
}
