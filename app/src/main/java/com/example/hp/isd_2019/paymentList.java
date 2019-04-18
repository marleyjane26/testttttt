package com.example.hp.isd_2019;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class paymentList extends AppCompatActivity {


    private ProgressBar progressBar;
    private ListView listView;
    private List<PaymentModel> paymentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        progressBar = findViewById(R.id.progressBar);
        paymentList = new ArrayList<>();
        //this method will fetch and parse the data
        loadList();
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        loadList();
    }



    private void loadList() {
        String JSON_URL = "http://www.snowcorp.org/api/androidlearning/jsondemo";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //hiding the progressbar after completion and showing list view
                        progressBar.setVisibility(View.GONE);
                        listView.setVisibility(View.VISIBLE);
                        // Showing json data in log monitor
                        Log.d("json", response.toString());
                        try {
                            //we have the array named hero inside the object
                            //so here we are getting that json array


                            //now looping through all the elements of the json array
                            for (int i = 0; i < response.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject jsonObject = response.getJSONObject(i);
                                //creating a hero object and giving them the values from json object
                                PaymentModel item = new PaymentModel();

                                item.setClient_id(Integer.parseInt(jsonObject.getString("id")));
                                //adding the json data to list
                                paymentList.add(item);
                            }
                            //creating custom adapter object
                            paymentAdapter mAdapter = new paymentAdapter(paymentList, getApplicationContext());
                            //adding the adapter to list view
                            listView.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        //displaying the error in toast if occurred
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        //adding the string request to request queue
        volley.getInstance().addToRequestQueue(jsonArrayRequest);
    }
}

