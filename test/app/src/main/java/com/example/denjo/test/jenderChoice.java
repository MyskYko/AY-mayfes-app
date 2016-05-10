package com.example.denjo.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class jenderChoice extends AppCompatActivity {
    int[] resultId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jender_choice);
        Intent i = getIntent();
        resultId = i.getIntArrayExtra("resultId");
    }

    public void men(View view){
        Intent intent = new Intent(this, result.class);
        intent.putExtra("resultId", resultId);
        intent.putExtra("category",9);
        startActivity(intent);
        finish();
    }
    public void women(View view){
        Intent intent = new Intent(this, result.class);
        intent.putExtra("resultId", resultId);
        intent.putExtra("category",10);
        startActivity(intent);
        finish();
    }
}
