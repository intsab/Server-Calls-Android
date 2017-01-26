package com.intandif.networkcalls;

import android.util.Log;

import com.android.internal.http.multipart.MultipartEntity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by intsab and uferah on 23/01/2017.
 */

public class NetworkCalls {
    public static NetworkCalls obj;

    public static synchronized NetworkCalls getInstance() {
        if (obj == null)
            obj = new NetworkCalls();
        return obj;
    }

    public void postRequest(final String url, final ArrayList<NameValuePair> params, final ResponseListener responseListener) {

        Runnable r = new Runnable() {
            public void run() {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);

                List<NameValuePair> nameValuePair = params;
                try {
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                    String responseString = EntityUtils.toString(entity, "UTF-8");
//                    ResponseListener nResponce = responceListner;
                    responseListener.onResponse(responseString);
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                    responseListener.onErrorResponse(e.getMessage().toString());
                } catch (IOException e) {
                    responseListener.onErrorResponse(e.getMessage().toString());
                    e.printStackTrace();
                }
            }
        };

        new Thread(r).start();


    }

    public void getRequest(final String url, final ResponseListener responseListener) {
        Runnable r = new Runnable() {
            public void run() {
                HttpClient httpclient = new DefaultHttpClient();
                HttpGet httpget = new HttpGet(url);
                HttpResponse response;
                try {
                    response = httpclient.execute(httpget);
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String responseString = EntityUtils.toString(entity, "UTF-8");
                        responseListener.onResponse(responseString);
                    }
                } catch (Exception e) {
                    responseListener.onErrorResponse(e.getMessage().toString());
                }
            }
        };
        new Thread(r).start();
    }

    public interface ResponseListener {
        void onResponse(String response);

        void onErrorResponse(String error);

    }


    public void uploadFileRequest(final String url, final File file, final String fileKey, final ArrayList<NameValuePair> params, final ResponseListener responseListener) {

        Runnable r = new Runnable() {
            public void run() {
                try {
                    HttpClient client = new DefaultHttpClient();
                    HttpPost post = new HttpPost(url);

                    MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
                    entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
                    for (int x = 0; x < params.size(); x++) {
                        NameValuePair pare = params.get(x);
                        entityBuilder.addTextBody(pare.getName(), pare.getValue());
                    }

                    if (file != null) {
                        entityBuilder.addBinaryBody(fileKey, file);
                    }

                    HttpEntity entity = entityBuilder.build();
                    post.setEntity(entity);
                    HttpResponse response = client.execute(post);
                    HttpEntity httpEntity = response.getEntity();
                    String result = EntityUtils.toString(httpEntity);
                    responseListener.onResponse(result);
                    Log.v("result", result);
                } catch (Exception e) {
                    e.printStackTrace();
                    responseListener.onErrorResponse(e.getMessage());
                }
            }
        };
        new Thread(r).start();

    }
}

