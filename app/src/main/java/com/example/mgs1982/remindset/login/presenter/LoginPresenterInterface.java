package com.example.mgs1982.remindset.login.presenter;

import com.example.mgs1982.remindset.registration.model.UserModel;



public interface LoginPresenterInterface
{

    void getLoginResponseFromFireBase(String email,String password);
    void loginSuccess(UserModel model);
    void loginFailure(String message);
    void showProgressDailog(String message);
    void hideProgressDialog();
}
