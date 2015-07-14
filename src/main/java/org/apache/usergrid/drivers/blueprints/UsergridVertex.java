package org.apache.usergrid.drivers.blueprints;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.javaws.exceptions.InvalidArgumentException;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.VertexQuery;
import org.apache.usergrid.java.client.Client;
import org.apache.usergrid.java.client.entities.Entity;
import org.apache.usergrid.java.client.response.ApiResponse;
import org.apache.usergrid.java.client.utils.JsonUtils;
import org.springframework.http.HttpMethod;
import org.apache.usergrid.java.client.*;


import java.util.*;

/**
 * Created by ApigeeCorporation on 6/29/15.
 */
public class UsergridVertex extends Entity implements Vertex, UsergridChangedThing {
  private static String CONNECTIONING = "connecting" ;
  private static String defaultType;
  private static String METADATA = "metadata";
  private static String CONNECTIONS = "connections";



  public UsergridVertex(String defaultType) {
    super.setType(defaultType);
  }

  public static void setDefaultType(String defaultType) {
    UsergridVertex.defaultType = defaultType;
  }

  /**
   * This gets edges that are connected to the vertex in a
   * particular direction specified, and having a specific label
   *
   * @param direction
   * @param labels
   * @return
   */
  public Iterable<Edge> getEdges(Direction direction, String... labels) {
    /**
     1) Check if the vertex exists.
     2) Get the UUIDs of edges that are connected to the
     particular vertex in a particular direction and with a particular label
     3) Return an iterable of edges
     */

    String srcType = this.getType();
    String srcId = this.getUuid().toString();

    List<Edge> edges = new ArrayList<Edge>();
    ApiResponse response = UsergridGraph.client.queryEdgesForVertex(srcType, srcId);
    Entity trgUUID = response.getFirstEntity();

    switch (direction){
      case  OUT:
        if(trgUUID.getProperties().get(METADATA).findValue(CONNECTIONS) == null){
          System.out.println("the vertex doesnt have outgoing edges.");
          return null;
        }
        Iterator<String> connections = trgUUID.getProperties().get(METADATA).findValue(CONNECTIONS).fieldNames();
        while (connections.hasNext()){
          String name = connections.next();
          ApiResponse resp = UsergridGraph.client.queryConnection(srcType,srcId,name);
          List<Entity> entities = resp.getEntities();
          edges = getAllEdgesForVertex(entities, name, edges,Direction.OUT);
        }
        return edges;

      case  IN:
        if(trgUUID.getProperties().get(METADATA).findValue(CONNECTIONING) == null){
          System.out.println("the vertex doesnt have incomming edges.");
          return null;
        }

        Iterator<String> connectioning = trgUUID.getProperties().get(METADATA).findValue(CONNECTIONING).fieldNames();
        while (connectioning.hasNext()){
          String name = connectioning.next();
          ApiResponse resp = UsergridGraph.client.queryConnectingEdges(srcType, srcId, CONNECTIONING, name);
          List<Entity> entities = resp.getEntities();
          edges = getAllEdgesForVertex(entities, name, edges, Direction.IN);
        }
        return edges;
    }

    return null;
  }


  private List<Edge> getAllEdgesForVertex(List<Entity> entities, String name,List<Edge> edges, Direction dir) {
    for (int i = 0; i < entities.size(); i++) {
      Entity e = entities.get(i);
      EntityId entityId = new EntityId(e.getType() + ":" + e.getUuid());
      Vertex v1 = UsergridGraph.getVertexByEntityId(entityId);
      UsergridVertex v2 = (UsergridVertex)v1;
      Edge e1 = null;
      if (dir == Direction.OUT)
          e1 = new UsergridEdge(this,v2,name,UsergridGraph.client);
      else if (dir == Direction.IN)
          e1 = new UsergridEdge(v2,this,name,UsergridGraph.client);
      edges.add(e1);
    }
    return edges;
  }


  /**
   * This gets all the adjacent vertices connected to the vertex by an edge specified by a particular direction and label
   *
   * @param direction
   * @param labels
   * @return
   */
  public Iterable<Vertex> getVertices(Direction direction, String... labels) {
    /**
     1) Check if the vertex exists
     2) Get the UUIDs of edges that are connected to the
     particular vertex in a particular direction and with a particular label
     3)Get the vertices at the other end of the edge
     4) Return an iterable of vertices
     */
    return null;
  }

  /**
   * Generate a query object that can be
   * used to fine tune which connections/entities are retrieved that are incident/adjacent to this entity.
   *
   * @return
   */
  public VertexQuery query() {
    return null;
  }


  /**
   * Adds edge
   *
   * @param label
   * @param inVertex
   * @return
   */
  public Edge addEdge(String label, Vertex inVertex) {
    /**
     1) Check if the vertex exists
     2) Use the following to add an edge - connectEntities( String connectingEntityType,String
     connectingEntityId, String connectionType, String connectedEntityId) in org.apache.usergrid.java.client
     3) Return the newly created edge
     */
    UsergridEdge e = new UsergridEdge(this, (UsergridVertex) inVertex, label, UsergridGraph.client);
    UsergridGraph.client.connectEntities(this, (UsergridVertex) inVertex, label);
    return e;
  }

  /**
   * Get a particular property of a vertex specified by a key
   *
   * @param key
   * @param <T>
   * @return
   */
  public <T> T getProperty(String key) {

    T propertyValue = (T) super.getStringProperty(key);
    return propertyValue;
    /**
     1) Check if the vertex exists
     2) Use the void setProperty(String name, float/String/long/int/boolean/JsonNode value) in
     org.apache.usergrid.java.client.entities
     3) If any other type throw an error
     */

  }

  /**
   * Get all the property keys for a particular vertex
   *
   * @return
   */
  public Set<String> getPropertyKeys() {
    return null;
  }

  public void onChanged(Client client) {

  }

  /**
   * This sets a particular value of a property using the specified key
   *
   * @param key
   * @param value
   */
  public void setLocalProperty(String key, Object value) {

    if (value instanceof String) {
      super.setProperty(key, (String) value);
    } else if (value instanceof JsonNode) {
      super.setProperty(key, (JsonNode) value);
    } else if (value instanceof Integer) {
      super.setProperty(key, (Integer) value);
    } else if (value instanceof Float) {
      super.setProperty(key, (Float) value);
    } else if (value instanceof Boolean) {
      super.setProperty(key, (Boolean) value);
    } else if (value instanceof Long) {
      super.setProperty(key, (Long) value);
    } else {
      throw new IllegalArgumentException("Supplied id class of " + String.valueOf(value.getClass()) + " is not supported");
    }
  }

  public void setProperty(String key, Object value) {

    setLocalProperty(key, value);

    super.save();
  }

  /**
   * Remove a particular property as specified by the key
   *
   * @param key
   * @param <T>
   * @return
   */
  public <T> T removeProperty(String key) {

    JsonNode oldValue = super.properties.get(key);
    super.properties.remove(key);
    super.save();
    return (T) oldValue;
  }

  /**
   * Removes or deletes the vertex or entity
   */
  public void remove() {

    super.delete();

  }

  /**
   * This gets the Id of the vertex
   *
   * @return
   */
  public Object getId() {

    String ObjectType = this.getType();
    UUID ObjectUUID = this.getUuid();
    String id = ObjectType + ":" + ObjectUUID;
    return id;

  }

  /**
   * This sets the type of vertex (the collection)
   *
   * @param newType
   */
  @Override
  public void setType(String newType) {
    if (newType.equals(super.getType())) {
      //Do nothing
    } else {
      super.setType(newType);
    }
  }
}
