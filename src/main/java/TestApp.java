import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.GraphFactory;
import com.tinkerpop.blueprints.Vertex;
import org.apache.usergrid.drivers.blueprints.UsergridEdge;
//import org.apache.usergrid.java.client.model.EntityId;

import java.util.UUID;

/**
 * Created by ApigeeCorporation on 6/29/15.
 */
public class TestApp {

  public static void main(String[] args) {


    Graph usergrid = GraphFactory.open("/Users/nishitarao/dev/usergrid-blueprints/src/main/resources/usergrid.properties");

    Vertex v1 = usergrid.addVertex("person");
    Vertex v2 = usergrid.addVertex("restaurant");

    Object eId = "type:visits";
    Edge e = usergrid.addEdge(eId,v1,v2,"visits");


//    Vertex v2 = usergrid.getVertex("<type>:<uuid>");
//
//    Vertex v2 = usergrid.getVertex(new EntityId("type", UUID.fromString("uuid")));
//
    System.out.println(usergrid);
  }
}
