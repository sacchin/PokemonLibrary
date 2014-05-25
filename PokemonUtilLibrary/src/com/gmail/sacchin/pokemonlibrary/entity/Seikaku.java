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
		if("���݂�����".equals(skillName)){
			return SAMISIGARI;
		}else if("�������ς�".equals(skillName)){
			return IJIPPARI;
		}else if("��񂿂�".equals(skillName)){
			return YANCHA;
		}else if("�䂤����".equals(skillName)){
			return YUKAN;
		}else if("���ԂƂ�".equals(skillName)){
			return ZUBUTOI;
		}else if("���ς�".equals(skillName)){
			return WANPAKU;
		}else if("�̂��Ă�".equals(skillName)){
			return NOTENKI;
		}else if("�̂�".equals(skillName)){
			return NONKI;
		}else if("�Ђ�����".equals(skillName)){
			return HIKAEME;
		}else if("�����Ƃ�".equals(skillName)){
			return OTTORI;
		}else if("���������".equals(skillName)){
			return UKKARIYA;
		}else if("�ꂢ����".equals(skillName)){
			return REISEI;
		}else if("�����₩".equals(skillName)){
			return ODAYAKA;
		}else if("���ƂȂ���".equals(skillName)){
			return OTONASI;
		}else if("���񂿂傤".equals(skillName)){
			return SINCHO;
		}else if("�Ȃ܂���".equals(skillName)){
			return NAMAIKI;
		}else if("�����т傤".equals(skillName)){
			return OKUBYO;
		}else if("��������".equals(skillName)){
			return SEKKATI;
		}else if("�悤��".equals(skillName)){
			return YOKI;
		}else if("�ނ��Ⴋ".equals(skillName)){
			return MUJAKI;
		}else if("�Ă��".equals(skillName)){
			return TEREYA;
		}else if("����΂��".equals(skillName)){
			return GANBAIYA;
		}else if("���Ȃ�".equals(skillName)){
			return SUNAO;
		}else if("���܂���".equals(skillName)){
			return KIMAGURE;
		}else if("�܂���".equals(skillName)){
			return MAJIME;
		}else{
			return -1;
		}
	}
}
