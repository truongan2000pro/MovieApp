package com.movieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class userInfo extends AppCompatActivity {
//    private FirebaseAuth mAuth;
//    Button signoutBtn;
//    TextView userEmail;
      BottomNavigationView botNav;
      NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        botNav = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        botNav.setOnNavigationItemSelectedListener(navListener);


//        mAuth = FirebaseAuth.getInstance();
//        signoutBtn = (Button) findViewById(R.id.signOutBtn);
//        userEmail = (TextView) findViewById(R.id.infoEmail);
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//
//
//        if(user == null){
//            Toast.makeText(this, "no USER.",
//                    Toast.LENGTH_SHORT).show();        }
//        else{
//            userEmail.setText(user.getEmail());
//            Toast.makeText(this, "USER",
//                    Toast.LENGTH_SHORT).show();
//        }
//
//        signoutBtn.setOnClickListener(this::Onclick);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new homeFragment();
                            break;
                        case R.id.nav_user:
                            selectedFragment = new userInfoFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment,selectedFragment).commit();
                        return true ;
                    }

            };
//    public void Onclick(View v ){
//        if(v == signoutBtn){
//            mAuth.signOut();
//            Intent toLogIn = new Intent(this, login_screen.class);
//            startActivity(toLogIn);
//        }
//
//    }
}