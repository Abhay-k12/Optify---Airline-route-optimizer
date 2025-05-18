package Administrator;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.sql.*;
import java.time.format.DateTimeFormatter;

class Flight {
    long FlightId;
    String source;
    String destination;
    LocalDateTime flighDateTime;
    LocalDateTime reachDateTime;
    double ecoCost;
    double bussiCost;
    double fstClCost;

    public Flight(long FlightId, String source, String destination, LocalDateTime flighDate, LocalDateTime reachDate, double ecoCost, double bussiCost, double fstClCost) {
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
        PreparedStatement pst = con.prepareStatement("INSERT INTO flights VALUES (?,?,?,?)");
        
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
        PreparedStatement pst = con.prepareStatement("UPDATE flights SET flightDate = ?, reachDate = ? WHERE FlightId = ?");
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

public class FeedFlights {

    public static void main(String args[]) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Flightdb", "root", "");
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        int choice = displayMenu(sc1);
        try {
            switch(choice) {
                case 1: {
                    Flight fl = returnFlight(sc1,sc2);
                    fl.insertDb(con);
                    break;
                }
                case 2: {
                    System.out.print("\tEnter the flight Id:");
                    int tempId = sc1.nextInt();
                    String newFlightDate = inpuDate(sc1).toString();
                    String newReachDate = inpuDate(sc1).toString();
                    Flight.updateDb(con,tempId,newFlightDate,newReachDate);
                    break;
                }
                case 3: {
                    System.out.print("\tEnter the flight Id:");
                    int tempId = sc1.nextInt();
                    Flight.DeleteDb(con, tempId);
                    break;
    
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static int displayMenu(Scanner sc) {
        System.out.println("Enter (1) to add a new flight.");
        System.out.println("Enter (2) to update a flight time.");
        System.out.println("Enter (3) to cancel a flight.");
        int choice  = sc.nextInt();
        return choice;
    }

    static Flight returnFlight(Scanner sc1, Scanner sc2) {
        System.out.print("\tEnter the flight ID:");
        long FlightId = sc1.nextLong();
        
        System.out.print("\tEnter the flight source:");
        String source = sc2.nextLine();
        
        System.out.print("\tEnter the flight destination:");
        String destination = sc2.nextLine();
        
        System.out.print("\tEnter the flight takeoff date & time (YYYY-MM-DD HH:mm:ss):");
        LocalDateTime flighDate = inpuDate(sc2);
        
        System.out.print("\tEnter the flight landing date & time (YYYY-MM-DD HH:mm:ss):");
        LocalDateTime reachDate = inpuDate(sc2);
        
        System.out.print("\tEnter the Economic flight cost:");
        double ecoCost = sc1.nextDouble();
        
        System.out.print("\tEnter the Bussiness flight cost:");
        double bussiCost = sc1.nextDouble();
        
        System.out.print("\tEnter the flight ID:");
        double fstClCost = sc1.nextDouble();

        return new Flight(FlightId, source, destination, flighDate, reachDate, ecoCost, bussiCost, fstClCost);
    }

    static LocalDateTime inpuDate (Scanner sc) {
        String dateTime = sc.nextLine();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTime, pattern);
    }
}