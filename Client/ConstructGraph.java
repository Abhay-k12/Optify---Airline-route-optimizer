package client;

import java.util.ArrayList;
import java.util.List;

import administrator.AirPortsCodes;

import java.util.Arrays;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
 

public class ConstructGraph {
    private AirPortsCodes codes;
    public int ports;
    public List<List<List<Double>>> adj;

    public ConstructGraph() {
        codes = new AirPortsCodes();
        ports = codes.airPorts.length;
        adj = new ArrayList<>(ports);
    }

    private void addEdge(int src, int dst, double ecoCost, double busiCost, double fstCost, double duration){
        List<Double> newEdge = new ArrayList<>(Arrays.asList((double)dst ,ecoCost,busiCost,fstCost,duration));
        adj.get(src).add(newEdge);
    }

    public void buildGraph(final Connection con) throws Exception {
        Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * flights FROM ");

        while(rs.next()) {
            int src = codes.getAirportCode(rs.getString("source"));
            int dst = codes.getAirportCode(rs.getString("destination"));
            LocalDateTime flightDate = convertStrToDate(rs.getString("flightDate"));
            LocalDateTime landingDate = convertStrToDate(rs.getString("landingDate"));
            double duration = DiffernceBetweenDates(flightDate, landingDate);
            double ecoCost = rs.getDouble("ecoClCost");
            double busiCost = rs.getDouble("busiClCost");
            double fstClCst = rs.getDouble("firstClCost");

            addEdge(src, dst, ecoCost, busiCost, fstClCst, duration);
        }
    }

    private LocalDateTime convertStrToDate(String date) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date,pattern);
    }

    private double DiffernceBetweenDates(LocalDateTime sd, LocalDateTime ed) {
        Duration duration = Duration.between(sd, ed);
        return duration.toHours();
    }
}
