package org.apache.usergrid.drivers.blueprints;

import com.sun.javaws.exceptions.InvalidArgumentException;
import com.tinkerpop.blueprints.Direction;
import org.apache.usergrid.java.client.response.ApiResponse;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by ApigeeCorporation on 7/10/15.
 */
public class ValidationUtils {

  public static final String STRING_DUPLICATE_PROPERTY = "duplicate_unique_property_exists";
  //TODO: Enter the appropriate error string
  public static final String INVALID_CREDENTIALS = "invalid_Credentials";
  public static final String FORBIDDEN = "forbidden";
  public static final String SERVER_ERROR = "Server error or 500";
  public static final String INCORRECT_CONTENT = "Incorrect Content error 400 - Bad request";

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



  public static void validateDuplicate(ApiResponse response, Class<RuntimeException> exceptionClass, String message) {
    if (response.toString().contains(STRING_DUPLICATE_PROPERTY)) {
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
      }

    }
  }

  public static void validateCredentials(ApiResponse response, Class<RuntimeException> exceptionClass, String message){
    if (response.toString().contains(INVALID_CREDENTIALS)){
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
      }
    }
  }


  public static void validateAccess(ApiResponse response, Class<RuntimeException> exceptionClass, String message){
    if (response.toString().contains(FORBIDDEN)){
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
      }
    }
  }

    public static void validateRequest(ApiResponse response, Class<RuntimeException>exceptionClass, String message) {
    if (response.toString().contains(INCORRECT_CONTENT)){
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
        }
    }
    }

  public static void serverError(ApiResponse response, Class<IOException> exceptionClass, String message){
    if (response.toString().contains(SERVER_ERROR)){
      try {
        Constructor<IOException> c = exceptionClass.getDeclaredConstructor(String.class);
        IOException e = c.newInstance(message);
        throw e;
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }



}