package com.example.secondproject.Fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.example.secondproject.Activitys.MainActivity;
import com.example.secondproject.R;
import com.example.secondproject.Utilis.PreferenceUtils;

public class Profile extends Fragment implements View.OnClickListener{


    public Profile() {
        // Required empty public constructor
    }


    Button  buttonLogout;
    ImageView avatar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View FragmentUI =inflater.inflate(R.layout.fragment_profile, container, false);
       //avatar = FragmentUI.findViewById(R.id.imageView);
       // avatar.setImageResource(avatar);
        buttonLogout = FragmentUI.findViewById(R.id.buttonLogout);
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
