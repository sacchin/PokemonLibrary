package com.gmail.sacchin.pokemonlibrary.entity;

public class Skill {
	private int no;
	private String jname;
	private String ename;
	private int type;
	private int power;
	private int accuracy;
	private int category;
	private int pp;
	
	/**
	 * 
	 * @param no
	 * @param ‚Šname
	 * @param ename
	 * @param type
	 * @param power
	 * @param accuracy
	 * @param category
	 * @param pp
	 */
	public Skill(int no, String ‚Šname, String ename, int type, int power, int accuracy, int category, int pp) {
		this.no = no;
		this.jname = ‚Šname;
		this.ename = ename;
		this.type = type;
		this.power = power;
		this.accuracy = accuracy;
		this.category = category;
		this.pp = pp;
	}

	public int getNo() {
		return no;
	}

	public String getJname() {
		return jname;
	}

	public String getEname() {
		return ename;
	}

	public int getType() {
		return type;
	}

	public int getPower() {
		return power;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public int getCategory() {
		return category;
	}

	public int getPp() {
		return pp;
	}		
}
