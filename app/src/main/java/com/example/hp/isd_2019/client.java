package com.example.hp.isd_2019;

public class client {
    private int clientId;
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

    public int getClientId() {
        return clientId;}

    public String getPhoneNumber() {
        return phoneNumber;}

    public void setClientId(int clientId) {
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
}
