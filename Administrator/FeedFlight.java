package administrator;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.sql.*;
import java.time.format.DateTimeFormatter;

public class FeedFlight {
    long FlightId;
    String source;
    String destination;
    LocalDateTime flighDateTime;
    LocalDateTime reachDateTime;
    double ecoCost;
    double bussiCost;
    double fstClCost;

    public FeedFlight(long FlightId, String source, String destination, LocalDateTime flighDate, LocalDateTime reachDate, double ecoCost, double bussiCost, double fstClCost) {
        this.FlightId = FlightId;
        this.source = source.toLowerCase();
        this.destination = destination.toLowerCase();
        this.flighDateTime = flighDate;
        this.reachDateTime = reachDate;
        this.bussiCost = bussiCost;
        this.ecoCost = ecoCost;
        this.fstClCost = fstClCost;
    }

    public void insertDb(final Connection con) throws Exception {
        PreparedStatement pst = con.prepareStatement("INSERT INTO flights VALUES (?,?,?,?,?,?,?,?)");
        
        pst.setLong(1, FlightId);
        pst.setString(2, source);
		pst.setString(3,destination);
		pst.setString(4,flighDateTime.toString());
		pst.setString(5,reachDateTime.toString());
        pst.setDouble(6,ecoCost);
        pst.setDouble(7,bussiCost);
        pst.setDouble(8,fstClCost);

		int rows = pst.executeUpdate();
		if(rows>0) System.out.println("\t\tFlight from "+source+" to "+destination+" is added.");
        else System.out.println("\t\tError: Flight can't be inserted.");
    }

    public static void updateDb (final Connection con, int Id, String newFlightDate, String newReachDate) throws Exception {
        PreparedStatement pst = con.prepareStatement("UPDATE flights SET flightDate = ?, landingDate = ? WHERE FlightId = ?");
        pst.setString(1, newFlightDate);
        pst.setString(2, newReachDate);
        pst.setInt(3, Id);

        int rows = pst.executeUpdate();
		if(rows>0) System.out.println("\t\tFlight timings updated");
        else System.out.println("\t\tError: Flight can't be updated.");
    }

    public static void DeleteDb(final Connection con, int id) throws Exception {
        PreparedStatement pst = con.prepareStatement("DELETE FROM flights WHERE FlightId = ?");
        pst.setInt(1, id);

        int rows = pst.executeUpdate();
		if(rows>0) System.out.println("\t\tFlight Cancelled");
        else System.out.println("\t\tError: Flight can't be canceled.");
    }
}
