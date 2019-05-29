package controllers;

import api.post.UserHandler;
import model.UserInput;
import com.fasterxml.jackson.databind.JsonNode;
import dao.InMemoryUserRepository;
import model.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserController extends Controller {
    public Result registerUser(Http.Request request) {
        JsonNode node = request.body().asJson();
        UserInput input = null;
        try {
            //lets make a UserInput object from the JSON
            input = Json.fromJson(node, UserInput.class);
            //print out the body of our request for logging purposes.
            System.out.println("The node: " + node);
            //lets build the User From the UserInput
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
        //throw the process at the handler to free up the thread.
        UserHandler handler = new UserHandler(new InMemoryUserRepository());
        handler.create(input);
        return ok(Json.toJson(input));
    }

    public Result checkLogin(Http.Request request){
        JsonNode node = request.body().asJson();
        UserInput input = Json.fromJson(node, UserInput.class);
        UserHandler handler = new UserHandler(new InMemoryUserRepository());
        System.out.println("attempting to log in with following info : " + node.asText());
        Optional<User> user = handler.login(input);
        if(user.isPresent()&&
                user.get().getPassword() != null &&
                user.get().getUsername() != null &&
                user.get().getPassword().equals(input.getPassword())) {
            System.out.println("returning json: " + Json.toJson(user));
            Map<String, String> headers = new HashMap<String, String>();
            Result result = new Result(200, headers);

            return ok(Json.toJson(user.get()));
        }
        return badRequest("User/Password Combination Invalid.");
    }
}
