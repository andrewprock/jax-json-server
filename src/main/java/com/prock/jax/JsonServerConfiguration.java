package com.prock.jax;

import com.codahale.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class JsonServerConfiguration extends Configuration {
    @NotEmpty
    private String databaseHost;

    @NotEmpty
    private String databaseName;
    
    @NotEmpty
    private String collection;
    
    @JsonProperty
    public String getDatabaseHost() {
        return databaseHost;
    }

    //@JsonProperty
    public int getDatabasePort() {
        return 27017;
    }

    @JsonProperty
    public String getDatabaseName() {
        return databaseName;
    }

    @JsonProperty
    public String getCollection() {
        return collection;
    }
}
