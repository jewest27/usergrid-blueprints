import com.tinkerpop.blueprints.*;
import org.apache.usergrid.drivers.blueprints.EntityId;
import org.apache.usergrid.drivers.blueprints.UsergridEdge;
//import org.apache.usergrid.java.client.model.EntityId;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by ApigeeCorporation on 6/29/15.
 */
public class TestApp {


  public static void main(String[] args) {


    Graph usergrid = GraphFactory.open("src/main/resources/usergrid.properties");

    System.out.println("Creating VERTICES");
    Vertex person1 = usergrid.addVertex("person:ayesha");
    System.out.println("id person1 :: " + person1.getId());

    Vertex restaurant1 = usergrid.addVertex("restaurant:amici");
    System.out.println("id restaurant1 :: " + restaurant1.getId());

    Vertex restaurant2 = usergrid.addVertex("restaurant:CPK");
    System.out.println("id restaurant2 :: " + restaurant2.getId());

    System.out.println();
    System.out.println("Getting VERTICES");
    Vertex testGet = usergrid.getVertex(person1.getId()); //Gets vertex using getVetex which in turn uses getId
    System.out.println(testGet);

    System.out.println();
    System.out.println("Setting and getting propertices for VERTICES");
    restaurant1.setProperty("tag", "Italian"); // Sets a property
    System.out.println("Getting the property for Amici restaurant : " + restaurant1.getProperty("tag")); //Gets a property

    System.out.println();
    System.out.println("Adding EDGES");
    Edge e1 = person1.addEdge("visits", restaurant1);
    System.out.println("Edge1 id : " + e1.getId());

    Edge e2 = usergrid.addEdge(null, person1, restaurant2, "visits");
    System.out.println("Edge2 id : " + e2.getId());

    Edge e3 = person1.addEdge("likes", restaurant1);
    System.out.println("Edge3 id : " + e3.getId());


    System.out.println();
    System.out.println("Getting EDGES");
//    person:ayesha-->visits-->restaurant:amici
    String edgeId = person1.getId() + "-->visits-->" + restaurant1.getId();
    e3 = usergrid.getEdge(edgeId);
    System.out.println("Get edge : " + e3.getId());


//    System.out.println("Deleting EDGES");
//
//    usergrid.removeEdge(e3);
//    e2.remove();
//    System.out.println("Deleting VERTICES");
//    usergrid.removeVertex(restaurant1);
//    restaurant2.remove();

  }
}
