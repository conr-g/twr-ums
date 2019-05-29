package model;

import lombok.Value;

import java.time.LocalDate;

@Value
public class UserInfo {
    private final UserInput input;
    private final LocalDate lastUpdated;
    public UserInfo(UserInput input, LocalDate lastUpdated){
        this.input = input;
        this.lastUpdated = lastUpdated;
    }

    public static UserInfoBuilder builder() {
        return new UserInfoBuilder();
    }

    public UserInput getInput() {
        return this.input;
    }

    public LocalDate getLastUpdated() {
        return this.lastUpdated;
    }

    public static class UserInfoBuilder {
        private UserInput input;
        private LocalDate lastUpdated;

        UserInfoBuilder() {
        }

        public UserInfoBuilder input(UserInput input) {
            this.input = input;
            return this;
        }

        public UserInfoBuilder lastUpdated(LocalDate lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public UserInfo build() {
            return new UserInfo(input, lastUpdated);
        }

        public String toString() {
            return "UserInfo.UserInfoBuilder(input=" + this.input + ", lastUpdated=" + this.lastUpdated + ")";
        }
    }
}
