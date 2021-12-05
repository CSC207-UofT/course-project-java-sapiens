package com.yde.sapiensdelivery.use_cases;

import com.yde.sapiensdelivery.entities.Commodity;
import com.yde.sapiensdelivery.entities.Outlet;

import java.util.ArrayList;

public class OutletManager {
    private final Outlet outlet;

    public OutletManager(Outlet outlet) {
        this.outlet = outlet;
    }

    public Commodity newCommodityAtIndex(int index) {
        // Need to make a copy of whatever Commodity is in the Store rather than to return it
        Commodity outletComm = outlet.getCommodityAtIndex(index);
        return new Commodity(outletComm.getName(), outletComm.getPrice(), outletComm.getQuantity());
    }

    public ArrayList<Commodity> getCommList(){
        return this.outlet.getCommodities();
    }

    public String getCommNameAtIndex(int index) {
        // Need to show the name in Spinner
        return outlet.getCommodityAtIndex(index).getName();
    }
    public String getOutletName(){
        return this.outlet.getName();
    }

    public String getOutletAddress(){
        return this.outlet.getAddress();
    }
}
