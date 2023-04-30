package com.srivn.works.smusers.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

class CachingConfigTest {
    @Test
    void test_cacheManager() {
        // Arrange and Act
        CacheManager actualCacheManagerResult = (new CachingConfig()).cacheManager();

        // Assert
        assertEquals(1, actualCacheManagerResult.getCacheNames().size());
        assertFalse(((ConcurrentMapCacheManager) actualCacheManagerResult).isStoreByValue());
        assertTrue(((ConcurrentMapCacheManager) actualCacheManagerResult).isAllowNullValues());
    }
}

