package com.example.hp.isd_2019;

public class creditcard {
    private   int cc_id;
    private  String name_holder;
    private int cc_number;
    private int expire_date;
    private double balance;


    public creditcard(int cc_id,int id_user,String name_holder,int cc_number,int expire_date,double balance){

        this.cc_id=cc_id;

        this.cc_number=cc_number;
        this.name_holder=name_holder;
        this.expire_date=expire_date;
        this.balance=balance;
    }
    // Getter
    public String getName_holder() {
        return name_holder;
    }
    public int getCc_id() {
        return cc_id;
    }

    public int getCc_number() {
        return cc_number;
    }

    public int getExpire_date() {
        return expire_date;
    }

    public double getBalance() {
        return balance;
    }


    public void setCc_id(int cc_id) {
        this.cc_id = cc_id;
    }


    public void setName_holder(String name_holder) {
        this.name_holder = name_holder;
    }

    public void setCc_number(int cc_number) {
        this.cc_number = cc_number;
    }

    public void setExpire_date(int expire_date) {
        this.expire_date = expire_date;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

