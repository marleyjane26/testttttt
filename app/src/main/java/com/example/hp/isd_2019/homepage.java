package com.example.hp.isd_2019;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class homepage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView humdityText;
    TextView tempText;
    String humds;
    String temps;
    DatabaseReference dref = FirebaseDatabase.getInstance().getReference("DHT11");
    Query lastQueryHumd = dref.child("humd").orderByKey().limitToLast(1);
    Query lastQueryTemp = dref.child("temp").orderByKey().limitToLast(1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        tempText = (TextView) findViewById(R.id.temp);
        humdityText= (TextView) findViewById(R.id.humd);

        lastQueryHumd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                humds = dataSnapshot.getValue().toString();
                String segments[] = humds.split("=");
                String document = segments[segments.length - 1];
                String last = document.replaceAll("[=}{]", "");

                humdityText.setText(last);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w( "loadPost:onCancelled", databaseError.toException());
            }
        });

        lastQueryTemp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                temps = dataSnapshot.getValue().toString();
                String segments[] = temps.split("=");
                String document = segments[segments.length - 1];
                String last = document.replaceAll("[=}{]", "");

                tempText.setText(last);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w( "loadPost:onCancelled", databaseError.toException());
            }
        });
        GraphView graph = (GraphView) findViewById(R.id.graph);
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
        series.setBackgroundColor(Color.rgb(164, 66, 244));
        series.setDrawDataPoints(true);
        graph.addSeries(series);


       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homepage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent my_intent= new Intent(homepage.this,paymentList.class);
            startActivity(my_intent);
        } else if (id == R.id.nav_gallery) {
            Intent my_intent= new Intent(homepage.this,Main2Activity.class);
            startActivity(my_intent);
        } else if (id == R.id.nav_slideshow) {
            Intent my_intent= new Intent(homepage.this,MainActivity.class);
            startActivity(my_intent);
        } else if (id == R.id.nav_manage) {
            Intent my_intent= new Intent(homepage.this,SinglePayment.class);
            startActivity(my_intent);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
