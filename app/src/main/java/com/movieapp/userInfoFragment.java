package com.movieapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link userInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class userInfoFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public userInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment userInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static userInfoFragment newInstance(String param1, String param2) {
        userInfoFragment fragment = new userInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private FirebaseAuth mAuth;
    Button signoutBtn;
    TextView userEmail;
    Button infoChange;
    EditText userFullName;
    EditText userPassword;
    EditText userPasswordConfirm;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    public void Onclick(View v ){
        if(v == signoutBtn){
            mAuth.signOut();
            Intent toLogIn = new Intent(getActivity(), login_screen.class);
            startActivity(toLogIn);
        }
        if(v == infoChange ){
            if(user == null){
                Toast.makeText(getActivity(), "You are not SIGNED IN!", Toast.LENGTH_SHORT).show();
            }
            else{
                if(userFullName.getText().toString().matches("" )&& userPassword.getText().toString().matches("") && userPasswordConfirm.getText().toString().matches("")  ){
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName("").build();

                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getActivity(),"Updated",Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(getActivity(),"Error happened, Please check all the fields again!",Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
                else{
                    if(!userPasswordConfirm.getText().toString().equals("") && !userPassword.getText().toString().equals("") && userPassword.getText().toString().equals(userPasswordConfirm.getText().toString()) ){
//                        Toast.makeText(getActivity(),"ASDSAD", Toast.LENGTH_SHORT).show();
                        user.updatePassword(userPassword.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(getActivity(),"Password Updated, you will be logged out!",Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            Toast.makeText(getActivity(),"Your password must be at least 6 characters , Please check all the password fields again!",Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });

                    }
                    else{
                        Toast.makeText(getActivity(),"Password will not be updated!",Toast.LENGTH_SHORT).show();
                    }
//                    else if (userFullName.getText().toString()!="" && userPasswordConfirm.getText().toString() !="" && userPassword.getText().toString() != "" && userPassword.getText().toString().equals(userPasswordConfirm.getText().toString())){
//                        Toast.makeText(getActivity(),"Plase update your name and password separatedly , our server can not handle both of it at the same time!",Toast.LENGTH_SHORT).show();
//
//                    }


                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(userFullName.getText().toString()).build();
                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getActivity()," Fullname Updated ",Toast.LENGTH_SHORT).show();

                                    }
                                    else{
                                        Toast.makeText(getActivity(),"Error happened, Please check all the fields again!, if you just updated your password , this error may occur!",Toast.LENGTH_LONG).show();

                                    }
                                }
                            });

                }




            }
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_info, container, false);
        mAuth = FirebaseAuth.getInstance();
        signoutBtn = (Button) v.findViewById(R.id.signOutBtn);
        userEmail = (TextView) v.findViewById(R.id.infoEmail);
        infoChange = (Button) v.findViewById(R.id.infoBtn);
        userFullName = (EditText) v.findViewById(R.id.infoName);
        userPassword = (EditText) v.findViewById(R.id.infoPassword);
        userPasswordConfirm = (EditText) v.findViewById(R.id.infoPasswordComfirm);
        String password;

        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null) {
            password = bundle.getString("password");
            userPassword.setText(password);
            userPasswordConfirm.setText(password);


        }


        if(user == null){
            Toast.makeText(getActivity(), "no USER.", Toast.LENGTH_SHORT).show();
            }
        else{

            userEmail.setText(user.getEmail());
            userFullName.setText(user.getDisplayName());
            Toast.makeText(getActivity(),"USER", Toast.LENGTH_SHORT).show();
        }
        infoChange.setOnClickListener(this::Onclick);
        signoutBtn.setOnClickListener(this::Onclick);
        return v;
    }
}