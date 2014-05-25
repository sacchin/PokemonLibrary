package com.gmail.sacchin.pokemonlibrary.entity;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {
	/**
	 * nnn-mm
	 */
	private String no = "";
	private String jname;
	private String ename;
	private int type1;
	private int type2;
	private float weight;
	private int h;
	private int a;
	private int b;
	private int c;
	private int d;
	private int s;
	private String characteristic1;
	private String characteristic2;
	private String characteristicd;
	private List<Pokemon> mega = null;

	/**
	 * 
	 * @param no
	 * @param ‚Šname
	 * @param ename
	 * @param h
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param s
	 * @param characteristic1
	 * @param characteristic2
	 * @param characteristicd
	 * @param type1
	 * @param type2
	 * @param weight
	 */
	public Pokemon(String no, String ‚Šname, String ename, int h, int a, int b, int c, int d, int s, 
			String characteristic1, String characteristic2, String characteristicd, int type1, int type2, float weight) {
		this.no = no;
		this.jname = ‚Šname;
		this.ename = ename;
		this.h = h;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.s = s;
		this.characteristic1 = characteristic1;
		this.characteristic2 = characteristic2;
		this.characteristicd = characteristicd;
		this.type1 = type1;
		this.type2 = type2;
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

	public int getType1() {
		return type1;
	}

	public int getType2() {
		return type2;
	}

	public float getWeight() {
		return weight;
	}

	public int getH() {
		return h;
	}

	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}

	public int getC() {
		return c;
	}

	public int getD() {
		return d;
	}

	public int getS() {
		return s;
	}

	public String getCharacteristic1() {
		return characteristic1;
	}

	public String getCharacteristic2() {
		return characteristic2;
	}

	public String getCharacteristicd() {
		return characteristicd;
	}

	public List<Pokemon> getMega() {
		return mega;
	}
}
