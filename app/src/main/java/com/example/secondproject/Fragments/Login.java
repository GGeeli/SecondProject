package com.example.secondproject.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.secondproject.BottomNavigationActivity;
import com.example.secondproject.R;
import com.example.secondproject.SQLite.LocalDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {


    public Login() {
        // Required empty public constructor
    }

    EditText username, password;
    Button login, register;
    LocalDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View FragmentUI = inflater.inflate(R.layout.fragment_login, container, false);

        username = FragmentUI.findViewById(R.id.editText);
        password = FragmentUI.findViewById(R.id.editText2);
        login = FragmentUI.findViewById(R.id.button);
        register = FragmentUI.findViewById(R.id.button2);
        db = new LocalDatabase(getContext());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loginIsSuccesfull()){
                    Intent intent = new Intent(getContext(), BottomNavigationActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(), "Invalid credentials!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new Register());
                fragmentTransaction.commit();
            }
        });

        return FragmentUI;
    }

    boolean loginIsSuccesfull(){
        boolean token = false;
        String s_username = username.getText().toString();
        String s_password = password.getText().toString();
        Boolean chkusernamePass = db.usernamePassword(s_username,s_password);
        if(chkusernamePass){token = true;}else{token=false;}
        return token;
    }

}
