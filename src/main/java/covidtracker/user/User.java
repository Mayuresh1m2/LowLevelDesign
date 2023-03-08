package covidtracker.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class User {
    @Getter
    String userId;
    @Getter
    String password;
    @Setter
    @Getter
    boolean isAdmin;

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
