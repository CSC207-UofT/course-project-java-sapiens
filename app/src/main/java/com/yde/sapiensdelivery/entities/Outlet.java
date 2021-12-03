package com.yde.sapiensdelivery.entities;

import java.util.ArrayList;

public class Outlet {
    String address;
    String name;
    ArrayList<Commodity> commodities;

    public Outlet(String address, String name, ArrayList<Commodity> commodities) {
        this.address = address;
        this.name = name;
        this.commodities = commodities;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Commodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(ArrayList<Commodity> commodities) {
        this.commodities = commodities;
    }
}
