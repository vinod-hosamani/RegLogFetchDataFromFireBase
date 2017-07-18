package com.example.mgs1982.remindset.registration.presenter;

import com.example.mgs1982.remindset.registration.model.UserModel;



public interface RegistrationPresenterInterface
{
    void getRegister(UserModel userModel);
    void registrationSuccess(String message);
    void registrationFailure(String message);
    void showProgressDialog(String message);
    void hideProgressDialog();

    void userFireBaseSuccess(String message);
    void userFireBaseFailure(String message);
    void userFireBase(UserModel userModel);

}
