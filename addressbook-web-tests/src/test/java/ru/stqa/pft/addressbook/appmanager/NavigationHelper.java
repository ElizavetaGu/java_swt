package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd); //обращение к конструктору базового класса
    }

    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }
}
