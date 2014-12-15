package com.gmail.sacchin.pokemonbattleanalyzer.entity.pgl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gmail.sacchin.pokemonbattleanalyzer.entity.Ability;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.Item;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.JSONParser;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.PockemonCharacteristic;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.PockemonSkill;

public class RankingPokemonTrend {
	//	{"wazaInfo":[{"ranking":1,"typeId":9,"usageRate":63.333333333333336,"name":"","sequenceNumber":1},
	//	"seikakuInfo":[{"ranking":1,"usageRate":66.66666666666667,"name":"","sequenceNumber":1},
	//	"tokuseiInfo":[{"ranking":1,"usageRate":93.33333333333333,"name":"","sequenceNumber":1},
	//	"itemInfo":[{"ranking":1,"usageRate":26.666666666666668,"name":"","sequenceNumber":1},

	private ArrayList<PockemonSkill> skillList = null;
	private ArrayList<Ability> abilityList = null;
	private ArrayList<PockemonCharacteristic> characteristicList = null;
	private ArrayList<Item> itemList = null;


	public static RankingPokemonTrend createRankingPokemonTrend(JSONObject rankingPokemonTrend){
		try {
			JSONArray wazaInfo = rankingPokemonTrend.getJSONArray("wazaInfo");
			JSONArray seikakuInfo = rankingPokemonTrend.getJSONArray("seikakuInfo");
			JSONArray tokuseiInfo = rankingPokemonTrend.getJSONArray("tokuseiInfo");
			JSONArray itemInfo = rankingPokemonTrend.getJSONArray("itemInfo");
			return new RankingPokemonTrend (wazaInfo, seikakuInfo, tokuseiInfo, itemInfo);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public RankingPokemonTrend(JSONArray wazaInfo, JSONArray seikakuInfo, JSONArray tokuseiInfo, JSONArray itemInfo){
		skillList = JSONParser.createSkillList(wazaInfo);
		abilityList = JSONParser.createAbilityList(tokuseiInfo);
		characteristicList = JSONParser.createCharacteristicList(seikakuInfo);
		itemList = JSONParser.createItemList(itemInfo);
	}

	public ArrayList<PockemonSkill> getSkillList() {
		return skillList;
	}

	public ArrayList<Ability> getAbilityList() {
		return abilityList;
	}

	public ArrayList<PockemonCharacteristic> getCharacteristicList() {
		return characteristicList;
	}

	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public TreeMap<String, String[]> createSkillMap(){
		HashMap<String, String[]> result = new HashMap<String, String[]>();
		for(PockemonSkill pockemonSkill : skillList){
			if(pockemonSkill.getJname() == null || pockemonSkill.getJname().isEmpty()){
				continue;
			}

			String[] temp = new String[2];
			temp[0] = pockemonSkill.getJname();
			temp[1] = String.valueOf((int)pockemonSkill.getUsageRate());
			result.put(String.valueOf(pockemonSkill.getSequenceNumber()), temp);
		}
		TreeMap<String, String[]> treeMap =
				new TreeMap<String, String[]>(new UsageMapComparator(result));
				treeMap.putAll(result);

				return treeMap;
	}
	public TreeMap<String, String[]> createAbilityMap(){
		HashMap<String, String[]> result = new HashMap<String, String[]>();
		for(Ability ability : abilityList){
			if(ability.getName() == null || ability.getName().isEmpty()){
				continue;
			}
			String[] temp = new String[2];
			temp[0] = ability.getName();
			temp[1] = String.valueOf((int)ability.getUsageRate());
			result.put(String.valueOf(ability.getSequenceNumber()), temp);
		}
		TreeMap<String, String[]> treeMap =
				new TreeMap<String, String[]>(new UsageMapComparator(result));
				treeMap.putAll(result);

				return treeMap;
	}
	public TreeMap<String, String[]> createCharacteristicMap(){
		HashMap<String, String[]> result = new HashMap<String, String[]>();
		for(PockemonCharacteristic pockemonCharacteristic : characteristicList){
			if(pockemonCharacteristic.getName() == null || pockemonCharacteristic.getName().isEmpty()){
				continue;
			}
			String[] temp = new String[2];
			temp[0] = pockemonCharacteristic.getName();
			temp[1] = String.valueOf((int)pockemonCharacteristic.getUsageRate());
			result.put(String.valueOf(pockemonCharacteristic.getSequenceNumber()), temp);
		}
		TreeMap<String, String[]> treeMap =
				new TreeMap<String, String[]>(new UsageMapComparator(result));
				treeMap.putAll(result);

				return treeMap;
	}
	public TreeMap<String, String[]> createItemMap(){
		HashMap<String, String[]> result = new HashMap<String, String[]>();
		for(Item item : itemList){
			if(item.getName() == null || item.getName().isEmpty()){
				continue;
			}
			String[] temp = new String[2];
			temp[0] = item.getName();
			temp[1] = String.valueOf((int)item.getUsageRate());
			result.put(String.valueOf(item.getSequenceNumber()), temp);
		}
		TreeMap<String, String[]> treeMap =
				new TreeMap<String, String[]>(new UsageMapComparator(result));
				treeMap.putAll(result);

				return treeMap;
	}


	class UsageMapComparator implements Comparator<String> {
		private Map<String, String[]> map;
		public UsageMapComparator(Map<String, String[]>  map) {
			this.map = map;
		}
		public int compare(String key1, String key2) {
			try {
				int value1 = Integer.parseInt(map.get(key1)[1]);
				int value2 = Integer.parseInt(map.get(key2)[1]);
				if(value1 == value2){
					return key1.toLowerCase().compareTo(key2.toLowerCase());
				}else if(value1 < value2){
					return 1;
				}else{
					return -1;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return 0;
			}
		}
	}
}
