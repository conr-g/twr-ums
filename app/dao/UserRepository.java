package dao;

import model.UserInput;
import model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public interface UserRepository {
    static Map<String, User> dataStore = new HashMap<>();
    void save(User user);

    Optional<User> loginUser(UserInput input);
}
