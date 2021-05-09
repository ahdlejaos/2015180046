package kr.ac.kpu.game.s2015180046.dragonflight.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.ArrayList;

import kr.ac.kpu.game.s2015180046.dragonflight.R;
import kr.ac.kpu.game.s2015180046.dragonflight.framework.BoxCollidable;
import kr.ac.kpu.game.s2015180046.dragonflight.framework.GameBitmap;
import kr.ac.kpu.game.s2015180046.dragonflight.framework.GameObject;
import kr.ac.kpu.game.s2015180046.dragonflight.framework.Recyclable;

public class Bullet implements GameObject, BoxCollidable, Recyclable {
    private float x;
    private final GameBitmap bitmap;
    private float y;
    private int speed;

    private Bullet(float x, float y, int speed){
        this.x = x;
        this.y = y;
        this.speed = -speed;
        this.bitmap = new GameBitmap(R.mipmap.laser_1);
//        halfWidth = bitmap.getWidth()/2;
//        halfHeight = bitmap.getHeight() /2;
    }

//    private static ArrayList<Bullet> recycleBin = new ArrayList<>();

    public static Bullet get(float x, float y,int speed){ //생성을해주는함수
        MainGame game = MainGame.get();
        Bullet bullet = (Bullet) game.get(Bullet.class);
        if(bullet==null) {
            return new Bullet(x, y, speed);
        }

        bullet.init(x,y,speed);
        return bullet;
    }

    private void init(float x, float y, int speed) {
        this.x = x;
        this.y=y;
        this.speed = -speed;

    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        y += speed * game.frameTime;
//                1초당 움직여야하는 픽셀수
        if(y<0){ //화면상에서 사라질때
            game.remove(this);
          //  recycle();
        }
    }
// ctrl u
    @Override
    public void draw(Canvas canvas) {

      bitmap.draw(canvas,x,y);

    }

    @Override
    public void getBoundingRect(RectF rect) {
        bitmap.getBoundingRect(x,y,rect);

    }


    @Override
    public void recycle() {
        //재활용통에 들어가는 시점에 불리는 함수
    }
}
