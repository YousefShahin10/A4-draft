package cities;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import meshcomponents.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import graphComponents.*;
import algorithm.*;
import colors.*;
import algorithm.*;
import graphComponents.*;

public class CityCreator {
    private MyMesh mesh;
    private Random random;
    private int cityNum;
    public List<MyVertex> cityList = new ArrayList<>();
    private MyVertex capital;
    public CityCreator(MyMesh mesh, int cityNum) {
        this.mesh = mesh;
        this.random = new Random();
        this.cityNum = cityNum;


    }
    public void createCitiesWithRoads(){
        generateCities();
    }

    public void generateCities() {
        List<MyPolygon> polygons = mesh.getPolygons();
        int count = 0;
        while (count < cityNum) {
            MyPolygon polygon = polygons.get(random.nextInt(polygons.size()));
            if (!polygon.getColor().equals(IslandColors.OCEAN) && !polygon.getColor().equals(IslandColors.LAGOON) && !polygon.getColor().equals(IslandColors.LAKE) && !polygon.getColor().equals(IslandColors.BIOME_TUNDRA)) {
                int centroidId = polygon.getCentroidId();
                MyVertex centroid = mesh.getVertexs().get(centroidId);
                if (centroid.getColor() != IslandColors.CITY) {
                    cityList.add(centroid);
                    centroid.setColor(IslandColors.CITY);
                    count++;
                }
            }
        }
        findCapitalCity();
    }

    public void findCapitalCity(){
        capital = cityList.get(random.nextInt(cityList.size()));
        capital.setColor(IslandColors.CAPITAL);
    }
    public List<MyVertex> getCities(){
        return cityList;
    }
    public MyVertex getCapital() {
        return capital;
    }

}