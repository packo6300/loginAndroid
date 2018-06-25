package com.packo.login.service;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

/**
 * Created by sistemas10 on 25/06/2018.
 */

public class JsonRequest implements Serializable{

    private final String TAG=this.toString();

    static InputStream inputStream;
    static JSONObject jsonObject;
    static String json ;

    public JsonRequest(){

    }
    public JSONObject getJsonFromUrl(String url){
        try{

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "n");
            }
            inputStream.close();
            json = sb.toString();
            System.out.println(json);
            jsonObject = new JSONObject(json);
        }
        catch (Exception e){
            Log.e(TAG,e.toString());
        }
        finally {
            return jsonObject;
        }
    }
}
