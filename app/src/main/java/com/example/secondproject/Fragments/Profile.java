package com.example.secondproject.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.secondproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {


    public Profile() {
        // Required empty public constructor
    }


    Button imagebtn;
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


        return FragmentUI;
    }

}
