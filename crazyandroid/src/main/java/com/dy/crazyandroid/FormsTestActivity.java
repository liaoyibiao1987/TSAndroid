package com.dy.crazyandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class FormsTestActivity extends AppCompatActivity {

    private int mThemeId = -1; // 皮肤主题ID，默认-1 不处理
    private ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            if (savedInstanceState.getInt("theme", -1) != -1) {// 读取皮肤主题ID，-1 不处理
                mThemeId = savedInstanceState.getInt("theme");
                this.setTheme(mThemeId);  //设置主题皮肤
            }
        }
        setContentView(R.layout.activity_forms_test);

        toggleButton = (ToggleButton) findViewById(R.id.tb_swtichTheme);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton.isChecked() == true) {
                    android.widget.Toast.makeText(getApplicationContext(), "isChecked = true", Toast.LENGTH_SHORT).show();
                    onTheme(R.style.AppTheme);
                } else {
                    android.widget.Toast.makeText(getApplicationContext(), "isChecked = false", Toast.LENGTH_SHORT).show();
                    onTheme(R.style.AppThemeNigth);
                }
            }
        });

    }

    private void onTheme(int iThemeId) {
        mThemeId = iThemeId;
        this.recreate();
    }

    // 保存主题ID，onCreate 时读取主题
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("theme", mThemeId);
    }
}
