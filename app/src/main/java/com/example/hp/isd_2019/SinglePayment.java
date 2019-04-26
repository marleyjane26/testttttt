package com.example.hp.isd_2019;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SinglePayment extends AppCompatActivity {
    TextView id,cons,total,costof1,issued,st;
    Button btn;
    int uid=1;
    int IntId;
    private String URLstring = "https://lbpower.000webhostapp.com/api/getsingle.php?fk_client="+uid+"&id=";
    private static ProgressDialog mProgressDialog;
    TextView list;
    //<!--TODO:ADD THE UID FROM FIREBASE ATHENTACATED USER LATER... NOW IS FOR TESTING-->

    public void setURLstring(int id) {
        this.URLstring+=id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_payment);
        id=(TextView)findViewById(R.id.idint);
        cons=(TextView)findViewById(R.id.jsonConsump);
        costof1=(TextView)findViewById(R.id.jsonCost);
        total=(TextView)findViewById(R.id.jsonTotal);
        issued=(TextView)findViewById(R.id.jsonIssued);
        st=(TextView)findViewById(R.id.jsonStatus);
        btn=(Button) findViewById(R.id.btn);
        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("id",0);
        IntId=intValue;
        setURLstring(intValue);
        Log.d("strrrrr", ">>" + URLstring);
        //list = findViewById(R.id.single);
        Toast.makeText(getApplicationContext(),Integer.toString(intValue),Toast.LENGTH_SHORT).show();
        retrieveJSON();
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


                            JSONArray dataArray  = obj.getJSONArray("data");

                            for (int i = 0; i < dataArray.length(); i++) {

                                PaymentModel x = new PaymentModel();
                                JSONObject dataobj = dataArray.getJSONObject(i);
                                Log.d("strrrrr", ">>" + dataobj.getString("consumption"));
                                id.setText(Integer.toString(IntId));
                                cons.setText(dataobj.getString("consumption"));
                               costof1.setText(dataobj.getString("costof1"));
                                total.setText(dataobj.getString("Total"));
                                x.setPayment_state(Integer.parseInt(dataobj.getString("payment_st")));
                                issued.setText(dataobj.getString("issued_date"));
                                if(x.getPayment_state()){

                                    st.setText("Payed");
                                    st.setTextColor(Color.parseColor("#0cd32a"));
                                    btn.setVisibility(View.GONE);

                                }else{

                                    st.setText("Unpaid");

                                }
                                //dataobj.getString("payment_date");



                                removeSimpleProgressDialog();

                            }


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

