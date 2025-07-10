package servlets;

import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;

import javax.servlet.*;
import javax.servlet.http.*;
import org.json.JSONObject;

public class ViewFlightServlet extends HttpServlet {
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        StringBuilder res = new StringBuilder();
        String line;
        
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        
        JSONObject json = new JSONObject(sb.toString());
        int flightId = json.getInt("flightId");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flightdb", "root", "");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM flights");
        
        while(rs.next()) {
            String src = rs.getString("source").toUpperCase();
            String dst = rs.getString("destination").toUpperCase();

            String flightDate = rs.getString("flightDate");
            String landingDate = rs.getString("landingDate");

            double ecoCost = rs.getDouble("ecoClCost");
            double busiCost = rs.getDouble("bussiClCost");
            double fstClCost = rs.getDouble("firstClCost");
            int Id = rs.getInt("FlightId");

            if(Id == flightId) {
                res.append("Flight Id:"+flightId);
                res.append("\nSource:"+src);
                res.append("\nDestination:"+dst);
                res.append("\nFlight Date:"+flightDate);
                res.append("\nLanding Date:"+landingDate);
                res.append("\nEconomical class Cost:"+ecoCost);
                res.append("\nBussiness class Cost:"+busiCost);
                res.append("\nFirst class Cost:"+fstClCost);
                break;
            }
        }
        
        response.setContentType("text/plain");
        if (res.isEmpty()) {
            response.getWriter().println("No such flight exist.");
        } else {
            response.getWriter().println(res.toString());
        }
        con.close();
      } catch (Exception e) {
        response.getWriter().println("Error: " + e.getMessage());
      }
   }
}
