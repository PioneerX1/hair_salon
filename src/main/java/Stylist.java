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

  public static List<Stylist> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists;";
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  public static Stylist find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists WHERE id=:id;";
      Stylist stylist = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Stylist.class);
    return stylist;
    }
  }

  public void updateWage(int wage) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stylists SET wage = :wage WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("wage", wage)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void updateName(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stylists SET name = :name WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  @Override
  public boolean equals(Object otherStylist) {
    if(!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getId() == newStylist.getId() &&
             this.getName().equals(newStylist.getName()) &&
             this.getWage() == newStylist.getWage();
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
