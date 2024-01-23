package com.solvd.nav.classes;

import com.solvd.nav.helpers.RefWeightHelper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Graph {
    private final HashMap<Long, Node> graph = new HashMap<>();
    private final HashSet<Node> unprocessedShort = new HashSet<>();
    private final HashMap<Node, RefWeight> nodeWeightShort = new HashMap<>();

    public Graph() {
    }

    private Node getNode(Node node) {
        if (node.id() <= 0)
            return null;
        if (graph.containsKey(node.id()))
            return graph.get(node.id());
        return null;
    }

    private Node addNode(Node node) {
        graph.put(node.id(), node);
        return node;
    }

    public Node addOrGet(Node node) {
        var tmpNode = getNode(node);
        if (tmpNode == null)
            tmpNode = addNode(node);
        return tmpNode;
    }

    public void add(Node first, Node second, RefWeight weight) {
        fillUp(first, second, weight);
        fillUp(second, first, weight);
    }

    private void fillUp(Node first, Node second, RefWeight weight) {
        var node = addOrGet(first);
        var neighbour = addOrGet(second);
        var ref = new Ref(neighbour, weight);
        node.refs().add(ref);
        neighbour.parents().put(node, ref);
    }

    public void nodeIterating() {
        iterateWrap();
    }

    private void iterate(Node node, HashSet<Node> checked) {
        System.out.println(node);
        checked.add(node);
        for (var element : node.refs())
            if (!checked.contains(element.neighbour()))
                iterate(element.neighbour(), checked);
    }

    private void iterateWrap() {
        HashSet<Node> checked = new HashSet<>();
        for (var element : graph.entrySet()) {
            var node = element.getValue();
            if (!checked.contains(node))
                iterate(node, checked);
        }
    }
    public LinkedList<Node> makePath(Node start, Node finish)
    {
        HashSet<Node> unprocessed = new HashSet<>();
        HashMap<Node, RefWeight> weightMap = new HashMap<>();
        initHashTables(start, unprocessed, weightMap);
        calculateDistanceForEachNode(unprocessed, weightMap);
        if (weightMap.get(finish).isMAXDistance())
            return null;
        return getShortedPath(start, finish, weightMap);
    }
    public LinkedList<Node> makeShortPath(Node start, Node finish) {
        HashSet<Node> unprocessed= new HashSet<>();
        HashMap<Node, RefWeight> weightMap = new HashMap<>();
        initHashTables(start, unprocessed, weightMap);
        calculateDistanceForEachNode(unprocessed, weightMap);
        if (weightMap.get(finish).isMAXDistance())
            return null;
        return getShortedPath(start, finish, weightMap);
    }
    public LinkedList<Node> makeFastPath(Node start, Node finish) {
        HashSet<Node> unprocessed = new HashSet<>();
        HashMap<Node, RefWeight> nodeWeight = new HashMap<>();
        initHashTables(start, unprocessed, nodeWeight);
        calculateTimeForEachNode(unprocessed, nodeWeight);
        if (nodeWeight.get(finish).isMAXTime())
            return null;
        return getShortedPath(start, finish, nodeWeight);
    }

    private void initHashTables(Node start, HashSet<Node> unprocessed, HashMap<Node, RefWeight> nodeWeight) {
        for (var element : graph.entrySet()) {
            Node node = element.getValue();
            unprocessed.add(node);
            nodeWeight.put(node, RefWeightHelper.MAX());
        }
        nodeWeight.put(start, new RefWeight());
    }

    private void calculateDistanceForEachNode(HashSet<Node> unprocessed, HashMap<Node, RefWeight> nodeWeight) {
        while (!unprocessed.isEmpty()) {
            Node node = getNodeWithMinDistance(unprocessed, nodeWeight);
            if (nodeWeight.get(node).isMAXDistance())
                return;

            for (var element : node.refs()) {
                Node neighbour = element.neighbour();
                if (unprocessed.contains(neighbour)) {
                    var newWeight = RefWeightHelper.distanceSummation(nodeWeight.get(node), element.weight());
                    if (RefWeightHelper.isLessDistance(newWeight, nodeWeight.get(neighbour)))
                        nodeWeight.put(neighbour, newWeight);
                }
            }
            unprocessed.remove(node);
        }
    }
    private void calculateWeightForEachNode(HashSet<Node> unprocessed, HashMap<Node, RefWeight> nodeWeight) {
        while (!unprocessed.isEmpty()) {
            Node node = getNodeWithMinDistance(unprocessed, nodeWeight);
            if (nodeWeight.get(node).isMAXDistance())
                return;

            for (var element : node.refs()) {
                Node neighbour = element.neighbour();
                if (unprocessed.contains(neighbour)) {
                    var newWeight = RefWeightHelper.distanceSummation(nodeWeight.get(node), element.weight());
                    if (RefWeightHelper.isLessDistance(newWeight, nodeWeight.get(neighbour)))
                        nodeWeight.put(neighbour, newWeight);
                }
            }
            unprocessed.remove(node);
        }
    }
    private void calculateTimeForEachNode(HashSet<Node> unprocessed, HashMap<Node, RefWeight> nodeWeight) {
        while (!unprocessed.isEmpty()) {
            Node node = getNodeWithMinTime(unprocessed, nodeWeight);
            if (nodeWeight.get(node).isMAXTime())
                return;

            for (var element : node.refs()) {
                Node neighbour = element.neighbour();
                if (unprocessed.contains(neighbour)) {
                    var newWeight = RefWeightHelper.timeSummation(nodeWeight.get(node), element.weight());
                    if (RefWeightHelper.isLessTime(newWeight, nodeWeight.get(neighbour)))
                        nodeWeight.put(neighbour, newWeight);
                }
            }
            unprocessed.remove(node);
        }
    }

    private Node getNodeWithMinDistance(HashSet<Node> unprocessed, HashMap<Node, RefWeight> nodeWeight) {
        Node node = null;
        float distance = Float.MAX_VALUE;
        for (var element : unprocessed) {
            if (nodeWeight.get(element).distance() < distance) {
                distance = nodeWeight.get(element).distance();
                node = element;
            }
        }
        return node;
    }

    private Node getNodeWithMinTime(HashSet<Node> unprocessed, HashMap<Node, RefWeight> nodeWeight) {
        Node node = null;
        float time = Float.MAX_VALUE;
        for (var element : unprocessed) {
            if (nodeWeight.get(element).time() < time) {
                time = nodeWeight.get(element).time();
                node = element;
            }
        }
        return node;
    }

    public LinkedList<Node> getShortedPath(Node start, Node finish, HashMap<Node, RefWeight> nodeWeight) {
        LinkedList<Node> path = new LinkedList<>();
        var node = finish;
        while (node != start) {
            var distance = nodeWeight.get(node).distance();
            path.addFirst(node);
            for (var element : node.parents().entrySet())
            {
                var neighbour = element.getKey();
                var parentRef = element.getValue();
                if(!nodeWeight.containsKey(neighbour))
                    continue;

                if( (parentRef.weight().distance() + nodeWeight.get(neighbour).distance()) == distance)
                {
                    nodeWeight.remove(node);
                    node = neighbour;
                    break;
                }
            }
        }
        path.addFirst(node);
        return path;
    }
}
