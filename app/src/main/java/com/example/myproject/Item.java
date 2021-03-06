package com.example.myproject;

public class Item {
    private int itemId;
    private String itemName;
    private double itemPrice;
    private String itemDesc;
    private String fastId;

    public Item() {
        itemId = -1;
    }


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String showLanguage) {
        this.itemDesc = showLanguage;
    }

    public String getFastId() {
        return fastId;
    }

    public void setFastId(String fastId) {
        this.fastId = fastId;
    }
}