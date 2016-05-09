package com.example.denjo.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

<<<<<<< HEAD:test/app/src/main/java/com/example/denjo/test/Home.java
public class Home extends AppCompatActivity {
=======

>>>>>>> feature/http_request2:test/app/src/main/java/com/example/denjo/test/test.java

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void moveTutorial(View view) {
        Intent intent = new Intent(this, tutorial1.class);
        startActivity(intent);
    }

    public void moveStart(View view) {
        Intent intent = new Intent(this, preStart.class);
        startActivity(intent);
    }
    public void moveMore(View view) {
        Intent intent = new Intent(this, more.class);
        startActivity(intent);
    }
    public void moveResult(View view) {
        Intent intent = new Intent(this, result.class);
        intent.putExtra("resultId", 1002);
        startActivity(intent);
    }
}
