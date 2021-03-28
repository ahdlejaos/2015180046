package kr.ac.kpu.game.s2015180046.animation;

import android.app.Notification;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {


    private static final String TAG = MyView.class.getSimpleName();
    Paint paint = new Paint();
    Rect rect = new Rect();

    public MyView(Context context, AttributeSet set) {
        super(context , set);
        paint.setColor(0xff0044ff);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       Log.d(TAG,"onTouch"+ event);
       return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int l = 0 + getPaddingLeft();
        int t = 0 + getPaddingTop();
        int w = getWidth() - getPaddingRight() -l;
        int h = getHeight() - getPaddingBottom() - t;
        rect.set(l,t,w,h);
        Log.d(TAG,"drawing"+rect);
        canvas.drawRect(rect,paint);



    }
}


