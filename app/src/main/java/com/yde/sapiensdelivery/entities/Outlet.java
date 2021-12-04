package com.yde.sapiensdelivery.entities;

import java.util.ArrayList;

public class Outlet {
    private String address;
    private String name;
    private ArrayList<Commodity> commodities;

    public Outlet( String name, String address, ArrayList<Commodity> commodities) {
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

    public Commodity getCommodityAtIndex(int index) {
        return commodities.get(index);
    }

    public void setCommodities(ArrayList<Commodity> commodities) {
        this.commodities = commodities;
    }

    public ArrayList<Commodity> getCommodities() {
        return commodities;
    }
}
