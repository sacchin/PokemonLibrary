package com.gmail.sacchin.pockemonbattletools.http;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gmail.sacchin.pockemonbattletools.ToolActivity;
import com.gmail.sacchin.pockemonbattletools.entity.pgl.RankingPokemonTrend;

import android.util.Log;

public class PGLGetter implements Runnable {

	private final static String URL = "http://3ds.pokemon-gl.com/frontendApi/gbu/getSeasonPokemonDetail";
	
	private String pockemonNo = "";
	private int index = 0;
	private ToolActivity activity = null;
	
	public PGLGetter (String pockemonNo, int index, ToolActivity activity){
		this.pockemonNo = pockemonNo;
		this.index = index;
		this.activity = activity;
	}

	public String doPost(String pockemonNo) {
		StringBuilder result = new StringBuilder();

		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost method = new HttpPost(URL);
		method.setHeader("Referer", "http://3ds.pokemon-gl.com/battle/");

		List<NameValuePair> params = createParameter(pockemonNo);
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
		params.add(new BasicNameValuePair("pokemonId", pockemonNo + "-0"));
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
		Log.v("PGLGetter[" + index + "]", "posting " + pockemonNo + "-0");
		String jsonStr = "";
		try {
			jsonStr = doPost(pockemonNo);
			if(jsonStr != null && !jsonStr.isEmpty()){
				JSONObject j = new JSONObject(jsonStr);
				
//				JSONArray rankingPokemonSuffererWaza = j.getJSONArray("rankingPokemonSuffererWaza");
//				JSONArray rankingPokemonSufferer = j.getJSONArray("rankingPokemonSufferer");
//				JSONArray rankingPokemonIn = j.getJSONArray("rankingPokemonIn");
//				JSONArray rankingPokemonDownWaza = j.getJSONArray("rankingPokemonDownWaza");
//				JSONArray rankingPokemonDown = j.getJSONArray("rankingPokemonDown");
//				JSONObject rankingPokemonInfo = j.getJSONObject("rankingPokemonInfo");
//				JSONObject rankingPokemonDownWazaOther = j.getJSONObject("rankingPokemonDownWazaOther");
				
				JSONObject rankingPokemonTrend = j.getJSONObject("rankingPokemonTrend");
				RankingPokemonTrend trend = RankingPokemonTrend.createRankingPokemonTrend(rankingPokemonTrend);
				activity.finishDownload(index, trend);
			}
		} catch (JSONException e) {
			Log.e("PGLGetter[" + index + "]", "JSONException : " + jsonStr);
		}
	}
}
