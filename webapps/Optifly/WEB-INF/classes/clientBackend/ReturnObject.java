package clientBackend;

import java.util.*;

public class ReturnObject {
    public List<String[]> distance;
    public double ecoClcost, busiClCost, firstClCost;
    public double time;

    public ReturnObject(){
        distance = new ArrayList<>();
    }

    public ReturnObject(List<String[]> distance, double ecoClCost, double busiClCost, double firstClCost, double time) {
        this.distance = distance;
        this.ecoClcost = ecoClCost;
        this.busiClCost = busiClCost;
        this.firstClCost = firstClCost;
        this.time = time;
    }
}
