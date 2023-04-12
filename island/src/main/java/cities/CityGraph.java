package cities;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import meshcomponents.*;
import graphComponents.*;
import java.util.*;

public class CityGraph extends Graph {
    private List<MyVertex> vertices;
    private List<MyVertex> cities;
    public Map<MyVertex, Node> vertexToNodeMap;

    private MyMesh mesh;

    //public CityGraph(List<MyVertex> allVertices, List<MyVertex> cityVertices, MyMesh mesh) {
    public CityGraph(MyMesh mesh) {
        super();
        this.mesh = mesh;
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        adjacencyList = new HashMap<Node, List<Node>>();
        vertexToNodeMap = new HashMap<>();
    }
    @Override
    public void createNodes() {
        List<MyPolygon> polygons = mesh.getPolygons();
        for (MyPolygon p : polygons){
            int centroidId = p.getCentroidId();
            Node node = new Node(centroidId, p.getElevation());
            addNode(node);
            vertexToNodeMap.put(mesh.getVertexs().get(centroidId), node);
        }

        /*for (MyVertex vertex : vertices) {
            Node node = new Node(vertex.getId(), vertex.getElevation());
            addNode(node);
            vertexToNodeMap.put(vertex, node);
        }*/
    }
    @Override
    public void createEdges() {
        int idCount = 0;
        List<MyPolygon> polygons = mesh.getPolygons();
        for (MyPolygon p : polygons){
            List<Integer> neighbours = p.getNeighbours();
            MyVertex centroid1 = mesh.getVertexs().get(p.getCentroidId());
            Node n1 = vertexToNodeMap.get(centroid1);
            for (Integer n : neighbours){
                MyPolygon neighbourP = mesh.getPolygons().get(n);
                MyVertex centroid2 = mesh.getVertexs().get(neighbourP.getCentroidId());
                Node n2 = vertexToNodeMap.get(centroid2);

                double edgeWeight = findEdgeWeight(centroid1, centroid2);
                Edge edge = new Edge(idCount, n1, n2, edgeWeight);
                addEdge(edge);
                idCount++;
            }
        }
        /*for (int i = 0; i < vertices.size() - 1; i++) {
            MyVertex v1 = vertices.get(i);
            Node n1 = vertexToNodeMap.get(v1);
            for (int j = i + 1; j < vertices.size(); j++) {
                MyVertex v2 = vertices.get(j);
                Node n2 = vertexToNodeMap.get(v2);
                double distance = findEdgeWeight(v1,v2);
                //double distance = v1.distance(v2);
                Edge edge = new Edge(j, n1, n2, distance);
                addEdge(edge);
            }
        }*/
    }
    public Node getCapitalCity(MyVertex capital){
        Node capitalCity = vertexToNodeMap.get(capital);
        return capitalCity;
    }
    public List<Node> getCityNodes(List<MyVertex> cities){
        List<Node> wantedCities = new ArrayList<>();
        for (MyVertex v : cities){
            Node n = vertexToNodeMap.get(v);
            wantedCities.add(n);
        }
        return wantedCities;
    }
    private double findEdgeWeight(MyVertex v1, MyVertex v2) {
        double x1 = v1.getX();
        double y1 = v1.getY();
        double x2 = v2.getX();
        double y2 = v2.getY();

        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        return distance;
    }
    public Map<MyVertex, Node> getVertexToNodeAssociation(){
        return vertexToNodeMap;
    }

}