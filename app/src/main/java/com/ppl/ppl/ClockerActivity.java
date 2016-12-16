package com.ppl.ppl;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.*;
import android.widget.RemoteViews;

public class ClockerActivity extends AppCompatActivity {
    /*NotificationManager noteMng = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    private android.app.Notification note;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clocker);
        /*
        Notification noti = new Notification(R.drawable.drawablenormal, "测试的提示信息", 10000);
        noti.flags = Notification.FLAG_INSISTENT;
// 1、创建一个自定义的消息布局 notification.xml
// 2、在程序代码中使用RemoteViews的方法来定义image和text。然后把RemoteViews对象传到contentView字段
        RemoteViews remoteView = new RemoteViews(this.getPackageName(), R.layout.activity_action_bar_activty);
        remoteView.setImageViewResource(R.id.image, R.drawable.drawables);
        remoteView.setTextViewText(R.id.text, "Hello,this message is in a custom expanded view");
        noti.contentView = remoteView;
// 3、为Notification的contentIntent字段定义一个Intent(注意，使用自定义View不需要setLatestEventInfo()方法)

//这儿点击后简答启动Settings模块
        PendingIntent contentIntent = PendingIntent.getActivity
                (ClockerActivity.this, 0, new Intent("android.settings.SETTINGS"), 0);
        noti.contentIntent = contentIntent;

        */
    }
}
