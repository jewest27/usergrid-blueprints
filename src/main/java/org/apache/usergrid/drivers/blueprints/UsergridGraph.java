package org.apache.usergrid.drivers.blueprints;

import com.sun.javaws.exceptions.InvalidArgumentException;
import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import com.tinkerpop.blueprints.*;
import org.apache.commons.configuration.Configuration;
import org.apache.usergrid.java.client.Client;
import org.apache.usergrid.java.client.SingletonClient;
import org.apache.usergrid.java.client.response.ApiResponse;
//import org.apache.usergrid.java.client.model.EntityId;

import javax.swing.text.html.parser.Entity;
import java.util.Iterator;
import java.util.Properties;
import java.util.UUID;

/**
 * Created by ApigeeCorporation on 6/29/15.
 */
public class UsergridGraph implements Graph {

    private static Features features;

    static {
        features = new Features();
        /**
         * Does the graph allow for two edges with the same vertices and edge label to exist?
         */
        features.supportsDuplicateEdges = Boolean.FALSE;

        /**
         * Does the graph allow an edge to have the same out/tail and in/head vertex?
         */
        features.supportsSelfLoops = Boolean.FALSE;

        /**
         * Does the graph allow any serializable object to be used as a property value for a graph element?
         */
        features.supportsSerializableObjectProperty = Boolean.FALSE;

        /**
         * Does the graph allows boolean to be used as a property value for a graph element?
         */
        features.supportsBooleanProperty = Boolean.TRUE;

        /**
         * Does the graph allows double to be used as a property value for a graph element?
         */
        features.supportsDoubleProperty = Boolean.TRUE;

        /**
         * Does the graph allows float to be used as a property value for a graph element?
         */
        features.supportsFloatProperty = Boolean.TRUE;

        /**
         * Does the graph allows integer to be used as a property value for a graph element?
         */
        features.supportsIntegerProperty = Boolean.TRUE;

        /**
         * Does the graph allows a primitive array to be used as a property value for a graph element?
         */
        features.supportsPrimitiveArrayProperty = Boolean.TRUE;

        /**
         * Does the graph allows list (all objects with the list have the same data types) to be used as a property
         * value for a graph element?
         */
        features.supportsUniformListProperty = Boolean.TRUE;

        /**
         * Does the graph allows a mixed list (different data types within the same list) to be used as a
         * property value for a graph element?
         */
        features.supportsMixedListProperty = Boolean.FALSE;

        /**
         * Does the graph allows long to be used as a property value for a graph element?
         */
        features.supportsLongProperty = Boolean.TRUE;

        /**
         * Does the graph allows map to be used as a property value for a graph element?
         */
        features.supportsMapProperty = Boolean.TRUE;

        /**
         * Graph allows string to be used as a property value for a graph element.
         */
        features.supportsStringProperty = Boolean.TRUE;

        /**
         * Does the graph return elements not explicitly created with addVertex or addEdge?
         */
        features.hasImplicitElements = Boolean.TRUE;

        /**
         * Does the graph ignore user provided ids in graph.addVertex(Object id)?
         */
        features.ignoresSuppliedIds = Boolean.FALSE;

        /**
         * Does the graph persist the graph to disk after shutdown?
         */
        features.isPersistent = Boolean.TRUE;

        /**
         * Does the graph implement WrapperGraph?
         */
        features.isWrapper = Boolean.FALSE;

        /**
         * Does the graph implement IndexableGraph?
         */
        features.supportsIndices = Boolean.FALSE;

        /**
         * Does the graph support the indexing of vertices by their properties?
         */
        features.supportsVertexIndex = Boolean.FALSE;

        /**
         * Does the graph support the indexing of edges by their properties?
         */
        features.supportsEdgeIndex = Boolean.FALSE;

        /**
         * Does the graph implement KeyIndexableGraph?
         */
        features.supportsKeyIndices = Boolean.FALSE;

        /**
         * Does the graph support key indexing on vertices?
         */
        features.supportsVertexKeyIndex = Boolean.FALSE;

        /**
         * Does the graph support key indexing on edges?
         */
        features.supportsEdgeKeyIndex = Boolean.FALSE;

        /**
         * Does the graph support graph.getEdges()?
         */
        features.supportsEdgeIteration = Boolean.FALSE;

        /**
         * Does the graph support graph.getVertices()?
         */
        features.supportsVertexIteration = Boolean.FALSE;

        /**
         * Does the graph support retrieving edges by id, i.e. graph.getEdge(Object id)?
         */
        features.supportsEdgeRetrieval = Boolean.FALSE;

        /**
         * Does the graph support setting and retrieving properties on vertices?
         */
        features.supportsVertexProperties = Boolean.TRUE;

        /**
         * Does the graph support setting and retrieving properties on edges?
         */
        features.supportsEdgeProperties = Boolean.FALSE;

        /**
         * Does the graph implement TransactionalGraph?
         */
        features.supportsTransactions = Boolean.FALSE;

        /**
         * Does the graph implement ThreadedTransactionalGraph?
         */
        features.supportsThreadedTransactions = Boolean.FALSE;

        /**
         * Does the graph support transactions managed such that multiple threads operating on the same graph instance
         * can have isolated transactions?
         */
        features.supportsThreadIsolatedTransactions = Boolean.FALSE;
    }

    private Client client;
    private String defaultType;

    protected void getClient() {

    }

    /**
     * @param config
     */
    public UsergridGraph(Configuration config) {

//TODO: Change to appropriate location
        if (config == null) {
            throw new IllegalArgumentException("Check the configuration settings");
        }
        this.defaultType = config.getString("usergrid.defaultType");

        String orgName = config.getString("usergrid.organization");
        String appName = config.getString("usergrid.application");
        String apiUrl = config.getString("usergrid.apiUrl");
        String clientId = config.getString("usergrid.client_id");
        String clientSecret = config.getString("usergrid.client_secret");


        if (orgName == null || appName == null) {
            throw new RuntimeException("Check the configuration settings. OrgName or App name is null");
        }

        if (apiUrl == null)
            SingletonClient.initialize(orgName, appName);
        else
            SingletonClient.initialize(apiUrl, orgName, appName);

        client = SingletonClient.getInstance();

        client.authorizeAppClient(clientId, clientSecret);


    }

    /**
     * This returns all the features that the Blueprint supports for Usergrid.
     *
     * @return
     */
    public Features getFeatures() {
        return features;
    }


    /**
     * This calls the client and create a new entity in the default collection
     * using the ID.toString() as the name of the entity. It returns the newly created vertex.
     *
     * @param id - The value of id.toString would be used for the name
     * @return the newly created vertex
     */
    public Vertex addVertex(Object id) {

      /*
     1) Check if client is initialized
     2) Check that id is of supported type, else throw IllegalArgumentException error
     3) Create the entity using - ApiResponse createEntity(Map<String, Object> properties)
      in org.apache.usergrid.java.client
     4) Return the newly created vertex
     */

        assertClientInitialized();


          if (id instanceof String) {

              String[] parts = id.toString().split(":");
              String type = parts[0];
              String StringUUID = parts[1];
              UsergridVertex v = new UsergridVertex(type);
              client.createEntity(v);
              v.save();
              //v.setProperty("name",StringUUID);
              return v;
          }


          /*
          else if (id instanceof EntityId){
              //TODO: Add logic that separates the type and entity ID and use the type during creation
              UsergridVertex v = new UsergridVertex(defaultType);
              client.createEntity(v);
              v.save();
              return v;

      }
*/
        throw new IllegalArgumentException("Supplied id class of " + String.valueOf(id.getClass()) + " is not supported");

    }


    protected void assertClientInitialized() {
        if (client == null) {
            //TODO: Initialize client? OR throw exception?
            throw new IllegalArgumentException("Client is not initialized");
        }
    }

    /**
     * This gets a particular Vertex (entity) using the ID of the vertex
     *
     * @param id
     * @return
     */

    public Vertex getVertex(Object id) {

    /*
     1) Check if client is initialized
     2) Check that id is of supported type, else throw IllegalArgumentException error
     3) Get and return the entity - Query queryEntitiesRequest(HttpMethod method,Map<String,
     Object> params, Object data, String... segments) in org.apache.usergrid.java.client
     4) Return null if no vertex is referenced by the identifier
     */


        if (id instanceof String) {
            return getVertexByString((String) id);
        } else {
            if (id instanceof EntityId) {
                return getVertexByEntityId((EntityId) id);
            }
        }

        throw new IllegalArgumentException("Supplied id class of " + String.valueOf(id.getClass()) + " is not supported");
    }


    /**
     * This gets a particular vertex using the Entity ID.
     *
     * @param id
     * @return
     */

    private Vertex getVertexByEntityId(EntityId id)
     /*
     1) Check if client is initialized
     2) Check that id is of EntityId (type)
     3) Get and return the entity
     4) Return null if no vertex is referenced by the identifier
     */

    {

        ApiResponse response = SingletonClient.getInstance().queryEntity(id.type, id.UUID);
        String uuid = response.getFirstEntity().getStringProperty("uuid");
        UsergridVertex v = new UsergridVertex(id.type);
        v.setUuid(UUID.fromString(uuid));
        return v;

    }


    /**
     * This gets a vertex by ID (String)
     *
     * @param id
     * @return
     */
    private Vertex getVertexByString(String id) {
     /*
     1) Check if client is initialized
     2) Check that id is a string (type)
     3) Get and return the entity
     4) Return null if no vertex is referenced by the identifier
     */

        String[] parts = id.split(":");
        String type = parts[0];
        String StringUUID = parts[1];
        ApiResponse response = SingletonClient.getInstance().queryEntity(type, StringUUID);
        String uuid = response.getFirstEntity().getStringProperty("uuid");
        UsergridVertex v = new UsergridVertex(type);
        v.setUuid(UUID.fromString(uuid));
        return v;

    }


    /**
     * This deletes a particular vertex (entity) by taking the vertex as an identifier
     *
     * @param vertex
     */

    public void removeVertex(Vertex vertex) {

     /*
     1) Check if client is initialized
     2) Check if vertex exists
     3) Delete all edges connected to the vertex using disconnectEntities(String connectingEntityType,
     String connectingEntityId, String connectionType, String connectedEntityId) in org.apache.usergrid.java.client
     4) Delete the vertex //TODO: The method delete() is defined in org.apache.usergrid.java.client.entities but has not been implemented
     5) Return null if no vertex is referenced by the identifier
     */
      String id = vertex.getId().toString();
      String[] parts = id.split(":");
      String type = parts[0];
      String StringUUID = parts[1];
      ApiResponse response = SingletonClient.getInstance().deleteEntity(type, StringUUID);
      System.out.println(response);


    }






  /**
   * {
   throw new UnsupportedOperationException("Not supported for Usergrid");
   }
   * Return an iterable to all the vertices in the graph that have a particular key/value property.
   * @param key
   * @param value
   * @return
   */
  public Iterable<Vertex> getVertices(String key, Object value) {
    return null;
  }



    /**
     * {
     * throw new UnsupportedOperationException("Not supported for Usergrid");
     * }
     * Returns an iterable to all the vertices in the graph.
     *
     * @return
     */
    public Iterable<Vertex> getVertices() {
        // need to be able to page
        return null;
    }





    /**
     * This function adds a connection (or an edge) between two vertices
     *
     * @param id
     * @param outVertex
     * @param inVertex
     * @param label
     * @return
     */
    public Edge addEdge(Object id, Vertex outVertex, Vertex inVertex, String label) {

        /*
        1. Check client initialized.
        2. Check if the two vertices are valid.
        3. Retrieve the EntityIds of the two entities
        3. Call connectEntities( String connectingEntityType, String connectingEntityId, String connectionType, String connectedEntityId)
        4. Return the connection(or edge) // TODO : currently returns ApiResponse. Should return an edge.
         */

        //Check client initialized.
        assertClientInitialized();

        //check if the connection name is passed.
        if (label == null)
            throw new IllegalArgumentException("label not specified");

        //TODO : will uncomment once getId for vertex is implemented.
//        if (getVertex(outVertex.getId()) == null || getVertex(inVertex.getId()) == null){
//            throw new IllegalArgumentException("the vertices to connect are invalid");
//        }

        UsergridEdge e = new UsergridEdge((UsergridVertex) outVertex, (UsergridVertex) inVertex, label,client);
        UsergridVertex source = (UsergridVertex) outVertex;
        UsergridVertex target = (UsergridVertex) inVertex;

        client.connectEntities(source, target, label);

//        System.out.println("prop id : " + e.getId());
        return e;

    }

    /**
     * This function returns a connection (or edge). Takes the Connection id as an input.
     *
     * @param id
     * @return
     */

    public Edge getEdge(Object id) {

    /*
    //TODO: Define the ObjectId for an edge, liek how we have for a vertex.

    1. Get the client. Check if client initialzed.
    2. Get the edge using the type of the edge. // TODO : how to retrieve an edge in usergrid. multiple edges have the same name, how to distinguish ?
    3. Return the connection(or edge).
     */
        return null;
    }

    /**
     * This function removes the connection between two entities in the graph
     *
     * @param edge
     */
    public void removeEdge(Edge edge) {

    /*
    1. Get the client. Check if its intitialzed.
    2. Get the connection(or edge) by the Id //TODO : how to retrieve an edge.
    3. Check if the edge is a valid edge.
    4. call disconnectEntities(String connectingEntityType, String connectingEntityId, String connectionType, String connectedEntityId)

    */
    }

    /**
     * Not Implemented for Usergrid
     *
     * @return
     */

    public Iterable<Edge> getEdges() {
        throw new UnsupportedOperationException("Not supported for Usergrid");
    }

    /**
     * Not implemented for Usergrid.
     * Return an iterable to all the edges in the graph that have a particular key/value property.
     *
     * @param key
     * @param value
     * @return
     */
    public Iterable<Edge> getEdges(String key, Object value) {
        throw new UnsupportedOperationException("Not supported for Usergrid");
    }


    public GraphQuery query() {
        return null;
    }

    /**
     * Closes the client connection. Properly close the graph.
     */
    public void shutdown() {
    /*
    1. Check the client initialized.
    2. Close the connection to Usergrid.
    3. Error handling if closeConnection() failed.
     */
    }
}
