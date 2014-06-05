package com.gmail.sacchin.pockemonbattletools.entity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

public class JSONParser {
	public static ArrayList<PockemonSkill> createSkillList(JSONArray wazaInfo){
		ArrayList<PockemonSkill> result = new ArrayList<PockemonSkill>();
		if(wazaInfo == null || wazaInfo.length() == 0){
			return result;
		}
		for(int i = 0 ; i < wazaInfo.length() ; i++){
			try {
				PockemonSkill temp = PockemonSkill.createPockemonSkill(wazaInfo.getJSONObject(i));
				if(temp != null&& 2 < temp.getUsageRate()){
					result.add(temp);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static ArrayList<Ability> createAbilityList(JSONArray tokuseiInfo){
		ArrayList<Ability> result = new ArrayList<Ability>();
		if(tokuseiInfo == null || tokuseiInfo.length() == 0){
			return result;
		}
		for(int i = 0 ; i < tokuseiInfo.length() ; i++){
			try {
				Ability temp = Ability.createAbility(tokuseiInfo.getJSONObject(i));
				if(temp != null&& 2 < temp.getUsageRate()){
					result.add(temp);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static ArrayList<PockemonCharacteristic> createCharacteristicList(JSONArray seikakuInfo){
		ArrayList<PockemonCharacteristic> result = new ArrayList<PockemonCharacteristic>();
		if(seikakuInfo == null || seikakuInfo.length() == 0){
			return result;
		}
		for(int i = 0 ; i < seikakuInfo.length() ; i++){
			try {
				PockemonCharacteristic temp = PockemonCharacteristic.createCharacteristic(seikakuInfo.getJSONObject(i));
				if(temp != null&& 2 < temp.getUsageRate()){
					result.add(temp);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static ArrayList<Item> createItemList(JSONArray itemInfo){
		ArrayList<Item> result = new ArrayList<Item>();
		if(itemInfo == null || itemInfo.length() == 0){
			return result;
		}
		for(int i = 0 ; i < itemInfo.length() ; i++){
			try {
				Item temp = Item.createItem(itemInfo.getJSONObject(i));
				if(temp != null&& 2 < temp.getUsageRate()){
					result.add(temp);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
