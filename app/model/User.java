package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    @JsonProperty
    private final String username;
    @JsonProperty
    private final String password;
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public static class UserBuilder {
        private String username;
        private String password;

        UserBuilder() {
        }

        public User.UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public User.UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(username, password);
        }

        public String toString() {
            return "User.UserBuilder(username=" + this.username + ", password=" + this.password + ")";
        }
    }
}