package com.ppl.ppl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import layout.com.DailogsActivity;

public class NavigationActivity extends AppCompatActivity {
    Button navimageveiw;
    Button btnScrollTest;
    Button btnlistViewTest;
    Button btnDailog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        navimageveiw = (Button) findViewById(R.id.btnImageView);
        navimageveiw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, ImageViewActivity.class);
                startActivity(intent);
            }
        });

        btnScrollTest = (Button) findViewById(R.id.btnScrollTest);
        btnScrollTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, ScrollActivity.class);
                startActivity(intent);
            }
        });

        btnlistViewTest = (Button) findViewById(R.id.listViewTest);
        btnlistViewTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        btnDailog = (Button) findViewById(R.id.btnDailogTest);
        btnDailog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, DailogsActivity.class);
                startActivity(intent);
            }
        });

    }
}
