import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class ClientTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Client_instantiatesCorrectly_true() {
    Client testClient1 = new Client("Chuck", 37, "hates trimmers", 1);
    assertTrue(testClient1 instanceof Client);
  }
  @Test
  public void getName_retrievesClientName_Chuck() {
    Client testClient1 = new Client("Chuck", 37, "hates trimmers", 1);
    assertEquals("Chuck", testClient1.getName());
  }
  @Test
  public void getAge_retrievesClientAge_37() {
    Client testClient1 = new Client("Chuck", 37, "hates trimmers", 1);
    assertEquals(37, testClient1.getAge());
  }
  @Test
  public void getSpecialReq_retrievesClientSpecialRequirements_hatestrimmers() {
    Client testClient1 = new Client("Chuck", 37, "hates trimmers", 1);
    assertEquals("hates trimmers", testClient1.getSpecialReq());
  }
  @Test
  public void getStylistId_retrievesClientStylistId_stylistId() {
    Stylist testStylist1 = new Stylist("Brenda", 10);
    testStylist1.save();
    Client testClient1 = new Client("Chuck", 37, "hates trimmers", testStylist1.getId());
    assertEquals(testStylist1.getId(), testClient1.getStylistId());
  }
  @Test
  public void getId_createsIdWhenSaving_true() {
    Client testClient1 = new Client("Chuck", 37, "hates trimmers", 1);
    testClient1.save();
    assertTrue(testClient1.getId() > 0);
  }
  @Test
  public void all_retrievesAllClients() {
    Stylist testStylist1 = new Stylist("Brenda", 10);
    testStylist1.save();
    Client testClient1 = new Client("Chuck", 37, "hates trimmers", testStylist1.getId());
    testClient1.save();
    Client testClient2 = new Client("Sarah", 40, "hates people", testStylist1.getId());
    testClient2.save();
    assertTrue(Client.all().get(0).equals(testClient1));
    assertTrue(Client.all().get(1).equals(testClient2));
  }
  @Test
  public void find_retrievesClientWithSameId_testClient2() {
    Stylist testStylist1 = new Stylist("Brenda", 10);
    testStylist1.save();
    Client testClient1 = new Client("Chuck", 37, "hates trimmers", testStylist1.getId());
    testClient1.save();
    Client testClient2 = new Client("Sarah", 40, "hates people", testStylist1.getId());
    testClient2.save();
    assertEquals(Client.find(testClient2.getId()), testClient2);
  }


}
