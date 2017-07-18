package com.example.mgs1982.remindset.login.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;

import com.example.mgs1982.remindset.R;
import com.example.mgs1982.remindset.fetchdata.ui.DisplayDataFromFB;
import com.example.mgs1982.remindset.login.presenter.LoginPresenter;
import com.example.mgs1982.remindset.registration.model.UserModel;
import com.example.mgs1982.remindset.session.SessionManagement;



public class LoginActivity extends AppCompatActivity implements LoginActivityInterface {

    AppCompatEditText editTextEmail;
    AppCompatEditText editTextPass;
    AppCompatButton btnLogin;
    SessionManagement session;
    ProgressDialog progressDialog;
    String email, password;
    LoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    public void initView()
    {
       session = new SessionManagement(this);
        presenter = new LoginPresenter(this, this);
        editTextEmail=(AppCompatEditText)findViewById(R.id.editTextLogEmail);
        editTextPass=(AppCompatEditText)findViewById(R.id.editTextLogPass);
        btnLogin=(AppCompatButton)findViewById(R.id.btnLogLogin);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void loginSuccess(UserModel model)
    {
        loginToSharedPreference(model);
    }

    @Override
    public void loginFailure(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPogressDailog(String message)
    {

        if (!isFinishing())
        {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(message);
            progressDialog.show();
        }

    }

    @Override
    public void hideProgressDailog()
    {

        if (!isFinishing() && progressDialog != null)
        {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnLogLogin:
                email=editTextEmail.getText().toString();
                password=editTextPass.getText().toString();
                presenter.getLoginResponseFromFireBase(email,password);
                break;
        }

    }
    public void loginToSharedPreference(UserModel model)
    {
        session.put(model);
        Intent intent = new Intent(this, DisplayDataFromFB.class);
        startActivity(intent);
        finish();
    }
}
