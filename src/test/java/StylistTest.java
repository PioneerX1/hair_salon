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
  public void getId_instantiatesWithAnId_true() {
    Stylist testStylist1 = new Stylist("Brenda", 10);
    testStylist1.save();
    assertTrue(testStylist1.getId() > 1);
  }


}
