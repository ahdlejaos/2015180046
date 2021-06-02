package kr.ac.kpu.game.s2015180046.dragonflight.game;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.nfc.Tag;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

import kr.ac.kpu.game.s2015180046.dragonflight.R;
import kr.ac.kpu.game.s2015180046.dragonflight.framework.AnimationGameBitmap;
import kr.ac.kpu.game.s2015180046.dragonflight.framework.BoxCollidable;
import kr.ac.kpu.game.s2015180046.dragonflight.framework.GameBitmap;
import kr.ac.kpu.game.s2015180046.dragonflight.framework.GameObject;
import kr.ac.kpu.game.s2015180046.dragonflight.framework.Recyclable;
import kr.ac.kpu.game.s2015180046.dragonflight.ui.view.GameView;

public class Enemy implements GameObject, BoxCollidable, Recyclable {

    private static final float FRAMES_PER_SECONDS = 8.0f;
    private static final int[] RESOURCE_IDS = {
            R.mipmap.enemy_01, R.mipmap.enemy_02, R.mipmap.enemy_03, R.mipmap.enemy_04, R.mipmap.enemy_05,
            R.mipmap.enemy_06, R.mipmap.enemy_07, R.mipmap.enemy_08, R.mipmap.enemy_09, R.mipmap.enemy_10,
            R.mipmap.enemy_11, R.mipmap.enemy_12, R.mipmap.enemy_13, R.mipmap.enemy_14, R.mipmap.enemy_15,
            R.mipmap.enemy_16, R.mipmap.enemy_17, R.mipmap.enemy_18, R.mipmap.enemy_19, R.mipmap.enemy_20,


    };
    private static final String TAG = Enemy.class.getSimpleName();

    private float x;
    private GameBitmap bitmap;
    private int level;

    private float y;
    private int speed;


    private Enemy(){
        Log.d(TAG,"enemy construct");
  //생성자에서는 아무일도 안하도록
//        정사각형이면 0
//        halfWidth = bitmap.getWidth()/2;
//        halfHeight = bitmap.getHeight() /2;
    }
    public static Enemy get(int level,float x, float y,int speed){ //생성을해주는함수
        MainGame game = MainGame.get();
        Enemy enemy = (Enemy) game.get(Enemy.class);

        if(enemy==null) {
            enemy = new Enemy();
        }

        enemy.init(level,x,y,speed);
        return enemy;

        //생성자에서 만드나 재활용할때 만드나 똑같으니까 생성자는 아무것도안하고 재활용함수에서 init함
    }

    private void init(int level,float x, float y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.level = level;

        int resId = RESOURCE_IDS[level -1];
        this.bitmap = new AnimationGameBitmap(resId,FRAMES_PER_SECONDS,0);
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        y += speed * game.frameTime;
//                1초당 움직여야하는 픽셀수

        if(y> GameView.view.getHeight()){
            game.remove(this);
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

    }

    public int hp =8;

}



