package com.example.hp.isd_2019;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class feedBack extends AppCompatActivity {
 static final int CUSTOM_DIALOG_ID=0;
 Button customDialog_Dismiss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);


    Button buttonStartDialog = (Button)findViewById(R.id.send);
        buttonStartDialog.setOnClickListener(new Button.OnClickListener(){
@Override
public void onClick(View arg0) {
        // TODO Auto-generated method stub
        showDialog(CUSTOM_DIALOG_ID);
        }
        });

        }
private Button.OnClickListener customDialog_DismissOnClickListener  = new Button.OnClickListener(){
@Override
public void onClick(View arg0) {
        // TODO Auto-generated method stub
        dismissDialog(CUSTOM_DIALOG_ID);
        }
        };
@Override
protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        Dialog dialog = null;;
        switch(id) {
        case CUSTOM_DIALOG_ID:
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_feed_back);
        dialog.setTitle("Feed Back Form");
        customDialog_Dismiss = (Button)dialog.findViewById(R.id.cancel);
        customDialog_Dismiss.setOnClickListener(customDialog_DismissOnClickListener);
        break;
        }
        return dialog;
        }
        }