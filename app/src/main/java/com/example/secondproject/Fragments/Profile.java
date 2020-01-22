package com.example.secondproject.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.secondproject.Activitys.MainActivity;
import com.example.secondproject.R;
import com.example.secondproject.Utilis.PreferenceUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment implements View.OnClickListener{


    public Profile() {
        // Required empty public constructor
    }


    Button imagebtn, buttonLogout;
    ImageView avatar;
    EditText oldpass, newpass1, newpass2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View FragmentUI =inflater.inflate(R.layout.fragment_profile, container, false);
        imagebtn = FragmentUI.findViewById(R.id.button4);
        avatar = FragmentUI.findViewById(R.id.imageView);
        oldpass = FragmentUI.findViewById(R.id.editText6);
        newpass1 = FragmentUI.findViewById(R.id.editText7);
        newpass2 = FragmentUI.findViewById(R.id.editText8);
        avatar.setImageResource(R.drawable.avatar);
        buttonLogout = FragmentUI.findViewById(R.id.button3);
        buttonLogout.setOnClickListener(this);

        return FragmentUI;
    }

    @Override
    public void onClick(View v) {
        PreferenceUtils.savePassword(null,getContext());
        PreferenceUtils.saveEmail(null,getContext());
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }
}
