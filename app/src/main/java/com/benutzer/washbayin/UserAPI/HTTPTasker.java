package com.benutzer.washbayin.UserAPI;

import android.os.AsyncTask;

//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicHeader;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.benutzer.washbayin.utilities.HTTPResponseInterface;
import com.benutzer.washbayin.utilities.HTTPStructure;

/**
 * Created by amitesh on 23/5/16.
 */
public class HTTPTasker extends AsyncTask<HTTPStructure, String, String> {
    HttpURLConnection httpURLConnection;
    StringBuilder result;
    public HTTPResponseInterface responseInterface = null;
    @Override
    protected String doInBackground(HTTPStructure... params) {
        try{
            URL url = new URL(params[0].getUrl());
            result = new StringBuilder();
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod(params[0].getMethod());
            if(params[0].getAuthToken() != null){
                String authToken = "bearer " + params[0].getAuthToken();
                httpURLConnection.setRequestProperty("Authorization", authToken);
            }
            httpURLConnection.setRequestProperty("Content-Type", params[0].getContentType());
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoInput(true);
            if(params[0].getDataJson() != null){
                //httpURLConnection.setChunkedStreamingMode(0);
                System.out.println(params[0].getDataJson());
                OutputStream os = new BufferedOutputStream(httpURLConnection.getOutputStream());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
                writer.write(params[0].getDataJson());
                writer.flush();
            }

            //read response
            if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_ACCEPTED || httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream is = new BufferedInputStream(httpURLConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                if(params[0].getUrl().indexOf("signin") > 0 || params[0].getUrl().indexOf("signup") > 0){
                    result.delete(0, result.length());
                    result.append(httpURLConnection.getHeaderField("Authorization"));
                }
            }else{
                InputStream is = new BufferedInputStream(httpURLConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
            }

        }catch(MalformedURLException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }finally {
            httpURLConnection.disconnect();
        }

        return result.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        responseInterface.publishResponse(s);
    }
}
