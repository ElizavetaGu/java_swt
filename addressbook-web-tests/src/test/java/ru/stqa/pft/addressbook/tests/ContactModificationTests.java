package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createAContact(new ContactData("Liza", "Gurova",
                    "Street", "1234", "12@er.ry", "test14"), true);
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("LizaModified",
                "GurovaModified", "StreetModified", "1234Modified",
                "12@er.ryModified", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToContactPage();
    }
}
