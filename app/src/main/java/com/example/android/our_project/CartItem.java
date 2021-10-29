package com.example.android.our_project;

public class CartItem {

    String meal ;
    int med_amout;
    int large_amout;
    int total_price;

    public CartItem(String meal, int med_amout, int large_amout, int total_price) {
        this.meal = meal;
        this.med_amout = med_amout;
        this.large_amout = large_amout;
        this.total_price = total_price;
    }

    public String getMeal() {
        return meal;
    }

    public int getMed_amout() {
        return med_amout;
    }

    public int getLarge_amout() {
        return large_amout;
    }

    public int getTotal_price() {
        return total_price;
    }
}
