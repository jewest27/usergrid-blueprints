import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.GraphFactory;
import com.tinkerpop.blueprints.Vertex;
import org.apache.usergrid.java.client.model.EntityId;

import java.util.UUID;

/**
 * Created by ApigeeCorporation on 6/29/15.
 */
public class TestApp {

  public static void main(String[] args) {
    Graph usergrid = GraphFactory.open("/Users/ApigeeCorporation/code/usergrid/usergrid-blueprints/src/main/resources/usergrid.properties");

    Vertex v = usergrid.addVertex("type");

//    Vertex v2 = usergrid.getVertex("<type>:<uuid>");
//
//    Vertex v2 = usergrid.getVertex(new EntityId("type", UUID.fromString("uuid")));
//
    System.out.println(usergrid);
  }
}
