package covidtracker.session;

import lombok.Getter;

public class SessionToken {
    String sessionTokenId;
    @Getter
    String userId;
    long expiryTime;

    public SessionToken(String sessionTokenId, String userId, long expiryTime) {
        this.userId = userId;
        this.sessionTokenId = sessionTokenId;
        this.expiryTime = expiryTime;
    }
}
