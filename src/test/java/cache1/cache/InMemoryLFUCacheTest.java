package cache1.cache;


import cache1.eviction.strategy.EvictionStrategyType;
import cache1.impl.Cache;
import cache1.storage.InMemoryStorage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class InMemoryLFUCacheTest {
    cache1.impl.Cache<String, String> cache;

    @Before
    public void setup() {
        cache = new Cache<>(5, new InMemoryStorage<>(EvictionStrategyType.LFU));
    }

    @Test
    public void testNormalOperation() {
        cache.put("1", "user1");
        cache.put("2", "user2");
        cache.put("3", "user3");
        cache.put("4", "user4");
        cache.put("5", "user5");
        cache.put("6", "user6");
        Assert.assertNull(cache.get("1"));
        Assert.assertEquals(cache.get("2"), "user2");
        Assert.assertEquals(cache.get("3"), "user3");
    }

    @Test
    public void testEviction() throws InterruptedException {
        //Sleep is important otherwise all the timestamps are same and thus there is no way
        // to maintain insertion ordering in queue
        cache.put("1", "user1");
        Thread.sleep(10);
        cache.put("2", "user2");
        Thread.sleep(10);
        cache.put("3", "user3");
        Thread.sleep(10);
        cache.put("4", "user4");
        Thread.sleep(10);
        cache.put("5", "user5");
        Thread.sleep(10);
        cache.get("1");
        cache.get("2");
        cache.get("2");
        cache.get("2");
        cache.put("6", "user6");
        Assert.assertEquals(cache.get("6"), "user6");
        Assert.assertEquals(cache.get("3"), null);
        Assert.assertEquals(cache.get("4"), "user4");
    }

    @Test
    public void testIFCacheEmptyReturnsProperResponse() {
        Assert.assertTrue(cache.isEmpty());
        cache.put("1", "user1");
        Assert.assertFalse(cache.isEmpty());
    }

    @Test
    public void testProperCacheSizeIsReturned() {
        Assert.assertEquals(0, cache.getSize());
        cache.put("1", "user1");
        Assert.assertEquals(1, cache.getSize());
        cache.put("1", "user1");
        Assert.assertEquals(1, cache.getSize());
        cache.put("2", "user2");
        Assert.assertEquals(2, cache.getSize());
        cache.put("3", "user2");
        cache.put("4", "user2");
        cache.put("5", "user2");
        cache.put("6", "user2");
        cache.put("7", "user2");
        Assert.assertEquals(5, cache.getSize());
    }

}
