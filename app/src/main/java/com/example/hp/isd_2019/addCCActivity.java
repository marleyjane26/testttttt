package com.example.hp.isd_2019;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;

public class addCCActivity extends AppCompatActivity {
EditText number,name,year,month,cvc;
Button save;
    RequestQueue requestQueue;

    // Create string variable to hold the EditText Value.
    String numberholer, nameholder, yearholder,monthholder,cvcholder;
   String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
    // Creating Progress dialog.
    ProgressDialog progressDialog;

    // Storing server url into String variable.
    String HttpUrl = "https://lbpower.000webhostapp.com/api/postcc.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cc);
        number=(EditText) findViewById(R.id.numberE);
        name=(EditText) findViewById(R.id.nameE);
        year=(EditText) findViewById(R.id.yearE);
        month=(EditText) findViewById(R.id.monthE);
        cvc=(EditText) findViewById(R.id.cvcE);
        save=(Button) findViewById(R.id.btnsave);

        // Creating Volley newRequestQueue .
        requestQueue = Volley.newRequestQueue(addCCActivity.this);

        progressDialog = new ProgressDialog(addCCActivity.this);

        // Adding click listener to button.
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Showing progress dialog at user registration time.
                progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
                progressDialog.show();

                // Calling method to get value from EditText.
                GetValueFromEditText();

                // Creating string request with post method.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {

                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();

                                // Showing response message coming from server.
                                Toast.makeText(addCCActivity.this, ServerResponse, Toast.LENGTH_LONG).show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        addCCActivity.this.finish();
                                    }
                                }, 2000);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();

                                // Showing error message if something goes wrong.
                                Toast.makeText(addCCActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                                number.getText().clear();
                              name.getText().clear();
                             month.getText().clear();
                              year.getText().clear();
                              cvc.getText().clear();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {

                        // Creating Map String Params.
                        Map<String, String> params = new HashMap<String, String>();

                        // Adding All values to Params.
                        params.put("uid", currentuser);
                        params.put("number", numberholer);
                        params.put("name", nameholder);
                        params.put("month", monthholder);
                        params.put("year", yearholder);
                        params.put("cvc", cvcholder);


                        return params;
                    }

                };

                // Creating RequestQueue.
                RequestQueue requestQueue = Volley.newRequestQueue(addCCActivity.this);

                // Adding the StringRequest object into requestQueue.
                requestQueue.add(stringRequest);

            }
        });

    }

    // Creating method to get value from EditText.
    public void GetValueFromEditText(){

        numberholer = number.getText().toString().trim();
        nameholder = name.getText().toString().trim();
        monthholder = month.getText().toString().trim();
        yearholder = year.getText().toString().trim();
        cvcholder = cvc.getText().toString().trim();


    }
}
