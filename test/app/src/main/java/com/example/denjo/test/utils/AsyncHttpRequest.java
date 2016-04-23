package com.example.denjo.test.utils;

/**
 * Created by kazuya on 16/04/24.
 */

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.example.denjo.test.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncHttpRequest extends AsyncTask<String, String, String> {
    private String attachmentName = "bitmap";
    private String attachmentFileName = "bitmap.png";
    private String crlf = "\r\n";
    private String twoHyphens = "--";
    private String boudary = "testestest";
    private Context context; //TODO 普通はコンテクストというものを使うのでぐぐってみて
    
    private Resources res;
    private String result;
    
    public AsyncHttpRequest(Context context) {
        super();
        this.context = context;
        res = context.getResources();
    }
    
    @Override
    protected String doInBackground(String... params) {
        // Input data:
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.sayane);
        try {
            // Setup the request;
            HttpURLConnection httpUrlConnection = null;
            URL url = new URL("http://52.27.116.147/result");
            // URL url = new URL("http://api.imgur.com");
            httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setUseCaches(false);
            httpUrlConnection.setDoOutput(true);
            
            httpUrlConnection.setRequestMethod("POST");
            httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");
            httpUrlConnection.setRequestProperty("Cache-Control", "no-cache");
            httpUrlConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + this.boudary);
            
            // Start content wrapper:
            DataOutputStream request = new DataOutputStream(httpUrlConnection.getOutputStream());
            
            request.writeBytes(this.twoHyphens + this.boudary + this.crlf);
            request.writeBytes("Content-Disposition: form-data; name=\"" + this.attachmentName + "\";filename=\"" + this.attachmentFileName + "\"" + this.crlf);
            request.writeBytes(this.crlf);
            
            // Convert Bitmap to Bytebuffer:
            
            byte[] pixels = new byte[bitmap.getWidth() * bitmap.getHeight()];
            for (int i = 0; i < bitmap.getWidth(); i++) {
                for (int j = 0; j < bitmap.getHeight(); j++) {
                    // ???
                    pixels[i + j] = (byte) ((bitmap.getPixel(i, j) & 0x80) >> 7);
                }
            }
            request.write(pixels);
            
            // End content wrapper:
            request.writeBytes(this.crlf);
            request.writeBytes(this.twoHyphens + this.boudary + this.twoHyphens + this.crlf);
            
            // Flush output buffer;
            request.flush();
            request.close();
            
            // Get response:
            InputStream responseStream = new BufferedInputStream(httpUrlConnection.getInputStream());
            BufferedReader responseStreamReader = new BufferedReader(new InputStreamReader(responseStream));
            
            String line = "";
            StringBuilder stringBuilder = new StringBuilder();
            
            while ((line = responseStreamReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            responseStreamReader.close();
            String response = stringBuilder.toString();
            
            // Close response stream;
            responseStream.close();
            
            // Close the connection;
            httpUrlConnection.disconnect();
            
            return response;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    @Override
    protected  void onPostExecute(String result) {
    }
}