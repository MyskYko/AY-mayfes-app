package com.example.denjo.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class more extends AppCompatActivity {
    int[] resultId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        Intent i = getIntent();
        resultId = i.getIntArrayExtra("resultId");
    }
    public void moveJender(View view) {
        Intent intent = new Intent(this, jenderChoice.class);
        intent.putExtra("resultId", resultId);
        startActivity(intent);
        finish();
    }
    public void moveGenre(View view) {
        Intent intent = new Intent(this, genre_choice.class);
        intent.putExtra("resultId", resultId);
        startActivity(intent);
        finish();
    }
    public void moveTitle(View view) {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        finish();
    }
}
