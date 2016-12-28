package com.ppl.ppl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ListActivity extends AppCompatActivity {

    private String[] names = new String[]{"飞鸟", "走鸟", "菜鸟", "老鸟", "蓝鸟", "水鸟"};
    private int[] images = new int[]{R.drawable.niao1, R.drawable.niao2, R.drawable.niao3, R.drawable.niao4, R.drawable.niao5, R.drawable.niao6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        bindingList();
    }

    private void bindingList() {
        List<Map<String, Object>> listniao = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("header", images[i]);
            item.put("name", names[i]);
            listniao.add(item);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this
                , listniao
                , R.layout.activity_list
                , new String[]{"header", "name"}
                , new int[]{R.id.list_image, R.id.list_name});
        ListView listView = (ListView)findViewById(R.id.list_views);
        listView.setAdapter(simpleAdapter);
    }
}
