package org.apache.usergrid.drivers.blueprints;

import com.tinkerpop.blueprints.*;

/**
 * Created by ApigeeCorporation on 6/29/15.
 */
public class UsergridGraphQuery implements GraphQuery {

  /**
   * @param key
   * @return
   */
  public GraphQuery has(String key) {
    return null;
  }

  /**
   * @param key
   * @return
   */
  public GraphQuery hasNot(String key) {
    return null;
  }

  /**
   * @param key
   * @param value
   * @return
   */
  public GraphQuery has(String key, Object value) {
    return null;
  }

  /**
   * @param key
   * @param value
   * @return
   */
  public GraphQuery hasNot(String key, Object value) {
    return null;
  }

  /**
   * @param key
   * @param predicate
   * @param value
   * @return
   */
  public GraphQuery has(String key, Predicate predicate, Object value) {
    return null;
  }

  /**
   * @param key
   * @param value
   * @param compare
   * @param <T>
   * @return
   */
  public <T extends Comparable<T>> GraphQuery has(String key, T value, Compare compare) {
    return null;
  }

  /**
   * @param key
   * @param startValue
   * @param endValue
   * @param <T>
   * @return
   */
  public <T extends Comparable<?>> GraphQuery interval(String key, T startValue, T endValue) {
    return null;
  }

  /**
   * @param limit
   * @return
   */
  public GraphQuery limit(int limit) {
    return null;
  }

  /**
   * @return
   */
  public Iterable<Edge> edges() {
    return null;
  }

  /**
   * @return
   */
  public Iterable<Vertex> vertices() {
    return null;
  }
}
