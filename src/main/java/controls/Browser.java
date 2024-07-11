package controls;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import root.IocBuilder;

import java.time.Duration;

public class Browser {
    WebDriver driver = IocBuilder.getContainer().getComponent(WebDriver.class);
    public void navigate(String url)
    {
        driver.navigate().to(url);
    }

    public String getCurrentUrl()
    {
        return driver.getCurrentUrl();
    }
    public void close()
    {
        driver.quit();
    }
}
