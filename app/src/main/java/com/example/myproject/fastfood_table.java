package com.example.myproject;

public class fastfood_table {
    private int fas_Id;
    private  String fas_name;
    private  String contact;
    private  String address;

    public fastfood_table() {
        fas_Id = 1;
    }

    public int getFas_Id() {
        return fas_Id;
    }

    public void setFas_Id(int fas_Id) {
        this.fas_Id = fas_Id;
    }

    public String getFas_name() {
        return fas_name;
    }

    public void setFas_name(String fas_name) {
        this.fas_name = fas_name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

