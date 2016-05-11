package com.example.denjo.test.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.ContactsContract;
import android.renderscript.ScriptGroup;
import android.util.Log;

import com.example.denjo.test.R;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by denjo on 2016/04/27.
 */

public class HttpConnector {

    Globals globalshp;

    /* ネットワーク接続有無 */
    public static boolean isConnected(ConnectivityManager manager){
        NetworkInfo info = manager.getActiveNetworkInfo();
        if(info == null){
            return false;
        }
        return info.isConnected();
    }

    /* リクエスト情報クラス */
    public static class RequestInfo{
        public String url = null;
        public List<Param> params = new ArrayList<Param>();
        public AsyncCallback asyncCallBack = null;
    }

    /* Postパラメータ情報クラス */
    public static class Param {
        public static final int TYPE_STRING = 1;
        public static final int TYPE_IMAGE = 2;

        private int type;
        private String key;
        private String value;

        private Bitmap capturedImage;

        public Param(int type, String key, String value, Bitmap capturedImage){
            this.type = type;
            this.key = key;
            this.value = value;
            this.capturedImage = capturedImage;
        }
    }

    /* リクエスト呼び出しクラス */
    public static  void Request(Context context, RequestInfo requestInfo, Globals globals){
        AsyncHttpRequest asyncHttpRequest = new AsyncHttpRequest(context, globals,requestInfo.asyncCallBack);
        asyncHttpRequest.url = requestInfo.url;
        if(requestInfo.url != null) {
            asyncHttpRequest.params.addAll(requestInfo.params);
        }

        // リクエスト実行
        asyncHttpRequest.execute();
    }

    /* リクエスト実行本体 */
    private static class AsyncHttpRequest extends AsyncTask<Void, Void, String>{
        private Context context = null;
        private String url = null;
        private List<Param> params = new ArrayList<Param>();
        private AsyncCallback asyncCallback = null;
        private ProgressDialog progressDialog;
        private Globals globals;
        private String str;

        public AsyncHttpRequest(Context context, Globals globals, AsyncCallback asyncCallback){
            this.context = context;
            this.globals = globals;
            this.asyncCallback = asyncCallback;
        }

        @Override
        protected  void onPreExecute(){
            progressDialog = new ProgressDialog(context);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Connecting to Server...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... params){
            URI uri = null;
            try {
                uri = new URI(url);
            }catch(URISyntaxException e){
                e.printStackTrace();
                return null;
            }

            HttpUriRequest request = null;
            if(this.params != null && this.params.size() > 0){

                HttpPost post = new HttpPost(uri);

                MultipartEntityBuilder entity = MultipartEntityBuilder.create();
                entity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
                entity.setCharset(Consts.UTF_8);

                for(Param p:this.params){
                    switch (p.type){
                        case Param.TYPE_STRING:
                            ContentType textContentType = ContentType.create("text/plain");
                            entity.addTextBody(p.key, p.value, textContentType);
                            break;
                        case Param.TYPE_IMAGE:

                           if(p.capturedImage != null){
                               ByteArrayOutputStream stream = new ByteArrayOutputStream();
                               p.capturedImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                               final byte[] bitmapdata = stream.toByteArray();

                               ContentType imageContentType = ContentType.create("image/jpeg");
                               entity.addBinaryBody("upfile", bitmapdata, imageContentType, "sayane.jpg");
                           }
                            break;
                    }
                }
                post.setEntity(entity.build());
                request = post;
            }

            HttpClient httpClient = new DefaultHttpClient();
            try{
                httpClient.execute(request, new ResponseHandler<String>() {
                    @Override
                    public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                        System.out.print("HTTP Status Code:");
                        System.out.println(response.getStatusLine().getStatusCode());
                        switch (response.getStatusLine().getStatusCode()){
                            case HttpStatus.SC_OK:

                            /*
                                if(response.getEntity().getContentLength() > 0){
                                    StringBuilder stringBuilder = new StringBuilder();
                                    try{
                                        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()), 65728);
                                        String line = null;

                                        while((line = reader.readLine()) != null){
                                            stringBuilder.append(line);
                                        }
                                    } catch (IOException e){
                                        e.printStackTrace();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    System.out.println(stringBuilder.toString());
                                    */
                                str = EntityUtils.toString(response.getEntity());
                                    return str;
                            case HttpStatus.SC_NOT_FOUND:
                                throw new RuntimeException("httpClient.execute SC_NOT_FOUND");
                            default:
                                throw new RuntimeException("httpClient.execute error!");
                        }
                    }
                });
            } catch (ClientProtocolException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            } finally {
                if (httpClient != null){
                    try{
                        httpClient.getConnectionManager().shutdown();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            return str;
        }

        @Override
        protected void onPostExecute(String response) {

            // プロセスダイアログ表示終了
            if (this.progressDialog != null && this.progressDialog.isShowing()) {
                this.progressDialog.dismiss();
            }
            // 起動元をコールバック
           if (this.asyncCallback != null) {
                this.asyncCallback.onPostExecute(context,response);
           }
        }
    }
}