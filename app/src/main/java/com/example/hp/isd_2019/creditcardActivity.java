package com.example.hp.isd_2019;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class creditcardActivity extends AppCompatActivity {

    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private String URLstring = "https://lbpower.000webhostapp.com/api/cc4one.php?fk_client="+currentuser;
    TextView name,number,month,year,cvc;
    SwipeRefreshLayout pullToRefresh;
    TextView empty;


    private static ProgressDialog mProgressDialog;
    private ListView listView;
    ArrayList<creditcard> dataModelArrayList=new ArrayList<creditcard>();
    private ListAdapter listAdapter;
    private ListAdapter emptys;


    FloatingActionButton add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditcard);

       listView= findViewById(R.id.cclist);
       retrieveJSON();
       pullToRefresh = (SwipeRefreshLayout) findViewById(R.id.pullToRefresh);
       // pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
          //  @Override
          //  public void onRefresh() {
            //    listView.setEmptyView(findViewById(R.id.empty_list_item));

            //    pullToRefresh.setRefreshing(false);
             //   retrieveJSON();
           // }
       // });


       add=(FloatingActionButton) findViewById(R.id.btnadd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(creditcardActivity.this, addCCActivity.class);
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


                            JSONArray dataArray  = obj.getJSONArray("data");

                            for (int i = 0; i < dataArray.length(); i++) {

                                creditcard x = new creditcard();
                                JSONObject dataobj = dataArray.getJSONObject(i);
                                Log.d("strrrrr", ">>" + dataobj.getString("id_cc"));
                                x.setCc_number(dataobj.getString("cc_number"));
                                x.setName_holder(dataobj.getString("name_holder"));
                                x.setCvc(dataobj.getString("cvc"));
                                x.setBalance(dataobj.getString("balance"));
                                x.setCc_id(dataobj.getString("id_cc"));
                                x.setExpire_date(dataobj.getString("expire_date"));


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

        removeSimpleProgressDialog();  //will remove progress dialog
        listAdapter = new ccadapter(this, dataModelArrayList);
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
