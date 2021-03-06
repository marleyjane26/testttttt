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
import android.widget.ImageView;
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
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SinglePayment extends AppCompatActivity {
    TextView id,cons,total,costof1,issued,payed,payedtext,due;
    Button btn;
    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

    int IntId;
    ImageView bckgroud;
    private String URLstring = "https://lbpower.000webhostapp.com/api/getsingle.php?fk_client="+currentuser+"&id=";
    private static ProgressDialog mProgressDialog;
    TextView list;
    public String totals;
    public String ids;


    public void setURLstring(int id) {
        this.URLstring+=id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_payment);
        id=(TextView)findViewById(R.id.jsonid);
        cons=(TextView)findViewById(R.id.jsonConsump);
        costof1=(TextView)findViewById(R.id.jsonCost);
        //bckgroud=(ImageView) findViewById(R.id.imageView2);//TODO:(1:XML)(added to (single /cc/profile))AT THE END must add this overlay to hide xml uppon refresh
        total=(TextView)findViewById(R.id.jsonTotal);
        issued=(TextView)findViewById(R.id.jsonIssued);
        payed=(TextView)findViewById(R.id.jsonStatus);
        due=(TextView)findViewById(R.id.jsonDue);
        payedtext=(TextView)findViewById(R.id.PaymentStatus);
        btn=(Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent activityChangeIntent = new Intent(SinglePayment.this, Pay.class);

               activityChangeIntent.putExtra("id",id.getText());
                activityChangeIntent.putExtra("total",totals);

                //TODO:SEND payment attruibe to pay activty and get the credit information to send also to post php file //
                startActivity(activityChangeIntent);
            }
        });
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
                                totals=dataobj.getString("Total");
                                x.setPayment_state(Integer.parseInt(dataobj.getString("payment_st")));
                                x.setIssued_date(dataobj.getString("issued_date"));
                                issued.setText(dataobj.getString("issued_date"));
//TODO: EFADFSDF
                                due.setText(x.getdue());
                                if(x.getPayment_state()){
                                    payed.setText(dataobj.getString("payment_date"));
                                    btn.setVisibility(View.GONE);

                                }else{

                                    payed.setVisibility(View.GONE);
                                    payedtext.setVisibility(View.GONE);
                                }
                                //dataobj.getString("payment_date");


                               // bckgroud.setVisibility(View.GONE);//TODO:(1:XML)
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

