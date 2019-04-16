package com.example.hp.isd_2019;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.isd_2019.PaymentModel;

import java.util.ArrayList;

public class PaymentListAdapter extends ArrayAdapter<PaymentModel> {
    private int resourceLayout;
    private Context mContext;

    public PaymentListAdapter(Context aContext,int resource, ArrayList<PaymentModel> listData) {
        super(aContext,resource,listData);
        this.resourceLayout = resource;
        mContext = aContext;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }
        PaymentModel p = getItem(position);
        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.name);
            TextView tt2 = (TextView) v.findViewById(R.id.designation);
            TextView tt3 = (TextView) v.findViewById(R.id.location);
            if (tt1 != null) {
                tt1.setText(p.getClient_id());
            }
            if (tt2 != null) {
                tt2.setText(p.getIssued_date());
            }
            if (tt3 != null) {
                tt3.setText(p.getPayed_date());
            }
        }
        return v;
    }
}
