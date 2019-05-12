package com.example.hp.isd_2019;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;
import static android.Manifest.permission.CALL_PHONE;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FloatingActionButton call;
    String url="https://lbpower.000webhostapp.com/public/index.php?id="+currentuser;
    WebView graph;
   public String number="";
    private static ProgressDialog mProgressDialog;
    TextView kwhText;
    TextView billText;
    private FirebaseAuth mAuth;
    int MY_PERMISSIONS_REQUEST_CALL_PHONE;
    private String URLstring = "https://lbpower.000webhostapp.com/api/getkwh.php?fk_client="+currentuser;
//TODO:get more to attruibe from the php:number of supplier &(0/1) if client found in the device table
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        kwhText = (TextView) findViewById(R.id.kwh);
        billText= (TextView) findViewById(R.id.bill);
        graph=(WebView) findViewById(R.id.graph);
        graph.setWebViewClient(new WebViewClient());
        WebSettings webSettings=graph.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
        graph.setWebChromeClient(new WebChromeClient());
        graph.loadUrl(url);
        call=(FloatingActionButton) findViewById(R.id.fab);



        /*
        *
        *  GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6),
                new DataPoint(4, 6),
                new DataPoint(4, 6),
                new DataPoint(4, 6),
                new DataPoint(4, 12)

        });
        series.setColor(Color.rgb(164, 66, 244));
        series.setThickness(6);
        series.setDrawBackground(true);
        series.setBackgroundColor(Color.GREEN);
        series.setDrawDataPoints(true);
        graph.addSeries(series);
        *
        *
        * */

        mAuth = FirebaseAuth.getInstance();


       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);
        retrieveJSON();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+number));

                if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(i);
                } else {
                    requestPermissions(new String[]{CALL_PHONE}, 1);
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        retrieveJSON();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (doubleBackToExitPressedOnce) {
            finishAffinity();
            finish();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_homepage_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.nav_setting) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_payments) {
            Intent my_intent= new Intent(homepage.this,paymentList.class);
            startActivity(my_intent);
        } else if (id == R.id.nav_profile) {
            Intent my_intent= new Intent(homepage.this,ProfileActivity.class);
            startActivity(my_intent);

        } else if (id == R.id.nav_setting) {
            Intent my_intent= new Intent(homepage.this,settingActivity.class);
            startActivity(my_intent);

        } else if (id == R.id.nav_creditC) {
            Intent my_intent = new Intent(homepage.this, creditcardActivity.class);
            startActivity(my_intent);

        }else if (id == R.id.nav_contact) {
            Intent my_intent = new Intent(homepage.this, contactUsActivity.class);
            startActivity(my_intent);
        } else if (id == R.id.nav_share) {

//TODO:update the navbar add signout to list



                  mAuth.signOut();

            Intent intent = new Intent(this,login.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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


                                JSONObject dataobj = dataArray.getJSONObject(i);
                                Log.d("strrrrr", ">>" + dataobj.getString("kw"));
                                kwhText.setText(dataobj.getString("kw"));
                                billText.setText(dataobj.getString("bill"));
                                if(number.isEmpty()){

                                    number=dataobj.getString("phone");

                                }


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

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

            if(requestCode== MY_PERMISSIONS_REQUEST_CALL_PHONE) {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }

            }
        return;
            // other 'case' lines to check for other
            // permissions this app might request.

    }

}
