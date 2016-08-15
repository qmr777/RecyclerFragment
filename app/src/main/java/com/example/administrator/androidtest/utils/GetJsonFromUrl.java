package com.example.administrator.androidtest.utils;

import android.util.Log;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/3.
 * 根据Url获取json
 */
public class GetJsonFromUrl {
    private static byte[] readStream(InputStream inputStream) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            bout.write(buffer, 0, len);
        }
        bout.close();
        inputStream.close();
        return bout.toByteArray();
    }

    public static String GetString(String u, Map<String,String> post, String tag) {
        JSONObject jsonObject = new JSONObject(post);
        return GetString(u,jsonObject,tag);
    }

    public static String GetString(String u, JSONObject post,String tag) {
        Log.e(tag,post.toString());
        return GetString(u,post);
    }

    public static String GetString(String u, Map<String,String> post){
        JSONObject jsonObject = new JSONObject(post);
        return GetString(u,jsonObject);
    }

    public static String GetString(String u, JSONObject post) {
        URL url = null;
        HttpURLConnection connection;
        String s = null;
        try {
            url = new URL(u);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            assert url!=null;
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            OutputStream os = connection.getOutputStream();
            if(post!=null) {
                os.write(post.toString().getBytes());
            }
            os.close();
            InputStream is = connection.getInputStream();
            s = new String(readStream(is));
            //Log.d("AsyncTask",s);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public static String GetString(String u, String post) {
        URL url = null;
        HttpURLConnection connection;
        String s = null;
        try {
            url = new URL(u);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            assert url!=null;
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            OutputStream os = connection.getOutputStream();
            if(post!=null) {
                os.write(post.toString().getBytes());
            }
            os.close();
            InputStream is = connection.getInputStream();
            s = new String(readStream(is));
            //Log.d("AsyncTask",s);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }


}
