package com.example.hp.isd_2019;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class paymentAdapter extends ArrayAdapter<PaymentModel> {
    //the hero list that will be displayed
    private List<PaymentModel> paymentlist;
    //the context object
    private Context mCtx;
    //here we are getting the movies list and context
    //so while creating the object of this adapter class we need to give movie list and context
    public paymentAdapter(List<PaymentModel> paymentlist, Context mCtx) {
        super(mCtx, R.layout.listpymnt, paymentlist);
        this.paymentlist = paymentlist;
        this.mCtx = mCtx;
    }
    //this method will return the list item
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        //getting the layout inflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.listpymnt, null, true);
        //getting text views
        TextView textView = listViewItem.findViewById(R.id.pymnt);

        //Getting the json data for the specified position
        PaymentModel items = paymentlist.get(position);
        //setting json values to text view
        textView.setText(items.getid());

        return listViewItem;
    }
}