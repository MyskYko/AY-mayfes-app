package com.example.denjo.test.utils;

import java.io.InputStream;
/**
 * Created by denjo on 2016/04/27.
 */
public interface AsyncCallback {
    void onPostExecute(InputStream response);
}
