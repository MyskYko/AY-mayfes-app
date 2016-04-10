package com.example.denjo.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class tutorial1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial1);
    }
    public void moveFirst(View view) {
        setContentView(R.layout.activity_tutorial1);
    }
    public void moveSecond(View view) {
        setContentView(R.layout.activity_tutorial2);
    }
    public void moveThird(View view) {
        setContentView(R.layout.activity_tutorial3);
    }
    public void finishTutorial(View view) {
        finish();
    }
}
