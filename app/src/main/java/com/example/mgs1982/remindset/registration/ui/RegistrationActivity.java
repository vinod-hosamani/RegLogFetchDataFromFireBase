package com.example.mgs1982.remindset.registration.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.example.mgs1982.remindset.R;
import com.example.mgs1982.remindset.login.ui.LoginActivity;
import com.example.mgs1982.remindset.registration.model.UserModel;
import com.example.mgs1982.remindset.registration.presenter.RegistrationPresenter;



public class RegistrationActivity extends AppCompatActivity implements RegistrationActivityInterface
{
    UserModel userModel;
    RegistrationPresenter registrationPresenter;
    ProgressDialog mProgressDialog;


    AppCompatEditText editTextRegUserName;
    AppCompatEditText editTextRegPass;
    AppCompatEditText email;
    AppCompatButton btnReg;
    AppCompatButton btnDlongin;

    AppCompatTextView textViewuserFire;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initView();
    }

    public void initView()
    {
        registrationPresenter= new RegistrationPresenter(this,this);

        editTextRegUserName=(AppCompatEditText)findViewById(R.id.editTextRegUserName);
        email=(AppCompatEditText)findViewById(R.id.editTextEmail);
        editTextRegPass=(AppCompatEditText)findViewById(R.id.editTextRegPass);
        btnReg=(AppCompatButton)findViewById(R.id.btnRegistration);
        btnDlongin=(AppCompatButton)findViewById(R.id.btnDlogin);
        btnReg.setOnClickListener(this);
        btnDlongin.setOnClickListener(this);

       // textViewuserFire=(AppCompatTextView)findViewById(R.id.textViewFirebaseID);
    }

    @Override
    public void registrationSuccess(String message)
    {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registrationFailure(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showProgressDialog(String message)
    {
        if(mProgressDialog!=null && !mProgressDialog.isShowing()) {
            mProgressDialog.setMessage(message);
            mProgressDialog.show();
        }

    }

    @Override
    public void hideProgressDialog()
    {
        if(mProgressDialog!=null)
            mProgressDialog.hide();
    }

    @Override
    public void userFireBaseSuccuss(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void userFireBaseFailure(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnRegistration:
                userModel=new UserModel();
                userModel.setUserName(editTextRegUserName.getText().toString());
                userModel.setEmail(email.getText().toString());
                userModel.setPassword(editTextRegPass.getText().toString());

            if(validationRegistration(userModel))
             {
                registrationPresenter.getRegister(userModel);
                 Intent intent = new Intent(this, LoginActivity.class);
                 startActivity(intent);
                 finish();
             }
              break;
            case R.id.btnDlogin:
                Intent intent=new Intent(this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }

    }

    private boolean validationRegistration(UserModel userModel)
    {
        boolean flag = true, toast = true;

        int passwordlen = userModel.getPassword().length();

        if(userModel.getUserName().length()==0 || userModel.getPassword().length()==0)
        {
            Toast.makeText(this, "enter both the field", Toast.LENGTH_SHORT).show();
        }
        if(userModel.getUserName().length()>25)
        {
            Toast.makeText(this, "enter valide user name", Toast.LENGTH_SHORT).show();
            flag = flag && false;
        }
        if(toast)
        {
            if(passwordlen<8)
            {
                Toast.makeText(this, "invalid password length", Toast.LENGTH_SHORT).show();
                flag = flag && false;

            }
        }
        return flag;
    }
}
