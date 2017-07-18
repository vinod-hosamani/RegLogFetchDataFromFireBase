package com.example.mgs1982.remindset.registration.interactor;

import com.example.mgs1982.remindset.registration.model.UserModel;


public interface RegistrationInteractorInterface
{
    void getRegisterResponse(UserModel model);

    void getUserFireBase(UserModel userModel);
}
