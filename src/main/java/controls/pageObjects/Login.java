package controls.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import root.IocBuilder;

import javax.annotation.CheckForNull;
import java.time.Duration;

public class Login {
    WebDriver driver = IocBuilder.getContainer().getComponent(WebDriver.class);
    private static final System.Logger logger = System.getLogger(Login.class.getName());
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    @CheckForNull
    private WebElement findControlById(String id){
        try
        {
            wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
            return driver.findElement(By.id(id));
        }
        catch (Exception e)
        {
            logger.log(System.Logger.Level.ERROR, e.getMessage());
        }
        return null;
    }

    WebElement username = this.findControlById("login-field");
    WebElement  password = this.findControlById("password-field");
    WebElement login = this.findControlById("login-form-submit");
    WebElement errorMessage = this.findControlById("login-error-msg-holder");
    WebElement successfulMessage = this.findControlById("system-holder");

    public void enterUsername(String name)
    {
        try
        {
            if(username != null)
            {
                username.sendKeys(name);
            }
            else
            {
                logger.log(System.Logger.Level.WARNING, "Login Field is not found!");
            }
        }
        catch (Exception e)
        {
            logger.log(System.Logger.Level.ERROR, e.getMessage());
        }
    }

    public void enterPassword(String pass)
    {
        try
        {
            if(password != null)
            {
                password.sendKeys(pass);
            }
            else
            {
                logger.log(System.Logger.Level.WARNING, "Password Field is not found!");
            }
        }
        catch (Exception e)
        {
            logger.log(System.Logger.Level.ERROR, e.getMessage());
        }
    }

    public void submit()
    {
        try
        {
            if(login != null)
            {
                login.click();
            }
            else
            {
                logger.log(System.Logger.Level.WARNING, "Login/submit button is not found!");
            }
        }
        catch (Exception e)
        {
            logger.log(System.Logger.Level.ERROR, e.getMessage());
        }
    }

    public String getLoginMessage()
    {
        try
        {
            if(errorMessage != null && errorMessage.isDisplayed())
            {
                return errorMessage.getText().replace("\n", " ");
            }
            else if (successfulMessage != null && successfulMessage.isDisplayed())
            {
                return successfulMessage.getText();
            }
        }
        catch (Exception e)
        {
            logger.log(System.Logger.Level.ERROR, e.getMessage());
        }
        return "";
    }
}
