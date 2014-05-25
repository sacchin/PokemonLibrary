package com.gmail.sacchin.pockemonbattletools.entity;

import org.json.JSONException;
import org.json.JSONObject;

public class Tokusei {
	int ranking = 0;
	double usageRate = 0;
	String name = "";

	public Tokusei(JSONObject tokusei){
		try {
			this.ranking = tokusei.getInt("ranking");
			this.usageRate = tokusei.getDouble("usageRate");
			this.name = tokusei.getString("name");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
