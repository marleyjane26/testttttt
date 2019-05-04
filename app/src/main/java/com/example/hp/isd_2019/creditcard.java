package com.example.hp.isd_2019;

public class creditcard {
    private   String cc_id;
    private  String name_holder;
    private String cc_number;
    private String expire_date;
    private String balance;
    private String Cvc;

    public creditcard() {




    }

    public String getCc_id() {
        return cc_id;
    }

    public String getName_holder() {
        return name_holder;
    }

    public String getCc_number() {
        return cc_number;
    }

    public String getExpire_date() {
        return expire_date;
    }


    public String getBalance() {
        return balance;
    }

    public String getCvc() {
        return Cvc;
    }

    public void setCc_id(String cc_id) {
        this.cc_id = cc_id;
    }

    public void setName_holder(String name_holder) {
        this.name_holder = name_holder;
    }

    public void setCc_number(String cc_number) {
        this.cc_number = cc_number;
    }

    public void setExpire_date(String expire_date) {
        this.expire_date = expire_date;
    }


    public void setBalance(String balance) {
        this.balance = balance;
    }

    public void setCvc(String cvc) {
        this.Cvc = cvc;
    }


    public String getyear(){


        String [] partsts= getExpire_date().split("-");
        return  partsts[0];

    }
    public String getmonth(){


        String [] partsts= getExpire_date().split("-");
        return  partsts[1];

    }

}

