package kr.ac.kpu.game.s2015180046.dragonflight.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;

import kr.ac.kpu.game.s2015180046.dragonflight.R;
import kr.ac.kpu.game.s2015180046.dragonflight.framework.GameObject;
import kr.ac.kpu.game.s2015180046.dragonflight.framework.Recyclable;
import kr.ac.kpu.game.s2015180046.dragonflight.ui.view.GameView;
import kr.ac.kpu.game.s2015180046.dragonflight.utils.CollisionHelper;

public class MainGame {
    private static final String TAG = MainGame.class.getSimpleName();
    // singleton
    private static MainGame instance;
    private Player player;
    private Score score;


    public static MainGame get() {
        if (instance == null) {
            instance = new MainGame();
        }
        return instance;
    }
    public float frameTime;
    private boolean initialized;

//    Player player;
    ArrayList<ArrayList<GameObject>> layers;
    private static HashMap<Class,ArrayList<GameObject>>recycleBin = new HashMap<>();
//    클래스를 key로해서 게임오브젝트의 어래이리스트를 해시맵으로할거다
 /*   객체를재활용해야할때는
    객체의 클래스를 가지고와서
    클래스를 키로하는 어레이를 얻어서
    거기다가 넣는다*/

    public void recycle(GameObject object){
        Class clazz = object.getClass();
        ArrayList<GameObject> array = recycleBin.get(clazz);
        if(array == null){ //첫번째호출시 비어있으니까 어래이리스트생성
            array = new ArrayList<>();
            recycleBin.put(clazz,array);
        }
        array.add(object);
    }
 /*   객체를 가져갈때는
    무슨클래스를위해 필요한건지 물어보고
    이클래스를가지고 어래이를 얻은다음에
    어래이에있는 첫번째 객채를 꺼내서 리턴한다*/
    //재활용통
    public GameObject get(Class clazz){//재활용통에서가져가는함수
        ArrayList<GameObject> array = recycleBin.get(clazz);
        if(array==null || array.isEmpty()) return null;
        return array.remove(0);
    }


    public enum Layer{
        bg1,enemy,bullet,player,bg2,ui,controller,ENEMY_COUNT
    }

    public boolean initResources() { // 여기서 그림그림
        if (initialized) {
            return false;
        }
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();

        initLayers(Layer.ENEMY_COUNT.ordinal());//최초의 어레이리스트 초기화 , 자바는 enum이랑 int가 호환이 안되서 ordinal을 사용해서 쓴다

        player = new Player(w/2, h - 300);
        //layers.get(Layer.player.ordinal()).add(player);
        add(Layer.player,player);
        add(Layer.controller,new EnemyGenerator());

        int margin = (int) (20*GameView.MULTIPLIER);
        score = new Score(w-margin, margin);
        score.setScore(0);
        add(Layer.ui,score);

        VerticalScrollBackground bg = new VerticalScrollBackground(R.mipmap.bg_city,10);
        add(Layer.bg1,bg);

        VerticalScrollBackground clouds = new VerticalScrollBackground(R.mipmap.clouds,20);
        add(Layer.bg2,clouds);
        initialized = true;
        return true;
    }


    private void initLayers(int layerCount) {
        layers = new ArrayList<>();
        for ( int i = 0; i < layerCount; i++){
            layers.add(new ArrayList<>());
        }
    }

    /*최초의 객체가 생성될때는 layer가 null이였음
    layercount를 추가해서 레이어 갯수를 늘림림
     */


    public void update() {
        //if (!initialized) return;
        for (ArrayList<GameObject> objects: layers){//모든update에 add해야되니까 이중루프로
            for(GameObject o : objects){
                o.update();
            }
        }

        ArrayList<GameObject> enemies = layers.get(Layer.enemy.ordinal());
        ArrayList<GameObject> bullets = layers.get(Layer.bullet.ordinal());
        for(GameObject o1: enemies){
            Enemy enemy = (Enemy)o1;
            boolean isCollided = false;
            for(GameObject o2: bullets){
                Bullet bullet = (Bullet) o2;
                if(CollisionHelper.isCollides(enemy,bullet)){
                    remove(bullet);
                    remove(enemy);
                    score.addScore(10);
                    isCollided = true;
                    break;
                }
            }
            if(isCollided){
                break;
            }
        }
       /* for (GameObject o : objects) {
            o.update();
        }

        for(GameObject o1 : objects){
            if(!(o1 instanceof Enemy)){
                continue;
            }
            Enemy enemy = (Enemy) o1;
            boolean removed = false;
            for(GameObject o2 : objects){
                if(!(o2 instanceof Bullet)){
                    continue;
                }

                Bullet bullet = (Bullet) o2;

                if (CollisionHelper.isCollides((BoxCollidable)o1, (BoxCollidable)o2)) {
                    Log.d(TAG, "Collision!" + o1 + " - " + o2);
                    remove(enemy);
                    remove(bullet);
                   // bullet.recycle();
                    //recycle(bullet);
                    removed = true;
                    break;
                }
            }
            if (removed){
                continue;
            }
            if(CollisionHelper.isCollides((BoxCollidable)enemy,player)){
                Log.d(TAG, "Collision!: Enemy - player");
            }


        }
*/
    }

    public void draw(Canvas canvas) {
        //if (!initialized) return;
        for (ArrayList<GameObject> objects: layers){//모든update에 add해야되니까 이중루프로
            for(GameObject o : objects){
                o.draw(canvas);
            }
        }

    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
//        if (action == MotionEvent.ACTION_DOWN) {
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
            player.moveTo(event.getX(), event.getY());
            return true;
        }
        return false;
    }


    public void add(Layer layer,GameObject gameObject){
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject> objects = layers.get(layer.ordinal());//layer의 어레이리스트나옴
                objects.add(gameObject);
            }
        });
    }


    public void remove(GameObject gameObject) {
        if(gameObject instanceof Recyclable){
            ((Recyclable) gameObject).recycle();
            recycle(gameObject);
        }
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                for (ArrayList<GameObject> objects: layers){//모든update에 add해야되니까 이중루프로
                    boolean removed = objects.remove(gameObject);
                    if(removed){
                        break;
                    }
                }
                }


        });
    }
}
