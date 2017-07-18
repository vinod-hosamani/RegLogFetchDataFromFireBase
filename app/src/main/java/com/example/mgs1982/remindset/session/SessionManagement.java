package com.example.mgs1982.remindset.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mgs1982.remindset.constant.Constant;
import com.example.mgs1982.remindset.registration.model.UserModel;

import static android.content.Context.MODE_PRIVATE;



public class SessionManagement
{

    static SharedPreferences userDataPref;
    static SharedPreferences.Editor userEditor;
    static Context context;

    public SessionManagement(Context context)
    {
        this.context=context;
        if(userDataPref==null && userEditor==null)
        {
            userDataPref=this.context.getSharedPreferences(Constant.user_pref_name,MODE_PRIVATE);
            userEditor=userDataPref.edit();
        }
    }
    public void put(UserModel userModel)
    {
        userEditor.putString(Constant.key_id,userModel.getId());
        userEditor.putString(Constant.key_userName,userModel.getUserName());
        userEditor.putString(Constant.key_email,userModel.getEmail());
        userEditor.putString(Constant.key_password,userModel.getPassword());
    }

   /* public UserModel getUserDetail()
    {
        UserModel userModel=new UserModel();
        userModel.setUserName(userDataPref.getString(Constant.key_userName,Constant.empty_value));
        userModel.setEmail(userDataPref.getString(Constant.key_email,Constant.empty_value));
        userModel.setPassword(userDataPref.getString(Constant.key_password,Constant.empty_value));
        userModel.setId(userDataPref.getString(Constant.key_id,Constant.empty_value));

        return userModel;
    }*/


}
