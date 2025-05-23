package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.AbstractMap.SimpleEntry;

import client.ConstructGraph;

class PathConstruction {
    ConstructGraph graph;

    public PathConstruction(ConstructGraph graph) {
        this.graph = graph;
        System.out.println(graph.ports);
    }

    ArrayList<Integer> dijkstras(int src, int dst, int priority) {

        ArrayList <Double> dist = new ArrayList<>(Collections.nCopies(graph.ports,Double.MAX_VALUE));
        ArrayList <Boolean> visited = new ArrayList<>(Collections.nCopies(graph.ports,false));

        PriorityQueue<SimpleEntry<Double, Integer>> pq = new PriorityQueue<>();

        ArrayList<ArrayList<Integer>> parentFlightId = new ArrayList<>(graph.ports);
        ArrayList<Integer> parent = new ArrayList<>(Collections.nCopies(graph.ports,-1));
        ArrayList<Integer> ids = new ArrayList<>(Collections.nCopies(graph.ports, null));

        double alpha=0.5, beta=0.5;
        if(priority==1) {alpha=0.8; beta=0.2;}
        if(priority==2) {alpha=0.2; beta=0.8;};


        dist.set(src,0.0);

    }
}

public class OptimisePath {

    public static void main(String srgs[]) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flightdb","root","");
        ConstructGraph graph = new ConstructGraph();
        graph.buildGraph(con);
        PathConstruction path = new PathConstruction(graph);
    }
}
