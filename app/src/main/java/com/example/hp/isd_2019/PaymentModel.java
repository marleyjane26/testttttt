package com.example.hp.isd_2019;
import java.text.DateFormatSymbols;
public class PaymentModel {
    private int id;
    private double Total;
    private double consumption;
    private double costof1kw;
    private int payment_state;
    private String issued_date;
    private String payed_date;
    private  String due_date;


    public PaymentModel() {

    }

    public PaymentModel(int client_id, double total, double consumption, double costof1kw, int payment_state, String issued_date, String payed_date) {
        this.id = client_id;
        Total = total;
        this.consumption = consumption;
        this.costof1kw = costof1kw;
        this.payment_state = payment_state;
        this.issued_date = issued_date;
        this.payed_date = payed_date;
    }

    public int getid() {
        return id;
    }


    public boolean getPayment_state() {
        if(this.payment_state==1){


            return  true;
        }
        else return  false;
    }

public String getdue(){
    String [] partsts= this.issued_date.split("-");
    int day=Integer.parseInt(partsts[2]);
    int due=day+3;
    String finals=partsts[0]+"-"+partsts[1]+"-"+due;

    return finals;



}


    public String getMonth() {

        String [] partsts= this.issued_date.split("-");
       int month=Integer.parseInt(partsts[1]);


        return new DateFormatSymbols().getMonths()[month-1];


    }

        public  String getmonths(){

            String monthString;

            String [] partsts= this.issued_date.split("-");
            String month=partsts[1];
            switch (month) {
                case "01":  monthString = "January";       break;
                case "02":  monthString = "February";      break;
                case "03":  monthString = "March";         break;
                case "04":  monthString = "April";         break;
                case "05":  monthString = "May";           break;
                case "06":  monthString = "June";          break;
                case "07":  monthString = "July";          break;
                case "08":  monthString = "August";        break;
                case "09":  monthString = "September";     break;
                case "10": monthString = "October";       break;
                case "11": monthString = "November";      break;
                case "12": monthString = "December";      break;
                default: monthString = "Invalid month"; break;
            }
            return monthString;


        }
    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }
    public void setid(int id) {
        this.id = id;
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
