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
import java.util.regex.Pattern;

public class result extends AppCompatActivity {
    public int resultId;
    public String resultName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        //intent経由でidを受け取れる(http://android.roof-balcony.com/intent/putextra/)受け取り失敗時は理由はないが1001にしてある。
        Intent i = getIntent();
        resultId = i.getIntExtra("resultId", 1001);
        //getName
        resultName = getName("sorce.txt");
        if(resultName != null) {
            TextView resultText = (TextView) findViewById(R.id.textView12);
            resultText.setText(resultName);
        }
        //imageViewにdrawble内の画像"rxxxx"を表示させる。(xxxx=resultId)
        ImageView resultImage = (ImageView) findViewById(R.id.imageView5);
        resultImage.setImageResource(getResources().getIdentifier("r" + resultId, "drawable", getPackageName()));
    }


    public String getName(String filename) {
        //1行ずつ読み込み
        try {
            File file = new File(filename);

            if (checkBeforeReadfile(file)) {
                BufferedReader br
                        = new BufferedReader(new FileReader(file));

                String str;
                while ((str = br.readLine()) != null) {
                    //トークン分解
                    Pattern p = Pattern.compile("[�@|\\s]+");
                    String[] result = p.split(str);
                    if (result.length != 2) {
                        System.out.println("different number of token");
                    }
                    //idがresuluIdと一致したとき説明を返す。
                    int tmpid = Integer.parseInt(result[0]);
                    if(tmpid == resultId){
                        return result[1];
                    }
                }
                br.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    private static boolean checkBeforeReadfile(File file) {
        if (file.exists()) {
            if (file.isFile() && file.canRead()) {
                return true;
            }
        }
        return false;
    }
}
