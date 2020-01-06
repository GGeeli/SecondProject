package com.example.secondproject.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.secondproject.R;
import com.example.secondproject.SQLite.LocalDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment {


    public Register() {
        // Required empty public constructor
    }

    EditText username,email, password;
    Button create;
    LocalDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View FragmentUI = inflater.inflate(R.layout.fragment_register, container, false);

        username = FragmentUI.findViewById(R.id.editText3);
        email = FragmentUI.findViewById(R.id.editText4);
        password = FragmentUI.findViewById(R.id.editText5);
        create = FragmentUI.findViewById(R.id.button3);
        db = new LocalDatabase(getContext());

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()){
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, new Login());
                    fragmentTransaction.commit();
                }
            }
        });


        return FragmentUI;
    }

    boolean validate(){
        boolean token = false;
        String s_username = username.getText().toString();
        String s_email = email.getText().toString();
        String s_password = password.getText().toString();
        if(s_username.equals("") || s_email.equals("") || s_password.equals("")){
            Toast.makeText(getContext(), "Fill all the fields above!", Toast.LENGTH_SHORT).show();
            token=false;
        }else{
            Boolean chkusername = db.checkusername(s_username);
            Boolean chkemail = db.checkemail(s_email);
            if(!chkusername){
                Toast.makeText(getContext(), "Username taken.", Toast.LENGTH_SHORT).show();
            }else if (!chkemail)
            {
                Toast.makeText(getContext(), "Email taken.", Toast.LENGTH_SHORT).show();
            }else{
                Boolean insert = db.insert(s_username,s_email,s_password);
                if(insert){
                    Toast.makeText(getContext(), "Account created!", Toast.LENGTH_SHORT).show();
                    token=true;}
                else{
                    Toast.makeText(getContext(), "Error creating user!", Toast.LENGTH_SHORT).show();
                    token=false;
                }
            }
        }
        return token;
    }

}