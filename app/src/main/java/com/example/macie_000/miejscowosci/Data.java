package com.example.macie_000.miejscowosci;

import android.util.JsonReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by macie_000 on 2015-05-31.
 */
public class Data {

    public static ArrayList<Geonames> Get(String sUrl){

        InputStream oInputStream = null;
        ArrayList<Geonames> geonamesArrayList = new ArrayList<Geonames>();
        Geonames oGeonames = null;

        try{

            DefaultHttpClient oDefaulHttpClient = new DefaultHttpClient();
            HttpPost oHttpPost = new HttpPost(sUrl);
            HttpResponse oHttpResponse = oDefaulHttpClient.execute(oHttpPost);
            HttpEntity oHttpEntity = oHttpResponse.getEntity();
            oInputStream = oHttpEntity.getContent();

            BufferedReader oBufferedReader = new BufferedReader(new InputStreamReader(
                    oInputStream, "UTF-8"),8);
            StringBuilder oStringBuilder = new StringBuilder();
            String sLine = "";
            while ((sLine = oBufferedReader.readLine()) != null){
                oStringBuilder.append(sLine);
            }
            oInputStream.close();
            JSONObject oJSONObject = new JSONObject(oStringBuilder.toString());
            JSONArray oJSONArray = oJSONObject.getJSONArray("postalcodes");

            for(int i=0; i<oJSONArray.length();i++){
                JSONObject object = oJSONArray.getJSONObject(i);
                geonamesArrayList.add(new Geonames(object.getString("postalcode"), object.getString("placeName"),
                        object.getString("adminName1")));
            }
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        finally {
            return geonamesArrayList;
        }
    }

}
