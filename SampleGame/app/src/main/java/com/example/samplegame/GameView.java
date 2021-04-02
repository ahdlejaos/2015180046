package com.example.samplegame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View {
    private static final String TAG = GameView.class.getSimpleName();
    public static final int ballCount = 10;
//이전에작업 ctrl shift backspace

    //   변수형 final은 생성자에서 모두값이결정되야함
//    private  Ball b1,b2;
    Player player;
  //  ArrayList<Ball> balls = new ArrayList<>();
    ArrayList<GameObject> objects = new ArrayList<>();
    //모든 오브젝트를 한배열에서관리
//    자바에서는 모든객체는  object를 상속받음
    //    자바에서의배열 = ArrayList<> , 공 배열
    private long lastFrame;
    public static float frameTime;
    public static GameView view;

    //객체를 몰라도 접근할수있음 = static
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        GameView.view = this;

// XML 에서지정한것들을 로드할때 getResources 사용
// activity나 view가 가지고있는 getResources 쓰면댐
        initResources();
//        ball객체생성
        startUpdating();

    }

    private void startUpdating() {
        doGameFrame();
//        
//        1이벤트처리 -> 2계산 -> 3그림

    }

    private void doGameFrame() {


        for (GameObject o : objects)
            o.update();
//         공통분모를 만들어준다


        // b1.update();
        //  b2.update();
//
/*        b1.x += b1.dx * frameTime;
        b1.y += b1.dy * frameTime;
//        시간에 비례해서 움직임
        b2.x += b2.dx * frameTime;
        b2.y += b2.dy * frameTime;*/

//        update();        
//        draw();
        invalidate();
//      invalidate() =   ondraw가 불리도록하는함수
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long time) {
                if (lastFrame == 0) {
                    lastFrame = time;
                }
                frameTime = (float) (time - lastFrame) / 1_000_000_000;
                doGameFrame();
                lastFrame = time;
            }
//            초단위로 시간이 얼마나지났는지 알수있음
        });
//        postDelayed(new Runnable(){
//            @Override
//            public void run() {
//                doGameFrame();
//            }
//        }, 15);
//특정코드를 준비해놓고 이코드를 얼마후에 실행시켜주는함수

    }

    private void initResources() {
//        생성하는곳
/*        Resources res = getResources();
        bitmap = BitmapFactory.decodeResource(res,R.mipmap.soccer_ball_240);*/
//                resource ID를가지고  BITMAP 생성해줌

//ondraw가 불리도록하는함수

        player = new Player(100, 100, 0, 0);
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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (GameObject o : objects) {
            o.draw(canvas);
        }

//        player.draw(canvas);
  /*      b1.draw(canvas);
        b2.draw(canvas);*/
//         얘가알아서그리게하자


//        부모가 가지고있는함수 검색하면 오버라이드가능
//        super.onDraw(canvas);  부모부르는거


        //  Log.d(TAG,"Drawing at:" + b1 + "," + b2 + "frametime" +frameTime);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
            player.moveTo(event.getX(), event.getY());
            return true;
            // 처리를했을땐 RETURN TRUE
        }
        return false;
    }
}
