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
    private GameBitmap bitmap;
    private float y;
    private int speed;
    public int dmg=2;
    public int power;
    public int size;
    public int level;

    private static final int[] RESOURCE_IDS = {
            R.mipmap.bullet1, R.mipmap.bullet2, R.mipmap.bullet3, R.mipmap.bullet4, R.mipmap.bullet5,
    };


    private Bullet(int level,float x, float y, int speed){
        this.x = x;
        this.y = y;
        this.speed = -speed;
        this.level = level;
//        halfWidth = bitmap.getWidth()/2;
//        halfHeight = bitmap.getHeight() /2;

        if(this.level == 1) {
            this.power = 10;
            this.size = 4;
        }
        else if(this.level == 2) {
            this.power = 30;
            this.size = 4;
        }
        else if(this.level == 3) {
            this.power = 50;
            this.size = 4;
        }
        else if(this.level == 4) {
            this.power = 100;
            this.size = 5;
        }
        else if(this.level == 5) {
            this.power = 150;
            this.size = 5;
        }

        int resId = RESOURCE_IDS[level -1];
        this.bitmap = new GameBitmap(resId);


    }

//    private static ArrayList<Bullet> recycleBin = new ArrayList<>();

    public static Bullet get(int level, float x, float y,int speed){ //생성을해주는함수
        MainGame game = MainGame.get();
        Bullet bullet = (Bullet) game.get(Bullet.class);
        if(bullet==null) {
            return new Bullet(level,x, y, speed);
        }

        bullet.init(level , x,y,speed);
        return bullet;
    }

    private void init(int level , float x, float y, int speed) {
        this.x = x;
        this.y=y;
        this.speed = -speed;
        this.level = level;

        if(this.level == 1) {
            this.power = 10;
            this.size = 4;
        }
        else if(this.level == 2) {
            this.power = 30;
            this.size = 4;
        }
        else if(this.level == 3) {
            this.power = 50;
            this.size = 4;
        }
        else if(this.level == 4) {
            this.power = 100;
            this.size = 5;
        }
        else if(this.level == 5) {
            this.power = 150;
            this.size = 5;
        }

        int resId = RESOURCE_IDS[level - 1];
        this.bitmap = new GameBitmap(resId);

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


        if(this.level == 1 || this.level == 2) {
            bitmap.drawSize(canvas, x, y,2);
        }
        else if(this.level == 3) {
            bitmap.drawSize(canvas, x, y,3);
        }
        else if(this.level == 4) {
            bitmap.drawSize(canvas, x, y,4);
        }
        else if(this.level == 5) {
            bitmap.drawSize(canvas, x, y,4);
        }
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
