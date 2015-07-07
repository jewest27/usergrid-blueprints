import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.GraphFactory;
import com.tinkerpop.blueprints.Vertex;
import org.apache.usergrid.drivers.blueprints.EntityId;
import org.apache.usergrid.drivers.blueprints.UsergridEdge;
//import org.apache.usergrid.java.client.model.EntityId;

import java.util.UUID;

/**
 * Created by ApigeeCorporation on 6/29/15.
 */
public class TestApp {

  public static void main(String[] args) {


    Graph usergrid = GraphFactory.open("/Users/ayeshadastagiri/usergrid-blueprints/src/main/resources/usergrid.properties");

    Vertex v1 = usergrid.addVertex("person");
    Vertex v2 = usergrid.addVertex("restaurant");


    //Object eId = "type:visits";
    Edge e = usergrid.addEdge(null,v1,v2,"visits");


//    Vertex v2 = usergrid.getVertex("<type>:<uuid>");
//
//    Vertex v2 = usergrid.getVertex(new EntityId("type", UUID.fromString("uuid")));
//
    //EntityId test = new EntityId("people:ab711e95-2432-11e5-8a7e-8e25b400a634");
    //System.out.println(usergrid.getVertex(test));
    System.out.println(usergrid.getVertex("people:ab711e95-2432-11e5-8a7e-8e25b400a634"));
  }
}
