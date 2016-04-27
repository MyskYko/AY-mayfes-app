package com.example.denjo.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.denjo.test.utils.HttpConnector;
import com.example.denjo.test.utils.HttpRequest;

public class test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void moveTutorial(View view) {
        Intent intent = new Intent(this, tutorial1.class);
        startActivity(intent);
    }

    public void moveStart(View view) {
        Intent intent = new Intent(this, preStart.class);
        startActivity(intent);
    }
    public void moveChoice(View view) {
        Intent intent = new Intent(this, genre_choice.class);
        startActivity(intent);
    }
}
