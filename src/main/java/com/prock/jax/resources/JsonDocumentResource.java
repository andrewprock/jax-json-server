package com.prock.jax.resources;

import com.codahale.metrics.annotation.Timed;
import com.prock.jax.core.JsonDoc;
import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.TimeUnit;

@Path("/jsondoc")
@Produces(MediaType.APPLICATION_JSON)
public class JsonDocumentResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonDocumentResource.class);

    // The document resource pulls it's documents out of mongo.
	private Mongo mongo;
	private DB db;
    DBCollection collection;

    public JsonDocumentResource(String host, int port, String database, String inputCollection) {
		try {
			this.mongo = new Mongo(host, port);
			this.db = mongo.getDB(database);
            this.collection = db.getCollection(inputCollection);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
    }

    // Retrieve document from mongo based on field/value
    public String retrieveJson(String field, Object value) {
        BasicDBObject fields = new BasicDBObject();
        fields.put(field, value);

        DBCursor result = this.collection.find(fields);
        String document = result.next().toString();
        return document;
    }

    @GET
    @Timed(name = "get-requests")
    public JsonDoc getJsonDocument(@QueryParam("field") String field, 
                                   @QueryParam("value") String value) {
        String json = retrieveJson(field, value);
        return new JsonDoc(87, json);
    }
}
