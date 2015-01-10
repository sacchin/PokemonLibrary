package com.gmail.sacchin.pokemonbattleanalyzer.entity;

import com.gmail.sacchin.pokemonbattleanalyzer.entity.pgl.RankingPokemonIn;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

public class JSONParser {
	public static List<PokemonSkill> createSkillList(JSONArray wazaInfo){
		List<PokemonSkill> result = new ArrayList<>();
		if(wazaInfo == null || wazaInfo.length() == 0){
			return result;
		}
		for(int i = 0 ; i < wazaInfo.length() ; i++){
			try {
				PokemonSkill temp = PokemonSkill.createPockemonSkill(wazaInfo.getJSONObject(i));
				if(temp != null&& 2 < temp.getUsageRate()){
					result.add(temp);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static List<Ability> createAbilityList(JSONArray tokuseiInfo){
		List<Ability> result = new ArrayList<>();
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
	
	public static List<PokemonCharacteristic> createCharacteristicList(JSONArray seikakuInfo){
		List<PokemonCharacteristic> result = new ArrayList<>();
		if(seikakuInfo == null || seikakuInfo.length() == 0){
			return result;
		}
		for(int i = 0 ; i < seikakuInfo.length() ; i++){
			try {
				PokemonCharacteristic temp = PokemonCharacteristic.createCharacteristic(seikakuInfo.getJSONObject(i));
				if(temp != null&& 2 < temp.getUsageRate()){
					result.add(temp);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static List<Item> createItemList(JSONArray itemInfo){
		List<Item> result = new ArrayList<>();
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

    public static List<RankingPokemonIn> createPokemonRankingList(JSONArray pbaPokemonRankingInfo){
        List<RankingPokemonIn> result = new ArrayList<>();
        if(pbaPokemonRankingInfo == null || pbaPokemonRankingInfo.length() == 0){
            return result;
        }
        for(int i = 0 ; i < pbaPokemonRankingInfo.length() ; i++){
            try {
                RankingPokemonIn temp = RankingPokemonIn.createRankingPokemon(pbaPokemonRankingInfo.getJSONObject(i));
                result.add(temp);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}

