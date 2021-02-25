package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd); //обращение к конструктору базового класса
    }

    public void returnToContactPage() {
        click(By.linkText("home page"));
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    //второй элемент - форма зполняется при создании (true) или при модификации (false)
    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("email"), contactData.getEmail());
        //если при создании, проверяет, есть ли список с группами
        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            //если при модификации, проверяет, что списка с группами нет
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void initContactModification() {
        click(By.xpath("(//img[@alt='Edit'])[1]"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void submitContactDeletion() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
        //click(By.xpath("//input[@type='checkbox'][12]"));
    }

    public void confirmContactDeletion() {
        wd.switchTo().alert().accept();
    }

    public void createAContact(ContactData contact, boolean creation) {
        initContactCreation();
        fillContactForm(contact, creation);
        submitContactCreation();
        returnToContactPage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }
}
