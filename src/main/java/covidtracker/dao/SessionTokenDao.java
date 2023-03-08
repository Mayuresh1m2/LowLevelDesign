package covidtracker.dao;

import covidtracker.session.SessionToken;

public interface SessionTokenDao {
    void addSessionToken(SessionToken sessionToken, String userId);

    SessionToken getSessionToken(String userId);
}
