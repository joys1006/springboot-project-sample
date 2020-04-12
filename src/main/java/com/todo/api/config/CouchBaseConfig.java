package com.todo.api.config;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.spring.cache.CacheBuilder;
import com.couchbase.client.spring.cache.CouchbaseCacheManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


import java.util.HashMap;
import java.util.Map;

@EnableCaching
@Configuration
@Slf4j
public class CouchBaseConfig {

    @Autowired
    CacheConfig cacheConfig;

    @Autowired
    private Environment env;

    @Bean
    public Bucket serviceBucket() {
        CouchBaseConnector couchBaseConnector = new CouchBaseConnector(
                env.getProperty("couchbaseCache.serverNodes", String[].class),
                env.getProperty("couchbaseCache.bucketName"),
                env.getProperty("couchbaseCache.bucketPassword"),
                env
        );
        return couchBaseConnector.bucket();
    }

    @Bean
    public CacheManager cacheManager() {

        Map<String, CacheBuilder> cacheBuilders = new HashMap<>();

        for (Map.Entry<String, Integer> entry : cacheConfig.getExpiration().entrySet()){
            CacheBuilder cacheBuilder = CacheBuilder.newInstance(serviceBucket()).withExpiration(entry.getValue());
            cacheBuilders.put(entry.getKey(), cacheBuilder);
        }

        return new CouchbaseCacheManager(cacheBuilders);
    }

}
