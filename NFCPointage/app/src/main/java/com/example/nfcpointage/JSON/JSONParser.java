package com.example.nfcpointage.JSON;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Onitiana pc on 12/05/2018.
 */

public class JSONParser
{
    // inputStream recoit les donnees a partir d'une source en tant que bits
    static InputStream inputStream = null;

    // jsonObject sert a stocker l'objet recut de l'URL
    static JSONObject jsonObject = null;
    static String jSon = new String();

    // Constructeur
    public JSONParser()
    {

    }

    public JSONObject makeHttpRequest(String url)
    {
        // Recuperation et envoi de donnees
        DefaultHttpClient httpClient = new DefaultHttpClient();

        //Methode post
        HttpPost httpPost = new HttpPost(url);
        try {
            // Http response pour stocker le resultat de la requete en local
            HttpResponse httpResponse = httpClient.execute(httpPost);

            // HttpEntity pour extraire les donnees a partir de la reponse et inputstream pour le contenu des donnees
            HttpEntity httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Lecture des donnees de l'inputStream am zay
        try
        {
            BufferedReader reader = new BufferedReader( new InputStreamReader(inputStream, "UTF-8"), 8); //pqoi taille=8??
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;

            /*while((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }*/
            while ((line = reader.readLine()) != null){
                stringBuilder.append(line);
            }
            inputStream.close();
            jSon =  stringBuilder.toString().substring(0,  stringBuilder.toString().length()-1);
        } catch (Exception e) {
            Log.e("JSON Parser", "Error converting java " + e.toString());
        }
        try{
            jsonObject = new JSONObject(jSon+"}");
        }
        catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return jsonObject;
    }
    public JSONObject makeHttpUrl(String url, List<BasicNameValuePair> params) {

        // Making HTTP request
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            String get = "";
            if(params!=null) {
                for (BasicNameValuePair item : params) {
                    get += item.getName() + "=" + URLEncoder.encode(item.getValue(), HTTP.UTF_8) + "&";
                }
            }
            if(!get.equals(""))
                get = get.substring(0, get.length()-1);

            httpPost.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
            HttpGet httpGet = new HttpGet(url+"?"+get);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            inputStream.close();
            Log.d("JSON_555", sb.toString());
            jSon = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jsonObject = new JSONObject(jSon);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return jsonObject;

    }

    public JSONObject getContent(String url){
        try {
            URL url1 = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) url1.openConnection();
            BufferedReader buff = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String input = "";
            String line;
            while ((line = buff.readLine())!=null){
                input += line+"\n";
            }
            buff.close();
            return new JSONObject(input);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}