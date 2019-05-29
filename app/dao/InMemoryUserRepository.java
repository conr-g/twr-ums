package dao;

import model.UserInput;
import model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository{
    static Map<String, User> dataStore = new HashMap<>();

    @Override
    public void save(User user) {
        InMemoryUserRepository.dataStore.put(user.getUsername(), user);
    }

    @Override
    public Optional<User> loginUser(UserInput user) {
        InMemoryUserRepository.dataStore.put("cgerrard", User.builder().username("cgerrard").password("name jeff").build());
        return Optional.of(InMemoryUserRepository.dataStore.get(user.getUsername()));
    }
}
