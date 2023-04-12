import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import cities.CityCreator;
import converter.Compiler;
import converter.Extractor;
import graphComponents.Edge;
import graphComponents.Graph;
import graphComponents.Node;
import meshcomponents.MyMesh;
import cities.*;
public class Main {
    public static void main(String [] args) throws IOException{
        Configuration configuration = new Configuration(args);

        Structs.Mesh aMesh = new MeshFactory().read(configuration.input());

        Extractor extractor = new Extractor(aMesh);

        MyMesh mesh = extractor.convert();

        IslandGenerator islandGenerator = new IslandGenerator(configuration.export());

        islandGenerator.generate(mesh);

        CityCreator cities = new CityCreator(mesh, 30);
        cities.createCitiesWithRoads();

        CityGraph graph = new CityGraph(mesh);
        graph.createNodes();
        graph.createEdges();
        System.out.println(graph.getCapitalCity(cities.getCapital()));
        System.out.println(graph.getCityNodes(cities.getCities()));

        Node capitalCity = graph.getCapitalCity(cities.getCapital());
        List<Node> citiesInGraph = new ArrayList<>();
        citiesInGraph = graph.getCityNodes(cities.getCities());

        RoadNetworkGenerator roads = new RoadNetworkGenerator(graph, capitalCity, citiesInGraph);
        roads.createNetwork(graph, capitalCity, citiesInGraph, graph.getVertexToNodeAssociation());

        Compiler compiler = new Compiler();

        Structs.Mesh exported = compiler.compile(mesh);

        new MeshFactory().write(exported, configuration.output());
    }
}
