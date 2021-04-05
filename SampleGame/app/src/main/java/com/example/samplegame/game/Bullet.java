package com.example.samplegame.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.samplegame.R;
import com.example.samplegame.framework.GameObject;
import com.example.samplegame.ui.view.GameView;

public class Bullet implements GameObject {
    private static int imageWidth;
    private static int imageHeight;
    private final float radius;
    private float x, y; // 현재위치
    private float dx, dy;// 속력
    Paint paint = new Paint();
//    private static Bitmap bitmap;

    public Bullet(float x, float y, float tx, float ty) {
//        여기를향해 발사
        this.x = x;
        this.y = y;
        this.radius =10.0f;

//        float speed = 1000;
        float delta_x = tx - this.x;
        float delta_y = ty - this.y;
        float angle = (float) Math.atan2(delta_y, delta_x);
//        각도 구하는식 ,
//        MainGame game = MainGame.get();
        float move_dist = 500;
//        한프레임당 움직임
        this.dx = (float) (move_dist * Math.cos(angle));
//       x 움직여야할거리
        this.dy = (float) (move_dist * Math.sin(angle));
//       y 움직여야할거리


        paint.setColor(0xFFFF0000);
        //        초기화
//        인자로넘어온거랑 멤버변수이름이같으면 this로
//        if (bitmap == null) {
//            Resources res = GameView.view.getResources();
//            bitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);
//            imageWidth = bitmap.getWidth();
//            imageHeight = bitmap.getHeight();
//        }
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
        boolean toBeDeleted = false;
        if (x < 0 || x > w - imageWidth) {
           toBeDeleted = true;
        }
        if (y < 0 || y > h - imageHeight) {
            toBeDeleted = true;
        }
        if(toBeDeleted){
            game.remove(this);
        }
    }

    public void draw(Canvas canvas) {

        canvas.drawCircle(x,y,radius,paint);
     //   canvas.drawBitmap(bitmap, this.x, this.y, null);

//        화면에bitmap인자받은거보여줌


    }





//   좌표는 float이좋음
}
