package com.example.hp.isd_2019;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class PaymentFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_payment, container, false);

        ArrayList<PaymentModel> paymentList = getListData();
        final ListView lv = (ListView) rootView.findViewById(R.id.list1);
        lv.setAdapter(new PaymentListAdapter(getContext(), R.layout.payment_listview, paymentList));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                PaymentModel user = (PaymentModel) lv.getItemAtPosition(position);
                Toast.makeText(getContext(), "Selected :" + " " + user.getClient_id()+", "+ user.getPayed_date(), Toast.LENGTH_SHORT).show();
            }
        });
        return inflater.inflate(R.layout.fragment_payment, container, false);
    }

    private ArrayList getListData() {
        ArrayList<PaymentModel> results = new ArrayList<>();

        PaymentModel user1 = new PaymentModel();
        user1.setClient_id(1);
        user1.setIssued_date("Team Leader");
        user1.setPayed_date("Hyderabad");
        results.add(user1);

        PaymentModel user2 = new PaymentModel();
        user2.setClient_id(2);
        user2.setIssued_date("Team Leader");
        user2.setPayed_date("Hyderabad");
        results.add(user2);

        PaymentModel user3 = new PaymentModel();
        user3.setClient_id(3);
        user3.setIssued_date("Team Leader");
        user3.setPayed_date("Hyderabad");
        results.add(user3);

        return results;
    }
}
