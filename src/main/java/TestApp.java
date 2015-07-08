import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.GraphFactory;
import com.tinkerpop.blueprints.Vertex;
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


    Graph usergrid = GraphFactory.open("/Users/ayeshadastagiri/usergrid-blueprints/src/main/resources/usergrid.properties");

    Vertex v1 = usergrid.addVertex("person:ayesha");
    System.out.println("id v1 :: " + usergrid.getVertex(v1.getId()));


    Vertex v2 = usergrid.addVertex("restaurant:amici");
    System.out.println("id v2 :: " +usergrid.getVertex(v2.getId()));


    //Object eId = "type:visits";
    Edge e1 = usergrid.addEdge(null,v1,v2,"visits");
    //e1.getId();
    String edgeId = v1.getId()+"-->Visits";

    Edge e2 = usergrid.getEdge(edgeId);
//    System.out.println(e2.getId());
//    Vertex v2 = usergrid.getVertex("<type>:<uuid>");
//
//    Vertex v2 = usergrid.getVertex(new EntityId("type", UUID.fromString("uuid")));
//
    //EntityId test = new EntityId("people:ab711e95-2432-11e5-8a7e-8e25b400a634");
    //System.out.println(usergrid.getVertex(test));
//    System.out.println(usergrid.getVertex("people:ab711e95-2432-11e5-8a7e-8e25b400a634"));
  }
}
