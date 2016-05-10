package com.example.denjo.test;

import android.content.Intent;
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
    public void moveFourth(View view) {
        setContentView(R.layout.activity_tutorial4);
    }
    public void finishTutorial(View view) {
        Intent intent = new Intent(this, preStart.class);
        startActivity(intent);
        finish();
    }
}
