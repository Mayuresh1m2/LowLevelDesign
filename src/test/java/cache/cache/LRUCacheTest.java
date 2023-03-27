package cache.cache;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class LRUCacheTest {
    LRUCache<String, String> cache;
    @Before
    public void setup(){
        cache = new LRUCache<>(5);
    }
    @Test
    public void testNormalOperation() {
        cache.put("1","user1");
        cache.put("2","user2");
        cache.put("3","user3");
        cache.put("4","user4");
        cache.put("5","user5");
        cache.put("6","user6");
        Assert.assertEquals(cache.get("1"), Optional.empty());
        Assert.assertEquals(cache.get("2"), Optional.of("user2"));
        Assert.assertEquals(cache.get("3"), Optional.of("user3"));
    }

    @Test
    public void testEviction() {
        cache.put("1","user1");
        cache.put("2","user2");
        cache.put("3","user3");
        cache.put("4","user3");
        cache.put("5","user3");
        cache.get("1");
        cache.put("6","user3");
        Assert.assertEquals(cache.get("1"), Optional.of("user1"));
        Assert.assertEquals(cache.get("2"), Optional.empty());
        Assert.assertEquals(cache.get("3"), Optional.of("user3"));
    }

    @Test
    public void testIFCacheEmptyReturnsProperResponse(){
        Assert.assertEquals(cache.isEmpty(),true);
        cache.put("1","user1");
        Assert.assertEquals(cache.isEmpty(),false);
    }

    @Test
    public void testProperCacheSizeIsReturned(){
        Assert.assertEquals(0, cache.size());
        cache.put("1","user1");
        Assert.assertEquals(1, cache.size());
        cache.put("1","user1");
        Assert.assertEquals(1, cache.size());
        cache.put("2","user2");
        Assert.assertEquals(2, cache.size());
        cache.put("3","user2");
        cache.put("4","user2");
        cache.put("5","user2");
        cache.put("6","user2");
        cache.put("7","user2");
        Assert.assertEquals(5,cache.size());
    }

    @Test
    public void testClearCacheEmptiesTheCache(){
        cache.put("3","user2");
        cache.put("4","user2");
        cache.put("5","user2");
        Assert.assertEquals(3,cache.size());
        cache.clear();
        Assert.assertEquals(0,cache.size());
    }
}
