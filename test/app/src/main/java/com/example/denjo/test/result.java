package com.example.denjo.test;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class result extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        FileInputStream fis = null;
        BufferedReader in = null;
        String text = "";

        ImageView imageView = (ImageView) findViewById(R.id.imageResult);
        imageView.setImageResource(R.drawable.text);
        linerLayout.addView()

        // ファイルの読込
        try {
            try {
                fis = this.openFileInput("test.txt");
                in = new BufferedReader(new InputStreamReader(fis));
                String str;
                while ((str = in.readLine()) != null) {
                    text = str;
                }
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
