package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;



@Value
public class UserInput {
    private final String username;
    private final String password;

    @JsonCreator
    public UserInput(
            @JsonProperty("username") String username,
            @JsonProperty("password") String password ){
        this.username = username;
        this.password = password;
    }

    public static UserInputBuilder builder() {
        return new UserInputBuilder();
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public static class UserInputBuilder {
        private String username;
        private String password;

        UserInputBuilder() {
        }

        public UserInputBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserInputBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserInput build() {
            return new UserInput(username, password);
        }

        public String toString() {
            return "UserInput.UserInputBuilder(username=" + this.username + ", password=" + this.password + ")";
        }
    }
}
