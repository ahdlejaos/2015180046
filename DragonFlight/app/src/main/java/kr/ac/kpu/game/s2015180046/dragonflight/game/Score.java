package kr.ac.kpu.game.s2015180046.dragonflight.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2015180046.dragonflight.R;
import kr.ac.kpu.game.s2015180046.dragonflight.framework.GameBitmap;
import kr.ac.kpu.game.s2015180046.dragonflight.framework.GameObject;
import kr.ac.kpu.game.s2015180046.dragonflight.ui.view.GameView;

public class Score implements GameObject {
    private final Bitmap bitmap;
    private final int right;
    private final int top;

    private Rect src = new Rect();
    private RectF dst = new RectF();


    public void setScore(int score) {
        this.score = score;
        this.displayScore = score;
    }

    public void addScore(int amount) {
        this.score += amount;
      //  setScore(this.score+amount);
    }

    private int score,displayScore;

    public Score(int right,int top){

        bitmap = GameBitmap.load(R.mipmap.number_24x32);
        this.right = right;
        this.top = top;
    }
    @Override
    public void update() {
        if(displayScore < score){
            displayScore++;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        int value = this.displayScore;
        int numberWidth = bitmap.getWidth()/10;
        int numberHeight = bitmap.getHeight();
        int x = right;
        int destinationWidth = (int) (numberWidth* GameView.MULTIPLIER);
        int destinationHeight = (int) (numberHeight* GameView.MULTIPLIER);

        while(value>0){
            int digit = value % 10;//맨오른쪽숫자를그리고 10으로나눠 계속나누다보면 while문빠져나감
            src.set(digit*numberWidth,0,(digit+1)*numberWidth,numberHeight);
            x -= destinationWidth;
            dst.set(x,top,x+destinationWidth,top+destinationHeight);
            canvas.drawBitmap(bitmap,src,dst,null);
            value /= 10;


        }
    }

}
