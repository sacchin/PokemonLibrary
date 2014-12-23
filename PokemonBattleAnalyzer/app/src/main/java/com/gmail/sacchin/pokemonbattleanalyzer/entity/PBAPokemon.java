package com.gmail.sacchin.pokemonbattleanalyzer.entity;

import com.gmail.sacchin.pokemonlibrary.entity.*;

public class PBAPokemon extends Pokemon{
	private int resourceId = 0;
	private int rowId = 0;
	private int count = 0;
	
	public PBAPokemon(String no, String jname, String ename, int h, int a, int b, int c, int d, int s,
                      String ability1, String ability2, String abilityd,
                      int type1, int type2, float weight, int count) {
		super(no, jname, ename, h, a, b, c, d, s, ability1, ability2, abilityd, type1, type2, weight);
		this.count = count;
	}
	
	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
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
