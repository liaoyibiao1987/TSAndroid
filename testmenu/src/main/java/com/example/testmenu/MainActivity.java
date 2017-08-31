package com.example.testmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.SubMenu;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements KeyEvent.Callback {

    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        SubMenu fontmenu = menu.addSubMenu("字体大小");
        fontmenu.setHeaderTitle("选择字体大小");
        fontmenu.add(0,0,0,"11");
        fontmenu.add(0,1,1,"222");
        fontmenu.add(0,2,2,"3333");

        return super.onCreateOptionsMenu(menu);
    }
}
