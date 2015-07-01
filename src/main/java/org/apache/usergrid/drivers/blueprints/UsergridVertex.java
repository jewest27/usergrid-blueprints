package org.apache.usergrid.drivers.blueprints;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.VertexQuery;
import org.apache.usergrid.java.client.Client;
import org.apache.usergrid.java.client.entities.Entity;

import java.util.Set;

/**
 * Created by ApigeeCorporation on 6/29/15.
 */
public class UsergridVertex extends Entity implements Vertex , UsergridChangedThing{
  private static String defaultType;

  public UsergridVertex(String defaultType) {
    super.setType(defaultType);
  }

  public static void setDefaultType(String defaultType) {
    UsergridVertex.defaultType = defaultType;
  }

  /**
   * This gets edges that are connected to the vertex in a
   * particular direction specified, and having a specific label
   * @param direction
   * @param labels
   * @return
   */
  public Iterable<Edge> getEdges(Direction direction, String... labels) {
      /**
       1) Check if the vertex exists
       2) Get the UUIDs of edges that are connected to the
       particular vertex in a particular direction and with a particular label
       3) Return an iterable of edges
       */

    return null;
  }


  /**
   * This gets all the adjacent vertices connected to the vertex by an edge specified by a particular direction and label
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
   * @return
   */
  public VertexQuery query() {
    return null;
  }

  public Edge addEdge(String label, Vertex inVertex) {
      /**
       1) Check if the vertex exists
       2) Use the following to add an edge - connectEntities( String connectingEntityType,String
       connectingEntityId, String connectionType, String connectedEntityId) in org.apache.usergrid.java.client
       3) Return the newly created edge
       */

    return null;
  }

  /**
   * Get a particular property of a vertex specified by a key
   * @param key
   * @param <T>
   * @return
   */
  public <T> T getProperty(String key) {
      /**
       1) Check if the vertex exists
       2) Use the void setProperty(String name, float/String/long/int/boolean/JsonNode value) in
       org.apache.usergrid.java.client.entities
       3) If any other type throw an error
       */

    return null;
  }

  /**
   * Get all the property keys for a particular vertex
   * @return
   */
  public Set<String> getPropertyKeys() {
    return null;
  }

  public void onChanged(Client client)
  {

  }

  /**
   * This sets a particular value of a property using the specified key
   * @param key
   * @param value
   */
  public void setProperty(String key, Object value) {
//    Client.changed(this);
  }

  /**
   * Remove a particular property as specified by the key
   * @param key
   * @param <T>
   * @return
   */
  public <T> T removeProperty(String key) {
    return null;
  }

  public void remove() {

  }

  /**
   * This gets the Id of the vertex
   * @return
   */
  public Object getId() {
    return null;
  }

  /**
   * This sets the type of vertex (the collection)
   * @param newType
   */
  @Override
  public void setType(String newType) {
    if (newType.equals(super.getType())) {
      //no op
    } else {
      this.delete();
      super.setType(newType);
      this.save();
    }
  }
}
