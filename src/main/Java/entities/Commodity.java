package entities;

public class Commodity {
    private String name;
    private float price;

    public Commodity(String name, float price){
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
