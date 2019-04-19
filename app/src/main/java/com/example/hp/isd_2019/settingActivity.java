package com.example.hp.isd_2019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class settingActivity extends AppCompatActivity {
    // Array of strings...
    ListView simpleList;
    String settList[] = {"Send feedback", "App version", "About Us", "Logout"};

    @Override   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);      setContentView(R.layout.activity_main);
        simpleList = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_setting, R.id.textView, settList);
        simpleList.setAdapter(arrayAdapter);
    }
}