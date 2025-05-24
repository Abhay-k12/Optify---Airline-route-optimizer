package clientBackend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.AbstractMap.SimpleEntry;

import clientBackend.ConstructGraph;

public class OptimisePath {
    ConstructGraph graph;

    public OptimisePath(ConstructGraph graph) {
        this.graph = graph;
        System.out.println(graph.ports);
    }

    public List<int[]> dijkstra(int source, int destination, String priority) {
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


}
