package com.example.mgs1982.remindset.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.mgs1982.remindset.R;
import com.example.mgs1982.remindset.home.HomeScreenActivity;


public class NotifyService extends Service
{
    public class ServiceBinder extends Binder
    {
        NotifyService getService()
        {
            return NotifyService.this;
        }
    }

    private static final int NOTIFICATION = 123;
    public static final String INTENT_NOTIFY = "INTENT_NOTIFY";
    private NotificationManager notificationMgr;

    @Override
    public void onCreate()
    {
        Log.i("NotifyService", "onCreate()");
        notificationMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        if(intent.getBooleanExtra(INTENT_NOTIFY, false))
            showNotification();

        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return mBinder;
    }

    private final IBinder mBinder = new ServiceBinder();

    private void showNotification()
    {
        CharSequence title = "Alarm!!";
        int icon = R.drawable.bell;
        CharSequence text = "Your notification time is upon us.";
        long time = System.currentTimeMillis();

        PendingIntent contentIntent = PendingIntent
                .getActivity(this, 0, new Intent(this, HomeScreenActivity.class), 0);

        Notification notification = new Notification.Builder(this)
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setContentText(text)
                .setContentIntent(contentIntent)
                .setWhen(time).build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationMgr.notify(NOTIFICATION, notification);

        stopSelf();
    }
}
