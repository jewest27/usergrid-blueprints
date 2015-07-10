package org.apache.usergrid.drivers.blueprints;

import com.sun.javaws.exceptions.InvalidArgumentException;
import com.tinkerpop.blueprints.Direction;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by ApigeeCorporation on 7/10/15.
 */
public class ValidationUtils {

  public static void validateNotNull(Object o, Class<RuntimeException> exceptionClass, String message) {
    if (o == null) {

      try {
        Constructor<RuntimeException> c = exceptionClass.getDeclaredConstructor(String.class);
        RuntimeException e = c.newInstance(message);
        throw e;

      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }


    }
  }


  public static void validateStringNotEmpty(String s, Class<RuntimeException> exceptionClass, String message) {
    if (s == null || s.length() == 0) {

      try {
        Constructor<RuntimeException> c = exceptionClass.getDeclaredConstructor(String.class);
        RuntimeException e = c.newInstance(message);
        throw e;

      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }


    }
  }


}
