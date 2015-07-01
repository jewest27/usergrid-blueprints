package org.apache.usergrid.drivers.blueprints;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import org.apache.usergrid.java.client.Client;

import java.util.Set;

/**
 * Created by ApigeeCorporation on 6/29/15.
 */
public class UsergridEdge implements UsergridChangedThing, Edge {


  /**
   *
   * Return the label associated with the edge.
   *
   * @return
   */
  public String getLabel() {

    /*
    1. get the client connection. check if its initialized.
    2. get the timestamp associated with the entity. // Confirm.
     */

    return null;
  }


  public void onChanged(Client client) {

  }


  /**
   *
   *  Return the tail/out or head/in vertex.
   * @param direction
   * @return
   * @throws IllegalArgumentException
   */
  public Vertex getVertex(Direction direction) throws IllegalArgumentException {

    /*
    1. Check the client initialized.
    2. check the direction : IN -> connected entity , OUT -> connecting entity //TODO discuss : what if its BOTH. ?
    3. if IN :
          get the connected Entity
                (ex : c3.getEntities().get(0).getStringProperty("name"))
      else if OUT :
          get the connecting entity
                (ex : entity1.getProperties().get("metadata").get("connecting"))
      else :
          //TODO : for BOTH
     */


    return null;
  }

  /**
   * Not implementing for the usergrid blueprints.
   * @param key
   * @param <T>
   * @return
   */
  public <T> T getProperty(String key) {
    return null;
  }

  /**
   * Not implementing for the usergrid blueprints.
   * @return
   */
  public Set<String> getPropertyKeys() {
    return null;
  }

  /**
   * Not implementing for the usergrid blueprints.
   * @param key
   * @param value
   */
  public void setProperty(String key, Object value) {

  }

  /**
   * Not implementing for the usergrid blueprints.
   * @param key
   * @param <T>
   * @return
   */
  public <T> T removeProperty(String key) {
    return null;
  }

  /**
   * removes the edge
   */
  public void remove() {


    /*
    1. check client is initialized.
    2.check if the connection/edge is valid
    3. delete the connection . check : disconnectEntities in client.java
     */
  }


  /**
   *
   * should return the Id for the given edge.
   * @return
   */
  public Object getId() {

    /*
    1. check if client is initialized.
    2. check if the edge is valid.
    3. return the edge id.
     */
    return null;
  }
}
