package kr.ac.kpu.game.s2015180046.cookierun.utils;


import android.graphics.RectF;

import kr.ac.kpu.game.s2015180046.cookierun.framework.iface.BoxCollidable;


public class CollisionHelper {

    private static RectF rect1 = new RectF();
    private static RectF rect2 = new RectF();
    public static boolean isCollides(BoxCollidable object1, BoxCollidable object2){
        object1.getBoundingRect(rect1);
        object2.getBoundingRect(rect2);

        if (rect1.left > rect2.right) return false;
        if (rect1.top > rect2.bottom) return false;
        if (rect1.right < rect2.left) return false;
        if (rect1.bottom < rect2.top) return false;

        return true;
    }

}
