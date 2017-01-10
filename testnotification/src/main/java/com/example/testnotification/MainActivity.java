package com.example.testnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {
    Button btnnotify;
    Button btncustomernotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnnotify = (Button) findViewById(R.id.btnPopNotification);

        btnnotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDefaultNotification();
            }
        });

        btncustomernotify = (Button) findViewById(R.id.btnPopNotification2);
        btncustomernotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomizeNotification();
            }
        });


        Log.i("activity", "测试log");
    }

    private void showDefaultNotification() {
        CharSequence title = "i am new";

        // 定义Notication的各种属性
        //将该通知显示为默认View
        PendingIntent contentIntent = PendingIntent.getActivity
                (MainActivity.this, 0, new Intent(MainActivity.this, MainActivity.class), 0);

        // 创建一个通知
        Notification mNotification = new Notification.Builder(this)
                .setAutoCancel(true)
                .setContentTitle("这是标题")
                .setContentText("这是提示详细信息")
                .setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.icon)
                .setWhen(System.currentTimeMillis())
                .build();

        //mNotification.tickerText = "NotificationTest";
        //mNotification.when = System.currentTimeMillis(); // 立即发生此通知
        // 添加声音效果
        //mNotification.defaults |= Notification.DEFAULT_SOUND;

        // 添加震动,后来得知需要添加震动权限 : Virbate Permission
        //mNotification.defaults |= Notification.DEFAULT_VIBRATE ;

        //添加状态标志

        //FLAG_AUTO_CANCEL          该通知能被状态栏的清除按钮给清除掉
        //FLAG_NO_CLEAR                 该通知能被状态栏的清除按钮给清除掉
        //FLAG_ONGOING_EVENT      通知放置在正在运行
        //FLAG_INSISTENT                通知的音乐效果一直播放
        //mNotification.flags = Notification.FLAG_INSISTENT ;

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //注册此通知
        // 如果该NOTIFICATION_ID的通知已存在，会显示最新通知的相关信息 ，比如tickerText 等
        mNotificationManager.notify(2, mNotification);
    }

    //自定义显示的通知 ，创建RemoteView对象
    private void showCustomizeNotification() {

       /* CharSequence title = "i am new";
        int icon = R.drawable.icon;
        long when = System.currentTimeMillis();*/

        //这儿点击后简单启动Settings模块
        PendingIntent contentIntent = PendingIntent.getActivity
                (MainActivity.this, 0, new Intent("android.settings.SETTINGS"), 0);
        Notification noti = new Notification.Builder(this)
                // API 11添加的方法
                .setContentIntent(contentIntent).setSmallIcon(R.drawable.icon)
                // 设置状态栏的小标题
                .setLargeIcon(
                        BitmapFactory.decodeResource(getResources(),
                                R.drawable.icon))// 设置下拉列表里的图标
                .setWhen(System.currentTimeMillis()).setTicker("凤姐来啦")// 设置状态栏的显示的信息
                .setAutoCancel(true)// 设置可以清除
                .setContentTitle("通知通知") // 设置下拉列表里的标题
                .setContentText("凤姐即将光临天拓游戏,各部门做好防雷准备").build();
        ;

        NotificationCompat.Builder builer = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icon);
        // 1、创建一个自定义的消息布局 view.xml
        // 2、在程序代码中使用RemoteViews的方法来定义image和text。然后把RemoteViews对象传到contentView字段
        RemoteViews remoteView = new RemoteViews(this.getPackageName(), R.layout.notification);
        remoteView.setImageViewResource(R.id.image, R.drawable.icon);
        remoteView.setTextViewText(R.id.text, "通知类型为：自定义View");
        remoteView.setOnClickPendingIntent(R.id.text, contentIntent);
        builer.setContent(remoteView);
        builer.setContentIntent(contentIntent);
        // 3、为Notification的contentIntent字段定义一个Intent(注意，使用自定义View不需要setLatestEventInfo()方法)

        NotificationManager mnotiManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //mnotiManager.notify(0, noti);
        mnotiManager.notify(22, builer.build());

    }


    private void removeNotification() {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // 取消的只是当前Context的Notification
        mNotificationManager.cancel(2);
    }

}
