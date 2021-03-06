package com.gmail.sacchin.pokemonbattleanalyzer.entity;

import org.json.JSONException;
import org.json.JSONObject;

import com.gmail.sacchin.pokemonlibrary.entity.Skill;

public class PokemonSkill extends Skill{
	private double usageRate = 0;
	private int ranking = 0;
	private int sequenceNumber = 0;
	
	public static PokemonSkill createPockemonSkill(JSONObject waza){
		try {
			String name = waza.getString("name");
			if(name == null || name.equals("null") ){
				return null;
			}
			
			PokemonSkill obj = new PokemonSkill(0, waza.getString("name"), waza.getString("name"), waza.getInt("typeId"), 0, 0, 0, 0);
			obj.setRanking(waza.getInt("ranking"));
			obj.setUsageRate(waza.getDouble("usageRate"));
			obj.setSequenceNumber(waza.getInt("sequenceNumber"));
			return obj;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
			
	public PokemonSkill(int no, String name, String ename, int type, int power,
                        int accuracy, int category, int pp) {
		super(no, name, ename, type, power, accuracy, category, pp);
	}

	public double getUsageRate() {
		return usageRate;
	}

	public void setUsageRate(double usageRate) {
		this.usageRate = usageRate;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
}
