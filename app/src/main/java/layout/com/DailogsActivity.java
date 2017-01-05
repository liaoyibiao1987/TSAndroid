package layout.com;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.ColorRes;
import android.support.v7.app.AlertDialog;
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
    Button btnManulyToast;
    Button btnMnulaAlert;

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

        btnManulyToast = (Button) findViewById(R.id.btnManlyToast);
        btnManulyToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showManualToast();
            }
        });

        btnMnulaAlert = (Button) findViewById(R.id.btnmanulaalert);
        btnMnulaAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showManualDailog();
            }
        });


        bg = (RelativeLayout) findViewById(R.id.activity_dailogs);
        final Random random = new Random(0xFFFFFFFF);
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 7) {
                    Toast toast = Toast.makeText(DailogsActivity.this, "测试", Toast.LENGTH_SHORT);
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
        }, 0, 1000);
    }

    private void showManualDailog() {
        AlertDialog.Builder builder = new  AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.layoutmaintenancetoast, null))
                // Add action buttons
                .setPositiveButton(R.string.action_sign_in, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton(R.string.action_sign_in_short, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //DailogsActivity.this.getDialog().cancel();
                    }
                });
        builder.create();
        builder.show();
    }

    private void showManualToast() {
        Toast toast = new Toast(this);
        View manualToast = LayoutInflater.from(DailogsActivity.this).inflate(R.layout.layoutmaintenancetoast, null);
        //toast.setGravity(Gravity.LEFT | Gravity.TOP, 0, 0);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(manualToast);
        toast.show();
    }

    private void showProcessDaillog() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("测试信息");
        dialog.setMessage("正在下载中请稍后...");
        dialog.setProgress(100);
        dialog.show();
        Toast toast = Toast.makeText(this, "测试信息", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.LEFT | Gravity.TOP, 0, 0);
        toast.show();

        View view = LayoutInflater.from(DailogsActivity.this).inflate(R.layout.activity_clocker, null);
    }
}
