
package servlets;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.JSONObject;

public class DeleteFlightServlet extends HttpServlet {
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

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flightdb", "root", "");
        PreparedStatement stmt = con.prepareStatement("DELETE FROM flights WHERE FlightId = ?");
        stmt.setInt(1, flightId);
        
        int rows = stmt.executeUpdate();

        response.setContentType("text/plain");
        if (rows > 0) {
            response.getWriter().println("Flight Deleted Successfully.");
        } else {
            response.getWriter().println("Error: Flight not found.");
        }

        con.close();
      } catch (Exception e) {
        response.getWriter().println("Error: " + e.getMessage());
      }
   }
}
