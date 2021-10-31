package entities;

public class Commodity {
    private String name;
    private double price;

    public Commodity(String name, double price){
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }
}
