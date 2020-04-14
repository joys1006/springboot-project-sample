package com.todo.api.config;

import com.couchbase.client.java.AsyncBucket;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.core.env.Environment;

import java.util.concurrent.TimeUnit;

@Slf4j
public class CouchBaseConnector implements DisposableBean {

    private static final Logger LOG = LoggerFactory.getLogger(CouchBaseConnector.class);

    public static final int DEFAULT_BUCKET_OPEN_TIMEOUT = 100 * 1000; // 100 seconds
    public static final int DEFAULT_IO_POOL_SIZE = 20;
    public static final int DEFAULT_COMPUTATION_POOL_SIZE = 20;
    public static final int DEFAULT_CONNECTION_TIME_OUT = 20000;

    private final Bucket bucket;

    private final Environment env;

    public CouchBaseConnector(String serverNodes, String bucketName, String bucketPassword, Environment env) {
        this.env = env;
        bucket = connect(serverNodes, bucketName, bucketPassword);
    }

    public CouchBaseConnector(String[] serverNodes, String bucketName, String bucketPassword, Environment env) {
        this.env = env;
        bucket = connect(serverNodes, bucketName, bucketPassword);
    }

    @Override
    public void destroy() throws Exception {
        disconnect();
    }

    public Bucket getBucket() {
        return this.bucket;
    }

    public CouchbaseEnvironment couchbaseEnvironment() {
        int computationPoolSize = (env.getProperty("couchbaseCache.computationPoolSize", Integer.class) == null) ?
                DEFAULT_COMPUTATION_POOL_SIZE : env.getProperty("couchbaseCache.computationPoolSize", Integer.class);

        int connetionTimeOut = (env.getProperty("couchbaseCache.connectionTimeOut", Integer.class) == null) ?
                DEFAULT_CONNECTION_TIME_OUT : env.getProperty("couchbaseCache.connectionTimeOut", Integer.class);

        int ioPoolSize = (env.getProperty("couchbaseCache.ioPoolSize", Integer.class) == null) ?
                DEFAULT_IO_POOL_SIZE : env.getProperty("couchbaseCache.ioPoolSize", Integer.class);

        return DefaultCouchbaseEnvironment
                .builder()
                .ioPoolSize(ioPoolSize)
                .computationPoolSize(computationPoolSize)
                .connectTimeout(connetionTimeOut)
                .build();
    }

    private Bucket connect(String serverNodes, String bucketName, String bucketPassword) {
        return connect(serverNodes.split(","), bucketName, bucketPassword);
    }

    public Bucket connect(String[] serverNodes, String bucketName, String bucketPassword) {
        System.out.println(bucketName + "-" + bucketPassword);
        try {
            CouchbaseEnvironment environment = couchbaseEnvironment();
            Cluster cluster = CouchbaseCluster.create(environment, serverNodes);
            return cluster.openBucket(bucketName, bucketPassword, DEFAULT_BUCKET_OPEN_TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            LOG.error("CONNECTION NOT ESTABLEISHED", e);
            return null;
        }
    }

    public void disconnect() {
        try {
            bucket.close();
            LOG.info("CouchBase connection closed successfully.");
        } catch (Exception e) {
            LOG.info("ERROR WHILE CONNECTION CLOSE:", e);
        }
    }

    public Bucket bucket() {
        return bucket;
    }

    public AsyncBucket asyncBucket() {
        return bucket.async();
    }

}
