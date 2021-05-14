package kr.ac.kpu.game.s2015180046.cookierun.framework;

import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

import kr.ac.kpu.game.s2015180046.cookierun.view.GameView;

public class IndexedAnimationGameBitmap extends AnimationGameBitmap{

    private final int frameHeight;

    public IndexedAnimationGameBitmap(int resId, float framesPerSecond, int frameCount){
        super(resId, framesPerSecond, frameCount);
        this.frameWidth =270; //super무시하고 다시엎어쓰겟다
        this.frameHeight =270;

    }

    protected ArrayList<Rect> srcRects;
    public void setIndices(int... indices){ //객체의 array가넘어오는방식
        srcRects = new ArrayList<Rect>();
        for (int index: indices){
            int x = index % 100;
            int y = index / 100;
            int left = 2 + x * 272;
            int top = 2 + y * 272;
            int right = left + 270;
            int bottom = top + 270;
            srcRects.add(new Rect(left,top,right,bottom));
        }

        frameCount = indices.length;
    }

    @Override
    public void draw(Canvas canvas, float x, float y) {
        int elapsed = (int)(System.currentTimeMillis() - createdOn);
        frameIndex = Math.round(elapsed * 0.001f * framesPerSecond) % frameCount;

        int fw = frameWidth;
        int h = frameHeight;
        float hw = fw / 2 * GameView.MULTIPLIER;
        float hh = h / 2 * GameView.MULTIPLIER;
        dstRect.set(x - hw, y - hh, x + hw, y + hh);
        canvas.drawBitmap(bitmap, srcRects.get(frameIndex), dstRect, null);
    }
}
