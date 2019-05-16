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

import org.bson.Document;

import forms.User;

public class HomeController extends Controller {
    FormFactory formFactory;
    private MongoClient mongoClient;

    @Inject
    public HomeController(FormFactory formFactory) {
        this.formFactory = formFactory;
        mongoClient = MongoClients.create();
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
        System.out.println(userForm.get().getUsername());
        System.out.println(userForm.get().getPassword());

        MongoDatabase database = mongoClient.getDatabase("waitingroom");

        Document document = new Document("user", userForm.get().getUsername())
                                 .append("password", userForm.get().getPassword());

        MongoCollection<Document> collection = database.getCollection("users");
        collection.insertOne(document);

        return ok();
    }

    public Result login() {
        return ok();
    }
}
