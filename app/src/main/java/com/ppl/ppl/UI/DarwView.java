package com.ppl.ppl.UI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by pingpingliao on 2016/12/14.
 */

public class DarwView extends View {
    public float currentx = 40;
    public float currenty = 50;

    Paint p = new Paint();

    public DarwView(Context context) {
        super(context);
    }

    public DarwView(Context context, AttributeSet set) {
        super(context, set);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p.setColor(Color.RED);
        canvas.drawCircle(currentx, currenty, 15, p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currentx = event.getX();
        currenty = event.getY();
        invalidate();
        return true;
        //return super.onTouchEvent(event);
    }
}
