package com.ppl.ppl;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class ScrollActivity extends AppCompatActivity {

    TextView tv;
    StringBuilder sb;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            sb.append("\n\t 我们的家乡在希望的田野上，垂涎在新建的住房上飘荡，小河在美丽的村庄旁流淌。");
            tv.setText(sb.toString());
            // TODO Auto-generated method stub
            //super.handleMessage(msg);
            //显示进度条
            //progress_bar.setProgress(msg.arg1);
            //重新把进程加入到进程队列中
            //update_progressn_bar.post(update_thread);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        sb = new StringBuilder();
        tv = (TextView) findViewById(R.id.textView3);

        SetTextView();
    }


    private void SetTextView() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg = handler.obtainMessage();
                handler.sendMessage(msg);
            }
        }, 1000, 2000);
    }

}
