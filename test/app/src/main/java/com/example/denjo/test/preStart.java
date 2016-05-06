package com.example.denjo.test;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.denjo.test.utils.AsyncCallback;
import com.example.denjo.test.utils.HttpConnector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class preStart extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_start);
        imageView = (ImageView) findViewById(R.id.imageView);

        postImage();
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

    private void postImage(){

        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        if(!HttpConnector.isConnected(connectivityManager)){
            new AlertDialog.Builder(this)
                    .setMessage("インターネット接続できません")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }

        HttpConnector.RequestInfo requestInfo = new HttpConnector.RequestInfo();

        requestInfo.url = "http://52.33.86.75/result";
        //requestInfo.url = "http://localhost:8000";

        System.out.println("try access to: " + requestInfo.url);

        requestInfo.params.add(new HttpConnector.Param(HttpConnector.Param.TYPE_IMAGE, "key1", "value1"));
        //requestInfo.params.add(new HttpConnector.Param(HttpConnector.Param.TYPE_STRING, "key_param2", "value_param2"));
        requestInfo.asyncCallBack = new AsyncCallback() {
            @Override
            public void onPostExecute(InputStream responseIS) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(responseIS));
                StringBuilder buf = new StringBuilder();
                String line;
                try{
                    while((line = reader.readLine()) != null){
                        buf.append(line);
                    }
                } catch (IOException e){
                    e.printStackTrace();
                    return;
                }
                String responseStr = buf.toString();

                JSONObject jsonObject = null;
                try{
                    jsonObject = new JSONObject(responseStr);
                    /* ! */
                } catch (JSONException e){
                    e.printStackTrace();
                    return;
                }

                /*  */
            }
        };
        HttpConnector.Request(this, requestInfo);
    }

}

