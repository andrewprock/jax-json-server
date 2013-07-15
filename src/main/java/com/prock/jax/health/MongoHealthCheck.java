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

    @Override
    protected Result check() throws Exception {
        // To check MongoDB, it suffices to query for the dtabase names.
        // An exeption is thrown if the database is offline.
        Mongo mongo = new Mongo(host, port);
        mongo.getDatabaseNames();
        return Result.healthy();
    }
}