package com.example.samplegame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View {
    private final Bitmap bitmap;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Resources res = getResources();
// XML 에서지정한것들을 로드할때 getResources 사용
// activity나 view가 가지고있은 getResources 쓰면댐
        bitmap = BitmapFactory.decodeResource(res,R.mipmap.soccer_ball_240);
//                resource ID를가지고  BITMAP 생성해줌




    }

    @Override
    protected void onDraw(Canvas canvas) {
//        부모가 가지고있는함수 검색하면 오버라이드가능
//        super.onDraw(canvas);  부모부르는거
        canvas.drawBitmap(bitmap,0,0,null);
//        화면에bitmap인자받은거보여줌

    }
}
