package paymentgateway.client;

import java.util.UUID;

public final class ClientUtils {
    public static String generateClientId(){
        return UUID.randomUUID().toString();
    }
}
