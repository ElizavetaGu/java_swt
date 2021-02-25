package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {
    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().goToHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createAContact(new ContactData("Liza", "Gurova",
                    "Street", "1234", "12@er.ry", "test14"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().submitContactDeletion();
        app.getContactHelper().confirmContactDeletion();
    }
}
