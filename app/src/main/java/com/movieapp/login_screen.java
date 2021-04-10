package com.movieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login_screen extends AppCompatActivity  {
    TextView signUpLink;
    TextView emailInput;
    TextView passInput;
    Button btnLogin;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        mAuth = FirebaseAuth.getInstance();
        signUpLink = (TextView) findViewById(R.id.toSignUpScreen);
        btnLogin = (Button) findViewById(R.id.loginBtn);
        emailInput = (TextView) findViewById(R.id.userInput);
        passInput = (TextView) findViewById(R.id.passInput);
        signUpLink.setOnClickListener(this::onClick);
        btnLogin.setOnClickListener(this::onClick);

    }

    public void onClick(View v){
        if(v == signUpLink){
            Intent signUpScreen = new Intent(this, signup_screen.class);
            startActivity(signUpScreen);

        }
        if(v == btnLogin){
            mAuth.signInWithEmailAndPassword(emailInput.getText().toString() , passInput.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete( @NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(login_screen.this, "Authentication OK.",
                                        Toast.LENGTH_SHORT).show();
//                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent userInfo = new Intent(login_screen.this, userInfo.class);
                                startActivity(userInfo);
                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(login_screen.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }
    }

}