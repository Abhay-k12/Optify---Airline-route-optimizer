package clientBackend;

import java.util.*;
import java.sql.*;
import administrator.AirPortsCodes;

public class OptimisePath {

    ConstructGraph graph;

    // Priority queue node
    static class Node implements Comparable<Node> {
        int airportCode;
        double distance;

        Node(int airportCode, double distance) {
            this.airportCode = airportCode;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Double.compare(this.distance, other.distance);
        }
    }

    private ReturnObject dijkstra(int source, int destination, String priority) {
        double[] dist = new double[graph.ports];
        int[] parent = new int[graph.ports];
        double[] flightTaken = new double[graph.ports];
        boolean[] visited = new boolean[graph.ports];

        double[] ecoClCost = new double[graph.ports];
        double[] busiClCost = new double[graph.ports];
        double[] firstClCost = new double[graph.ports];
        double[] totalTime = new double[graph.ports];

        Arrays.fill(dist, Double.MAX_VALUE);
        Arrays.fill(parent, -1);
        Arrays.fill(flightTaken, -1);
        Arrays.fill(visited, false);
        Arrays.fill(ecoClCost, 0);
        Arrays.fill(busiClCost, 0);
        Arrays.fill(firstClCost, 0);
        Arrays.fill(totalTime, 0);

        dist[source] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.airportCode;

            if (visited[u]) continue;
            visited[u] = true;

            if (u == destination) break;

            for (List<Double> edge : graph.adj.get(u)) {
                int v = edge.get(0).intValue();
                double ecoCost = edge.get(1);
                double busiCost = edge.get(2);
                double fstCost = edge.get(3);
                double duration = edge.get(4);
                double flightId = edge.get(5);

                double weight;
                switch (priority) {
                    case "cost":
                        weight = ecoCost;
                        break;
                    case "time":
                        weight = duration;
                        break;
                    case "both":
                        weight = ecoCost * 0.6 + duration * 0.4;
                        break;
                    default:
                        weight = ecoCost;
                }

                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    parent[v] = u;
                    flightTaken[v] = flightId;

                    ecoClCost[v] = ecoClCost[u] + ecoCost;
                    busiClCost[v] = busiClCost[u] + busiCost;
                    firstClCost[v] = firstClCost[u] + fstCost;
                    totalTime[v] = totalTime[u] + duration;

                    pq.add(new Node(v, dist[v])); // updated node entry
                }
            }
        }

        if (!visited[destination]) {
            return new ReturnObject(); 
        }

        AirPortsCodes codes = new AirPortsCodes();
        List<String[]> path = new ArrayList<>();
        for (int at = destination; at != -1; at = parent[at]) {
            int flightId = (int) flightTaken[at];
            if (at == source) {
                path.add(0, new String[]{codes.getAirport(at), "-1"});
            } else {
                path.add(0, new String[]{codes.getAirport(at), Integer.toString(flightId)});
            }
        }

        firstClCost[destination] = 25000;
        return new ReturnObject(
                path,
                ecoClCost[destination],
                busiClCost[destination],
                firstClCost[destination],
                totalTime[destination]
        );
    }

    public ReturnObject shortestPath(int source, int destination, String priority) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flightdb", "root", "");

        this.graph = new ConstructGraph();
        graph.buildGraph(con);

        ReturnObject result = dijkstra(source, destination, priority);
        con.close();
        return result;
    }
}
