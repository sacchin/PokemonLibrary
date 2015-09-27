package com.gmail.sacchin.pokemonbattleanalyzer.entity;

import org.json.JSONException;
import org.json.JSONObject;

import com.gmail.sacchin.pokemonlibrary.entity.Characteristic;

import java.util.HashMap;
import java.util.Map;

public class PokemonCharacteristic extends Characteristic{
	private int ranking = 0;
	private double usageRate = 0;
	private String name = "";
	private int sequenceNumber = 0;
	public static final String[] CHARACTERISTIC = {
			"さみしがり",
			"いじっぱり",
			"やんちゃ",
			"ゆうかん",
			"ずぶとい",
			"わんぱく",
			"のうてんき",
			"のんき",
			"ひかえめ",
			"おっとり",
			"うっかりや",
			"れいせい",
			"おだやか",
			"おとなしい",
			"しんちょう",
			"なまいき",
			"おくびょう",
			"せっかち",
			"ようき",
			"むじゃき",
			"てれや",
			"がんばりや",
			"すなお",
			"きまぐれ",
			"まじめ"
	};

	public static PokemonCharacteristic createCharacteristic(JSONObject seikaku){
		try {
			String name = seikaku.getString("name");
			if(name == null || name.equals("null") ){
				return null;
			}
			return new PokemonCharacteristic(seikaku.getInt("ranking"), seikaku.getDouble("usageRate"), seikaku.getString("name"), seikaku.getInt("sequenceNumber"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public PokemonCharacteristic(int ranking, double usageRate, String name, int sequenceNumber){
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

	public float[] getRevision(){
		return Characteristic.CHARACTERRISTIC_TABLE[Characteristic.convertCharacteristicNameToNo(name)];
	}
}
