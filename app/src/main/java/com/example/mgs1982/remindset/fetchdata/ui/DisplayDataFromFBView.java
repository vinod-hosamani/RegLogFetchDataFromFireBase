package com.example.mgs1982.remindset.fetchdata.ui;

import com.example.mgs1982.remindset.registration.model.UserModel;

import java.util.List;

/**
 * Created by MGS1982 on 7/18/2017.
 */

public interface DisplayDataFromFBView
{
    void displaySuccess(List<UserModel> userModelList);
    void displayFailure(String message);
}
