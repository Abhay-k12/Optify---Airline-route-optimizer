package servlets;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.JSONObject;

public class UpdateFlightServlet extends HttpServlet {
   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        
        JSONObject json = new JSONObject(sb.toString());

        int flightId = json.getInt("flightId");
        String flightDate = json.getString("flightDate") + "T00:00:00";
        String landingDate = json.getString("landingDate") + "T00:00:00";
        String source = json.getString("source");
        String destination = json.getString("destination");
        int ecoCost = json.getInt("ecoCost");
        int businessCost = json.getInt("businessCost");
        int firstCost = json.getInt("firstCost");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flightdb", "root", "");

        PreparedStatement pst = con.prepareStatement(
            "UPDATE flights SET flightDate=?, landingDate=?, source=?, destination=?, ecoClCost=?, bussiClCost=?, firstClCost=? WHERE FlightId=?"
        );

        pst.setString(1, flightDate);
        pst.setString(2, landingDate);
        pst.setString(3, source);
        pst.setString(4, destination);
        pst.setInt(5, ecoCost);
        pst.setInt(6, businessCost);
        pst.setInt(7, firstCost);
        pst.setInt(8, flightId);

        int rows = pst.executeUpdate();

        response.setContentType("text/plain");
        if (rows > 0) {
            response.getWriter().println("Flight updated successfully.");
        } else {
            response.getWriter().println("Error: Flight not found.");
        }

        con.close();
      } catch (Exception e) {
        response.getWriter().println("Error: " + e.getMessage());
      }
   }
}
