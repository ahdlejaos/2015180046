package com.example.samplegame.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import com.example.samplegame.R;
import com.example.samplegame.framework.GameObject;
import com.example.samplegame.ui.view.GameView;

public class Player implements GameObject {
    private static final String TAG = Player.class.getSimpleName();
    private static int imageWidth;
    private static int imageHeight;
    private float x, y; // 현재위치
    private float dx, dy;// 속력
    private float speed;
    private static Bitmap bitmap;
    private float tx, ty; //타겟
    private float angle = 0;

    public Player(float x, float y, float dx, float dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.tx = 0;
        this.ty = 0;
        this.speed = 800;
        //        초기화
//        인자로넘어온거랑 멤버변수이름이같으면 this로
        if (bitmap == null) {
            Resources res = GameView.view.getResources();
            bitmap = BitmapFactory.decodeResource(res, R.mipmap.plane_240);
            imageWidth = bitmap.getWidth();
            imageHeight = bitmap.getHeight();
        }

    }

    public void moveTo(float x, float y) {
        Bullet bullet = new Bullet(this.x,this.y,x,y);
        MainGame game = MainGame.get();
        game.add(bullet);
//        this.x = x;
//        this.y = y; 목적지를향해 바로가는거
//        this.tx = x;
//        this.ty = y;
        float delta_x = x - this.x;
        float delta_y = y - this.y;
        this.angle = (float) Math.atan2(delta_y, delta_x);
        Log.d(TAG,"angle");

//        각도 구하는식 ,
//        MainGame game = MainGame.get();
//        float move_dist = speed * game.frameTime;
//        한프레임당 움직임
//        this.dx = (float) (move_dist * Math.cos(angle));
//        x 움직여야할거리
//        this.dy = (float) (move_dist * Math.sin(angle));
//        y 움직여야할거리

    }
    public void update() {
//        float distance = (float) Math.sqrt(delta_x*delta_x + delta_y*delta_y);
////        거리를 구하는식
        x += dx;
        if ((dx > 0 && x > tx) || (dx < 0 && x < tx)) {
            x = tx;
        }
        y += dy;
        if ((dy > 0 && y > ty) || (dy < 0 && y < ty)) {
            y = ty;
        }
    }

    public void draw(Canvas canvas) {
        float left = x - imageWidth / 2;
        float top = y - imageHeight / 2;
        float degree = (float) (angle * 180 / Math.PI) + 90;
        //plane의 중심점 바꾸는거 , xy를 중심점으로 생각하고

        canvas.save();
        canvas.rotate(degree,x,y);
        canvas.drawBitmap(bitmap, left, top, null);
        canvas.restore();

//        화면에bitmap인자받은거보여줌


    }


//   좌표는 float이좋음
}
