package com.example.hp.isd_2019;

public class PaymentModel {
    private int client_id;
    private double Total;
    private double consumption;
    private double costof1kw;
    private int payment_state;
    private String issued_date;
    private String payed_date;

    public PaymentModel() {

    }

    public PaymentModel(int client_id, double total, double consumption, double costof1kw, int payment_state, String issued_date, String payed_date) {
        this.client_id = client_id;
        Total = total;
        this.consumption = consumption;
        this.costof1kw = costof1kw;
        this.payment_state = payment_state;
        this.issued_date = issued_date;
        this.payed_date = payed_date;
    }

    public int getid() {
        return client_id;
    }


    public boolean getPayment_state() {
        if(this.payment_state==1){


            return  true;
        }
        else return  false;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }
    public void setid(int id) {
        client_id = id;
    }
    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public double getCostof1kw() {
        return costof1kw;
    }

    public void setCostof1kw(double costof1kw) {
        this.costof1kw = costof1kw;
    }

    public void setPayment_state(int payment_state) {
        this.payment_state = payment_state;
    }

    public String getIssued_date() {
        return issued_date;
    }

    public void setIssued_date(String issued_date) {
        this.issued_date = issued_date;
    }

    public String getPayed_date() {
        return payed_date;
    }

    public void setPayed_date(String payed_date) {
        this.payed_date = payed_date;
    }
}
