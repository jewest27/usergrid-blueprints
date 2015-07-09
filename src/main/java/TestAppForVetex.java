import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.GraphFactory;
import com.tinkerpop.blueprints.Vertex;

/**
 * Created by nishitarao on 7/7/15.
 */
public class TestAppForVetex {
    public static void main(String[] args) {


        Graph usergrid = GraphFactory.open("/Users/nishitarao/dev/usergrid-blueprints/src/main/resources/usergrid.properties");

        Vertex v1 = usergrid.addVertex("person:Nishita"); // Adds a vertex
        Vertex testGet = usergrid.getVertex(v1.getId()); //Gets vertex using getVetex which in turn uses getId
        System.out.println(testGet);
        usergrid.removeVertex(v1); // Delete a vertex

        Vertex v2 = usergrid.addVertex("restaurant:Amici"); //Adds a vertex
        v2.setProperty("tag","Amici"); // Sets a property
        System.out.println(v2.getProperty("tag")); //Gets a property
        System.out.println(v2);

        //v2.removeProperty("tag"); //TODO: Have to check whether Usergrid supports this
        //System.out.println(v2);
        
        v2.remove(); //Deletes the vertex




    }
}
