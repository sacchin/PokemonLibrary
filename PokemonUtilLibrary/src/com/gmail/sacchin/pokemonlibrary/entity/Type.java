package com.gmail.sacchin.pokemonlibrary.entity;


public class Type {
	
	public enum TypeCode{
		NORMAL
		,FIRE
		,WATER
		,ELECTRIC
		,GRASS
		,ICE
		,FIGHTING
		,POINSON
		,GROUND
		,FLYNG
		,PSYCHIC
		,BUG
		,ROCK
		,GHOST
		,DRAGON
		,DARK
		,STEEL
		,FEARY
	}

	public static float AFFINITY_TABLE[][] = 
		{
		{1,1,1,1,1,1,1,1,1,1,1,1,0.5f,0,1,1,0.5f,1},
		{1,0.5f,0.5f,1,2,2,1,1,1,1,1,2,0.5f,1,0.5f,1,2,1},
		{1,2,0.5f,1,0.5f,1,1,1,2,1,1,1,2,1,0.5f,1,1,1},
		{1,1,2,0.5f,0.5f,1,1,1,0,2,1,1,1,1,0.5f,1,1,1},
		{1,0.5f,2,1,0.5f,1,1,0.5f,2,0.5f,1,0.5f,2,1,0.5f,1,0.5f,1},
		{1,0.5f,0.5f,1,2,0.5f,1,1,2,2,1,1,1,1,2,1,0.5f,1},
		{2,1,1,1,1,2,1,0.5f,1,0.5f,0.5f,0.5f,2,0,1,2,2,0.5f},
		{1,1,1,1,2,1,1,0.5f,0.5f,1,1,1,0.5f,0.5f,1,1,0,2},
		{1,2,1,2,0.5f,1,1,2,1,0,1,0.5f,2,1,1,1,2,1},
		{1,1,1,0.5f,2,1,2,1,1,1,1,2,0.5f,1,1,1,0.5f,1},
		{1,1,1,1,1,1,2,2,1,1,0.5f,1,1,1,1,0,0.5f,1},
		{1,0.5f,1,1,2,1,0.5f,0.5f,1,0.5f,2,1,1,0.5f,1,2,0.5f,0.5f},
		{1,2,1,1,1,2,0.5f,1,0.5f,2,1,2,1,1,1,1,0.5f,1},
		{0,1,1,1,1,1,1,1,1,1,2,1,1,2,1,0.5f,1,1},
		{1,1,1,1,1,1,1,1,11,1,1,1,1,1,2,1,0.5f,0},
		{1,1,1,1,1,1,0.5f,1,1,1,2,1,1,2,1,0.5f,1,0.5f},
		{1,0.5f,0.5f,0.5f,1,2,1,1,1,1,1,1,2,1,1,1,0.5f,2},
		{1,0.5f,1,1,1,1,2,0.5f,1,1,1,1,1,1,2,2,0.5f,1}
		};

	public static TypeCode convertNameToTypeCode(String typeName){
		if("ノーマル".equals(typeName)){
			return TypeCode.NORMAL;
		}else if("ほのお".equals(typeName)){
			return TypeCode.FIRE;
		}else if("みず".equals(typeName)){
			return TypeCode.WATER;
		}else if("でんき".equals(typeName)){
			return TypeCode.ELECTRIC;
		}else if("くさ".equals(typeName)){
			return TypeCode.GRASS;
		}else if("こおり".equals(typeName)){
			return TypeCode.ICE;
		}else if("かくとう".equals(typeName)){
			return TypeCode.FIGHTING;
		}else if("どく".equals(typeName)){
			return TypeCode.POINSON;
		}else if("じめん".equals(typeName)){
			return TypeCode.GROUND;
		}else if("ひこう".equals(typeName)){
			return TypeCode.FLYNG;
		}else if("エスパー".equals(typeName)){
			return TypeCode.PSYCHIC;
		}else if("むし".equals(typeName)){
			return TypeCode.BUG;
		}else if("いわ".equals(typeName)){
			return TypeCode.ROCK;
		}else if("ゴースト".equals(typeName)){
			return TypeCode.GHOST;
		}else if("ドラゴン".equals(typeName)){
			return TypeCode.DRAGON;
		}else if("あく".equals(typeName)){
			return TypeCode.DARK;
		}else if("はがね".equals(typeName)){
			return TypeCode.STEEL;
		}else if("フェアリー".equals(typeName)){
			return TypeCode.FEARY;
		}else{
			return null;
		}
	}

	public static String convertTypeCodeToName(TypeCode type){
		switch (type) {
		case NORMAL:
			return "ノーマル";
		case FIRE:
			return "ほのお";
		case WATER:
			return "みず";
		case ELECTRIC:
			return "でんき";
		case GRASS:
			return "くさ";
		case ICE:
			return "こおり";
		case FIGHTING:
			return "かくとう";		
		case POINSON:
			return "どく";
		case GROUND:
			return "じめん";
		case FLYNG:
			return "ひこう";
		case PSYCHIC:
			return "エスパー";
		case BUG:
			return "むし";
		case ROCK:
			return "いわ";
		case GHOST:
			return "ゴースト";
		case DRAGON:
			return "ドラゴン";
		case DARK:
			return "あく";
		case STEEL:
			return "はがね";
		case FEARY:
			return "フェアリー";
		default:
			return "エラー";
		}
	}
	
	public static int convertTypeCodeToNo(TypeCode type){
		switch (type) {
		case NORMAL:
			return 0;
		case FIRE:
			return 1;
		case WATER:
			return 2;
		case ELECTRIC:
			return 3;
		case GRASS:
			return 4;
		case ICE:
			return 5;
		case FIGHTING:
			return 6;
		case POINSON:
			return 7;
		case GROUND:
			return 8;
		case FLYNG:
			return 9;
		case PSYCHIC:
			return 10;
		case BUG:
			return 11;
		case ROCK:
			return 12;
		case GHOST:
			return 13;
		case DRAGON:
			return 14;
		case DARK:
			return 15;
		case STEEL:
			return 16;
		case FEARY:
			return 17;
		default:
			return -1;
		}
	}
	
	public static TypeCode convertNoToTypeCode(int type){
		switch (type) {
		case 0:
			return TypeCode.NORMAL;
		case 1:
			return TypeCode.FIRE;
		case 2:
			return TypeCode.WATER;
		case 3:
			return TypeCode.ELECTRIC;
		case 4:
			return TypeCode.GRASS;
		case 5:
			return TypeCode.ICE;
		case 6:
			return TypeCode.FIGHTING;
		case 7:
			return TypeCode.POINSON;
		case 8:
			return TypeCode.GROUND;
		case 9:
			return TypeCode.FLYNG;
		case 10:
			return TypeCode.PSYCHIC;
		case 11:
			return TypeCode.BUG;
		case 12:
			return TypeCode.ROCK;
		case 13:
			return TypeCode.GHOST;
		case 14:
			return TypeCode.DRAGON;
		case 15:
			return TypeCode.DARK;
		case 16:
			return TypeCode.STEEL;
		case 17:
			return TypeCode.FEARY;
		default:
			return null;
		}
	}
	
	public static TypeCode[] values(){
		return TypeCode.values();
	}
	
	public static float calcurateAffinity(TypeCode attackType, Pokemon p){
		int attackNo = convertTypeCodeToNo(attackType);
		int type1No = convertTypeCodeToNo(p.getType1());
		int type2No = convertTypeCodeToNo(p.getType2());
		
		return AFFINITY_TABLE[attackNo][type1No] * AFFINITY_TABLE[attackNo][type2No];
	}
}
