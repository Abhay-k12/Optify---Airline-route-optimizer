package webapps.Optifly.WEB_INF.classes.main;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import webapps.Optifly.WEB_INF.classes.administrator.AirPortsCodes;
import webapps.Optifly.WEB_INF.classes.clientBackend.*;

public class ClientMain {
    public static void main(String args[]) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flightdb","root","");
        ConstructGraph graph = new ConstructGraph();
        graph.buildGraph(con);

        AirPortsCodes code = new AirPortsCodes();
        OptimisePath path = new OptimisePath(graph);

        List<int[]> ans = path.dijkstra(code.getAirportCode("delhi"), code.getAirportCode("dehradun"), "eco");

        for(int i=0; i<ans.size(); i++) {
            System.out.println(code.getAirport(ans.get(i)[0]) + " " + ans.get(i)[1]);
        }
    }
}