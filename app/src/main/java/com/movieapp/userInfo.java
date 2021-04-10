package com.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class userInfo extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button signoutBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        mAuth = FirebaseAuth.getInstance();
        signoutBtn = (Button) findViewById(R.id.signOutBtn);

        if(mAuth.getCurrentUser() == null){
            Toast.makeText(this, "no USER.",
                    Toast.LENGTH_SHORT).show();        }
        else{
            Toast.makeText(this, "USER.",
                    Toast.LENGTH_SHORT).show();
        }

        signoutBtn.setOnClickListener(this::Onclick);
    }

    public void Onclick(View v ){
        if(v == signoutBtn){
            mAuth.signOut();
        }

    }
}