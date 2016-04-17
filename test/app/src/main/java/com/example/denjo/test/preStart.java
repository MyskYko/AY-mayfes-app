package com.example.denjo.test;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class preStart extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_start);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }



    public void onClickCameraButton(View view) {
        long dateTaken = System.currentTimeMillis();
        String filename = DateFormat.format("yyyy-MM-dd_kk.mm.ss", dateTaken).toString() + ".jpg";

        ContentResolver contentResolver = getContentResolver();
        ContentValues values = new ContentValues(5);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.Images.Media.DATE_MODIFIED, System.currentTimeMillis() / 1000);
        values.put(MediaStore.Images.Media.TITLE, filename);
        values.put(MediaStore.Images.Media.DISPLAY_NAME, filename);
        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        Uri pictureUri = contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        if (sp == null) sp = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("pictureUri", pictureUri.toString());
        editor.apply();

        Intent intent = new Intent();
        intent.setAction("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            // 正しい結果が得られなかった場合の処理
            // 撮影キャンセルなどするとこっちに来る

            if (sp == null) sp = getPreferences(MODE_PRIVATE);
            Uri tmpUri = Uri.parse(sp.getString("pictureUri", ""));
            if (tmpUri != null) {
                ContentResolver contentResolver = getContentResolver();
                try {
                    contentResolver.delete(tmpUri, null, null);
                } catch (Exception e) {
                    // 対象ファイルがない場合エラー
                }
                sp.edit().remove("pictureUri");
            }
            return;
        }

        if (requestCode == 1) {
            // 撮影成功時の処理

            Uri resultUri = null;
            if (sp == null) {
                sp = getPreferences(MODE_PRIVATE);
            }
            if (data != null && data.getData() != null) {
                resultUri = data.getData();
            } else {
                resultUri = Uri.parse(sp.getString("pictureUri", ""));
            }

            // resultUri に撮影した画像のURIが格納されている
            // 後は煮るなり焼くなりご自由に♪
            // 使い終わったらURIにある画像の削除を忘れずに！

            return;
        }
    }


    public void move(View view) {
        Intent intent = new Intent(this, test.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "preStart Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.denjo.test/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "preStart Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.denjo.test/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}

