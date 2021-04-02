    package com.example.samplegame;

    import android.content.Context;
    import android.content.res.Resources;
    import android.graphics.Bitmap;
    import android.graphics.BitmapFactory;
    import android.graphics.Canvas;
    import android.util.AttributeSet;
    import android.util.Log;
    import android.view.Choreographer;
    import android.view.View;

    import androidx.annotation.Nullable;

public class GameView extends View {
    private static final String TAG = GameView.class.getSimpleName();


//   변수형 final은 생성자에서 모두값이결정되야함
    private  Ball b1,b2;
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

        b1.update();
        b2.update();
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
            if(lastFrame == 0){
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
/*        Resources res = getResources();
        bitmap = BitmapFactory.decodeResource(res,R.mipmap.soccer_ball_240);*/
//                resource ID를가지고  BITMAP 생성해줌

//ondraw가 불리도록하는함수

//        refacter - extract method

         b1 = new Ball(100,100,100,200);
       b2 = new Ball(1000,100,-50,150);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        b1.draw(canvas);
        b2.draw(canvas);
//         얘가알아서그리게하자


//        부모가 가지고있는함수 검색하면 오버라이드가능
//        super.onDraw(canvas);  부모부르는거


        Log.d(TAG,"Drawing at:" + b1 + "," + b2 + "frametime" +frameTime);

    }
}
