package kr.ac.kpu.game.s2015180046.dragonflight.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2015180046.dragonflight.framework.GameBitmap;
import kr.ac.kpu.game.s2015180046.dragonflight.framework.GameObject;
import kr.ac.kpu.game.s2015180046.dragonflight.ui.view.GameView;

public class VerticalScrollBackground implements GameObject {

    private final Bitmap bitmap;
    private final float speed;
    private float scroll;


    private Rect srcRect = new Rect();
    private RectF dstRect = new RectF();
    public VerticalScrollBackground(int resID,int speed){
        this.speed = speed * GameView.MULTIPLIER; //speed는 스크롤되는속도
        bitmap = GameBitmap.load(resID);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        srcRect.set(0,0,w,h);
//        비트맵 크기
        float l = 0;//x - w / 2 * GameView.MULTIPLIER;
        float t = 0;//y - h / 2 * GameView.MULTIPLIER;
        float r = GameView.view.getWidth();//x + w / 2 * GameView.MULTIPLIER;
        float b = r * h / w; //y + h / 2 * GameView.MULTIPLIER; w:r = h:b
        dstRect.set(l,t,r,b);
    }
    @Override
    public void update() {
        MainGame game = MainGame.get();
        float amount = speed * game.frameTime;
        scroll += amount;

    }

    @Override
    public void draw(Canvas canvas){
        int viewWidth = GameView.view.getWidth();
        int viewHeight = GameView.view.getHeight();
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();

        int destinationHeight = viewWidth * bitmapHeight/bitmapWidth;//이미지가 그려질 높이



        int current = (int) (scroll%destinationHeight); //어디서부터 그림을 그려야하는가
        if(current>0) current -= destinationHeight;

        while (current<viewHeight){
            dstRect.set(0,current,viewWidth,current+destinationHeight);
            canvas.drawBitmap(bitmap,srcRect,dstRect,null);
            current += destinationHeight;
        }


    }
}
