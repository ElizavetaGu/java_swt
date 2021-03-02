package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test(enabled = false)
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData("Liza", "Gurova",
            "Street", "1234", "12@er.ry", "test14");
    app.contact().create(contact, true);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    //contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }

}
