package com.intandif.samplenetworkcalls;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnGet, btnPost, btnUpload;
    TextView tvGet, tvPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGet = (Button) findViewById(R.id.btnGet);
        btnPost = (Button) findViewById(R.id.btnPost);
        btnUpload = (Button) findViewById(R.id.btnUpload);
        tvGet = (TextView) findViewById(R.id.tvGet);
        tvPost = (TextView) findViewById(R.id.tvPost);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testGet();
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testPost();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testPostUpload();
            }
        });

    }

    public void testPost() {

        ArrayList<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", "Intsab"));
        params.add(new BasicNameValuePair("password", "Password"));
        NetworkCalls.getInstance().postRequest("http://xyz.com/api/login/format/json", params, new NetworkCalls.ResponseListener() {
            @Override
            public void onResponse(final String response) {
                Log.d("", response);
                MainActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        tvPost.setText(response);
                    }
                });

            }

            @Override
            public void onErrorResponse(String error) {

            }

        });
    }

    public void testGet() {
        NetworkCalls.getInstance().getRequest("http://xyz.com/api/getEvents/?limit=10&offset=0", new NetworkCalls.ResponseListener() {
            @Override
            public void onResponse(final String response) {
                Log.d("", response);
                MainActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        tvGet.setText(response);
                    }
                });
            }

            @Override
            public void onErrorResponse(String error) {

            }
        });
    }

    public void testPostUpload() {

        ArrayList<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("token", "22c61949efb02fd66c446f24dc680078"));
        params.add(new BasicNameValuePair("type", "profile"));
        File file = new File("/storage/sdcard0/DCIM/Camera/img.jpg");
        NetworkCalls.getInstance().uploadFileRequest("http://xyz.com/api/uploadPhoto/format/json", file, "profile_pic", params, new NetworkCalls.ResponseListener() {
            @Override
            public void onResponse(String response) {
                Log.d("hi ", "");
            }

            @Override
            public void onErrorResponse(String error) {

            }
        });


    }

}
