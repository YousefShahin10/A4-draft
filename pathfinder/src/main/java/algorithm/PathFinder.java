package algorithm;

import graphComponents.Node;

import java.util.List;
public interface PathFinder {
    List<Node> findPath(Node source, Node destination);
}
