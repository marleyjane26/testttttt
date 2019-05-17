package com.example.hp.isd_2019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class Language extends AppCompatActivity {

    private ListView mainListView ;
    private ArrayAdapter<String> listAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        mainListView = (ListView) findViewById( R.id.mainListView );
        String[] languages = new String[] { "Arabic", "English", "Italian", "Dutch",
                "Russian"};

        ArrayList<String> langList = new ArrayList<String>();
        langList.addAll( Arrays.asList(languages) );
        listAdapter = new ArrayAdapter<String>(this, R.layout.rowlayout, langList);


        listAdapter.add( "Spanish" );
        listAdapter.add( "Portoguese" );


        mainListView.setAdapter( listAdapter );
    }
}



