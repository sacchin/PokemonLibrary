package com.gmail.sacchin.pokemonbattleanalyzer.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.gmail.sacchin.pokemonbattleanalyzer.fragment.ToolFragment;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.pgl.RankingPokemonTrend;

import android.os.Handler;
import android.util.Log;

public class PGLGetter implements Runnable {

    private final static String URL = "http://3ds.pokemon-gl.com/frontendApi/gbu/getSeasonPokemonDetail";

    private String pokemonNo = "";
    private int index = 0;
    private Handler handler = null;
    private RankingPokemonTrend trend = null;
    private ToolFragment fragment = null;

    public PGLGetter (String pokemonNo, ToolFragment fragment, int index, Handler handler){
        this.pokemonNo = pokemonNo;
        this.index = index;
        this.handler = handler;
        this.fragment = fragment;
    }

    public String doPost(String pokemonNo) {
        StringBuilder result = new StringBuilder();

        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost method = new HttpPost(URL);
        method.setHeader("Referer", "http://3ds.pokemon-gl.com/battle/");

        List<NameValuePair> params = createParameter(pokemonNo);
        try {
            method.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            HttpResponse response = client.execute(method);

            switch (response.getStatusLine().getStatusCode()) {
                case HttpStatus.SC_OK:
                    result.append(EntityUtils.toString(response.getEntity(), "UTF-8"));
                    break;
                case HttpStatus.SC_NOT_FOUND:
                    Log.e("doPost", "HttpStatus.SC_NOT_FOUND");
                    break;
                default:
                    break;
            }
        } catch (ClientProtocolException e) {
            Log.e("doPost", e.getMessage());
        } catch (IOException e) {
            Log.e("doPost", e.getMessage());
        } catch (Exception e) {
            Log.e("doPost", e.getMessage());
        }
        return result.toString();
    }

    private List<NameValuePair> createParameter(String pockemonNo) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("languageId", "1"));
        params.add(new BasicNameValuePair("seasonId", "4"));
        params.add(new BasicNameValuePair("battleType", "0"));
        params.add(new BasicNameValuePair("timezone", "JST"));
        params.add(new BasicNameValuePair("pokemonId", pockemonNo));
        params.add(new BasicNameValuePair("displayNumberWaza", "10"));
        params.add(new BasicNameValuePair("displayNumberTokusei", "3"));
        params.add(new BasicNameValuePair("displayNumberSeikaku", "10"));
        params.add(new BasicNameValuePair("displayNumberItem", "10"));
        params.add(new BasicNameValuePair("displayNumberLevel", "10"));
        params.add(new BasicNameValuePair("displayNumberPokemonIn", "10"));
        params.add(new BasicNameValuePair("timeStamp", String.valueOf(System.currentTimeMillis())));
        return params;
    }

    @Override
    public void run() {
        Log.v("PGLGetter[" + index + "]", "posting " + pokemonNo);
        String jsonStr = "";
        try {
            jsonStr = doPost(pokemonNo);
            if(jsonStr != null && !jsonStr.isEmpty()){
                JSONObject j = new JSONObject(jsonStr);
                JSONObject rankingPokemonTrend = j.getJSONObject("rankingPokemonTrend");
                trend = RankingPokemonTrend.createRankingPokemonTrend(rankingPokemonTrend);
                fragment.finishDownload(index, trend);
            }
        } catch (JSONException e) {
            Log.e("PGLGetter[" + index + "]", "JSONException : " + jsonStr);
        }
        if(index == 0){
            handler.post(new Runnable() {
                public void run() {
                    fragment.setTrend();
                }
            });
        }
    }
}
