import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class StylistTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Stylist_instantiatesCorrectly_true() {
    Stylist testStylist1 = new Stylist("Brenda", 10);
    assertTrue(testStylist1 instanceof Stylist);
  }
  @Test
  public void getName_retrievesStylistName_Brenda() {
    Stylist testStylist1 = new Stylist("Brenda", 10);
    assertEquals("Brenda", testStylist1.getName());
  }
  @Test
  public void getWage_retrievesStylistWage_10() {
    Stylist testStylist1 = new Stylist("Brenda", 10);
    assertEquals(10, testStylist1.getWage());
  }
  @Test
  public void getId_instantiatesWithAnId_true() {
    Stylist testStylist1 = new Stylist("Brenda", 10);
    testStylist1.save();
    assertTrue(testStylist1.getId() > 1);
  }
  @Test
  public void all_retrievesAllInstancesOfStylist_true() {
    Stylist testStylist1 = new Stylist("Brenda", 10);
    testStylist1.save();
    Stylist testStylist2 = new Stylist("Gladis", 8);
    testStylist2.save();
    assertEquals(true, Stylist.all().get(0).equals(testStylist1));
    assertEquals(true, Stylist.all().get(1).equals(testStylist2));
  }
  @Test
  public void find_retrievesStylistWithSameId_testStylist2() {
    Stylist testStylist1 = new Stylist("Brenda", 10);
    testStylist1.save();
    Stylist testStylist2 = new Stylist("Gladis", 8);
    testStylist2.save();
    assertEquals(Stylist.find(testStylist2.getId()), testStylist2);
  }
  @Test
  public void updateWage_updateWageForStylist_12() {
    Stylist testStylist1 = new Stylist("Brenda", 10);
    testStylist1.save();
    testStylist1.updateWage(12);
    assertEquals(12, Stylist.find(testStylist1.getId()).getWage());
  }
  @Test
  public void updateName_updateNameForStylist_Brandon() {
    Stylist testStylist1 = new Stylist("Brenda", 10);
    testStylist1.save();
    testStylist1.updateName("Brandon");
    assertEquals("Brandon", Stylist.find(testStylist1.getId()).getName());
  }
  @Test
  public void delete_deletesAStylist_true() {
    Stylist testStylist1 = new Stylist("Brenda", 10);
    testStylist1.save();
    int testStylistId = testStylist1.getId();
    testStylist1.delete();
    assertEquals(null, Stylist.find(testStylistId));
  }


}
