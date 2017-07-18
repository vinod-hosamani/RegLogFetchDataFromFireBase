package com.example.mgs1982.remindset.fetchdata.interactor;

import android.content.Context;

import com.example.mgs1982.remindset.fetchdata.presenter.DislpayDatafromFBInteractorInterface;
import com.example.mgs1982.remindset.fetchdata.presenter.DisplayDataFromFBPresenterInterface;
import com.example.mgs1982.remindset.fetchdata.ui.DisplayDataFromFBView;
import com.example.mgs1982.remindset.registration.model.UserModel;

import java.util.List;

/**
 * Created by MGS1982 on 7/18/2017.
 */

public class DisplayDatafromFBPresenter implements DisplayDataFromFBPresenterInterface
{
    Context context;
    DisplayDataFromFBView viewInterface;
    DislpayDatafromFBInteractorInterface interactorInterface;

    public DisplayDatafromFBPresenter(Context context, DisplayDataFromFBView viewInterface)
    {
        this.context = context;
        this.viewInterface = viewInterface;

        interactorInterface = new DisplayDatafromFBInteractor(context, this);
    }
    @Override
    public void getFBData()
    {
        interactorInterface.getFBData();
    }
    @Override
    public void getDataSuccess(List<UserModel> userModelList)
    {
        viewInterface.displaySuccess(userModelList);
    }
    @Override
    public void getDataFailure(String message)
    {
        viewInterface.displayFailure(message);
    }
}