package com.gmail.sacchin.pokemonlibrary.entity;

public class Characteristic {
	public static final int SAMISIGARI = 0;
	public static final int IJIPPARI = 1;
	public static final int YANCHA = 2;
	public static final int YUKAN = 3;
	public static final int ZUBUTOI = 4;
	public static final int WANPAKU = 5;
	public static final int NOTENKI = 6;
	public static final int NONKI = 7;
	public static final int HIKAEME = 8;	
	public static final int OTTORI = 9;
	public static final int UKKARIYA = 10;
	public static final int REISEI = 11;	
	public static final int ODAYAKA = 12;
	public static final int OTONASI = 13;
	public static final int SINCHO = 14;
	public static final int NAMAIKI = 15;
	public static final int OKUBYO = 16;
	public static final int SEKKATI = 17;
	public static final int YOKI = 18;
	public static final int MUJAKI = 19;
	public static final int TEREYA = 20;
	public static final int GANBAIYA = 21;
	public static final int SUNAO = 22;
	public static final int KIMAGURE = 23;
	public static final int MAJIME = 24;
	
	public static float CHARACTERRISTIC_TABLE[][] = 
		{
		{1.1f,0.9f,1,1,1},
		{1.1f,1,0.9f,1,1},
		{1.1f,1,1,0.9f,1},
		{1.1f,1,1,1,0.9f},
		{0.9f,1.1f,1,1,1},
		{1,1.1f,0.9f,1,1},
		{1,1.1f,1,0.9f,1},
		{1,1.1f,1,1,0.9f},
		{0.9f,1,1.1f,1,1},
		{1,0.9f,1.1f,1,1},
		{1,1,1.1f,0.9f,1},
		{1,1,1.1f,0.9f,1},
		{0.9f,1,1,1.1f,1},
		{1,0.9f,1,1.1f,1},
		{1,1,0.9f,1.1f,1},
		{1,1,1,1.1f,0.9f},
		{0.9f,1,1,1,1.1f},
		{1,0.9f,1,1,1.1f},
		{1,1,0.9f,1,1.1f},
		{1,1,1,0.9f,1.1f},
		{1,1,1,1,1}
		};

	public static int convertCharacteristicNameToNo(String skillName){
		if("さみしがり".equals(skillName)){
			return SAMISIGARI;
		}else if("いじっぱり".equals(skillName)){
			return IJIPPARI;
		}else if("やんちゃ".equals(skillName)){
			return YANCHA;
		}else if("ゆうかん".equals(skillName)){
			return YUKAN;
		}else if("ずぶとい".equals(skillName)){
			return ZUBUTOI;
		}else if("わんぱく".equals(skillName)){
			return WANPAKU;
		}else if("のうてんき".equals(skillName)){
			return NOTENKI;
		}else if("のんき".equals(skillName)){
			return NONKI;
		}else if("ひかえめ".equals(skillName)){
			return HIKAEME;
		}else if("おっとり".equals(skillName)){
			return OTTORI;
		}else if("うっかりや".equals(skillName)){
			return UKKARIYA;
		}else if("れいせい".equals(skillName)){
			return REISEI;
		}else if("おだやか".equals(skillName)){
			return ODAYAKA;
		}else if("おとなしい".equals(skillName)){
			return OTONASI;
		}else if("しんちょう".equals(skillName)){
			return SINCHO;
		}else if("なまいき".equals(skillName)){
			return NAMAIKI;
		}else if("おくびょう".equals(skillName)){
			return OKUBYO;
		}else if("せっかち".equals(skillName)){
			return SEKKATI;
		}else if("ようき".equals(skillName)){
			return YOKI;
		}else if("むじゃき".equals(skillName)){
			return MUJAKI;
		}else if("てれや".equals(skillName)){
			return TEREYA;
		}else if("がんばりや".equals(skillName)){
			return GANBAIYA;
		}else if("すなお".equals(skillName)){
			return SUNAO;
		}else if("きまぐれ".equals(skillName)){
			return KIMAGURE;
		}else if("まじめ".equals(skillName)){
			return MAJIME;
		}else{
			return -1;
		}
	}
}
