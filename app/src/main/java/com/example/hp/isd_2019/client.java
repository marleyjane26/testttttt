package com.example.hp.isd_2019;

public class client {
    private String clientId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String city;
    private String street;
    private String email;
    private String cost_1k;
    private String Supplier_fullname;
    private String Supplier_Contact;
    private int Cumulative ;
    private int Bill;
    private boolean device_st;
    public client()
    {


    }


    public String getFirstName() {
        return firstName;}

    public String getLastName() {
        return lastName;}

    public String getCity() {
        return city;}

    public String getStreet() {
        return street;}

    public String getEmail() {
        return email;}

    public String getClientId() {
        return clientId;}

    public String getPhoneNumber() {
        return phoneNumber;}

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCost_1k() {
        return cost_1k;
    }

    public void setCost_1k(String cost_1k) {
        this.cost_1k = cost_1k;
    }

    public String getSupplier_fullname() {
        return Supplier_fullname;
    }

    public void setSupplier_fullname(String supplier_fullname) {
        Supplier_fullname = supplier_fullname;
    }

    public String getSupplier_Contact() {
        return Supplier_Contact;
    }

    public void setSupplier_Contact(String supplier_Contact) {
        Supplier_Contact = supplier_Contact;
    }

    public int getCumulative() {
        return Cumulative;
    }

    public void setCumulative(int cumulative) {
        Cumulative = cumulative;
    }

    public int getBill() {
        return Bill;
    }

    public void setBill(int bill) {
        Bill = bill;
    }

    public boolean isDevice_st() {
        return device_st;
    }

    public void setDevice_st(boolean device_st) {
        this.device_st = device_st;
    }
}
