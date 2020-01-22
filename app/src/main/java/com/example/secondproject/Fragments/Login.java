package com.example.secondproject.Fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.secondproject.Activitys.BottomNavigationActivity;
import com.example.secondproject.Helper.InputValidation;
import com.example.secondproject.R;
import com.example.secondproject.SQLite.DatabaseHelper;
import com.example.secondproject.Utilis.PreferenceUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends Fragment implements View.OnClickListener {

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;
    private AppCompatTextView textViewLinkRegister;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    public Login() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View FragmentUI = inflater.inflate(R.layout.fragment_login, container, false);

        initViews(FragmentUI);
        initListeners();
        initObjects();

        return FragmentUI;
    }

    private void initViews(View view){

        textInputLayoutEmail = view.findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = view.findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = view.findViewById(R.id.textInputEditTextEmailLogin);
        textInputEditTextPassword = view.findViewById(R.id.textInputEditTextPasswordLogin);

        appCompatButtonLogin = view.findViewById(R.id.appCompatButtonLogin);

        textViewLinkRegister =view.findViewById(R.id.textViewLinkRegister);

        PreferenceUtils utilis = new PreferenceUtils();
        if (utilis.getEmail(getContext()) != null){
            Intent intent = new Intent(getActivity(), BottomNavigationActivity.class);
            startActivity(intent);
        }else{

        }
    }

    private void initListeners(){
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }

    private void initObjects(){
        databaseHelper = new DatabaseHelper(getContext());
        inputValidation = new InputValidation(getContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonLogin:
                verifyFromSQLite();
                break;
            case R.id.textViewLinkRegister:
                Fragment fragmentRegister = new Register();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragmentRegister);
                fragmentTransaction.commit();
                break;
        }
    }

    private void verifyFromSQLite(){

        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }

        String email = textInputEditTextEmail.getText().toString().trim();
        String password = textInputEditTextPassword.getText().toString().trim();

        if(databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim(), textInputEditTextPassword.getText().toString().trim())){


            PreferenceUtils.saveEmail(email,getContext());
            PreferenceUtils.savePassword(password,getContext());

            Intent intent = new Intent(getActivity(), BottomNavigationActivity.class);
            emptyInputEditText();
            startActivity(intent);

        }else
        {
            Toast.makeText(getContext(), "Error at login.", Toast.LENGTH_SHORT).show();
        }

    }

    private void emptyInputEditText(){
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
}

