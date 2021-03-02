package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    //локальная инициализация для группы тестов по модификации контактов, находящихся в этом классе,
    // которая подготавливает состояние для этой группы тестов
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.contact().list().size() == 0){
            app.contact().create(new ContactData("Liza", "Gurova",
                    "Street", "1234", "12@er.ry", "test14"), true);
        }
    }

    @Test
    public void testContactModification() {

        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData(before.get(index).getId(), "LizaModified",
                "GurovaModified", "StreetModified", "1234Modified",
                "12@er.ryModified", null);
        app.contact().modify(before, contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
