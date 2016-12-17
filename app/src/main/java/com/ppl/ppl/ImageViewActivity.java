package com.ppl.ppl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;

public class ImageViewActivity extends AppCompatActivity {
    FrameLayout frame = null;
    private boolean  flag = true;
    //由该类两个方法间的循环调用，实现界面不断更新
    class MyHandler extends Handler{
        int i = 0;
        public void handleMessage(Message msg) {
            i++;
            //总共7幅图，依次显示
            show(i % 7);
            //调用sleep函数
            sleep(50);
        }
        public void sleep(long delayMillis) {
            //判断是否继续飞翔
            if(flag) {
                //实质上是调用了一次handleMessage
                sendMessageDelayed(obtainMessage(0), delayMillis);
            }
        }
    }

    //该方法是被调用以更新帧布局的前景图片
    void show(int j) {
        //获取七张图片
        Drawable mybird1 = getResources().getDrawable(R.drawable.niao1);
        Drawable mybird2 = getResources().getDrawable(R.drawable.niao2);
        Drawable mybird3 = getResources().getDrawable(R.drawable.niao3);
        Drawable mybird4 = getResources().getDrawable(R.drawable.niao4);
        Drawable mybird5 = getResources().getDrawable(R.drawable.niao5);
        Drawable mybird6 = getResources().getDrawable(R.drawable.niao6);
        Drawable mybird7 = getResources().getDrawable(R.drawable.niao7);
        //不同的情况，设置不同的前景
        switch(j) {
            case 0:
                frame.setForeground(mybird1);
                break;
            case 1:
                frame.setForeground(mybird2);
                break;
            case 2:
                frame.setForeground(mybird3);
                break;
            case 3:
                frame.setForeground(mybird4);
                break;
            case 4:
                frame.setForeground(mybird5);
                break;
            case 5:
                frame.setForeground(mybird6);
                break;
            case 6:
                frame.setForeground(mybird7);
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        frame = (FrameLayout)findViewById(R.id.frame);
        //创建一个Handler子类对象，要调用其方法
        final MyHandler myHandler = new MyHandler();
        myHandler.sleep(50);
        //为frame设置单击事件，当其被击中时，在飞翔于暂停之间切换
        frame.setOnClickListener(new View.OnClickListener()  {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                flag = !flag;
                myHandler.sleep(50);
            }
        });
    }
}
