package com.example.hp.isd_2019;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private String URLstring = "https://lbpower.000webhostapp.com/api/getprofile4one.php?fk_client="+currentuser;
    TextView fname,lname,city,street,phone,email;
    FloatingActionButton edits;

    private static ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_profile);
        fname=(TextView)findViewById(R.id.firstName);
        lname=(TextView)findViewById(R.id.lastName);
        city=(TextView)findViewById(R.id.cityName);
        street=(TextView)findViewById(R.id.streetName);
        phone=(TextView)findViewById(R.id.phoneNumber);
        email=(TextView)findViewById(R.id.emailName);
       edits=(FloatingActionButton) findViewById(R.id.btn);
        super.onCreate(savedInstanceState);
        retrieveJSON();
        edits.setOnClickListener(new View.OnClickListener() {
         @Override
           public void onClick(View v) {
               Toast.makeText(getApplicationContext(),"yopeee ",Toast.LENGTH_SHORT).show();
               Intent myIntent = new Intent(ProfileActivity.this, editProfileActivity.class);
            //  myIntent.putExtra("id", id2next);
             startActivity(myIntent); }
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


                            JSONArray dataArray  = obj.getJSONArray("data");

                            for (int i = 0; i < dataArray.length(); i++) {

                                client x = new client();
                                JSONObject dataobj = dataArray.getJSONObject(i);
                                Log.d("strrrrr", ">>" + dataobj.getString("fname"));
                                x.setFirstName(dataobj.getString("fname"));
                                x.setLastName(dataobj.getString("lname"));
                                x.setCity(dataobj.getString("city"));
                                x.setStreet(dataobj.getString("street"));
                                x.setPhoneNumber(dataobj.getString("phone"));
                                x.setEmail(dataobj.getString("email"));
                                fname.setText(x.getFirstName());
                                lname.setText(x.getLastName());
                                city.setText(x.getCity());
                                street.setText(x.getStreet());
                                phone.setText(x.getPhoneNumber());
                                email.setText(x.getEmail());

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
