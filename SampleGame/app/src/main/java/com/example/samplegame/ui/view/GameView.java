package com.example.samplegame.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.samplegame.game.MainGame;

public class GameView extends View {
    private static final String TAG = GameView.class.getSimpleName();

//이전에작업 ctrl shift backspace

    //   변수형 final은 생성자에서 모두값이결정되야함
//    private  Ball b1,b2;
  //  ArrayList<Ball> balls = new ArrayList<>();

    //모든 오브젝트를 한배열에서관리
//    자바에서는 모든객체는  object를 상속받음
    //    자바에서의배열 = ArrayList<> , 공 배열
    private long lastFrame;

    public static GameView view;

    //객체를 몰라도 접근할수있음 = static
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        GameView.view = this;


// XML 에서지정한것들을 로드할때 getResources 사용
// activity나 view가 가지고있는 getResources 쓰면댐
//        initResources();
//        ball객체생성
        startUpdating();

}

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d(TAG,"Onsize" + w + "," + h);
        MainGame game = MainGame.get();
        //        싱글턴함수가지고오는방법
        game.initResources();
     //   super.onSizeChanged(w, h, oldw, oldh);
    }

    private void startUpdating() {
        doGameFrame();
//
//        1이벤트처리 -> 2계산 -> 3그림

    }

    private void doGameFrame() {
        MainGame game = MainGame.get();
        game.update();


//         공통분모를 만들어준다

//        draw();
        invalidate();
//      invalidate() =   ondraw가 불리도록하는함수
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long time) {
                if (lastFrame == 0) {
                    lastFrame = time;
                }
                game.frameTime = (float) (time - lastFrame) / 1_000_000_000;
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
    @Override
    protected void onDraw(Canvas canvas) {
        MainGame game = MainGame.get();
        game.draw(canvas);

//         얘가알아서그리게하자
//        부모가 가지고있는함수 검색하면 오버라이드가능
//        super.onDraw(canvas);  부모부르는거


        //  Log.d(TAG,"Drawing at:" + b1 + "," + b2 + "frametime" +frameTime);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        MainGame game = MainGame.get();
        return game.onTouchEvent(event);

    }
}
