package com.example.myproject;

public class CustomerData {

    private int customer_id;
    private String customer_email;
    private String customer_phone;
    private String customer_address;
    private String customer_fname;
    private String customer_lname;
    private String customer_fullName;
    private String password;
    private String rePassword;

    public CustomerData(int customer_id, String customer_email, String customer_phone,
                        String customer_address, String customer_fname, String customer_lname,
                        String password, String rePassword) {
        this.customer_id = customer_id;
        this.customer_email = customer_email;
        this.customer_phone = customer_phone;
        this.customer_address = customer_address;
        this.customer_fname = customer_fname;
        this.customer_lname = customer_lname;
        this.customer_fullName = customer_fname + " " + customer_lname;
        this.password = password;
        this.rePassword = rePassword;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getCustomer_fname() {
        return customer_fname;
    }

    public void setCustomer_fname(String customer_fname) {
        this.customer_fname = customer_fname;
    }

    public String getCustomer_lname() {
        return customer_lname;
    }

    public void setCustomer_lname(String customer_lname) {
        this.customer_lname = customer_lname;
    }

    public String getCustomer_fullName() {
        return customer_fullName;
    }

    public void setCustomer_fullName(String customer_fullName) {
        this.customer_fullName = customer_fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    @Override
    public String toString() {
        return "CustomerData{" +
                "customer_id=" + customer_id +
                ", customer_email='" + customer_email + '\'' +
                ", customer_phone='" + customer_phone + '\'' +
                ", customer_address='" + customer_address + '\'' +
                ", customer_fname='" + customer_fname + '\'' +
                ", customer_lname='" + customer_lname + '\'' +
                ", customer_fullName='" + customer_fullName + '\'' +
                ", password='" + password + '\'' +
                ", rePassword='" + rePassword + '\'' +
                '}';
    }
}
