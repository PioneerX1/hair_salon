import org.sql2o.*;
import java.util.*;

public class Client {
  private String name;
  private int age;
  private String specialReq;
  private int stylistId;
  private int id;

  public Client(String name, int age, String specialReq, int stylistId) {
    this.name = name;
    this.age = age;
    this.specialReq = specialReq;
    this.stylistId = stylistId;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients(name, age, specialReq, stylistId) VALUES (:name, :age, :specialReq, :stylistId);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("age", this.age)
        .addParameter("specialReq", this.specialReq)
        .addParameter("stylistId", this.stylistId)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Client> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients;";
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE id=:id;";
      Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
    return client;
    }
  }

  public void update(String name, int age, String specialReq, int stylistId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET name=:name, age=:age, specialReq=:specialReq, stylistId=:stylistId WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("age", age)
        .addParameter("specialReq", specialReq)
        .addParameter("stylistId", stylistId)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public static List<Client> findStylistId(int stylistId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE stylistId=:stylistId;";
      List<Client> clientsByStylist = con.createQuery(sql)
        .addParameter("stylistId", stylistId)
        .executeAndFetch(Client.class);
      return clientsByStylist;
    }
  }


  @Override
  public boolean equals(Object otherClient) {
    if(!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getId() == newClient.getId() &&
             this.getName().equals(newClient.getName()) &&
             this.getAge() == newClient.getAge() &&
             this.getSpecialReq().equals(newClient.getSpecialReq()) &&
             this.getStylistId() == newClient.getStylistId();
    }
  }


  public String getName() {
    return name;
  }
  public int getAge() {
    return age;
  }
  public String getSpecialReq() {
    return specialReq;
  }
  public int getStylistId() {
    return stylistId;
  }
  public int getId() {
    return id;
  }

}
