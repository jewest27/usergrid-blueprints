package org.apache.usergrid.drivers.blueprints;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.VertexQuery;
import org.apache.usergrid.java.client.entities.Entity;

import java.util.Set;

/**
 * Created by ApigeeCorporation on 6/29/15.
 */
public class UsergridVertex extends Entity implements Vertex {
  private static String defaultType;

  public UsergridVertex(String defaultType) {
    super.setType(defaultType);
  }

  public static void setDefaultType(String defaultType) {
    UsergridVertex.defaultType = defaultType;
  }

  public Iterable<Edge> getEdges(Direction direction, String... labels) {
    return null;
  }

  public Iterable<Vertex> getVertices(Direction direction, String... labels) {
    return null;
  }

  public VertexQuery query() {
    return null;
  }

  public Edge addEdge(String label, Vertex inVertex) {
    return null;
  }

  public <T> T getProperty(String key) {
    return null;
  }

  public Set<String> getPropertyKeys() {
    return null;
  }

  public void setProperty(String key, Object value) {

  }

  public <T> T removeProperty(String key) {
    return null;
  }

  public void remove() {

  }

  public Object getId() {
    return null;
  }

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
