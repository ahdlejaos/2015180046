package com.example.samplegame.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.example.samplegame.R;
import com.example.samplegame.framework.AnimationGameBitmap;
import com.example.samplegame.framework.GameObject;
import com.example.samplegame.ui.view.GameView;

public class Bullet implements GameObject {

    private final float radius;
    private final float angle;
    private float x, y; // 현재위치
    private float dx, dy;// 속력

    private static AnimationGameBitmap bitmap;
    private static float FRAME_RATE = 6.7f;

//    Paint paint = new Paint();
//    private static Bitmap bitmap;

    public Bullet(float x, float y, float tx, float ty) {
//        여기를향해 발사
        this.x = x;
        this.y = y;
        this.radius =10.0f;

//        float speed = 1000;
        float delta_x = tx - this.x;
        float delta_y = ty - this.y;
         angle = (float) Math.atan2(delta_y, delta_x);
//        각도 구하는식 ,
//        MainGame game = MainGame.get();
        float move_dist = 500;
//        한프레임당 움직임
        this.dx = (float) (move_dist * Math.cos(angle));
//       x 움직여야할거리
        this.dy = (float) (move_dist * Math.sin(angle));
//       y 움직여야할거리


//        paint.setColor(0xFFFF0000);
        //        초기화
//        인자로넘어온거랑 멤버변수이름이같으면 this로
//        if (bitmap == null) {
//            Resources res = GameView.view.getResources();
//            bitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);
//            imageWidth = bitmap.getWidth();
//            imageHeight = bitmap.getHeight();
//        }
        if (bitmap == null) {

            bitmap = new AnimationGameBitmap(R.mipmap.bullet_hadoken, FRAME_RATE, 0);
//            Resources res = GameView.view.getResources();
//            BitmapFactory.Options opts = new BitmapFactory.Options();
//            opts.inScaled = false;
////            원래이미지사이즈 그대로 보여주게함
//            bitmap = BitmapFactory.decodeResource(res, R.mipmap.laser_light,opts);
//            imageWidth = bitmap.getWidth();
//            imageHeight = bitmap.getHeight();
//        }
//        createdOn = System.currentTimeMillis();
////        System.currentTimeMillis = 현재시각을 밀리세컨드 단위로 반환함
        }
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
        boolean toBeDeleted = false;
        if (x < 0 || x > w - frameWidth) {
           toBeDeleted = true;
        }
        if (y < 0 || y > h - frameHeight) {
            toBeDeleted = true;
        }
        if(toBeDeleted){
            game.remove(this);
        }

 //       bitmap.update();
//        int elapsed = (int) (System.currentTimeMillis() - createdOn);
////        이수치로몇프레임그려야하는지알수있음
//        frameIndex = Math.round(elapsed *0.001f* FRAME_RATE) % 10;
////        frameIndex = (frameIndex + 1) % 24;

    }

    public void draw(Canvas canvas) {

        float degree = (float) (angle * 180 / Math.PI) + 90;
        canvas.save();
        canvas.rotate(degree,x,y);
        bitmap.draw(canvas,x,y);
        canvas.restore();
////         matrix에회전변환을곱해줌
//        int w = bitmap.getWidth();
////        int fw = w/24;
////         총24장짜리이미지니까
//        int h = bitmap.getHeight();
////         비트맵크기구하는법
//        int fw = w/10;
////        int ballRadius = 400;
//        int halfWidth =100;
//        int halfHeight = 124;
//        Rect src = new Rect(fw*frameIndex,0,fw*frameIndex+fw,h);
//        RectF dst = new RectF(x - halfWidth,y-halfHeight,x+halfWidth,y+halfHeight);
//
//
//
//
//        canvas.drawBitmap(bitmap,src,dst,null);

        //      canvas.drawBitmap(bitmap, this.x, this.y, null);

//        화면에bitmap인자받은거보여줌

//        canvas.drawCircle(x,y,radius,paint);
     //   canvas.drawBitmap(bitmap, this.x, this.y, null);

//        화면에bitmap인자받은거보여줌


    }





//   좌표는 float이좋음
}
