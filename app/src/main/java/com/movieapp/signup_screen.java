package com.movieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup_screen extends AppCompatActivity {
    TextView logInLink ;
    TextView regEmail;
    TextView regPass;
    TextView regPassConfirm;
    Button signUpBtn;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screen);
        mAuth = FirebaseAuth.getInstance();

        regEmail = (TextView) findViewById(R.id.registerUserInput);
        regPass = (TextView) findViewById(R.id.registerPassInput);
        regPassConfirm = (TextView) findViewById(R.id.registerPassInputConfirm);

        logInLink = (TextView) findViewById(R.id.toLogInScreen);
        signUpBtn = (Button) findViewById(R.id.SignUpBtn);

        logInLink.setOnClickListener(this::onClick);
        signUpBtn.setOnClickListener(this::onClick);

    }

    public void onClick(View view) {
        if(view == logInLink){
            Intent logInScreen = new Intent(this, login_screen.class);
            startActivity(logInScreen);

        }
        if(view == signUpBtn){
            String email = regEmail.getText().toString();
            String password = regPass.getText().toString();
            String passwordConfirm = regPassConfirm.getText().toString();

            if(email.matches("")&& password.matches("") && passwordConfirm.matches("")){
                Toast.makeText(signup_screen.this,"You must enter email , password and comfirm your password",Toast.LENGTH_SHORT).show();
            }
            else{  loadingBar.setTitle("Checking");
                loadingBar.setMessage("Please wait a few seconds");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
                if(password.equals(passwordConfirm)){
                    mAuth.createUserWithEmailAndPassword(email,password )
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        loadingBar.hide();
                                        Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();

                                        Intent userInfo = new Intent(signup_screen.this, userInfo.class);
                                        startActivity(userInfo);
                                    }
                                    else {
                                        loadingBar.hide();
                                        Toast.makeText(getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });}

            }

        }
    }
}