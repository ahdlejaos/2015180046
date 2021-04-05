package com.example.samplegame.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.samplegame.framework.GameObject;
import com.example.samplegame.R;
import com.example.samplegame.ui.view.GameView;

public class Ball implements GameObject {
    private static int imageWidth;
    private static int imageHeight;
    private float x, y; // 현재위치
    private float dx, dy;// 속력
    private static Bitmap bitmap;

    public Ball(float x, float y, float dx, float dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        //        초기화
//        인자로넘어온거랑 멤버변수이름이같으면 this로
        if (bitmap == null) {
            Resources res = GameView.view.getResources();
            bitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);
            imageWidth = bitmap.getWidth();
            imageHeight = bitmap.getHeight();
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
        if (x < 0 || x > w - imageWidth) {
            dx *= -1;
        }
        if (y < 0 || y > h - imageHeight) {
            dy = -dy;
        }
        //벽에닿으면 튕기게

    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, this.x, this.y, null);

//        화면에bitmap인자받은거보여줌


    }





//   좌표는 float이좋음
}
