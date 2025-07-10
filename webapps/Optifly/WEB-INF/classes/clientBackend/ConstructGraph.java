package clientBackend;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import administrator.AirPortsCodes;

public class ConstructGraph {
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

    private void addEdge(int src, int dst, double ecoCost, double busiCost, double fstCost, double duration, double flightId) {
        if (src >= 0 && src < ports && dst >= 0 && dst < ports) {
            List<Double> newEdge = Arrays.asList((double) dst, ecoCost, busiCost, fstCost, duration, flightId);
            adj.get(src).add(newEdge);
        } else {
            System.err.println("Invalid airport code for edge: src=" + src + " dst=" + dst);
        }
    }

    public void buildGraph(final Connection con) throws SQLException {

        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM flights");
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                int src = codes.getAirportCode(rs.getString("source"));
                int dst = codes.getAirportCode(rs.getString("destination"));

                LocalDateTime flightDate = convertStrToDate(rs.getString("flightDate"));
                LocalDateTime landingDate = convertStrToDate(rs.getString("landingDate"));
                double duration = differenceBetweenDates(flightDate, landingDate);

                double ecoCost = rs.getDouble("ecoClCost");
                double busiCost = rs.getDouble("bussiClCost");
                double fstClCost = rs.getDouble("firstClCost");
                double flightId = rs.getInt("FlightId");

                if (src != -1 && dst != -1) {
                    addEdge(src, dst, ecoCost, busiCost, fstClCost, duration, flightId);
                } else {
                    System.err.println("No such airport exists: source=" + rs.getString("source") + " destination=" + rs.getString("destination"));
                }
            }
        }
    }

    private LocalDateTime convertStrToDate(String date) {
        try {
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            return LocalDateTime.parse(date, pattern);
        } catch (Exception e) {
            System.err.println("Error parsing date string: " + date);
            return LocalDateTime.now(); // or throw new IllegalArgumentException(e)
        }
    }

    private double differenceBetweenDates(LocalDateTime start, LocalDateTime end) {
        Duration duration = Duration.between(start, end);
        return duration.toHours();
    }
}
