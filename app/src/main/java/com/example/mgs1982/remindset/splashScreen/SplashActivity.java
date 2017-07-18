package com.example.mgs1982.remindset.splashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.mgs1982.remindset.R;
import com.example.mgs1982.remindset.constant.Constant;
import com.example.mgs1982.remindset.registration.ui.RegistrationActivity;
import com.example.mgs1982.remindset.session.SessionManagement;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class SplashActivity extends AppCompatActivity
{
  SessionManagement sessionManagement;

   private static int SPLASH_TIME_OUT = 2000;
    FirebaseAuth authentication;
    DatabaseReference mfirebasedatabaseref;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__splashscreen);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mfirebasedatabaseref = FirebaseDatabase.getInstance().getReferenceFromUrl(Constant.FirebaseURL);
        mfirebasedatabaseref.keepSynced(true);
        authentication = FirebaseAuth.getInstance().getInstance();

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent i = new Intent(SplashActivity.this, RegistrationActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
