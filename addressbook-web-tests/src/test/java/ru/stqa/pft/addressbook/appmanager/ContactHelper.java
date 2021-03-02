package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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

    public void initContactModification(int index) {
        click(By.xpath("(//img[@alt='Edit'])[" + index + "]"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void submitContactDeletion() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
        //wd.findElements(By.xpath("(//input[@name='selected[]'])[" + (index + 1) + "]"))
        //click(By.name("selected[]"));
        //click(By.xpath("//input[@type='checkbox'][12]"));
    }

    public void confirmContactDeletion() {
        wd.switchTo().alert().accept();
    }

    public void create(ContactData contact, boolean creation) {
        initContactCreation();
        fillContactForm(contact, creation);
        submitContactCreation();
        returnToContactPage();
    }

    public void modify(List<ContactData> before, ContactData contact) {
        initContactModification(before.size());
        fillContactForm(contact, false);
        submitContactModification();
        returnToContactPage();
    }

    public void delete(int index) {
        selectContact(index);
        submitContactDeletion();
        confirmContactDeletion();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement element : elements){
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String name = cells.get(2).getText();
            String surname = cells.get(1).getText();
            ContactData contact = new ContactData(id, name, surname, null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
