package com.example.hp.isd_2019;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity  {

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
        setContentView(R.layout.activity_main);
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
        series.setBackgroundColor(Color.GREEN);
        series.setDrawDataPoints(true);
        graph.addSeries(series);





    }
}