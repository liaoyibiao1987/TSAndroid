package com.ppl.ppl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;

public class LayeroutActivity extends AppCompatActivity {
    String[] chars = new String[]{
            "A", "B", "C", "D", "E", "F", "G", "H"
    };
    GridLayout gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layerout);
        gd = (GridLayout) findViewById(R.id.gridlayer);

        for (int i = 0; i < chars.length; i++) {
            Button bt = new Button(this);
            bt.setText(chars[i]);
            bt.setTextSize(40);

            GridLayout.Spec rowspec = GridLayout.spec(i / 4 + 5);
            GridLayout.Spec colspec = GridLayout.spec(i % 4);

            GridLayout.LayoutParams parms = new GridLayout.LayoutParams(rowspec,colspec);
            parms.setGravity(Gravity.FILL);
            gd.addView(bt,parms);
        }
    }
}
