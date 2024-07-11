package root;

import controls.Browser;
import controls.pageObjects.Pages;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoCompositionException;

import java.time.Duration;

public class IocBuilder
{
    private static MutablePicoContainer container = new DefaultPicoContainer();
    private static final System.Logger logger = System.getLogger(IocBuilder.class.getName());

    public static StarbagHandler createNewTest(String url)
    {
        StarbagHandler handler = null;
        try
        {
            if(getContainer().getComponent(StarbagHandler.class) != null)
            {
                container =  new DefaultPicoContainer();
            }
            registerStarbag();
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("disable-infobars");
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            WebDriver driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.navigate().to(url);
            container.addComponent(driver);
            handler = container.getComponent(StarbagHandler.class);
        }
        catch(NullPointerException | PicoCompositionException e)
        {
            logger.log(System.Logger.Level.ERROR, e.getMessage());
        }

        return handler;
    }

    public static MutablePicoContainer getContainer()
    {
        return container;
    }
    private static void registerStarbag()
    {
        try
        {
            //handlers
            container.addComponent(StarbagHandler.class);
            container.addComponent(Browser.class);
            container.addComponent(Pages.class);

        }
        catch(PicoCompositionException e)
        {
            logger.log(System.Logger.Level.ERROR, e.getMessage());
        }
    }
}
