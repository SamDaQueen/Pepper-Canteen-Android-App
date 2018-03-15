package com.mukess.android.pepper;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Samreen on 11-03-2018.
 */

public class MenuItem implements Parcelable {

    public static final Creator<MenuItem> CREATOR = new Creator<MenuItem>() {
        @Override
        public MenuItem createFromParcel(Parcel in) {
            return new MenuItem(in);
        }

        @Override
        public MenuItem[] newArray(int size) {
            return new MenuItem[size];
        }
    };
    private String Name;
    private float Price;
    private int Quantity;

    public MenuItem() {

    }

    public MenuItem(String Name, float Price) {
        this.Name = Name;
        this.Price = Price;
        Quantity = 0;
    }

    protected MenuItem(Parcel in) {
        Name = in.readString();
        Price = in.readFloat();
        Quantity = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(Name);
        parcel.writeFloat(Price);
        parcel.writeInt(Quantity);
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

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        this.Quantity = quantity;
    }
}
