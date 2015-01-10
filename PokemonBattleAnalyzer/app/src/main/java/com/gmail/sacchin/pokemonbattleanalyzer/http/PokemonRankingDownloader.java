package com.gmail.sacchin.pokemonbattleanalyzer.http;

import android.os.Handler;
import android.util.Log;

import com.gmail.sacchin.pokemonbattleanalyzer.PartyDatabaseHelper;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.JSONParser;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.pgl.RankingPokemonIn;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.pgl.RankingPokemonTrend;
import com.gmail.sacchin.pokemonbattleanalyzer.fragment.ToolFragment;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * MainActivityが開始した時に、全ポケモンの順位をダウンロードするクラス
 */
public class PokemonRankingDownloader implements Runnable {

    /**
     * PGLのURL
     */
    private final static String URL = "http://3ds.pokemon-gl.com/frontendApi/gbu/getSeasonPokemonDetail";

    PartyDatabaseHelper databaseHelper;

    public PokemonRankingDownloader(PartyDatabaseHelper databaseHelper){
        this.databaseHelper = databaseHelper;
    }

    public String doPostToMyServer() {
        return "";
    }

        @Override
    public void run() {
        String jsonStr = "";
        try {
            jsonStr = doPostToMyServer();
            JSONArray temp = new JSONArray(jsonStr);
            List<RankingPokemonIn> rankingList = JSONParser.createPokemonRankingList(temp);

            databaseHelper.updatePBAPokemonRanking(rankingList);
        } catch (JSONException e) {
            Log.e("PokemonRankingDownloader", "JSONException : " + jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
