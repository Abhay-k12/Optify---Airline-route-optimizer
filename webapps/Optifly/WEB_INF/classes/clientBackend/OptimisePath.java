package clientBackend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.*;

import administrator.AirPortsCodes;

class ConstructGraph {
    private AirPortsCodes codes;
    public int ports;
    public List<List<List<Double>>> adj;

    public ConstructGraph() {
        codes = new AirPortsCodes();
        ports = codes.airPorts.length;
        adj = new ArrayList<>(ports);

        for (int i = 0; i < ports; i++) {
            adj.add(new ArrayList<>());
        }
    }

    private void addEdge(int src, int dst, double ecoCost, double busiCost, double fstCost, double duration, double flightId){
        List<Double> newEdge = new ArrayList<>(Arrays.asList((double)dst ,ecoCost,busiCost,fstCost,duration,flightId));
        adj.get(src).add(newEdge);
    }

    public void buildGraph(final Connection con) throws Exception {
        Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM  flights ");

        while(rs.next()) {
            int src = codes.getAirportCode(rs.getString("source"));
            int dst = codes.getAirportCode(rs.getString("destination"));
            LocalDateTime flightDate = convertStrToDate(rs.getString("flightDate"));
            LocalDateTime landingDate = convertStrToDate(rs.getString("landingDate"));
            double duration = DiffernceBetweenDates(flightDate, landingDate);
            double ecoCost = rs.getDouble("ecoClCost");
            double busiCost = rs.getDouble("bussiClCost");
            double fstClCst = rs.getDouble("firstClCost");
            double flightId = (double)rs.getInt("FlightId");

            if(src!=-1 && dst!=-1)
                addEdge(src, dst, ecoCost, busiCost, fstClCst, duration,flightId);
            else
                System.out.println("No such airPort exist");
        }
    }

    private LocalDateTime convertStrToDate(String date) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(date,pattern);
    }

    private double DiffernceBetweenDates(LocalDateTime sd, LocalDateTime ed) {
        Duration duration = Duration.between(sd, ed);
        return duration.toHours();
    }
}

public class OptimisePath {

    ConstructGraph graph;

    private List<int[]> dijkstra(int source, int destination, String priority) {
        double[] dist = new double[graph.ports];
        int[] prev = new int[graph.ports];
        double[] flightTaken = new double[graph.ports];
        boolean[] visited = new boolean[graph.ports];

        Arrays.fill(dist, Double.MAX_VALUE);
        Arrays.fill(prev, -1);
        Arrays.fill(flightTaken, -1);
        Arrays.fill(visited, false);

        dist[source] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingDouble(u -> dist[u]));
        pq.add(source);

        while (!pq.isEmpty()) {
            int u = pq.poll();
            if (u == destination) break;

            if (visited[u]) continue;
            visited[u] = true;

            for (List<Double> edge : graph.adj.get(u)) {
                int v = edge.get(0).intValue();
                double ecoCost = edge.get(1);
                double busiCost = edge.get(2);
                double fstCost  = edge.get(3);
                double duration = edge.get(4);
                double flightId = edge.get(5);

                double weight;

                switch (priority) {
                    case "eco":
                        weight = ecoCost;
                        break;
                    case "busi":
                        weight = busiCost;
                        break;
                    case "first":
                        weight = fstCost;
                        break;
                    case "time":
                        weight = duration;
                        break;
                    case "both":
                        // Example: combining economy cost and time with a ratio
                        weight = ecoCost * 0.6 + duration * 0.4;
                        break;
                    default:
                        weight = ecoCost;
                }

                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    prev[v] = u;
                    flightTaken[v] = flightId;
                    pq.add(v);
                }
            }
        }

        // Build path: each element [airport code, flight ID]
        List<int[]> path = new ArrayList<>();
        for (int at = destination; at != -1; at = prev[at]) {
            int flightId = (int) flightTaken[at];
            if (at == source) {
                path.add(0, new int[]{at, -1}); // source airport, no flight
            } else {
                path.add(0, new int[]{at, flightId});
            }
        }

        // If unreachable
        if (path.get(0)[0] != source) {
            return new ArrayList<>();
        }

        return path;
    }

    public  List<int[]> shortestPath(int source, int destination, String priority)throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/optifly_db", "root", "");
        this.graph = new ConstructGraph();
        graph.buildGraph(con);
        return dijkstra(source, destination, priority);
    }


}
