package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Liza", "Gurova", "Street", "1234", "12@er.ry"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToContactPage();
  }

}
