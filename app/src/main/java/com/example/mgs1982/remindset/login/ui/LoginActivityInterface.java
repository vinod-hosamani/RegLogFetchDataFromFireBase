package com.example.mgs1982.remindset.login.ui;

import android.view.View;

import com.example.mgs1982.remindset.registration.model.UserModel;



public interface LoginActivityInterface extends View.OnClickListener {

    void loginSuccess(UserModel model);
    void loginFailure(String message);
    void showPogressDailog(String message);
    void hideProgressDailog();
}
