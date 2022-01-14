package stockbot;

import org.openqa.selenium.WebDriver;

public abstract class Website {
    public boolean inStock;
    protected String url;
    protected String locationMethod;
    protected String comparator;
    protected WebDriver driver;

    public abstract void updateStock();
    protected abstract boolean isInStock();
    protected abstract void pageWait();
    protected abstract void setDriver();
}
