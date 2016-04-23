package com.example.denjo.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.denjo.test.utils.AsyncHttpRequest;

public class preStart extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_start);
        imageView = (ImageView) findViewById(R.id.imageView);
        AsyncHttpRequest asyncHttpRequest = new AsyncHttpRequest(this); //TODO 変数名ahrだとパット見何指してるか分からないのでこれ推奨
        asyncHttpRequest.execute();
    }

    public void onClickCameraButton(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // 撮影成功時の処理
            Bitmap capturedImage = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(capturedImage);
        }
    }


    public void move(View view) {
        Intent intent = new Intent(this, test.class);
        startActivity(intent);
    }
}

