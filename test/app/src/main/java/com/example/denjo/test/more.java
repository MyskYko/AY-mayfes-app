package com.example.denjo.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class more extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
    }
    public void moveJender(View view) {
        Intent intent = new Intent(this, jenderChoice.class);
        startActivity(intent);
    }

    public void moveGenre(View view) {
        Intent intent = new Intent(this, genre_choice.class);
        startActivity(intent);
    }
}
