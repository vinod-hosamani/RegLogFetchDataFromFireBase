package com.example.mgs1982.remindset.login.interactor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.example.mgs1982.remindset.constant.Constant;
import com.example.mgs1982.remindset.login.presenter.LoginPresenter;
import com.example.mgs1982.remindset.login.presenter.LoginPresenterInterface;
import com.example.mgs1982.remindset.registration.model.UserModel;
import com.example.mgs1982.remindset.util.Connectivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginInteactor extends AppCompatActivity implements LoginInteractorInterface {

    Context context;
    LoginPresenterInterface presenterInterface;
    UserModel model;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String userId;

    public LoginInteactor(Context context, LoginPresenterInterface PresenterInterface)
    {
        this.context=context;
        this.presenterInterface=PresenterInterface;
        firebaseAuth=FirebaseAuth.getInstance();
    }

    @Override
    public void getLoginResponseFromFirebase(String email, String password)
    {
        presenterInterface.showProgressDailog("loading plese wait");
        if(Connectivity.isNetworkConnected(context))
        {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                userId=task.getResult().getUser().getUid();
                                getUserData(userId);
                            }
                            else
                            {
                                presenterInterface.loginFailure("invalid email ro password");
                                presenterInterface.hideProgressDialog();
                            }

                        }
                    });
        }

    }


    public void getUserData(String userId)
    {
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference(Constant.key_firebase_user);
        databaseReference.child(userId)
                .addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        model=dataSnapshot.getValue(UserModel.class);
                        presenterInterface.loginSuccess(model);
                        presenterInterface.hideProgressDialog();

                    }

                    @Override
                    public void onCancelled(DatabaseError error)
                    {

                        presenterInterface.loginFailure(error.getMessage());
                        presenterInterface.hideProgressDialog();
                    }
                });
    }

}
