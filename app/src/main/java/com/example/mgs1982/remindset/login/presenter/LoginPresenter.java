package com.example.mgs1982.remindset.login.presenter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.example.mgs1982.remindset.login.interactor.LoginInteactor;
import com.example.mgs1982.remindset.login.interactor.LoginInteractorInterface;
import com.example.mgs1982.remindset.login.ui.LoginActivity;
import com.example.mgs1982.remindset.login.ui.LoginActivityInterface;
import com.example.mgs1982.remindset.registration.model.UserModel;



public class LoginPresenter extends AppCompatActivity implements LoginPresenterInterface {

    LoginActivityInterface viewInterface;
    LoginInteractorInterface interactor;

    public LoginPresenter(Context context, LoginActivityInterface viewInterface)
    {
        this.viewInterface=viewInterface;
        interactor=new LoginInteactor(context,this);
    }

    @Override
    public void getLoginResponseFromFireBase(String email, String password) {
        interactor.getLoginResponseFromFirebase(email, password);
    }

    @Override
    public void loginSuccess(UserModel model) {
    viewInterface.loginSuccess(model);
    }

    @Override
    public void loginFailure(String message) {
    viewInterface.loginFailure(message);
    }

    @Override
    public void showProgressDailog(String message) {
viewInterface.showPogressDailog(message);
    }

    @Override
    public void hideProgressDialog() {
viewInterface.hideProgressDailog();
    }
}
