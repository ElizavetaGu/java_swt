package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    //int before = app.getGroupHelper().getGroupCount();
    GroupData group = new GroupData().withName("test14");
    app.group().create(group);
    //int after = app.getGroupHelper().getGroupCount();
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();
    //Assert.assertEquals(after.size(), before.size() + 1);

    //group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    //список првращается в поток; по потоку пробегает функция сравнения и находит максимальный элемент
    //сравнивание идет по ID объектов; на выходе функция выдаст список из одного объекта с максимальным идентификатором
    //и выбирается идентификатор этого объекта

    //group.withID(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    //before.add(group);
    //сортировать множества не надо
    //Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    //before.sort(byId);
    //after.sort(byId);

    //Assert.assertEquals(before, after);
    assertThat(after, equalTo(
            before.withAdded(group.withID(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test'");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before));
  }
}
