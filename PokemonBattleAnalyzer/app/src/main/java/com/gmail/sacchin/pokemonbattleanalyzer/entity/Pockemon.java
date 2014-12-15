package com.gmail.sacchin.pokemonbattleanalyzer.entity;

import com.gmail.sacchin.pokemonlibrary.entity.*;

public class Pockemon extends Pokemon{
	private int resouceId = 0;
	private int rowId = 0;
	private int count = 0;
	
	public Pockemon(String no, String jname, String ename, int h, int a, int b, int c, int d, int s, 
			String ability1, String ability2, String abilityd, 
			int type1, int type2, float weight, int count) {
		super(no, jname, ename, h, a, b, c, d, s, ability1, ability2, abilityd, type1, type2, weight);
		this.count = count;
	}
	
	public int getResouceId() {
		return resouceId;
	}

	public void setResouceId(int resouceId) {
		this.resouceId = resouceId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}	
}