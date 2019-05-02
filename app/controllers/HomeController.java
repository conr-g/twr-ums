package controllers;

import play.mvc.*;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoIterable;

public class HomeController extends Controller {
    public Result index() {
        return ok();
    }

    public Result listDatabases() {
        MongoClient mongoClient = MongoClients.create();
        MongoIterable<String> databases = mongoClient.listDatabaseNames();
        for (String database : databases) {
            System.out.println(database);
        }
        return ok();
    }

    public Result createUser() {
        return ok();
    }

    public Result login() {
        return ok();
    }
}
