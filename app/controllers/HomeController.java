package controllers;

import play.mvc.*;
import play.data.Form;
import play.data.FormFactory;

import com.google.inject.Inject;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoIterable;

import org.bson.Document;

import forms.User;

public class HomeController extends Controller {
    FormFactory formFactory;

    @Inject
    public HomeController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    public Result index() {
        return ok("TWR-UMS");
    }

    public Result listDatabases() {
        MongoClient mongoClient = MongoClients.create();
        MongoIterable<String> databases = mongoClient.listDatabaseNames();
        for (String database : databases) {
            System.out.println(database);
        }
        return ok();
    }

    public Result createUser(Http.Request request) {
        Form<User> userForm = formFactory.form(User.class).bindFromRequest(request);
        System.out.println(userForm.get().getUsername());
        System.out.println(userForm.get().getPassword());

        MongoClient mongoClient = MongoClients.create();
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
