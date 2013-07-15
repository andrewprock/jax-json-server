package com.prock.jax.health;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.Mongo;
import com.mongodb.DB;

public class MongoHealthCheck extends HealthCheck {
    private String host;
    private int port;
    private String collection;

    public MongoHealthCheck(String host, int port, String collection) {
        this.host = host;
        this.port = port;
        this.collection = collection;
    }

    /**
     * To check the health of the MongoDB, it suffices to query for the
     * database names.  An exeption is thrown if the database is offline.
     */
    @Override
    protected Result check() throws Exception {
        Mongo mongo = new Mongo(host, port);
        mongo.getDatabaseNames();
        return Result.healthy();
    }
}