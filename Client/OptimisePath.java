package client;

import java.sql.Connection;
import java.sql.DriverManager;

import client.ConstructGraph;

class PathConstruction {
    ConstructGraph graph;

    public PathConstruction(ConstructGraph graph) {
        this.graph = graph;
        System.out.println(graph.ports);
    }
}

public class OptimisePath {
    public static void main(String srgs[]) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Flightdb","root","");
        ConstructGraph graph = new ConstructGraph();
        graph.buildGraph(con);
        PathConstruction path = new PathConstruction(graph);
    }
}
