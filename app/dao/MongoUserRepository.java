package dao;

import model.UserInput;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.User;
import org.bson.Document;

import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

public class MongoUserRepository implements UserRepository {


    @Override
    public void save(User user) {
         MongoClient mongoClient = MongoClients.create();
         MongoDatabase database = mongoClient.getDatabase("waitingroom");
         Document document = new Document("username", user.getUsername())
                .append("password", user.getPassword());

         MongoCollection<Document> collection = database.getCollection("users");
         collection.insertOne(document);
    }

    @Override
    public Optional<User> loginUser(UserInput user) {
        MongoClient mongoClient = MongoClients.create();
        MongoDatabase database = mongoClient.getDatabase("waitingroom");
        MongoCollection<Document> collection = database.getCollection("users");
        Document userFromDb = collection.find(eq("username", user.getUsername())).first();
        User returned = User.builder()
                .username(userFromDb.getString("username"))
                .password(userFromDb.getString("password"))
                .build();
        return Optional.of(returned);
    }
}
