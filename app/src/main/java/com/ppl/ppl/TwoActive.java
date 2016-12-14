package com.ppl.ppl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TwoActive extends AppCompatActivity {
    private TApp app;
    private ListView contextMenulist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_active);
        app = (TApp) getApplication();
        contextMenulist = (ListView) findViewById(R.id.listContacter);
        ArrayAdapter<String> stringapd = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, GetData());
        contextMenulist.setAdapter(stringapd);
    }

    private List<String> GetData() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add("name: " + i);
        }
        return list;
    }
}
