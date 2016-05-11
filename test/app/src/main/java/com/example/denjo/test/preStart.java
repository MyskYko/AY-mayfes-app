package com.example.denjo.test;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.denjo.test.utils.AsyncCallback;
import com.example.denjo.test.utils.Globals;
import com.example.denjo.test.utils.HttpConnector;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class preStart extends AppCompatActivity {
    ImageView imageView;

    Globals globals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        globals = (Globals) this.getApplication();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_start);
        imageView = (ImageView) findViewById(R.id.imageView);
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
            //setContentView(R.layout.activity_waiting);
            postImage(capturedImage);
        }

    }

    private void postImage(Bitmap capturedImage){

        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(!HttpConnector.isConnected(connectivityManager)){
            new AlertDialog.Builder(this)
                    .setMessage("インターネット接続できません")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }

        HttpConnector.RequestInfo requestInfo = new HttpConnector.RequestInfo();

        requestInfo.url = "http://52.69.77.43/result";
        System.out.println("try access to: " + requestInfo.url);

        requestInfo.params.add(new HttpConnector.Param(HttpConnector.Param.TYPE_IMAGE, "key1", "value1", capturedImage));
        requestInfo.asyncCallBack = new AsyncCallback() {
            @Override
            public void onPostExecute(Context context,String response) {
                JSONObject jsonObject = null;
                int[] resultId = new int[11];
                try{
                    jsonObject = new JSONObject(response);
                    System.out.println(response);
                    Iterator<String> iter = jsonObject.keys();
                    while(iter.hasNext()) {
                        String key = iter.next();
                        resultId[Integer.parseInt(key)] = jsonObject.getInt(key);
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                    return;
                }
                if(resultId[0] == 0){
                    // 顔認識ミス処理
                    setContentView(R.layout.activity_retake);
                }else {
                    Intent intent = new Intent(context, result.class);
                    intent.putExtra("resultId", resultId);
                    startActivity(intent);
                    finish();
                }
            }
        };
        HttpConnector.Request(this, requestInfo, globals);
    }

    public void move(View view) {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        finish();
    }

}