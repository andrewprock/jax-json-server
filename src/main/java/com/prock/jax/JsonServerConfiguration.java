package com.prock.jax;

import com.codahale.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;							// new
import javax.validation.constraints.NotNull;			// new

public class JsonServerConfiguration extends Configuration {
}
