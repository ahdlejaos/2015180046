package kr.ac.kpu.game.s2015180046.cookierun.framework;

import android.graphics.Canvas;
import android.graphics.Rect;

import kr.ac.kpu.game.s2015180046.cookierun.view.GameView;


public class AnimationGameBitmap extends GameBitmap {
    private static final int PIXEL_MULTIPLIER = 4;
  //  private Bitmap bitmap;
    private final int imageWidth;
    private final int imageHeight;
    protected int frameWidth;
    protected final long createdOn;
    protected int frameIndex;
    protected final float framesPerSecond;
    protected int frameCount;

    protected Rect srcRect = new Rect();

    public AnimationGameBitmap(int resId, float framesPerSecond, int frameCount) {
        super(resId); //GameBitmap으로서 생성자를 호출해줌
        //bitmap = GameBitmap.load(resId);
        imageWidth = bitmap.getWidth();
        imageHeight = bitmap.getHeight();
        if (frameCount == 0) {
            frameCount = imageWidth / imageHeight;
        }
        frameWidth = imageWidth / frameCount;
        this.framesPerSecond = framesPerSecond;
        this.frameCount = frameCount;
        createdOn = System.currentTimeMillis();
        frameIndex = 0;
    }

    //    public void update() {
//        int elapsed = (int)(System.currentTimeMillis() - createdOn);
//        frameIndex = Math.round(elapsed * 0.001f * framesPerSecond) % frameCount;
//    }

    public void draw(Canvas canvas, float x, float y) {
        int elapsed = (int)(System.currentTimeMillis() - createdOn);
        frameIndex = Math.round(elapsed * 0.001f * framesPerSecond) % frameCount;

        int fw = frameWidth;
        int h = imageHeight;
        float hw = fw / 2 * GameView.MULTIPLIER;
        float hh = h / 2 * GameView.MULTIPLIER;
        srcRect.set(fw * frameIndex, 0, fw * frameIndex + fw, h);
        dstRect.set(x - hw, y - hh, x + hw, y + hh);
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }

    public int getWidth() {
        return frameWidth ;
    }

    public int getHeight() {
        return imageHeight ;
    }
}












