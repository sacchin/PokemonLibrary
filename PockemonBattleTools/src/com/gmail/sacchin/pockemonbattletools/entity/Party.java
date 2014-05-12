package com.gmail.sacchin.pockemonbattletools.entity;

import java.sql.Timestamp;
import java.util.ArrayList;


public class Party {
	
	private Timestamp time = null;
	private ArrayList<IndividualPockemon> member = null;

	private String userName = null;
	private String memo = null;

	public Party(){
		this.member = new ArrayList<IndividualPockemon>();
	}

	public Party(Timestamp time, IndividualPockemon member1, IndividualPockemon member2,
			IndividualPockemon member3, IndividualPockemon member4, IndividualPockemon member5,
			IndividualPockemon member6, String memo, String userName) {
		this.time = time;
		this.member = new ArrayList<IndividualPockemon>();
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

	public ArrayList<IndividualPockemon> getMember() {
		return member;
	}

	public int setMember(IndividualPockemon pockemon) {
		if(this.member != null && this.member.size() < 6){
			this.member.add(pockemon);
			return this.member.size() - 1;
		}
		return -1;
	}
	
	public int removeMember(Pockemon pockemon){
		int index = -1;
		if(this.member != null && pockemon != null){
			for (int i = 0; i < this.member.size(); i++) {
				if(pockemon.getNo() == this.member.get(i).getNo()){
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
