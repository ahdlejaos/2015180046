package com.example.samplegame.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.samplegame.framework.GameObject;
import com.example.samplegame.ui.view.GameView;

import java.util.ArrayList;
import java.util.Random;

public class MainGame {


    //singleton
    //이객체는 하나만 존재하는, 하나만 만들어지는 패턴

    public static final int ballCount = 10;
    private static MainGame instance;
    Player player;
    public float frameTime;
    ArrayList<GameObject> objects = new ArrayList<>();
    private  boolean initialized;


    public static MainGame get() {
        if (instance == null) {
            instance = new MainGame();
        }
        return instance;
    }

    public void initResources() {
//        생성하는곳
/*        Resources res = getResources();
        bitmap = BitmapFactory.decodeResource(res,R.mipmap.soccer_ball_240);*/
//                resource ID를가지고  BITMAP 생성해줌
//ondraw가 불리도록하는함수

        if(initialized){
            return;
        }

        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();

        player = new Player(w/2, h/2, 0, 0);
//        refacter - extract method
        Random rand = new Random();
        for (int i = 0; i < ballCount; i++) {
            float x = rand.nextInt(1000);
            float y = rand.nextInt(1000);
            float dx = rand.nextFloat() * 1000 - 500;
            float dy = rand.nextFloat() * 1000 - 500;

            Ball b = new Ball(x, y, dx, dy);
            objects.add(b);
        }
        objects.add(player);
/*         b1 = new Ball(100,100,100,200);
       b2 = new Ball(1000,100,-50,150);
       */
        initialized = true;
    }

    public void update() {
    //    if(!initialized) return;
        for (GameObject o : objects)
            o.update();
    }

    public void draw(Canvas canvas) {
    //   if(!initialized) return;
        for (GameObject o : objects) {
            o.draw(canvas);
        }
    }
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
            player.moveTo(event.getX(), event.getY());
            return true;
            // 처리를했을땐 RETURN TRUE
        }
        return false;
    }

    public void add(GameObject gameObject) {
        objects.add(gameObject);
    }

    public void remove(GameObject gameObject){
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                objects.remove(gameObject);
            }
        });
//        현재의 콜스택이 다 리턴된다음에 불러달라는함수
//        루프를 도는중에 해당객체를 삭제하는건안된다
    }
}
