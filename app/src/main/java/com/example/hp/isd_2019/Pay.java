package com.example.hp.isd_2019;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
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
import java.util.HashMap;
import java.util.Map;

public class Pay extends AppCompatActivity {

    RadioButton firstcc, secondcc;
    // Creating button;
    Button pay;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;

    // Storing server url into String variable.
    String  balanceholder, totalholder,ccidholder,paymentidholder;
    String HttpUrlpost = "https://lbpower.000webhostapp.com/api/postpay.php";
    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private String URLstring = "https://lbpower.000webhostapp.com/api/cc4one.php?fk_client="+currentuser;
    private static ProgressDialog mProgressDialog;
    private ListView listView;
    public  ArrayList<creditcard> dataModelArrayList=new ArrayList<creditcard>();
    FloatingActionButton add;
    private  Double totapublic;
    private  Double idpublic;

    private boolean canpay=true;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        firstcc=(RadioButton) findViewById(R.id.cc1);
       secondcc=(RadioButton) findViewById(R.id.cc2);
        add=(FloatingActionButton) findViewById(R.id.floatingActionButton);
        pay=(Button) findViewById(R.id.payBtn);
        Intent mIntent = getIntent();
        String ids = mIntent.getStringExtra("id");
        paymentidholder=ids;
        String totals = mIntent.getStringExtra("total");
        totalholder=totals;
        Toast.makeText(getApplicationContext(), "total is  "+totals+"and id is "+ids,Toast.LENGTH_SHORT).show();
        idpublic=Double.parseDouble(ids);
        totapublic=Double.parseDouble(totals);
        requestQueue = Volley.newRequestQueue(Pay.this);
        progressDialog = new ProgressDialog(Pay.this);
        retrieveJSON();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Pay.this, addCCActivity.class);
                startActivity(myIntent);
            }
        });


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(canpay){

                    if(firstcc.isChecked()||secondcc.isChecked()){




                       progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
                        progressDialog.show();

                        // Calling method to get value from EditText.




                        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrlpost,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String ServerResponse) {

                                        // Hiding the progress dialog after all task complete.
                                       progressDialog.dismiss();

                                        // Showing response message coming from server.
                                        Toast.makeText(Pay.this, ServerResponse, Toast.LENGTH_LONG).show();
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Pay.this.finish();
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
                                        Toast.makeText(Pay.this, volleyError.toString(), Toast.LENGTH_LONG).show();

                                    }
                                }){
                            @Override
                            protected Map<String, String> getParams() {

                                // Creating Map String Params.
                                Map<String, String> params = new HashMap<String, String>();

                                // Adding All values to Params.
                                params.put("uid", currentuser);
                                params.put("ccid", ccidholder);
                                params.put("paymentid", paymentidholder);
                                params.put("balance", balanceholder);
                                params.put("Total", totalholder);



                                return params;
                            }

                        };

                        // Creating RequestQueue.
                        RequestQueue requestQueue = Volley.newRequestQueue(Pay.this);

                        // Adding the StringRequest object into requestQueue.
                        requestQueue.add(stringRequest);




                    }else{


                        Toast.makeText(getApplicationContext(),"you must choose a  credit card ",Toast.LENGTH_SHORT).show();

                    }















                }else{

                    Toast.makeText(getApplicationContext(),"you can't pay with the current credit card ",Toast.LENGTH_SHORT).show();


                }


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


                            JSONArray dataArray  = obj.getJSONArray("data");

                            for (int i = 0; i < dataArray.length(); i++) {

                                creditcard x = new creditcard();
                                JSONObject dataobj = dataArray.getJSONObject(i);
                                Log.d("strrrrr", ">>" + dataobj.getString("id_cc"));
                                ccidholder=dataobj.getString("id_cc");
                                x.setCc_number(dataobj.getString("cc_number"));
                                x.setBalance(dataobj.getString("balance"));


                                dataModelArrayList.add(x);
                                if(dataModelArrayList.size()>=2){

                                    add.hide();

                                }else{

                                    add.show(); }

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

        if(dataModelArrayList.size()==0){
            firstcc.setVisibility(View.GONE);
            secondcc.setVisibility(View.GONE);



        }else if(dataModelArrayList.size()==1){
            //String testString = "This Is Test";
           // char[] stringToCharArray = testString.toCharArray();
            firstcc.setText(dataModelArrayList.get(0).getCc_number());
            secondcc.setVisibility(View.GONE);
             }else{
            firstcc.setText(dataModelArrayList.get(0).getCc_number());
           secondcc.setText(dataModelArrayList.get(1).getCc_number());

        }



        removeSimpleProgressDialog();  //will remove progress dialog

    }
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        String str="";
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.cc1:
                if(checked)

                   Toast.makeText(getApplicationContext(),"the total is "+totapublic,Toast.LENGTH_SHORT).show();
               if(Double.parseDouble(dataModelArrayList.get(0).getBalance())>totapublic){
                   canpay=true;
                   balanceholder=dataModelArrayList.get(0).getBalance();
                   Toast.makeText(getApplicationContext(),"you can pay with balance "+dataModelArrayList.get(0).getBalance(),Toast.LENGTH_SHORT).show();

               }          else{     canpay=false;     Toast.makeText(getApplicationContext(),"you can't pay with balance ",Toast.LENGTH_SHORT).show();}


                break;
            case R.id.cc2:
                if(checked)
                    if(Double.parseDouble(dataModelArrayList.get(1).getBalance())>totapublic){
                        canpay=true;
                        balanceholder=dataModelArrayList.get(1).getBalance();
                      Toast.makeText(getApplicationContext(),"you can pay with balance "+dataModelArrayList.get(1).getBalance(),Toast.LENGTH_SHORT).show();

                    }       else{    canpay=false; Toast.makeText(getApplicationContext(),"you can't pay with balance ",Toast.LENGTH_SHORT).show();}
                    break;
        }

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

    public static void showSimpleProgressDialog(Context context, String title,String msg, boolean isCancelable) {
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

public String getsize(){

  return "the size is "+this.dataModelArrayList.size();


}

    ///public boolean canPay(int i){


        //TODO:(JAVA)IMPLEMTS IN FUNTRE THIS METHOD

     ////   int balance= Integer.parseInt(dataModelArrayList.get(i).getBalance());
      //// int total=totapublic;
      //// if(balance>total){
        ////   return true;
       ///}return  false;
   /// }


}


