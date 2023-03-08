package covidtracker.session;

import java.util.UUID;

public final class SessionUtil {
    public static String generateSessionTokenId() {
        return UUID.randomUUID().toString();
    }
}
