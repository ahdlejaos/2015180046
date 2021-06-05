package kr.ac.kpu.game.s2015180046.dragonflight.framework;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.HashMap;

import kr.ac.kpu.game.s2015180046.dragonflight.ui.view.GameView;

public class GameBitmap {
    private static HashMap<Integer, Bitmap> bitmaps = new HashMap<Integer, Bitmap>();
//공유해서쓰는거

    public static Bitmap load(int resId) {
        Bitmap bitmap = bitmaps.get(resId);
        if (bitmap == null) {
            Resources res = GameView.view.getResources();
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inScaled = false;
            bitmap = BitmapFactory.decodeResource(res, resId, opts);
            bitmaps.put(resId, bitmap);
        }
        return bitmap;
    }

    protected final Bitmap bitmap;
    protected RectF dstRect = new RectF();
    public GameBitmap(int resId){
        bitmap = load(resId);
    }

    public void draw(Canvas canvas,float x ,float y){
        int hW = getWidth()/2;
        int hH = getHeight()/2;
//        Rect srcRect = new Rect(left)
        float dl = x -hW* GameView.MULTIPLIER; //destinaiton1
        float dt = y -hH*GameView.MULTIPLIER;
        float dr = x +hW*GameView.MULTIPLIER;
        float db = y +hH*GameView.MULTIPLIER;
        dstRect.set(dl,dt,dr,db);
//        좌표는항상float 그래서 RectF  , ctrl+p 파라미터보임
        canvas.drawBitmap(bitmap, null, dstRect, null);
//        canvas.drawBitmap(bitmap,left,top,null);
    }

    public void drawSize(Canvas canvas, float x, float y,int size) {
        int hw = getWidth() / 2;
        int hh = getHeight() / 2;

        float dl = x - hw * size;
        float dt = y - hh * size;
        float dr = x + hw * size;
        float db = y + hh * size;
        dstRect.set(dl, dt, dr, db);
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public int getHeight() {
        return bitmap.getHeight();
    }

    public int getWidth() {
        return bitmap.getWidth();
    }

    public void getBoundingRect(float x, float y, RectF rect) {
        int hw = getWidth() / 2;
        int hh = getHeight() / 2;
        //Rect srcRect = new Rect(left, )
        float dl = x - hw * GameView.MULTIPLIER;
        float dt = y - hh * GameView.MULTIPLIER;
        float dr = x + hw * GameView.MULTIPLIER;
        float db = y + hh * GameView.MULTIPLIER;
        rect.set(dl, dt, dr, db);
    }


}


