package ca.mcmaster.cas.se2aa4.a2.visualizer.renderer;
import java.util.*;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties.ColorProperty;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class GraphicRenderer implements Renderer {

    private static final int THICKNESS = 3;
    public void render(Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(2f);
        canvas.setStroke(stroke);
        drawPolygons(aMesh,canvas);
        drawSegments(aMesh, canvas);
        drawCentroids(aMesh, canvas);
    }

    private void drawPolygons(Mesh aMesh, Graphics2D canvas) {
        for(Structs.Polygon p: aMesh.getPolygonsList()){
            drawAPolygon(p, aMesh, canvas);
        }
    }


    private void drawSegments(Mesh aMesh,Graphics2D canvas){
        List <Segment> segmentList = aMesh.getSegmentsList();
        List <Vertex> vertexList = aMesh.getVerticesList();

        for(Segment s : segmentList){

            //draw the line
            //Display Segments
            double v1x = vertexList.get(s.getV1Idx()).getX(), v1y = vertexList.get(s.getV1Idx()).getY();
            double v2x = vertexList.get(s.getV2Idx()).getX(), v2y = vertexList.get(s.getV2Idx()).getY();

            Optional<Color> fill = new ColorProperty().extract(s.getPropertiesList());

            int weight = extractWeight(s.getPropertiesList());

            canvas.setStroke(new BasicStroke(weight));
            canvas.setColor(fill.get());
            Line2D line = new Line2D.Double(new Point2D.Double(v1x, v1y), new Point2D.Double(v2x, v2y));
            canvas.draw(line);
        }
    }

    private void drawAPolygon(Structs.Polygon p, Mesh aMesh, Graphics2D canvas) {
        Hull hull = new Hull();
        for(Integer segmentIdx: p.getSegmentIdxsList()) {
            hull.add(aMesh.getSegments(segmentIdx), aMesh);
        }
        Path2D path = new Path2D.Float();
        Iterator<Vertex> vertices = hull.iterator();
        Vertex current = vertices.next();
        path.moveTo(current.getX(), current.getY());
        while (vertices.hasNext()) {
            current = vertices.next();
            path.lineTo(current.getX(), current.getY());
        }
        path.closePath();
        canvas.draw(path);
        Optional<Color> fill = new ColorProperty().extract(p.getPropertiesList());
        if(fill.isPresent()) {
            Color old = canvas.getColor();
            canvas.setColor(fill.get());
            canvas.fill(path);
            canvas.setColor(old);
        }
    }
    private void drawCentroids(Mesh aMesh,Graphics2D canvas){
        List <Structs.Polygon> polygonList = aMesh.getPolygonsList();
        List <Vertex> vertexList = aMesh.getVerticesList();
        List <Vertex> centroidList = new ArrayList<>();
        for(Structs.Polygon p : polygonList){
            Vertex c = vertexList.get(p.getCentroidIdx());

            double centroidX = c.getX();
            double centroidY = c.getY();

            Ellipse2D vertexCircle = new Ellipse2D.Double(centroidX,centroidY,15, 15);

            Optional<Color> fill = new ColorProperty().extract(p.getPropertiesList());
            Optional<Color> vertexFill = new ColorProperty().extract(c.getPropertiesList());
            if (vertexFill.get().equals(new Color(92, 43, 62)) || vertexFill.get().equals(new Color(30, 192, 23))){
                canvas.setColor(vertexFill.get());
                canvas.fill(vertexCircle);
                canvas.draw(vertexCircle);
            }
            /*if (!fill.get().equals(new Color(0,0,255))) {
                if (!fill.get().equals(new Color(52, 207, 235))) {
                    if (!fill.get().equals(new Color(174, 187, 252))) {
                        if (!fill.get().equals(new Color(102, 237, 255))) {
                            if (vertexFill.get().equals(new Color(92, 43, 62))){
                                canvas.setColor(vertexFill.get());
                                canvas.fill(vertexCircle);
                                canvas.draw(vertexCircle);
                            }
                        }
                    }
                }
            }*/
        }
    }

    private int extractWeight(List<Property> properties) {
        String val = null;
        for(Property p: properties) {
            if (p.getKey().equals("weight")) {
                val = p.getValue();
            }
        }
        if (val == null)
            return 1;

        return Integer.parseInt(val);
    }
}
