package com.example.mgs1982.remindset.registration.interactor;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.mgs1982.remindset.constant.Constant;
import com.example.mgs1982.remindset.registration.model.UserModel;
import com.example.mgs1982.remindset.registration.presenter.RegistrationPresenter;
import com.example.mgs1982.remindset.registration.presenter.RegistrationPresenterInterface;
import com.example.mgs1982.remindset.util.Connectivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationInteractor implements  RegistrationInteractorInterface
{
    Context context;
    RegistrationPresenterInterface registrationPresenterInterface;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String userId;

    UserModel userModel;
    public RegistrationInteractor(Context context, RegistrationPresenterInterface registrationPresenterInterface)
    {
        this.context=context;
        this.registrationPresenterInterface=registrationPresenterInterface;
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference(Constant.key_firebase_user);
    }
    @Override
    public void getRegisterResponse(final UserModel model)
    {
        registrationPresenterInterface.showProgressDialog("registration is in progress");
      /* if(Connectivity.isNetworkConnected(context))
       {*/
           firebaseAuth=FirebaseAuth.getInstance();
           firebaseAuth.createUserWithEmailAndPassword(model.getEmail(),model.getPassword())
            .addOnCompleteListener(new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if(task.isSuccessful())
                    {
                        userId=task.getResult().getUser().getUid();
                        model.setId(userId);
                        databaseReference.child(userId).setValue(model);

                        registrationPresenterInterface.registrationSuccess("registration completed successfully");
                        registrationPresenterInterface.hideProgressDialog();
                    }
                    else
                    {
                        databaseReference.child("vinod").setValue(model);
                        registrationPresenterInterface.registrationFailure("registratoin failed");
                        registrationPresenterInterface.hideProgressDialog();
                    }
                }
            });

       /* else
       {
           registrationPresenterInterface.registrationFailure("no internet connction");
       }*/
    }
    @Override
    public void getUserFireBase(UserModel userModel)
    {
       String  userId=FirebaseAuth.getInstance().getCurrentUser().getUid();

            databaseReference.child(userId).child(userModel.getUserName())
                    .child(String.valueOf(userModel.getUserName())).setValue(userModel);
        registrationPresenterInterface.userFireBaseSuccess("successfully changed");

    }
}
