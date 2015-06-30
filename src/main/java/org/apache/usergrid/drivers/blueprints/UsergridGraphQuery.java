package org.apache.usergrid.drivers.blueprints;

import com.tinkerpop.blueprints.*;

/**
 * Created by ApigeeCorporation on 6/29/15.
 */
public class UsergridGraphQuery implements GraphQuery {

  public GraphQuery has(String key) {
    return null;
  }

  public GraphQuery hasNot(String key) {
    return null;
  }

  public GraphQuery has(String key, Object value) {
    return null;
  }

  public GraphQuery hasNot(String key, Object value) {
    return null;
  }

  public GraphQuery has(String key, Predicate predicate, Object value) {
    return null;
  }

  public <T extends Comparable<T>> GraphQuery has(String key, T value, Compare compare) {
    return null;
  }

  public <T extends Comparable<?>> GraphQuery interval(String key, T startValue, T endValue) {
    return null;
  }

  public GraphQuery limit(int limit) {
    return null;
  }

  public Iterable<Edge> edges() {
    return null;
  }

  public Iterable<Vertex> vertices() {
    return null;
  }
}
