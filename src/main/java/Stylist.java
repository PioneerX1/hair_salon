import org.sql2o.*;
import java.util.*;

public class Stylist {
  private String name;
  private int wage;
  private int id;

  public Stylist(String name, int wage) {
    this.name = name;
    this.wage = wage;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists(name, wage) VALUES (:name, :wage);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("wage", this.wage)
        .executeUpdate()
        .getKey();
    }
  }

  public String getName() {
    return name;
  }
  public int getWage() {
    return wage;
  }
  public int getId() {
    return id;
  }


}
