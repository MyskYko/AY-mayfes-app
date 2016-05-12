package com.example.denjo.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void moveTutorial(View view) {
        Intent intent = new Intent(this, tutorial1.class);
        startActivity(intent);
        finish();
    }
    public void moveStart(View view) {
        Intent intent = new Intent(this, preStart.class);
        startActivity(intent);
        finish();
    }
    public void moveResult(View view) {
        //semiのなかみは{全体,男全体,女全体,男その他,男act,男art,男show,女その他,女act,女art,女show}を想定してます。
        int semiResult[] = {1001,1002,2001,1003,1101,1201,1301,2002,2101,2201,2301};
        Intent intent = new Intent(this, result.class);
        intent.putExtra("resultId", semiResult);
        startActivity(intent);
        finish();
    }
}
