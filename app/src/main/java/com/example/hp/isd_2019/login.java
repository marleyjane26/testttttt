package com.example.hp.isd_2019;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class login extends AppCompatActivity {



    private FirebaseAuth.AuthStateListener mAuthListener; private FirebaseAuth mAuth;

    // UI references.
    private EditText mEmail, mPassword;
    private Button btnSignIn,btnSignOut,btnAddItems,forget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmail = (EditText) findViewById(R.id.input_email);
        mPassword = (EditText) findViewById(R.id.input_password);
        btnSignIn = (Button) findViewById(R.id.btn_login);
        forget=(Button) findViewById(R.id.forgetbtn);


        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                  //  Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully signed in with: " +user.getUid());
                    client c =new client();
                    c.setClientId(user.getUid());
                    Intent my_intent= new Intent(login.this,homepage.class);
                    startActivity(my_intent);
                } else {
                    // User is signed out
                  //2  Log.d(TAG, "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out.");
                }
                // ...
            }
        };

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString();
                String pass = mPassword.getText().toString();
                if(!email.equals("") && !pass.equals("")){
                    mAuth.signInWithEmailAndPassword(email,pass);
                }else{
                    toastMessage("You didn't fill in all the fields.");
                }
            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(login.this, Forget_Password.class);
                startActivity(myIntent);
            }
        });
       // btnSignOut.setOnClickListener(new View.OnClickListener() {
      //      @Override
      //      public void onClick(View view) {
      //          mAuth.signOut();
       //         toastMessage("Signing Out...");
      //      }
     //   });

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    //add a toast to show when successfully signed in
    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}