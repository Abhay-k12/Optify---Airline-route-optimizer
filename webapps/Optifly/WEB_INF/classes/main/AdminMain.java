package webapps.Optifly.WEB_INF.classes.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import webapps.Optifly.WEB_INF.classes.administrator.FeedFlight;

public class AdminMain {
    public static void main(String args[]) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flightdb", "root", "");
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        int choice = displayMenu(sc1);
        try {
            switch(choice) {
                case 1: {
                    FeedFlight fl = returnFlight(sc1,sc2);
                    fl.insertDb(con);
                    break;
                }
                case 2: {
                    System.out.print("\tEnter the flight Id:");
                    int tempId = sc1.nextInt();
                    System.out.print("\tEnter the flight takeoff date & time (YYYY-MM-DD HH:mm:ss):");
                    String newFlightDate = inpuDate(sc2).toString();
                    System.out.print("\tEnter the flight landing date & time (YYYY-MM-DD HH:mm:ss):");
                    String newReachDate = inpuDate(sc2).toString();
                    FeedFlight.updateDb(con,tempId,newFlightDate,newReachDate);
                    break;
                }
                case 3: {
                    System.out.print("\tEnter the flight Id:");
                    int tempId = sc1.nextInt();
                    FeedFlight.DeleteDb(con, tempId);
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

    static FeedFlight returnFlight(Scanner sc1, Scanner sc2) {
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
        
        System.out.print("\tEnter the first Class Cost:");
        double fstClCost = sc1.nextDouble();

        return new FeedFlight(FlightId, source, destination, flighDate, reachDate, ecoCost, bussiCost, fstClCost);
    }

    static LocalDateTime inpuDate (Scanner sc) {
        String dateTime = sc.nextLine();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTime, pattern);
    }
}
