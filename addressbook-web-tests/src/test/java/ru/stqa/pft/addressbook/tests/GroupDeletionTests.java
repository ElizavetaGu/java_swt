package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupDeletionTests extends TestBase {
  //локальная инициализация для группы тестов по удалению групп, находящихся в этом классе,
  // которая подготавливает состояние для этой группы тестов
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if (app.group().all().size() == 0){
      app.group().create(new GroupData().withName("test14"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    Set<GroupData> before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() - 1); //сравнение размера списков групп

    before.remove(deletedGroup);
    Assert.assertEquals(before, after); //сравнение содержимого списков

    //app.logout();
  }
}
