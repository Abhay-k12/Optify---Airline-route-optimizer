package Administrator;

import java.util.HashMap;

public class AirPortsCodes {

    public String[] airPorts = {"Agartala", "Agatti", "Agra", "Ahmedabad", "Aizawl", "Amritsar", "Aurangabad",
        "Ayodhya", "Bagdogra", "Bareilly", "Belagavi", "Bengaluru", "Bhopal", "Bhubaneswar", "Chandigarh", "Chennai",
        "Coimbatore", "Darbhanga", "Dehradun", "Delhi", "Deoghar", "Dharamshala", "Dibrugarh", "Dimapur", "Diu", "Durgapur",
        "Gaya", "Goa", "Gondia", "Gorakhpur", "Guwahati", "Gwalior", "Hirasar", "Hubli", "Hyderabad", "Imphal", "Indore",
        "Itanagar", "Jabalpur", "Jagdalpur", "Jaipur", "Jaisalmer", "Jammu", "Jharsuguda", "Jodhpur", "Jorhat", "Kadapa",
        "Kannur", "Kanpur", "Khajuraho", "Kochi", "Kolhapur", "Kolkata", "Kozhikode", "Kurnool", "Leh", "Lucknow", "Madurai",
        "Mangaluru", "Mumbai", "Mysuru", "Nagpur", "Nashik", "North Goa", "Pantnagar", "Patna", "Port Blair", "Prayagraj",
        "Puducherry", "Pune", "Raipur", "Rajahmundry", "Rajkot", "Ranchi", "Salem", "Shillong", "Shirdi", "Shivamogga",
        "Silchar", "Srinagar", "Surat", "Thiruvananthapuram", "Tiruchirappalli", "Tirupati", "Tuticorin", "Udaipur",
        "Vadodara", "Varanasi", "Vijayawada", "Visakhapatnam" };
    

    public String[] states = {"Tripura", "Ladakh", "Uttar Pradesh", "Gujarat", "Mizoram", "Punjab", "Maharashtra", "Uttar Pradesh", 
        "West Bengal", "Uttar Pradesh", "Karnataka", "Karnataka", "Madhya Pradesh", "Odisha", "Chandigarh", "Tamil Nadu", 
        "Tamil Nadu", "Bihar", "Uttarakhand", "Delhi", "Jharkhand", "Himachal Pradesh", "Assam", "Nagaland", "Dadra and Nagar Haveli and Daman and Diu", 
        "West Bengal", "Bihar", "Goa", "Maharashtra", "Uttar Pradesh", "Assam", "Madhya Pradesh", "Gujarat", "Karnataka",
        "Telangana", "Manipur", "Madhya Pradesh", "Arunachal Pradesh", "Madhya Pradesh", "Chhattisgarh", "Rajasthan", 
        "Rajasthan", "Jammu and Kashmir", "Odisha", "Rajasthan", "Assam", "Andhra Pradesh", "Kerala", "Uttar Pradesh", 
        "Madhya Pradesh", "Kerala", "Maharashtra", "West Bengal", "Kerala", "Andhra Pradesh", "Ladakh", "Uttar Pradesh", 
        "Tamil Nadu", "Karnataka", "Maharashtra", "Karnataka", "Maharashtra", "Maharashtra", "Goa", "Uttarakhand", 
        "Bihar", "Andaman and Nicobar Islands", "Uttar Pradesh", "Puducherry", "Maharashtra", "Chhattisgarh", "Andhra Pradesh", 
        "Gujarat", "Jharkhand", "Tamil Nadu", "Meghalaya", "Maharashtra", "Karnataka", "Assam", "Jammu and Kashmir",
        "Gujarat", "Kerala", "Tamil Nadu", "Andhra Pradesh", "Tamil Nadu", "Rajasthan", "Gujarat", "Uttar Pradesh", "Andhra Pradesh", "Andhra Pradesh"};


    public HashMap <String,Integer> portToCode = new HashMap<>();
    public HashMap <Integer,String> codeToPort = new HashMap<>();
    public HashMap <Integer,String> portCity = new HashMap<>();
        
    public AirPortsCodes() {
        for(int i=0; i<airPorts.length; i++){
            portToCode.put(airPorts[i].toLowerCase(), i);
            codeToPort.put(i,airPorts[i].toLowerCase());
            portCity.put(i,states[i].toLowerCase());
        }
    }

    public int getAirportCode(String str) {
        if(portToCode.containsKey(str.toLowerCase())) {return portToCode.get(str.toLowerCase());}
        return -1;
    }

    public String getAirport(int n) {
        if(n>0 && n<airPorts.length)
            return codeToPort.get(n);
        return null; 
    }

    public String getPortCity(int n) {
        if(n>0 && n<airPorts.length)
            return portCity.get(n);
        return null;  
    }
    
}
