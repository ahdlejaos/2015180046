package com.example.samplegame.framework;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.example.samplegame.ui.view.GameView;

public class AnimationGameBitmap extends GameBitmap {


    private final Bitmap bitmap;
    private final int imageWidth;
    private final int imageHeight;
    private final int frameWidth;
    private final long createdOn;
    private int frameIndex;
    private final float framesPerSecond;
    private final int frameCount;
    private int PIXEL_MULTIPLIER = 4;

    public AnimationGameBitmap(int resId, float framesPerSecond, int frameCount){
        //        어떤이미지,애니메이션속도(초당몇프레임인지),몇장인지

        Resources res = GameView.view.getResources();
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inScaled = false;
        bitmap = BitmapFactory.decodeResource(res, resId,opts);
        imageWidth = bitmap.getWidth();
        imageHeight = bitmap.getHeight();
        if(frameCount==0){
            frameCount = imageWidth / imageHeight;
        }

        frameWidth = imageWidth / frameCount;
        this.framesPerSecond = framesPerSecond;
        this.frameCount = frameCount;
        createdOn = System.currentTimeMillis();
        frameIndex = 0;
    }

/*    public void update(){
        int elapsed = (int) (System.currentTimeMillis() - createdOn);
//        이수치로몇프레임그려야하는지알수있음
        frameIndex = Math.round(elapsed *0.001f* framesPerSecond) % frameCount;
//        frameIndex = (frameIndex + 1) % 24;

    }*/

    public void draw(Canvas canvas, float x, float y){
        int elapsed = (int) (System.currentTimeMillis() - createdOn);
//        이수치로몇프레임그려야하는지알수있음
        frameIndex = Math.round(elapsed *0.001f* framesPerSecond) % frameCount;

        int fw = frameWidth;
        int height = imageHeight;
        int halfWidth = fw /2 * 4;
        int halfHeight = height /2 * 4;
        Rect src = new Rect(fw*frameIndex,0,fw*frameIndex+fw,height);
        RectF dst = new RectF(x - halfWidth,y-halfHeight,x+halfWidth,y+halfHeight);
        canvas.drawBitmap(bitmap,src,dst,null);

    }

    public int getWidth(){
        return frameWidth * PIXEL_MULTIPLIER;
    }

    public int getHeight(){
        return imageHeight * PIXEL_MULTIPLIER;
    }

}
