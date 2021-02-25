package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.*;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    WebDriver wd;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private String browser;

    //конструктор с параметром, при создании экземпляра класса в параметре указывается то значение,
    //которое присваивается в поле/аттрибут
    public ApplicationManager(String browser) {
        this.browser = browser; //поле
    }

    public void init() {
        //String browser = BrowserType.FIREFOX;
        if (browser.equals(BrowserType.FIREFOX)){
            wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)){
            wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.IE)){
            wd = new InternetExplorerDriver();
        }
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        sessionHelper.logout();
        wd.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
}
