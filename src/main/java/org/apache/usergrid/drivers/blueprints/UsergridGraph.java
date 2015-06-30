package org.apache.usergrid.drivers.blueprints;

import com.sun.javaws.exceptions.InvalidArgumentException;
import com.tinkerpop.blueprints.*;
import org.apache.commons.configuration.Configuration;
import org.apache.usergrid.java.client.Client;
import org.apache.usergrid.java.client.model.EntityId;

import java.util.Iterator;
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

  /**
   * @param config
   */
  public UsergridGraph(Configuration config) {
    this.defaultType = config.getString("usergrid.defaultType");
    System.out.println(config.getString("usergrid.defaultType"));
  }

  /**
   * @return
   */
  public Features getFeatures() {
    return features;
  }


  /**
   * This will call the client and create a new entity in the default collection
   * using the ID.toString() as the name of the entitiy.
   *
   * @param id - The value of id.toString would be used for the name
   * @return the newly created vertex
   */
  public Vertex addVertex(Object id) {

    if (id instanceof String) {
      UsergridVertex v = new UsergridVertex(defaultType);
      v.post();

    /*
     1) check client initialized
     2) check that id is a string (type)
     3) create the entity
     4)
     */

      return v;
    }

    throw new IllegalArgumentException("Supplied id class of " + String.valueOf(id.getClass()) + " is not supported");
  }

  //new SimpleId("user", new UUID())
  public Vertex getVertex(Object id) {

    if (id instanceof String) {
      return getVertexByString((String) id);
    } else {
      if (id instanceof EntityId) {
        return getVertexByEntityId((EntityId) id);
      }
    }

    throw new IllegalArgumentException("Supplied id class of " + String.valueOf(id.getClass()) + " is not supported");
  }

  private Vertex getVertexByEntityId(EntityId id) {
    return null;
  }

  private Vertex getVertexByString(String id) {
    return null;
  }

  public void removeVertex(Vertex vertex) {

  }

  public Iterable<Vertex> getVertices() {
    // need to be able to page
    return null;
  }

  public Iterable<Vertex> getVertices(String key, Object value) {
    return null;
  }


  /**
   * This function adds a connection (or an edge) betwwen two vertices
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
    return null;
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
   *
   */
  public void shutdown() {
    /*
    1. Check the client initialized.
    2. Close the connection to Usergrid.
    3. Error handling if closeConnection() failed.
     */
  }
}