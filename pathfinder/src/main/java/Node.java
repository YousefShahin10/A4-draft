public class Node {
    private int id;
    private String name;
    private double elevation;

    public Node(int id, String name, double elevation) {
        this.id = id;
        this.name = name;
        this.elevation = elevation;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getElevation() {
        return elevation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }
}