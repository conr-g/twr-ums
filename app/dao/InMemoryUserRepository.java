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
        InMemoryUserRepository.dataStore.put(user.getUsername(), User.builder().username(user.getUsername()).password(user.getPassword()).build());
        System.out.println(InMemoryUserRepository.dataStore.get(user.getUsername()).getUsername());
        return Optional.of(InMemoryUserRepository.dataStore.get(user.getUsername()));
    }
}
