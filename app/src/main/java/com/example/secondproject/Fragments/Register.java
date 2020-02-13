package com.example.secondproject.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.secondproject.Helper.InputValidation;
import com.example.secondproject.Model.User;
import com.example.secondproject.R;
import com.example.secondproject.SQLite.DatabaseHelper;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class Register extends Fragment implements View.OnClickListener{


    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;


    public Register() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View FragmentUI = inflater.inflate(R.layout.fragment_register, container, false);

        initViews(FragmentUI);
        initListeners();
        initObjects();

        return FragmentUI;
    }

    private void initViews(View v){

        nestedScrollView = v.findViewById(R.id.nestedScrollView);

        textInputLayoutName =  v.findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail =  v.findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword =  v.findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword =  v.findViewById(R.id.textInputLayoutConfirmPassword);

        textInputEditTextName = v.findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail = v.findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = v.findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = v.findViewById(R.id.textInputEditTextConfirmPassword);

        appCompatButtonRegister = v.findViewById(R.id.appCompatButtonRegisterDatabase);
        appCompatTextViewLoginLink = v.findViewById(R.id.textViewLinkLogin);

    }

    private void  initListeners(){
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);
    }

    private void initObjects(){
        inputValidation = new InputValidation(getContext());
        databaseHelper = new DatabaseHelper(getContext());
        user = new User();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.appCompatButtonRegisterDatabase:
                postDataToSQLite();
                break;
            case R.id.textViewLinkLogin:
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new Login());
                fragmentTransaction.commit();
                break;
        }
    }

    private void postDataToSQLite(){
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }

        if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {

            user.setName(textInputEditTextName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());

            databaseHelper.addUser(user);

            Toast.makeText(getContext(), "Account created.", Toast.LENGTH_SHORT).show();
            emptyInputEditText();

        } else {
            Toast.makeText(getContext(), R.string.error_email_exists, Toast.LENGTH_SHORT).show();
        }
    }
    private void emptyInputEditText(){
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }
}
