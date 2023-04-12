package graphComponents;

import java.util.ArrayList;
import java.util.List;
public class Node {
    private int id;
    private String name;
    private double elevation;


    public Node(int id, double elevation) {
        this.id = id;
        this.elevation = elevation;
    }

    public int getId() {
        return id;
    }


    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

}