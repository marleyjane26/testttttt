package com.example.hp.isd_2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class settingActivity extends AppCompatActivity {

    private Button language, appVer, aboutUs,deviceG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        language = (Button) findViewById(R.id.lanGbtn);
        appVer = (Button) findViewById(R.id.appVersion);
        aboutUs = (Button) findViewById(R.id.aboutUs);
        deviceG = (Button) findViewById(R.id.deviceGuide);

        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(),Language.class);
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
        deviceG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(),deviceGuide.class);
                startActivity(intent);
            }
        });

    }
}