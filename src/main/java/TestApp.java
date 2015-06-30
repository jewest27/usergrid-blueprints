import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.GraphFactory;
import com.tinkerpop.blueprints.Vertex;

/**
 * Created by ApigeeCorporation on 6/29/15.
 */
public class TestApp {

  public static void main(String[] args) {
    Graph usergrid = GraphFactory.open("/Users/ApigeeCorporation/code/usergrid/usergrid-blueprints/src/main/resources/usergrid.properties");

    Vertex v = usergrid.addVertex("myObject");

    System.out.println(usergrid);
  }
}
