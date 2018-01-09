package com.cyw.a2018010902;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    String idLove = "LOVE";
    NotificationChannel channelLove;
    NotificationManager nm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT>=26)
        {
            regChannel();
        }

    }
    @TargetApi(Build.VERSION_CODES.O)
    public void regChannel() {
        channelLove = new NotificationChannel(
                idLove,
                "Channel Love",
                NotificationManager.IMPORTANCE_HIGH);
        channelLove.setDescription("最重要的人");
        channelLove.enableLights(true);
        channelLove.enableVibration(true);
        nm.createNotificationChannel(channelLove);
    }
    @TargetApi(Build.VERSION_CODES.O)
    @SuppressWarnings("deprecation")
    public void click1(View v)
    {

        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= 26)
        {
            builder = new Notification.Builder(MainActivity.this, idLove);
        }
        else
        {
            builder = new Notification.Builder(MainActivity.this);
        }
        //這裡設定點notification就跳到下一頁
        Intent it = new Intent(MainActivity.this, InfoActivity.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);
        //pending Intent 暫緩???
        PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 123, it,
                PendingIntent.FLAG_UPDATE_CURRENT);


        builder.setContentTitle("測試");
        builder.setContentText("這是內容");
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        //設定點完這個Notifycationon就消失
        builder.setAutoCancel(true);
        builder.setContentIntent(pi);
        Notification notify = builder.build();
        nm.notify(1, notify);

    }
}
