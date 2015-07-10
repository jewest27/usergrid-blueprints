import com.tinkerpop.blueprints.*;

/**
 * Created by ayeshadastagiri on 7/8/15.
 */
public class TestConnection {

    public static void main(String[] args) {


        Graph usergrid = GraphFactory.open("/Users/ayeshadastagiri/usergrid-blueprints/src/main/resources/usergrid.properties");

        Vertex v1 = usergrid.addVertex("person:ayesha");
        System.out.println("id v1 :: " + usergrid.getVertex(v1.getId()));

        Vertex v2 = usergrid.addVertex("restaurant:amici");
        System.out.println("id v2 :: " +usergrid.getVertex(v2.getId()));

        Vertex v3 = usergrid.addVertex("restaurant:CPK");
        System.out.println("id v3 :: " +usergrid.getVertex(v3.getId()));


        v1.addEdge("likes",v2);
        v1.addEdge("visits",v2);
        v1.addEdge("visits",v3);

       //v1.getEdges(Direction.OUT);

        String edgeId = v1.getId()+"-->visits-->"+v2.getId();
        Edge e3 = usergrid.getEdge(edgeId);
        System.out.println("label : " + e3.getLabel());


        //Object eId = "type:visits";
//        Edge e1 = usergrid.addEdge(null,v1,v2,"visits");
//        //e1.getId();
//        Edge e2 = usergrid.addEdge(null,v1,v3,"visits");
//
//        //passing object id as string sourceId-->label-->targetId

//
//        e3.remove();
        //usergrid.removeEdge(e1);

    }
}
