package controllers;

import java.util.List;

import play.mvc.*;

import com.google.inject.Inject;
import play.libs.Json;

import com.google.common.collect.Lists;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoIterable;

public class HomeController extends Controller {
    private MongoClient mongoClient;

    @Inject
    public HomeController() {
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
}
