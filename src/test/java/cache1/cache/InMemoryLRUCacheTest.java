package cache1.cache;


import cache1.eviction.strategy.EvictionStrategyType;
import cache1.impl.Cache;
import cache1.storage.InMemoryStorage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InMemoryLRUCacheTest {
    Cache<String, String> inMemoryLRUCache;

    @Before
    public void setup() {
        inMemoryLRUCache = new Cache<>(5, new InMemoryStorage<>(EvictionStrategyType.LRU));
    }

    @Test
    public void testNormalOperation() {
        inMemoryLRUCache.put("1", "user1");
        inMemoryLRUCache.put("2", "user2");
        inMemoryLRUCache.put("3", "user3");
        inMemoryLRUCache.put("4", "user4");
        inMemoryLRUCache.put("5", "user5");
        inMemoryLRUCache.put("6", "user6");
        Assert.assertNull(inMemoryLRUCache.get("1"));
        Assert.assertEquals(inMemoryLRUCache.get("2"), "user2");
        Assert.assertEquals(inMemoryLRUCache.get("3"), "user3");
    }

    @Test
    public void testEviction() {
        inMemoryLRUCache.put("1", "user1");
        inMemoryLRUCache.put("2", "user2");
        inMemoryLRUCache.put("3", "user3");
        inMemoryLRUCache.put("4", "user4");
        inMemoryLRUCache.put("5", "user5");
        inMemoryLRUCache.get("1");
        inMemoryLRUCache.put("6", "user6");
        Assert.assertEquals(inMemoryLRUCache.get("1"), "user1");
        Assert.assertNull(inMemoryLRUCache.get("2"));
        Assert.assertEquals(inMemoryLRUCache.get("3"), "user3");
    }

    @Test
    public void testIFCacheEmptyReturnsProperResponse() {
        Assert.assertTrue(inMemoryLRUCache.isEmpty());
        inMemoryLRUCache.put("1", "user1");
        Assert.assertFalse(inMemoryLRUCache.isEmpty());
    }

    @Test
    public void testProperCacheSizeIsReturned() {
        Assert.assertEquals(0, inMemoryLRUCache.getSize());
        inMemoryLRUCache.put("1", "user1");
        Assert.assertEquals(1, inMemoryLRUCache.getSize());
        inMemoryLRUCache.put("1", "user1");
        Assert.assertEquals(1, inMemoryLRUCache.getSize());
        inMemoryLRUCache.put("2", "user2");
        Assert.assertEquals(2, inMemoryLRUCache.getSize());
        inMemoryLRUCache.put("3", "user2");
        inMemoryLRUCache.put("4", "user2");
        inMemoryLRUCache.put("5", "user2");
        inMemoryLRUCache.put("6", "user2");
        inMemoryLRUCache.put("7", "user2");
        Assert.assertEquals(5, inMemoryLRUCache.getSize());
    }
}
