package com.example.hp.isd_2019;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class creditcardActivity extends AppCompatActivity {

    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private String URLstring = "https://lbpower.000webhostapp.com/api/getcc4one.php?fk_client="+currentuser;
    TextView ccNum,ccNam,exD,exY,ccvC;

    private static ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cc);
        ccNum=(TextView)findViewById(R.id.ccnb);
        ccNam=(TextView)findViewById(R.id.ccname);
        exD=(TextView)findViewById(R.id.expDate);
        exY=(TextView)findViewById(R.id.year);
        ccvC=(TextView)findViewById(R.id.cvv);
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

                                creditcard x = new creditcard();
                                JSONObject dataobj = dataArray.getJSONObject(i);
                                Log.d("strrrrr", ">>" + dataobj.getString("fname"));
                                x.setCc_number(dataobj.getString("cc_number"));
                                x.setName_holder(dataobj.getString("name_holder"));
                                x.setExpire_date(dataobj.getString("expire_date"));
                                x.setYear(dataobj.getString("year"));
                                x.setCvc(dataobj.getString("cvc"));

                                ccNum.setText(x.getCc_number());
                                ccNam.setText(x.getName_holder());
                                exD.setText(x.getExpire_date());
                                exY.setText(x.getYear());
                                ccvC.setText(x.getCvc());


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
