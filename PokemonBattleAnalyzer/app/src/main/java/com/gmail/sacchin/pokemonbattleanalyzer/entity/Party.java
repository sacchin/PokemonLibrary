package com.gmail.sacchin.pokemonbattleanalyzer.entity;

import java.sql.Timestamp;
import java.util.ArrayList;


public class Party {
	
	private Timestamp time = null;
	private ArrayList<IndividualPBAPokemon> member = null;

	private String userName = null;
	private String memo = null;

	public Party(){
		this.member = new ArrayList<IndividualPBAPokemon>();
	}

	public Party(Timestamp time, IndividualPBAPokemon member1, IndividualPBAPokemon member2,
			IndividualPBAPokemon member3, IndividualPBAPokemon member4, IndividualPBAPokemon member5,
			IndividualPBAPokemon member6, String memo, String userName) {
		this.time = time;
		this.member = new ArrayList<IndividualPBAPokemon>();
		if(member1 != null){
			this.member.add(member1);
		}
		if(member2 != null){
		this.member.add(member2);
		}
		if(member3 != null){
		this.member.add(member3);
		}
		if(member4 != null){
		this.member.add(member4);
		}
		if(member5 != null){
		this.member.add(member5);
		}
		if(member6 != null){
		this.member.add(member6);
		}
		this.memo = memo;
		this.userName = userName;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public ArrayList<IndividualPBAPokemon> getMember() {
		return member;
	}

	public int setMember(IndividualPBAPokemon pockemon) {
		if(this.member != null && this.member.size() < 6){
			this.member.add(pockemon);
			return this.member.size() - 1;
		}
		return -1;
	}
	
	public int removeMember(PBAPokemon PBAPokemon){
		int index = -1;
		if(this.member != null && PBAPokemon != null){
			for (int i = 0; i < this.member.size(); i++) {
				if(PBAPokemon.getNo() == this.member.get(i).getNo()){
					index = i;
				}
			}
			this.member.remove(index);
			return index;
		}
		return -1;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMemo() {
		return memo;
	}
	
	public void clear(){
		if(member != null){
			member.clear();
		}
	}
}
