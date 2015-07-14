import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.GraphFactory;
import com.tinkerpop.blueprints.Vertex;

/**
 * Created by nishitarao on 7/7/15.
 */
public class TestAppForVertex {
    public static void main(String[] args) {


        Graph usergrid = GraphFactory.open("src/main/resources/usergrid.properties");

        Vertex v1 = usergrid.addVertex("person:Nishita"); // Adds a vertex
        Vertex testGet = usergrid.getVertex(v1.getId()); //Gets vertex using getVertex which in turn uses getId
        System.out.println(testGet);



        //v2.removeProperty("tag"); //TODO: Have to check whether Usergrid supports this
        //System.out.println(v2);

        //Deletes the vertex




    }
}
