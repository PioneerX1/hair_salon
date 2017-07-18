import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylist/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("stylist", stylist);
      model.put("clients", Client.findStylistId(stylist.getId()));
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("stylist-new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/add-stylist-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylist/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("stylist-name");
      int wage = Integer.parseInt(request.queryParams("stylist-wage"));
      Stylist stylist = new Stylist(name, wage);
      stylist.save();
      model.put("stylist", stylist);
      model.put("clients", Client.findStylistId(stylist.getId()));
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("stylist/:stylistId/client-new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylistId")));
      model.put("stylist", stylist);
      model.put("template", "templates/add-client-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("stylist/:stylistId/client/:clientId", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylistId")));
      String clientName = request.queryParams("client-name");
      int clientAge = Integer.parseInt(request.queryParams("client-age"));
      String clientReq = request.queryParams("client-req");
      Client client = new Client(clientName, clientAge, clientReq, stylist.getId());
      client.save();
      model.put("stylist", stylist);
      model.put("client", client);
      //needs to go to client page
      model.put("template", "templates/client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("stylist/:stylistId/client/:clientId", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylistId")));
      Client client = Client.find(Integer.parseInt(request.params(":clientId")));
      model.put("stylist", stylist);
      model.put("client", client);
      model.put("template", "templates/client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("stylist/:stylistId/update", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylistId")));
      model.put("stylist", stylist);
      model.put("template", "templates/update-stylist-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylist/:id/update", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      String name = request.queryParams("stylist-name");
      int wage = Integer.parseInt(request.queryParams("stylist-wage"));
      stylist.updateName(name);
      stylist.updateWage(wage);
      String url = String.format("/stylist/%d", stylist.getId());
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("stylist/:stylistId/client/:clientId/update", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylistId")));
      Client client = Client.find(Integer.parseInt(request.params(":clientId")));
      model.put("stylist", stylist);
      model.put("client", client);
      model.put("template", "templates/update-client-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylist/:stylistId/client/:clientId/update", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylistId")));
      Client client = Client.find(Integer.parseInt(request.params(":clientId")));
      String name = request.queryParams("client-name");
      int age = Integer.parseInt(request.queryParams("client-age"));
      String specialReq = request.queryParams("client-req");
      client.update(name, age, specialReq, stylist.getId());

      String url = String.format("/stylist/%d/client/%d", stylist.getId(), client.getId());
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


  }
}
