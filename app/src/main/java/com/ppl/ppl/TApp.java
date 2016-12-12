package com.ppl.ppl;

import android.app.Application;

/**
 * Created by pingpingliao on 2016/12/12.
 */

public class TApp extends Application {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
