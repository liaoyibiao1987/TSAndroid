package com.ppl.ppl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TwoActive extends AppCompatActivity {
    private TApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_active);
        app = (TApp) getApplication();
    }
}
