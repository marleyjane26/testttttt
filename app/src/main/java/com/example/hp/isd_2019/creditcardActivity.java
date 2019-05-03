package com.example.hp.isd_2019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class creditcardActivity extends AppCompatActivity {
    TextView ccNum,ccNam,exD,exY,ccvC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cc);
        ccNum=(TextView)findViewById(R.id.ccnb);
        ccNam=(TextView)findViewById(R.id.ccname);
        exD=(TextView)findViewById(R.id.expDate);
        exY=(TextView)findViewById(R.id.year);
        ccvC=(TextView)findViewById(R.id.cvv);

    }
}
