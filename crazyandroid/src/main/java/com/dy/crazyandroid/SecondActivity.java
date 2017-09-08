package com.dy.crazyandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class SecondActivity extends AppCompatActivity {
    protected ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        toggleButton = (ToggleButton) findViewById(R.id.tgbtn_view2_test1);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_view2_test1);
        Chronometer chronometer =(Chronometer)findViewById(R.id.chronometer2);
        chronometer.setFormat("计时时间：(%s)");

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //linearLayout.setOrientation(1);
                linearLayout.setOrientation((isChecked == true) ? LinearLayout.HORIZONTAL : LinearLayout.VERTICAL);
            }
        });

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {

            }
        });
        chronometer.start();
    }
}
