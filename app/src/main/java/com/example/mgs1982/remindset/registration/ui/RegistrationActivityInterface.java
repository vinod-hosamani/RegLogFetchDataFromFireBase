package com.example.mgs1982.remindset.registration.ui;

import android.view.View;


public interface RegistrationActivityInterface extends View.OnClickListener {
    void registrationSuccess(String message);
    void registrationFailure(String message);
    void showProgressDialog(String message);
    void hideProgressDialog();

    void userFireBaseSuccuss(String message);
    void userFireBaseFailure(String message);
}
