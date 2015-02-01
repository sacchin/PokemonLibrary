package com.gmail.sacchin.pokemonbattleanalyzer.entity;

import android.util.Log;

import com.gmail.sacchin.pokemonlibrary.entity.Type;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Ability {
    private static Map<Type.TypeCode, String[]> invalidAbility;
    private static Map<Type.TypeCode, String[]> scalUpAbility;
    private static final String[] ELECTRIC_INVALID_ABILITY = {
            "ちくでん",
            "でんきエンジン",
            "ひらいしん"};

    private static final String[] WATER_INVALID_ABILITY = {
            "かんそうはだ",
            "ちょすい",
            "よびみず"};

    private static final String[] GRASS_INVALID_ABILITY = {
            "そうしょく"};

    private static final String[] FIRE_INVALID_ABILITY = {
            "もらいび"};

    private static final String[] GRAND_INVALID_ABILITY = {
            "ふゆう"};

    private static final String[] FIRE_SCALE_UP_ABILITY = {
            "かんそうはだ"};

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

	public double getUsageRate() {
		return usageRate;
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

    public static float calcTypeScale(String ability, Type.TypeCode type){
        if(ability == null || type == null){
            throw new NullPointerException();
        }

        invalidAbility = new HashMap<>();
        invalidAbility.put(Type.TypeCode.ELECTRIC, ELECTRIC_INVALID_ABILITY);
        invalidAbility.put(Type.TypeCode.WATER, WATER_INVALID_ABILITY);
        invalidAbility.put(Type.TypeCode.GRASS, GRASS_INVALID_ABILITY);
        invalidAbility.put(Type.TypeCode.FIRE, FIRE_INVALID_ABILITY);
        invalidAbility.put(Type.TypeCode.GROUND, GRAND_INVALID_ABILITY);
        scalUpAbility = new HashMap<>();
        scalUpAbility.put(Type.TypeCode.FIRE, FIRE_SCALE_UP_ABILITY);

        String[] list = invalidAbility.get(type);
        if(list != null) {
            for(String temp : list){
                if(temp.equals(ability)){
                    return 0;
                }
            }
        }

        list = scalUpAbility.get(type);
        if(list != null) {
            for(String temp : list){
                if(temp.equals(ability)){
                    return 1.25f;
                }
            }
        }
        return 1;
    }
}
