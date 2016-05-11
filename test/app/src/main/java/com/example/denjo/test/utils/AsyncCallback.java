package com.example.denjo.test.utils;

import android.content.Context;

import java.io.InputStream;
/**
 * Created by denjo on 2016/04/27.
 */
public interface AsyncCallback {
    void onPostExecute(Context context,String response);
}
