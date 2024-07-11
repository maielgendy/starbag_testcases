package root;

import controls.Browser;
import controls.pageObjects.Pages;

public class StarbagHandler
{
    public final Browser browser;
    public final Pages pages;


    public StarbagHandler(Browser browser, Pages pages)
    {
        this.browser = browser;
        this.pages = pages;
    }
}