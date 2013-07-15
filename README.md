This is a simple demonstration project which implements a JAX-RS
server using the Dropwizard Java framework.

The project was created using the dropwizard-example as a template.

To build the project use maven to create the package:

    mvn package

Before running the project, mongodb must be installed and running on the
system.  Once mongodb is installed, no further configuration should be
required.

The database parameters are defined in json-server.yml, and by default
are set to:

    databaseHost: localhost
    databaseName: mydb
    collection: testData

The server expect mydb.testData to be populated with some kind of
data.  If there is no such collection, the server will not be able to
serve any documents.

The following command will start the server:

    java -jar target/json-server-0.7.0-SNAPSHOT.jar server json-server.yml

Once the server is started, you can query the database using curl.  If
used the xml-loader project to load the sample data into MongoDB,
execute the following query:

    curl "http://localhost:8080/jsondoc?field=test.attrib&value=moretest"

If everything works you should see the following json returned:

    {"id":87,"content":"{ \"_id\" : { \"$oid\" : \"51e3fa51b7beb0e410584c24\"} , \"test\" : { \"attrib\" : \"moretest\" , \"content\" : \"Turn this to JSON\"}}"}

A basic health check was implemented to monitor the status of MongoDB:

    curl "http://192.168.1.119:8081/healthcheck