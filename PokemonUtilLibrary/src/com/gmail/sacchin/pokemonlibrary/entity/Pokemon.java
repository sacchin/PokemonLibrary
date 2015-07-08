package com.gmail.sacchin.pokemonlibrary.entity;

import java.util.ArrayList;
import java.util.List;

import com.gmail.sacchin.pokemonlibrary.entity.Type.TypeCode;

public abstract class Pokemon {
	/**
	 * nnn-mm
	 */
	private String no = "";
	private String jname;
	private String ename;
	private TypeCode type1;
	private TypeCode type2;
	private float weight;
	private int h;
	private int a;
	private int b;
	private int c;
	private int d;
	private int s;
	private String ability1;
	private String ability2;
	private String abilityd;
	private List<Pokemon> mega = null;

	/**
	 * 
	 * @param no
	 * @param jname
	 * @param ename
	 * @param h
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param s
	 * @param ability1
	 * @param ability2
	 * @param abilityd
	 * @param type1
	 * @param type2
	 * @param weight
	 */
	public Pokemon(String no, String jname, String ename, int h, int a, int b, int c, int d, int s, 
			String ability1, String ability2, String abilityd, int type1, int type2, float weight) {
		this.no = no;
		this.jname = jname;
		this.ename = ename;
		this.h = h;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.s = s;
		this.ability1 = ability1;
		this.ability2 = ability2;
		this.abilityd = abilityd;
		this.type1 = Type.convertNoToTypeCode(type1);
		this.type2 = Type.convertNoToTypeCode(type2);
		this.weight = weight;
		this.mega = new ArrayList<Pokemon>();
	}

	/**
	 * 
	 * @param mega
	 */
	public void addMega(Pokemon mega) {
		if(this.mega == null){
			this.mega = new ArrayList<Pokemon>();
		}
		this.mega.add(mega);
	}

	public String getNo() {
		return no;
	}

	public String getJname() {
		return jname;
	}

	public String getEname() {
		return ename;
	}

	public TypeCode getType1() {
		return type1;
	}

	public TypeCode getType2() {
		return type2;
	}

	public float getWeight() {
		return weight;
	}

	public int getHPValue(int iv, int ev) {
		return ((h * 2 + iv + ev / 4 ) / 2) + 60;
	}
	
	public int getAttackValue(int iv, int ev) {
		return (int)((((a*2+iv+ev/4)/2)+5) * getAttackRevision());
	}

	public int getDeffenceValue(int iv, int ev) {
		return (int)((((b*2+iv+ev/4)/2)+5) * getDeffenceRevision());
	}

	public int getSpecialAttackValue(int iv, int ev) {
		return (int)((((c*2+iv+ev/4)/2)+5) * getSpecialAttackRevision());
	}

	public int getSpecialDeffenceValue(int iv, int ev) {
		return (int)((((d*2+iv+ev/4)/2)+5) * getSpecialDeffenceRevision());
	}

	public int getSpeedValue(int iv, int ev) {
		return (int)((((s*2+iv+ev/4)/2)+5) * getSpeedRevision());
	}
	
	public abstract float getAttackRevision();
	public abstract float getDeffenceRevision();
	public abstract float getSpecialAttackRevision();
	public abstract float getSpecialDeffenceRevision();
	public abstract float getSpeedRevision();

	public String getAbility1() {
		return ability1;
	}

	public String getAbility2() {
		return ability2;
	}

	public String getAbilityd() {
		return abilityd;
	}

	public List<Pokemon> getMega() {
		return mega;
	}
}
