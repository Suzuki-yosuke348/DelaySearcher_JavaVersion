package com.example.suzuy.delaysearcher;


import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.View;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<JSONArray> {

    private ArrayList<RailwayRoute> railwayRoutes;
    private ListView lv;
    private RailwayRoute route;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            //タップ時に路線名と会社名(String型)のデータを取得
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                route =((RailwayRoute)lv.getItemAtPosition(position));
                //Intent tweetIntent = new  Intent(MainActivity.this, TweetActivity.class);
                //Serialize型のデータをTweetActivityに受け渡す
                //tweetIntent.putExtra("ROUTE", route);
                //startActivity(tweetIntent);
            }
        });
        // JSONの取得
        getLoaderManager().restartLoader(1, null, this);
    }

    @Override
    public Loader<JSONArray> onCreateLoader(int id, Bundle args) {
        //APIを叩く
        String urlText = "https://tetsudo.rti-giken.jp/free/delay.json";
        JsonArrayLoader jsonArrayLoader = new JsonArrayLoader(this, urlText);
        jsonArrayLoader.forceLoad();
        return  jsonArrayLoader;
    }

    @Override
    public void onLoadFinished(Loader<JSONArray> loader, JSONArray data) {
        if (data != null) {
            try {
                railwayRoutes = new ArrayList<>();
                for (int i = 0; i < data.length(); i++)
                {
                    //JSONをリストに代入
                    JSONObject jsonObject = data.getJSONObject(i);
                    railwayRoutes.add(new RailwayRoute(jsonObject.getString("name"),
                            jsonObject.getString("company")));
                }
                Toast.makeText(this,"データを表示します。", Toast.LENGTH_SHORT).show();
                //ListViewに表示
                RouteAdapter adapter = new RouteAdapter(this, railwayRoutes);
                lv = (ListView)this.findViewById(R.id.listView);
                lv.setAdapter(adapter);
            } catch (JSONException e) {
                Log.d("onLoadFinished","JSONのパースに失敗しました。 JSONException=" + e);
            }
        }else{
            Log.d("onLoadFinished", "onLoadFinished error!");
        }
    }

    @Override
    public void onLoaderReset(Loader<JSONArray> loader) {
    }
}