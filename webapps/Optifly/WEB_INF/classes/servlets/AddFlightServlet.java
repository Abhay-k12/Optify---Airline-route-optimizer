package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.time.LocalDateTime;
import org.json.JSONObject;

public class AddFlightServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader =  request.getReader();
        StringBuilder jsonBuffer = new StringBuilder();
        String line;

        while((line = reader.readLine())!=null) jsonBuffer.append(line);

        String jsonString = jsonBuffer.toString();

        JSONObject json = new JSONObject(jsonString);
        try {
            long flightId = json.getLong("flightId");
            String source = json.getString("source");
            String dest = json.getString("destination");
            LocalDateTime flightDate = LocalDateTime.parse(json.getString("flightDate") + "T00:00:00");
            LocalDateTime landingDate = LocalDateTime.parse(json.getString("landingDate") + "T00:00:00");
            double eco = Double.parseDouble(json.getString("ecoCost"));
            double bus = Double.parseDouble(json.getString("businessCost"));
            double fst = Double.parseDouble(json.getString("firstCost"));

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flightdb", "root", "");

            insertDb(con,flightId, source, dest, flightDate, landingDate, eco, bus, fst);
            

            response.getWriter().println("Flight Added Successfully.");

        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    private void insertDb(final Connection con,long FlightId, String source, String destination, LocalDateTime flightDateTime, LocalDateTime reachDateTime, double ecoCost, double bussiCost,double fstClCost) throws Exception {
        PreparedStatement pst = con.prepareStatement("INSERT INTO flights VALUES (?,?,?,?,?,?,?,?)");
        
        pst.setLong(1, FlightId);
        pst.setString(2, source);
		pst.setString(3,destination);
		pst.setString(4,flightDateTime.toString());
		pst.setString(5,reachDateTime.toString());
        pst.setDouble(6,ecoCost);
        pst.setDouble(7,bussiCost);
        pst.setDouble(8,fstClCost);

		int rows = pst.executeUpdate();
		if(rows>0) System.out.println("\t\tFlight from "+source+" to "+destination+" is added.");
        else System.out.println("\t\tError: Flight can't be inserted.");
    }
}
