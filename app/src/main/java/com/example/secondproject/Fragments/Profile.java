package com.example.secondproject.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.example.secondproject.Activitys.MainActivity;
import com.example.secondproject.Helper.InputValidation;
import com.example.secondproject.R;
import com.example.secondproject.Utilis.PreferenceUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;

public class Profile extends Fragment implements View.OnClickListener{

    private AppCompatButton buttonLogout;
    private AppCompatButton changePicture;
    private AppCompatButton uploadPicture;
    private AppCompatButton changePassword;

    private TextInputLayout textInputLayoutPasswordOld;
    private TextInputLayout textInputLayoutNewPasswordOne;
    private TextInputLayout textInputLayoutNewPasswordTwo;

    private TextInputEditText textInputEditTextPasswordOld;
    private TextInputEditText textInputEditTextNewPasswordOne;
    private TextInputEditText textInputEditTextNewPasswordTwo;

    private InputValidation inputValidation;
    private NestedScrollView nestedScrollView;
    private ImageView avatar;

    public Profile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View FragmentUI =inflater.inflate(R.layout.fragment_profile, container, false);

        initViews(FragmentUI);
        initListeners();




        return FragmentUI;
    }

    private void initViews(View v){
        buttonLogout = v.findViewById(R.id.buttonLogout);
        changePicture = v.findViewById(R.id.appCompatButtonChangeAvatar);
        uploadPicture = v.findViewById(R.id.uploadAvatar);
        changePassword = v.findViewById(R.id.appCompatButtonChangePassword);

        textInputLayoutPasswordOld = v.findViewById(R.id.textInputLayoutOldPassword);
        textInputLayoutNewPasswordOne = v.findViewById(R.id.textInputLayoutNewPasswordOne);
        textInputLayoutNewPasswordTwo = v.findViewById(R.id.textInputLayoutNewPasswordTwo);

        textInputEditTextPasswordOld = v.findViewById(R.id.textInputEditTextOldPassword);
        textInputEditTextNewPasswordOne = v.findViewById(R.id.textInputEditTextNewPasswordOne);
        textInputEditTextNewPasswordTwo = v.findViewById(R.id.textInputEditTextNewPasswordTwo);

        inputValidation = new InputValidation(getContext());


        nestedScrollView=v.findViewById(R.id.nestedScrollViewProfile);

    }

    private void initListeners(){
        buttonLogout.setOnClickListener(this);
        changePicture.setOnClickListener(this);
        uploadPicture.setOnClickListener(this);
        changePassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonLogout:
                PreferenceUtils.savePassword(null,getContext());
                PreferenceUtils.saveEmail(null,getContext());
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.appCompatButtonChangeAvatar:
                break;
            case R.id.uploadAvatar:
                break;
            case R.id.appCompatButtonChangePassword:
                if(!inputValidation.isInputCurrentPassword(textInputEditTextPasswordOld,textInputLayoutPasswordOld, getString(R.string.error_user_exist)))
                {
                    return;
                }
                if(!inputValidation.isInputEditTextMatches(textInputEditTextNewPasswordOne,textInputEditTextNewPasswordTwo,textInputLayoutNewPasswordOne,getString(R.string.error_password_match)))
                {
                    return;
                }



                break;

        }

    }
}
