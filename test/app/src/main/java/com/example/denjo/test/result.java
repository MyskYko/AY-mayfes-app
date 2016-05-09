package com.example.denjo.test;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;

public class result extends AppCompatActivity {
    int resultId[];
    int category;
    String resultName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        //intent経由でidを受け取れる(http://android.roof-balcony.com/intent/putextra/)
        Intent i = getIntent();
        resultId = i.getIntArrayExtra("resultId");
        //カテゴリ選択をうけとる。とくになければ0（全体)になる。
        category = i.getIntExtra("category",0);
        //getName
        resultName = getName("sorce.txt");
        if(resultName != null) {
            TextView resultText = (TextView) findViewById(R.id.textView12);
            resultText.setText(resultName);
        }
        //imageViewにdrawble内の画像"rxxxx"を表示させる。(xxxx=resultId[category])
        ImageView resultImage = (ImageView) findViewById(R.id.imageView5);
        resultImage.setImageResource(getResources().getIdentifier("r" + resultId[category], "drawable", getPackageName()));
    }


    public String getName(String filename) {
        //1行ずつ読み込み
        String str = null;
        InputStream is = null;
        try {
            is = this.getAssets().open(filename);
            if (is != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                while ((str = br.readLine()) != null) {
                    //トークン分解
                    String[] result = str.split("\t", 0);
                    if (result.length != 2) {
                        System.out.println("different number of token");
                    }
                    //idがresultId[category]と一致したとき説明を返す。
                    int tmpId = Integer.parseInt(result[0]);
                    if (tmpId == resultId[category]) {
                        str = result[1];
                        break;
                    }
                }
                br.close();
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

}
