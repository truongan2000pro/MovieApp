package com.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class userInfo extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button signoutBtn;
    TextView userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        mAuth = FirebaseAuth.getInstance();
        signoutBtn = (Button) findViewById(R.id.signOutBtn);
        userEmail = (TextView) findViewById(R.id.infoEmail);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();



        if(user == null){
            Toast.makeText(this, "no USER.",
                    Toast.LENGTH_SHORT).show();        }
        else{
            userEmail.setText(user.getEmail());
            Toast.makeText(this, "USER",
                    Toast.LENGTH_SHORT).show();
        }

        signoutBtn.setOnClickListener(this::Onclick);
    }

    public void Onclick(View v ){
        if(v == signoutBtn){
            mAuth.signOut();
            Intent toLogIn = new Intent(this, login_screen.class);
            startActivity(toLogIn);
        }

    }
}