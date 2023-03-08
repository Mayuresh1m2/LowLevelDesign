package covidtracker.dao.impl;

import covidtracker.dao.SessionTokenDao;
import covidtracker.session.SessionToken;

import java.util.HashMap;
import java.util.Map;

public class InMemorySessionTokenDao implements SessionTokenDao {
    Map<String, SessionToken> sessionTokenMap;

    public InMemorySessionTokenDao() {
        this.sessionTokenMap = new HashMap<>();
    }

    @Override
    public void addSessionToken(SessionToken sessionToken, String userId) {
        sessionTokenMap.put(userId, sessionToken);
    }

    @Override
    public SessionToken getSessionToken(String userId) {
        return sessionTokenMap.get(userId);
    }


}
