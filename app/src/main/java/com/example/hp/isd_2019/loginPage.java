package com.example.hp.isd_2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class loginPage extends AppCompatActivity {

    Button btnSignIN;
    EditText edtEmail, edtPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnSignIN = (Button) findViewById(R.id.btn_login);
        edtEmail = (EditText) findViewById(R.id.input_email);
        edtPass = (EditText) findViewById(R.id.input_password);



    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.


    }
    public void login(View view){
        Intent my_intent= new Intent(loginPage.this,MainActivity.class);
        startActivity(my_intent);

    }


}