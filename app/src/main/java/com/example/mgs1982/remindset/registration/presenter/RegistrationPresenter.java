package com.example.mgs1982.remindset.registration.presenter;

import android.content.Context;

import com.example.mgs1982.remindset.registration.interactor.RegistrationInteractor;
import com.example.mgs1982.remindset.registration.interactor.RegistrationInteractorInterface;
import com.example.mgs1982.remindset.registration.model.UserModel;
import com.example.mgs1982.remindset.registration.ui.RegistrationActivityInterface;

public class RegistrationPresenter implements RegistrationPresenterInterface {

    RegistrationActivityInterface registrationActivityInterface;
    RegistrationInteractorInterface registrationInteractorInterface;

    public RegistrationPresenter(Context context,RegistrationActivityInterface registrationActivityInterface)
    {
        this.registrationActivityInterface=registrationActivityInterface;
        registrationInteractorInterface=new RegistrationInteractor(context,this);
    }
    @Override
    public void getRegister(UserModel userModel)
    {
       registrationInteractorInterface.getRegisterResponse(userModel);
    }

    @Override
    public void registrationSuccess(String message) {
       registrationActivityInterface.registrationSuccess(message);
    }

    @Override
    public void registrationFailure(String message) {
       registrationActivityInterface.registrationFailure(message);
    }

    @Override
    public void showProgressDialog(String message) {
       registrationActivityInterface.showProgressDialog(message);
    }

    @Override
    public void hideProgressDialog() {
       registrationActivityInterface.hideProgressDialog();
    }

    @Override
    public void userFireBaseSuccess(String message) {
        registrationActivityInterface.userFireBaseSuccuss(message);
    }

    @Override
    public void userFireBaseFailure(String message) {
        registrationActivityInterface.userFireBaseFailure(message);
    }

    @Override
    public void userFireBase(UserModel userModel) {
        registrationInteractorInterface.getUserFireBase(userModel);
    }
}
