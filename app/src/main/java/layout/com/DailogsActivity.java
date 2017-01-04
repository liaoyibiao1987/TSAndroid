package layout.com;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ppl.ppl.R;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class DailogsActivity extends AppCompatActivity {
    Button btnProgressDialog;
    RelativeLayout bg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailogs);
        btnProgressDialog = (Button) findViewById(R.id.btnProgress);
        btnProgressDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProcessDaillog();
            }
        });
        bg = (RelativeLayout) findViewById(R.id.activity_dailogs);
        final Random random = new Random(0xFFFFFFFF);
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 7) {
                    Toast toast = Toast.makeText(DailogsActivity.this, "测试信息", Toast.LENGTH_SHORT);
                    int colors = random.nextInt();
                    btnProgressDialog.setBackgroundColor(colors);
                    bg.setBackgroundColor(colors);
                    super.handleMessage(msg);
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 7;
                handler.sendMessage(msg);
            }
        }, 0, 100);
    }

    private void showProcessDaillog() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgress(100);
        dialog.show();
        Toast toast = Toast.makeText(this, "测试信息", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.LEFT | Gravity.TOP, 0, 0);
        toast.show();

        View view = LayoutInflater.from(DailogsActivity.this).inflate(R.layout.activity_clocker, null);
    }
}
