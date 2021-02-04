package com.example.suzuy.delaysearcher;

/**
 * Created by suzuy on 2017/01/18.
 */

import android.content.AsyncTaskLoader;
import org.json.JSONArray;
import org.json.JSONException;
import android.content.Context;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonArrayLoader extends AsyncTaskLoader<JSONArray> {

    private String urlText;



    public JsonArrayLoader(Context context, String urlText){

        super(context);

        this.urlText = urlText;

    }



    @Override

    public JSONArray loadInBackground(){

        HttpURLConnection connection = null;

        try{

            URL url = new URL(urlText);

            connection = (HttpURLConnection)url.openConnection();

            connection.setRequestMethod("GET");

            connection.connect();

        }

        catch (MalformedURLException exception){

            // 処理なし

        }

        catch (IOException exception){

            // 処理なし

        }



        try{

            BufferedInputStream inputStream = new BufferedInputStream(connection.getInputStream());

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];

            int length;

            while ((length = inputStream.read(buffer)) != -1){

                if (length > 0){

                    outputStream.write(buffer, 0, length);

                }

            }



            JSONArray json = new JSONArray(new String(outputStream.toByteArray()));

            return json;

        }

        catch (IOException exception){

            // 処理なし

        }

        catch (JSONException e) {

            e.printStackTrace();

        }

        return null;

    }

}
