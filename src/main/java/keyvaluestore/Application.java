package keyvaluestore;

import keyvaluestore.store.KeyValueStore;
import keyvaluestore.store.impl.InMemoryKeyValueStore;

public class Application {
    public static void main(String[] args) {
        KeyValueStore<String, String> keyValueStore = new InMemoryKeyValueStore<String, String>();
        String tid = "1";
        keyValueStore.beginTransaction(tid);
        keyValueStore.set("user4", "user4", tid);
        keyValueStore.rollback(tid);
        System.out.println(keyValueStore.get("user4"));// should return null
        keyValueStore.beginTransaction(tid);
        keyValueStore.set("user4", "user4", tid);
        keyValueStore.commit(tid);
        System.out.println(keyValueStore.get("user4"));// should return value


    }


}
