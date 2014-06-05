package com.gmail.sacchin.pockemonbattletools.entity;

import org.json.JSONException;
import org.json.JSONObject;

public class Ability {
	private int ranking = 0;
	private double usageRate = 0;
	private String name = "";
	private int sequenceNumber = 0;

	public static Ability createAbility(JSONObject tokusei){
		try {
			String name = tokusei.getString("name");
			if(name == null || name.equals("null") ){
				return null;
			}
			return new Ability(tokusei.getInt("ranking"), tokusei.getDouble("usageRate"), tokusei.getString("name"), tokusei.getInt("sequenceNumber"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Ability(int ranking, double usageRate, String name, int sequenceNumber){
		this.ranking = ranking;
		this.usageRate = usageRate;
		this.name = name;
		this.sequenceNumber = sequenceNumber;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public double getUsageRate() {
		return usageRate;
	}

	public void setUsageRate(double usageRate) {
		this.usageRate = usageRate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

}
