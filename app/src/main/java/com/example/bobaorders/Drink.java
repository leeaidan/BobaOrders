package com.example.bobaorders;

public class Drink {

    private String name;
    private int sweetness = 100;
    private int ice = 100;
    private double price;
    private String location;

    public Drink(String name, double price, String location){
        this.name = name;
        this.price = price;
        this.location = location;

    }

    public Drink(String name, double price){
        this.name = name;
        this.price = price;

    }

    @Override
    public String toString() {
        return "Drink{" +
                "name='" + name + '\'' +
                ", sweetness=" + sweetness +
                ", ice=" + ice +
                ", price=" + price +
                ", location='" + location + '\'' +
                '}';
    }

    public Drink(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSweetness() {
        return sweetness;
    }

    public void setSweetness(int sweetness) {
        this.sweetness = sweetness;
    }

    public int getIce() {
        return ice;
    }

    public void setIce(int ice) {
        this.ice = ice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
