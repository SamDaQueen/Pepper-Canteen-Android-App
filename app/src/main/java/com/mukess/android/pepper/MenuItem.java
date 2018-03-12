package com.mukess.android.pepper;

/**
 * Created by Samreen on 11-03-2018.
 */

public class MenuItem {

    private String Name;
    private float Price;

    public MenuItem() {

    }

    public MenuItem(String Name, float Price) {
        this.Name = Name;
        this.Price = Price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String itemName) {
        this.Name = itemName;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float itemPrice) {
        this.Price = itemPrice;
    }

}
