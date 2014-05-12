package com.gmail.sacchin.pockemonbattletools.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class IndividualPockemon extends Pockemon{

	private long id = 0;
	private Timestamp time = null;
	private String item = "";
	private String characteristic = null;
	private String skillNo1 = "";
	private String skillNo2 = "";
	private String skillNo3 = "";
	private String skillNo4 = "";
	private ArrayList<Entry<String, Integer>> itemStatistics = null;
	private ArrayList<Entry<String, Integer>> skillStatistics = null;

	public IndividualPockemon(Pockemon p, long id, Timestamp time, String item, String characteristic, 
			String skillNo1, String skillNo2, String skillNo3, String skillNo4){
		super(p.getNo(), p.getJname(), p.getEname(), p.getH(), p.getA(), p.getB(), p.getC(), p.getD(), p.getS(), p.getCharacteristic1(), 
				p.getCharacteristic2(), p.getCharacteristicd(), 0);
		setRowId(p.getRowId());
		this.id = id;
		this.time = time;
		this.item = item;
		this.characteristic = characteristic;
		this.skillNo1 = skillNo1;
		this.skillNo2 = skillNo2;
		this.skillNo3 = skillNo3;
		this.skillNo4 = skillNo4;
	}

	public IndividualPockemon(String no, String Çäname, String ename, int h, int a,
			int b, int c, int d, int s, String characteristic1,
			String characteristic2, String characteristicd, long id, Timestamp time, String item, String characteristic, 
			String skillNo1, String skillNo2, String skillNo3, String skillNo4){
		super(no, Çäname, ename, h, a, b, c, d, s, characteristic1, characteristic2,
				characteristicd, 0);
		this.id = id;
		this.time = time;
		this.item = item;
		this.characteristic = characteristic;
		this.skillNo1 = skillNo1;
		this.skillNo2 = skillNo2;
		this.skillNo3 = skillNo3;
		this.skillNo4 = skillNo4;
	}
	
	private List<Entry<String, Integer>> valueSort(Map<String, Integer> statistics) {
		List<Entry<String, Integer>> entries = new ArrayList<Entry<String, Integer>>(statistics.entrySet());
		Collections.sort(entries, new Comparator<Entry<String, Integer>>(){
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2){
				return ((Integer)o2.getValue()).compareTo((Integer)o1.getValue());//ç~èá
			}
		});
		return entries;
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Timestamp getTime() {
		return time;
	}


	public void setTime(Timestamp time) {
		this.time = time;
	}


	public String getItem() {
		return item;
	}


	public void setItem(String item) {
		this.item = item;
	}


	public String getCharacteristic() {
		return characteristic;
	}


	public void setCharacteristic(String characteristic) {
		this.characteristic = characteristic;
	}


	public String getSkillNo1() {
		return skillNo1;
	}


	public void setSkillNo1(String skillNo1) {
		this.skillNo1 = skillNo1;
	}


	public String getSkillNo2() {
		return skillNo2;
	}


	public void setSkillNo2(String skillNo2) {
		this.skillNo2 = skillNo2;
	}


	public String getSkillNo3() {
		return skillNo3;
	}


	public void setSkillNo3(String skillNo3) {
		this.skillNo3 = skillNo3;
	}


	public String getSkillNo4() {
		return skillNo4;
	}


	public void setSkillNo4(String skillNo4) {
		this.skillNo4 = skillNo4;
	}

	public void setItemStatistics(Map<String, Integer> itemStatistics) {
		ArrayList<Entry<String, Integer>> entries = new ArrayList<Entry<String, Integer>>();
		for(Entry<String, Integer> temp : valueSort(itemStatistics)){
			entries.add(temp);
		}

		this.itemStatistics = entries;
	}

	public Entry<String, Integer> getSkillStatictics(int i){
		if(skillStatistics != null && i < skillStatistics.size() ){
			return skillStatistics.get(i);
		}
		return null;
	}

	public Entry<String, Integer> getItemStatictics(int i){
		if(itemStatistics != null && i < itemStatistics.size() ){
			return itemStatistics.get(i);
		}
		return null;
	}

	public ArrayList<Entry<String, Integer>> getSkillStatistics() {
		return skillStatistics;
	}

	public void setSkillStatistics(Map<String, Integer> skillStatistics) {
		ArrayList<Entry<String, Integer>> entries = new ArrayList<Entry<String, Integer>>();
		for(Entry<String, Integer> temp : valueSort(skillStatistics)){
			entries.add(temp);
		}
		this.skillStatistics = entries;
	}
}
