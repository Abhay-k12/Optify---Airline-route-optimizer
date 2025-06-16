package servlets;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.JSONObject;

import administrator.AirPortsCodes;
import clientBackend.OptimisePath;
import clientBackend.ReturnObject;

public class OptimisePathServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();

        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject json = new JSONObject(sb.toString());
            String source = json.getString("source");
            String destination = json.getString("destination");
            String priority = json.getString("priority");

            OptimisePath op = new OptimisePath();
            AirPortsCodes codes = new AirPortsCodes();
            int srcCode = codes.getAirportCode(source);
            int dstCode = codes.getAirportCode(destination);

            ReturnObject path = op.shortestPath(srcCode, dstCode, priority);

            if (path.distance.isEmpty()) {
                out.print("No valid route found.");
                return;
            }

            StringBuilder result = new StringBuilder();
            result.append("Optimized Route:\n");

            for (int i = 0; i < path.distance.size(); i++) {
                String airport = path.distance.get(i)[0];
                String flightId = path.distance.get(i)[1];
                result.append(airport);
                if (flightId.equals(Integer.toString(-1))) {
                    result.append(" (Flight ID: ").append(flightId).append(")");
                }
                if (i != path.distance.size() - 1) result.append(" → ");
            }

            result.append("\nTotal Economical Class Cost = "+path.ecoClcost);
            result.append("\nTotal Bussiness Class Cost = "+path.busiClCost);
            result.append("\nTotal First Class Cost = "+path.firstClCost);
            result.append("\nTotal Duration of Flight = "+path.time);
            out.print(result.toString());

        } catch (Exception e) {
            resp.setStatus(500);
            out.print("Error: " + e.getMessage());
        }
    }
}
