package com.example.samplegame.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.example.samplegame.framework.AnimationGameBitmap;
import com.example.samplegame.framework.GameObject;
import com.example.samplegame.R;
import com.example.samplegame.ui.view.GameView;

public class Ball implements GameObject {

    private float x, y; // 현재위치
    private float dx, dy;// 속력
    private static AnimationGameBitmap bitmap;
    private static float FRAME_RATE = 8.5f;
//    1초당 8.5장 돌릴생각
    public Ball(float x, float y, float dx, float dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        //        초기화
//        인자로넘어온거랑 멤버변수이름이같으면 this로
        if (bitmap == null) {
            bitmap = new AnimationGameBitmap(R.mipmap.fireball_128_24f,FRAME_RATE,0);

        }

//        System.currentTimeMillis = 현재시각을 밀리세컨드 단위로 반환함

    }

    public void update() {
        MainGame game = MainGame.get();
        this.x += this.dx * game.frameTime;
        this.y += this.dy * game.frameTime;
//        시간에 비례해서 움직임
/*        b2.x += b2.dx * frameTime;
        b2.y += b2.dy * frameTime;*/

        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();
        int frameWidth = bitmap.getWidth();
        int frameHeight = bitmap.getHeight();
        if (x < 0 || x > w - frameWidth) {
            dx *= -1;
        }
        if (y < 0 || y > h - frameHeight) {
            dy = -dy;
        }
        //벽에닿으면 튕기게

     //   bitmap.update();
//        int elapsed = (int) (System.currentTimeMillis() - createdOn);
////        이수치로몇프레임그려야하는지알수있음
//        frameIndex = Math.round(elapsed *0.001f* FRAME_RATE)% 24;
////        frameIndex = (frameIndex + 1) % 24;
    }

    public void draw(Canvas canvas) {
        bitmap.draw(canvas,x,y);
//        int w = bitmap.getWidth();
////        int fw = w/24;
////         총24장짜리이미지니까
//        int h = bitmap.getHeight();
////         비트맵크기구하는법
//        int fw = h;
//        int ballRadius = 100;
//        Rect src = new Rect(fw*frameIndex,0,fw*frameIndex+fw,h);
//        RectF dst = new RectF(x - ballRadius,y-ballRadius,x+ballRadius,y+ballRadius);
//        canvas.drawBitmap(bitmap,src,dst,null);
//  //      canvas.drawBitmap(bitmap, this.x, this.y, null);

//        화면에bitmap인자받은거보여줌


    }





//   좌표는 float이좋음
}
