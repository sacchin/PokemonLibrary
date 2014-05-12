package com.gmail.sacchin.pockemonbattletools.entity;

import java.util.ArrayList;
import java.util.List;

public class Pockemon {
	private int resouceId = 0;
	private int rowId = 0;
	private String no = "";
	private String jname;
	private String ename;
	private int h;
	private int a;
	private int b;
	private int c;
	private int d;
	private int s;
	private String characteristic1;
	private String characteristic2;
	private String characteristicd;
	private int count = 0;
	private List<Pockemon> mega = null;
	
	public Pockemon(String no, String ‚Šname, String ename, int h, int a, int b, int c, int d, int s, 
			String characteristic1, String characteristic2, String characteristicd, int count) {
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
		this.count = count;
	}
	
	public int getResouceId() {
		return resouceId;
	}

	public void setResouceId(int resouceId) {
		this.resouceId = resouceId;
	}

	public String getNo() {
		return no;
	}
	
	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

	public String getCharacteristic1() {
		return characteristic1;
	}

	public void setCharacteristic1(String characteristic1) {
		this.characteristic1 = characteristic1;
	}

	public String getCharacteristic2() {
		return characteristic2;
	}

	public void setCharacteristic2(String characteristic2) {
		this.characteristic2 = characteristic2;
	}

	public String getCharacteristicd() {
		return characteristicd;
	}

	public void setCharacteristicd(String characteristicd) {
		this.characteristicd = characteristicd;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getJname() {
		return jname;
	}

	public void setJname(String jname) {
		this.jname = jname;
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

	public List<Pockemon> getMega() {
		return mega;
	}

	public void addMega(Pockemon mega) {
		if(this.mega == null){
			this.mega = new ArrayList<Pockemon>();
		}
		this.mega.add(mega);
	}
	
}
