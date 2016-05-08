package com.example.denjo.test;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

public class result extends AppCompatActivity {
    public int resultId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        //intent経由でidを受け取れる(http://android.roof-balcony.com/intent/putextra/)受け取り失敗時は理由はないが1001にしてある。
        Intent i = getIntent();
        resultId = i.getIntExtra("resultId",1001);
        //imageViewにdrawble内の画像"rxxxx"を表示させる。(xxxx=resultId)
        ImageView resultImage = (ImageView) findViewById(R.id.imageView5);
        resultImage.setImageResource(getResources().getIdentifier("r" + resultId, "drawable", getPackageName()));

    }
}
