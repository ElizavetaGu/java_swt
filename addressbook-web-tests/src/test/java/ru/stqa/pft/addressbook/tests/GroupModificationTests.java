package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

    //локальная инициализация для группы тестов по модификации групп, находящихся в этом классе,
    // которая подготавливает состояние для этой группы тестов
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test14"));
        }
    }

    @Test
    public void testGroupModification(){
        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withID(modifiedGroup.getId()).withName("test345modified").
                withHeader("testmodified").withFooter("testmodified");
        app.group().modify(group);
        Groups after = app.group().all();
        assertEquals(after.size(), before.size());

        before.remove(modifiedGroup);
        before.add(group);
        //Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        //before.sort(byId);
        //after.sort(byId);
        assertEquals(before, after);
        //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }
}
