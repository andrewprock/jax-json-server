package com.prock.jax;

import com.codahale.dropwizard.Application;
import com.codahale.dropwizard.setup.Bootstrap;
import com.codahale.dropwizard.setup.Environment;
import com.prock.jax.resources.*;
import com.prock.jax.health.*;

public class JsonServerApplication extends Application<JsonServerConfiguration> {
    public static void main(String[] args) throws Exception {
        new JsonServerApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<JsonServerConfiguration> bootstrap) {
        // not much here.
    }

    @Override
    public void run(JsonServerConfiguration configuration,
                    Environment environment) throws ClassNotFoundException {

        environment.healthChecks().register("mongodb", new MongoHealthCheck(configuration.getDatabaseHost(),
                                                                            configuration.getDatabasePort(),
                                                                            configuration.getCollection()));
        environment.jersey().register(new JsonDocumentResource(configuration.getDatabaseHost(), 
                                                               configuration.getDatabasePort(),
                                                               configuration.getDatabaseName(),
                                                               configuration.getCollection()));
    }
}
