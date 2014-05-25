package com.gmail.sacchin.pokemonlibrary.entity;

public class Seikaku {
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
	
	public static float SEIKAKU_TABLE[][] = 
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

	public static int convertSkillNameToNo(String skillName){
		if("‚³‚Ý‚µ‚ª‚è".equals(skillName)){
			return SAMISIGARI;
		}else if("‚¢‚¶‚Á‚Ï‚è".equals(skillName)){
			return IJIPPARI;
		}else if("‚â‚ñ‚¿‚á".equals(skillName)){
			return YANCHA;
		}else if("‚ä‚¤‚©‚ñ".equals(skillName)){
			return YUKAN;
		}else if("‚¸‚Ô‚Æ‚¢".equals(skillName)){
			return ZUBUTOI;
		}else if("‚í‚ñ‚Ï‚­".equals(skillName)){
			return WANPAKU;
		}else if("‚Ì‚¤‚Ä‚ñ‚«".equals(skillName)){
			return NOTENKI;
		}else if("‚Ì‚ñ‚«".equals(skillName)){
			return NONKI;
		}else if("‚Ð‚©‚¦‚ß".equals(skillName)){
			return HIKAEME;
		}else if("‚¨‚Á‚Æ‚è".equals(skillName)){
			return OTTORI;
		}else if("‚¤‚Á‚©‚è‚â".equals(skillName)){
			return UKKARIYA;
		}else if("‚ê‚¢‚¹‚¢".equals(skillName)){
			return REISEI;
		}else if("‚¨‚¾‚â‚©".equals(skillName)){
			return ODAYAKA;
		}else if("‚¨‚Æ‚È‚µ‚¢".equals(skillName)){
			return OTONASI;
		}else if("‚µ‚ñ‚¿‚å‚¤".equals(skillName)){
			return SINCHO;
		}else if("‚È‚Ü‚¢‚«".equals(skillName)){
			return NAMAIKI;
		}else if("‚¨‚­‚Ñ‚å‚¤".equals(skillName)){
			return OKUBYO;
		}else if("‚¹‚Á‚©‚¿".equals(skillName)){
			return SEKKATI;
		}else if("‚æ‚¤‚«".equals(skillName)){
			return YOKI;
		}else if("‚Þ‚¶‚á‚«".equals(skillName)){
			return MUJAKI;
		}else if("‚Ä‚ê‚â".equals(skillName)){
			return TEREYA;
		}else if("‚ª‚ñ‚Î‚è‚â".equals(skillName)){
			return GANBAIYA;
		}else if("‚·‚È‚¨".equals(skillName)){
			return SUNAO;
		}else if("‚«‚Ü‚®‚ê".equals(skillName)){
			return KIMAGURE;
		}else if("‚Ü‚¶‚ß".equals(skillName)){
			return MAJIME;
		}else{
			return -1;
		}
	}
}
