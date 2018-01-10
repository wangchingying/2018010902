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
    int NOTIFICATION_ID=1234;
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
    //小老鼠叫做annotation, TargetApi是告訴系統現在要用Oreo的版本跑程式, 搭配if
    @TargetApi(Build.VERSION_CODES.O)
    //@這個指令是要系統跑Oreo時, 忽略Oreo以下版本會出現的錯誤
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
        if (Build.VERSION.SDK_INT >= 26)
        {
            builder.setSmallIcon(R.drawable.ic_launcher_background);
        }else{
            builder.setSmallIcon(R.mipmap.ic_launcher);
        }
        //設定點完這個Notificationon就消失
        builder.setAutoCancel(true);
        builder.setContentIntent(pi);
        Notification notify = builder.build();
        nm.notify(NOTIFICATION_ID, notify);

    }
    //取消notification
    public void click2(View v)
    {
        nm.cancel(NOTIFICATION_ID);
    }
}
