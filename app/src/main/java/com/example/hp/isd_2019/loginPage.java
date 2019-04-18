package com.example.hp.isd_2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginPage extends AppCompatActivity {

    Button btnSignIN;
    EditText edtEmail, edtPass;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    String mUsername;
    String mEmailAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnSignIN = (Button) findViewById(R.id.btn_login);
        edtEmail = (EditText) findViewById(R.id.input_email);
        edtPass = (EditText) findViewById(R.id.input_password);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser == null){
            //Not signed in, launch the Sign In Activity
            startActivity(new Intent(this,loginPage.class));
            finish();
            return;
        }
        if (mFirebaseUser == null){
            //Not signed in, launch the Sign In Activity
            startActivity(new Intent(this, loginPage.class));
            finish();
            return;
        }else {
            mUsername = mFirebaseUser.getDisplayName();
            mEmailAddress = mFirebaseUser.getEmail();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
// Check if user is signed in (non-null) and update UI accordingly.
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
      //  updateUI(currentUser);

    }
    public void login(View view){
        Intent my_intent= new Intent(loginPage.this,MainActivity.class);
        startActivity(my_intent);

    }


}