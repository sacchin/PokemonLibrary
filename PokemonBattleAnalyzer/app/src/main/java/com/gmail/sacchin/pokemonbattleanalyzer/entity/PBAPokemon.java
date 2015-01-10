package com.gmail.sacchin.pokemonbattleanalyzer.entity;

import com.gmail.sacchin.pokemonlibrary.entity.*;

public class PBAPokemon extends Pokemon{
	private int resourceId = 0;
	private int rowId = 0;
	private int ranking = 0;
	
	public PBAPokemon(String no, String jname, String ename, int h, int a, int b, int c, int d, int s,
                      String ability1, String ability2, String abilityd,
                      int type1, int type2, float weight, int ranking) {
		super(no, jname, ename, h, a, b, c, d, s, ability1, ability2, abilityd, type1, type2, weight);
		this.ranking = ranking;
	}
	
	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	
	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}	
}
