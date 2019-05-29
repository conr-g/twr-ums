package api.post;

import dao.UserRepository;
import model.User;
import model.UserInfo;
import model.UserInput;

import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Supplier;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class UserHandler {
    private final UserRepository repository;

    public UserHandler(UserRepository repository) {
        this.repository = repository;
    }

    public CompletionStage<UserInfo> create(UserInput input) {
        User user = User.builder()
                .username(input.getUsername())
                .password(input.getPassword())
                .build();
        this.repository.save(user);
        CompletableFuture completableFuture = supplyAsync(new Supplier() {
            @Override
            public Object get() {
                return UserInfo.builder()
                        .input(input)
                        .lastUpdated(LocalDate.now());
            }
        });
        return completableFuture;
    }

    public Optional<User> login(UserInput input) {
        return this.repository.loginUser(input);
    }
}
