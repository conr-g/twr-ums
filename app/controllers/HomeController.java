package controllers;

import java.util.List;

import play.mvc.*;
import play.data.Form;
import play.data.FormFactory;

import com.google.inject.Inject;
import play.libs.Json;

import com.google.common.collect.Lists;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoIterable;
import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import forms.User;

public class HomeController extends Controller {
    private FormFactory formFactory;
    private MongoClient mongoClient;
    private MongoDatabase database;

    @Inject
    public HomeController(FormFactory formFactory) {
        this.formFactory = formFactory;
        mongoClient = MongoClients.create();
        database = mongoClient.getDatabase("waitingroom");
    }

    public Result index() {
        return ok("TWR-UMS");
    }

    public Result listDatabases() {
        MongoIterable<String> databases = mongoClient.listDatabaseNames();
        List<String> tmp = Lists.newArrayList(databases);
        return ok(Json.toJson(tmp));
    }

    public Result createUser(Http.Request request) {
        Form<User> userForm = formFactory.form(User.class).bindFromRequest(request);

        Document document = new Document("username", userForm.get().getUsername())
                                 .append("password", userForm.get().getPassword());

        MongoCollection<Document> collection = database.getCollection("users");
        collection.insertOne(document);

        return ok();
    }

    public Result login(Http.Request request) {
        Form<User> userForm = formFactory.form(User.class).bindFromRequest(request);

        MongoCollection<Document> collection = database.getCollection("users");
        Document user = collection.find(eq("username", userForm.get().getUsername())).first();

        if (user.get("password").equals(userForm.get().getPassword())) {
            System.out.println("Login successful");
            return ok();
        } else {
            System.out.println("Login failed");
            return ok();
        }
    }
}
