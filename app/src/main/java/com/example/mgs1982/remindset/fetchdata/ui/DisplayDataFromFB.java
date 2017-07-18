package com.example.mgs1982.remindset.fetchdata.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.mgs1982.remindset.R;
import com.example.mgs1982.remindset.fetchdata.presenter.DisplayDataFromFBPresenterInterface;
import com.example.mgs1982.remindset.fetchdata.presenter.DisplayDatafromFBPresenter;
import com.example.mgs1982.remindset.fetchdata.modelAdapter.ItemAdapter;
import com.example.mgs1982.remindset.registration.model.UserModel;

import java.util.List;

public class DisplayDataFromFB extends AppCompatActivity implements DisplayDataFromFBView
{

    RecyclerView usersRecyclerView;
    ItemAdapter adapter;

    DisplayDataFromFBPresenterInterface presenterInterface;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_datafromfb);
        initView();

        presenterInterface.getFBData();
    }

    public void initView()
    {
        usersRecyclerView = (RecyclerView) findViewById(R.id.usersRecyclerView);
        usersRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        adapter = new ItemAdapter(this);

        usersRecyclerView.setAdapter(adapter);
        presenterInterface = new DisplayDatafromFBPresenter(this, this);
    }

    @Override
    public void displaySuccess(List<UserModel> userModelList)
    {
        adapter.setUsersList(userModelList);
    }
    @Override
    public void displayFailure(String message)
    {

    }
}
