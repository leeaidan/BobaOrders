package com.example.bobaorders;

import java.util.ArrayList;

public class Drink {
    private String name;
    private String price;
    private String ice = "100% Ice";
    private String sweetness = "100% Sweetness";
    private ArrayList<Toppings> toppings = new ArrayList<Toppings>();

    public Drink(){

    }

    public Drink(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIce() {
        return ice;
    }

    public void setIce(String ice) {
        this.ice = ice;
    }

    public String getSweetness() {
        return sweetness;
    }

    public void setSweetness(String sweetness) {
        this.sweetness = sweetness;
    }

    public ArrayList<Toppings> getToppings() {
        return toppings;
    }

    public void addToppings(Toppings topping){
        toppings.add(topping);
    }

    public enum Toppings {
        NONE("None"), TAPIOCA("Tapioca Pearls"), AGAR("Agar Pearls"), COCONUT_JELLY("Coconut Jelly"),
        RAINBOW_JELLY("Rainbow Jelly"); //TODO: Add more, add a second field for the price for calculation purposes

        String s;
        Toppings(String s){
            this.s = s;
        }

        public String getString(){
            return s;
        }
    }

}
