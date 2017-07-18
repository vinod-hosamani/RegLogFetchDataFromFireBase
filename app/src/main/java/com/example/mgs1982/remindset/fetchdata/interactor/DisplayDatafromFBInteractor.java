package com.example.mgs1982.remindset.fetchdata.interactor;

import android.content.Context;

import com.example.mgs1982.remindset.R;
import com.example.mgs1982.remindset.fetchdata.presenter.DisplayDataFromFBPresenterInterface;
import com.example.mgs1982.remindset.constant.Constant;
import com.example.mgs1982.remindset.registration.model.UserModel;
import com.example.mgs1982.remindset.util.Connectivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MGS1982 on 7/18/2017.
 */

public class DisplayDatafromFBInteractor implements DislpayDatafromFBInteractorInterface {

    Context context;
    DisplayDataFromFBPresenterInterface presenterInterface;

    DatabaseReference databaseReference;

    public DisplayDatafromFBInteractor(Context context, DisplayDataFromFBPresenterInterface presenterInterface) {
        this.context = context;
        this.presenterInterface = presenterInterface;

        databaseReference = FirebaseDatabase.getInstance().getReference(Constant.key_firebase_user);
    }

    @Override
    public void getFBData()
    {
        if (Connectivity.isNetworkConnected(context))
        {
            databaseReference.addValueEventListener(new ValueEventListener()
            {
                List<UserModel> userModelList = new ArrayList<UserModel>();
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {
                    for (DataSnapshot dataSnapshot1 :dataSnapshot.getChildren())
                    {
                        UserModel model = dataSnapshot1.getValue(UserModel.class);
                        userModelList.add(model);
                    }

                    presenterInterface.getDataSuccess(userModelList);
                }

                @Override
                public void onCancelled(DatabaseError databaseError)
                {
                    presenterInterface.getDataFailure(context.getString(R.string.dataFetchFail));
                }
            });
        }
    }
}