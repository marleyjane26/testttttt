package com.example.hp.isd_2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class settingActivity extends AppCompatActivity {

    private Button feedback, appVer, aboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        feedback = (Button) findViewById(R.id.feedbtn);
        appVer = (Button) findViewById(R.id.appVersion);
        aboutUs = (Button) findViewById(R.id.aboutUs);

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(),feedBack.class);
              startActivity(intent);
            }
        });

        appVer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

               Intent intent = new Intent(view.getContext(),appVersion.class);
              startActivity(intent);
            }
        });

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(),aboutUsActivity.class);
                startActivity(intent);
            }
        });

    }
}