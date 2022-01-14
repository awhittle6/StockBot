package stockbot;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MicroCenter extends Website {
    public boolean inStock;
    protected String url;
    protected String locationMethod;
    protected String comparator;
    protected WebDriver driver ;
    
    public MicroCenter(String url, String locationMethod, String comparator){
        this.url = url;
        this.locationMethod = locationMethod;
        this.comparator = comparator;
        setDriver();
        updateStock();
    }
    @Override
    public void updateStock() {
        // TODO Auto-generated method stub
        this.driver.navigate().refresh();
        WebElement dataLayer = getElement();
        String innerHTML = dataLayer.getAttribute("innerHTML");
        this.inStock = dataLayerExtract(innerHTML);
    }

    @Override
    protected boolean isInStock() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected void pageWait() {
        boolean loaded =  false;
        int time = 0;
        while ((!loaded) && (time <= 3)){
            try{
                WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(1));
                wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
                loaded = true;
            } catch (org.openqa.selenium.TimeoutException e){
                time += 1;
                System.out.println("Failed to load page after  " + time + " seconds.");
            }
        }
        if (time >= 4){
            throw new org.openqa.selenium.TimeoutException("Element could not be found after " + time + " iterations,");
        }
        
    }

    @Override
    protected void setDriver() {
        // TODO Auto-generated method stub
        java.util.logging.Logger.getLogger("org.slf4j.impl.StaticLoggerBinder").setLevel(java.util.logging.Level.OFF);
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless","--disable-gpu");
        this.driver = new ChromeDriver(options);
        driver.get(this.url);
    }

    private WebElement getElement(){
        By by = By.cssSelector(locationMethod);
        pageWait();
        return this.driver.findElement(by);
    }

    private boolean dataLayerExtract(String innerHTML){
        try {
            if (innerHTML.length() < 4){
                return false;
            }
            else if (innerHTML.toLowerCase().equals("false") || innerHTML.toLowerCase().equals("true")){
                Boolean boo = Boolean.valueOf(innerHTML);
                return (boolean) boo;
            } else if(innerHTML.contains("'inStock'")){
                return dataLayerExtract(innerHTML.split("'inStock'")[1]);
            } else {return dataLayerExtract(innerHTML.split("'")[1]);}
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println(String.format("ArrayIndexOutOfBoundsException with %s", innerHTML));
            return false;
        }
    }

    public void quitDriver(){
        this.driver.quit();
        System.out.println("Headless browser closed.");
    }
}
