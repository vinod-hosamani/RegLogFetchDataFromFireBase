package com.example.mgs1982.remindset.fetchdata.presenter;

import com.example.mgs1982.remindset.registration.model.UserModel;

import java.util.List;

/**
 * Created by MGS1982 on 7/18/2017.
 */

public interface DisplayDataFromFBPresenterInterface
{

    void getFBData();

    void getDataSuccess(List<UserModel> userModelList);

    void getDataFailure(String message);
}
