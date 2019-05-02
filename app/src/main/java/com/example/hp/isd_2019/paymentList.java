package com.example.hp.isd_2019;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class paymentList extends AppCompatActivity {
    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private String URLstring = "https://lbpower.000webhostapp.com/api/getpayment4one.php?fk_client="+currentuser;
    int id2next;
    private static ProgressDialog mProgressDialog;
    private ListView listView;
    ArrayList<PaymentModel> dataModelArrayList;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_list);

        listView = findViewById(R.id.listView1);

        retrieveJSON();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
               id2next= dataModelArrayList.get(position).getid();
                Toast.makeText(getApplicationContext(),Integer.toString(id2next),Toast.LENGTH_SHORT).show();
               Intent myIntent = new Intent(paymentList.this, SinglePayment.class);
               myIntent.putExtra("id", id2next);
              startActivity(myIntent);


            }
        });

    }

    private void retrieveJSON() {

        showSimpleProgressDialog(this, "Loading...","Please wait",false);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);


                                dataModelArrayList = new ArrayList<>();
                                JSONArray dataArray  = obj.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    PaymentModel x = new PaymentModel();
                                    JSONObject dataobj = dataArray.getJSONObject(i);
                                    Log.d("strrrrr", ">>" + dataobj.getString("id"));
                                    x.setid(Integer.parseInt(dataobj.getString("id")));
                                    x.setPayment_state(Integer.parseInt(dataobj.getString("payment_st")));
                                    if(dataModelArrayList.isEmpty()){
                                        Log.d("befoooooooooore", ">>>>>>>>>>>>>>>>>>0000000000000000" );


                                    }
                                    else{


                                        Log.d("befoooooooooore", ">>>>>>>>>11111111111111111111111" );
                                    }


                                    dataModelArrayList.add(x);
                                    if(dataModelArrayList.isEmpty()){
                                        Log.d("Aftrrrrrrrrr", ">>>>>>>>>>>>>>>>>>0000000000000000" );


                                    }
                                    else{


                                        Log.d("Aftrrrrrrrr", ">>>>>>>>>11111111111111111111111" );
                                    }

                                }

                                setupListview();



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }

    private void setupListview(){

        removeSimpleProgressDialog();  //will remove progress dialog
        listAdapter = new paymentAdapter(this, dataModelArrayList);
        listView.setAdapter(listAdapter);

    }

    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showSimpleProgressDialog(Context context, String title,
                                                String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}