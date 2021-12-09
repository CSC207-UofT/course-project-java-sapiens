package com.yde.sapiensdelivery.entities;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class Outlet implements Serializable {
    private String address;
    private String name;
    private final ArrayList<Commodity> commodities;

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

    /**
     * @param index index that the Commodity is at
     * @return Commodity at index
     */
    public Commodity getCommodityAtIndex(int index) {
        return commodities.get(index);
    }

    public ArrayList<Commodity> getCommodities() {
        return commodities;
    }

    @NonNull
    public String toString() {
        return this.getName();
    }
}
