package org.apache.usergrid.drivers.blueprints;

import com.tinkerpop.blueprints.*;
import org.apache.commons.configuration.Configuration;
import org.apache.usergrid.java.client.Client;

import java.util.Iterator;

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
    features.supportsBooleanProperty = Boolean.FALSE;
    /**
     * Does the graph allows double to be used as a property value for a graph element?
     */
    features.supportsDoubleProperty = Boolean.FALSE;
    /**
     * Does the graph allows float to be used as a property value for a graph element?
     */
    features.supportsFloatProperty = Boolean.FALSE;
    /**
     * Does the graph allows integer to be used as a property value for a graph element?
     */
    features.supportsIntegerProperty = Boolean.FALSE;
    /**
     * Does the graph allows a primitive array to be used as a property value for a graph element?
     */
    features.supportsPrimitiveArrayProperty = Boolean.FALSE;
    /**
     * Does the graph allows list (all objects with the list have the same data types) to be used as a property
     * value for a graph element?
     */
    features.supportsUniformListProperty = Boolean.FALSE;
    /**
     * Does the graph allows a mixed list (different data types within the same list) to be used as a
     * property value for a graph element?
     */
    features.supportsMixedListProperty = Boolean.FALSE;
    /**
     * Does the graph allows long to be used as a property value for a graph element?
     */
    features.supportsLongProperty = Boolean.FALSE;
    /**
     * Does the graph allows map to be used as a property value for a graph element?
     */
    features.supportsMapProperty = Boolean.FALSE;
    /**
     * Graph allows string to be used as a property value for a graph element.
     */
    features.supportsStringProperty = Boolean.FALSE;
    /**
     * Does the graph return elements not explicitly created with addVertex or addEdge?
     */
    features.hasImplicitElements = Boolean.FALSE;
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
    features.supportsVertexProperties = Boolean.FALSE;
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
    UsergridVertex v = new UsergridVertex(defaultType);
    v.post();

    /*
     1) check client initialized
     2) check that id is a string
     3) create the entity
     4)
     */

    return v;
  }

  public Vertex getVertex(Object id) {
    // assumes id is uuid or name

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

  public Edge addEdge(Object id, Vertex outVertex, Vertex inVertex, String label) {
    return null;
  }

  public Edge getEdge(Object id) {
    return null;
  }

  public void removeEdge(Edge edge) {

  }

  public Iterable<Edge> getEdges() {
    return null;
  }

  public Iterable<Edge> getEdges(String key, Object value) {
    return null;
  }

  public GraphQuery query() {
    return null;
  }

  public void shutdown() {

  }
}
