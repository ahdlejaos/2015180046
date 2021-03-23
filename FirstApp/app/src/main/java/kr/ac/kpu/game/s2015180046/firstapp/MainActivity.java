package kr.ac.kpu.game.s2015180046.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView mainTextView;
    private ImageView mainImageView;

    private int pageIndex;
    private static int[] resIds = {
            R.mipmap.cat1,
            R.mipmap.cat2,
            R.mipmap.cat3,
            R.mipmap.cat4,
            R.mipmap.cat5,
    };
    private View nextButton;
    private View prevButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//여기에있는걸 화면에나오게하는함수
        pageIndex = 0;

        mainTextView = findViewById(R.id.mainTextView);
        mainImageView =findViewById(R.id.mainImageView);
        prevButton = findViewById(R.id.helloButton);
        nextButton = findViewById(R.id.worldButton);


        showimage();

    }
    public void onBtePrevious(View view){
        if (pageIndex == 0){
            return;
        }
        pageIndex--;
        showimage();
    }

    public void onBtnNext(View view) {
        if (pageIndex == resIds.length-1){
            return;
        }
        pageIndex++;
        showimage();
    }

    private void showimage() {
        mainTextView.setText((pageIndex + 1) + "/" + resIds.length);
        int resId = resIds[pageIndex];
        mainImageView.setImageResource(resId);


        prevButton.setEnabled(pageIndex!=0);
        nextButton.setEnabled(pageIndex != resIds.length -1);

    }



}