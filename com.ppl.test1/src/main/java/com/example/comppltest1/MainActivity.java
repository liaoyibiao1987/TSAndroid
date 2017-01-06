package com.example.comppltest1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnstop;
    Button btnstart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_PROGRESS);
        //requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnstart = (Button)findViewById(R.id.btnStartProcess);
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setProgressBarIndeterminateVisibility(true);
                setProgressBarVisibility(true);
                setProgress(4500);
            }
        });
        btnstop = (Button)findViewById(R.id.btnStopProcess);
        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setProgressBarIndeterminateVisibility(false);
                setProgressBarVisibility(false);
            }
        });


    }
}
