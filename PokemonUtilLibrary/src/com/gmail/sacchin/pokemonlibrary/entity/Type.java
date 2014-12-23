package com.gmail.sacchin.pokemonlibrary.entity;

public class Type {
	public static final int NORMAL = 0;
	public static final int FIRE = 1;
	public static final int WATER = 2;
	public static final int ELECTRIC = 3;
	public static final int GRASS = 4;
	public static final int ICE = 5;
	public static final int FIGHTING = 6;
	public static final int POINSON = 7;
	public static final int GROUND = 8;
	public static final int FLYNG = 9;
	public static final int PSYCHIC = 10;
	public static final int BUG = 11;
	public static final int ROCK = 12;
	public static final int GHOST = 13;
	public static final int DRAGON = 14;
	public static final int DARK = 15;
	public static final int STEEL = 16;
	public static final int FEARY = 17;

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

	public static int convertTypeNameToNo(String typeName){
		if("ノーマル".equals(typeName)){
			return NORMAL;
		}else if("ほのお".equals(typeName)){
			return FIRE;
		}else if("みず".equals(typeName)){
			return WATER;
		}else if("でんき".equals(typeName)){
			return ELECTRIC;
		}else if("くさ".equals(typeName)){
			return GRASS;
		}else if("こおり".equals(typeName)){
			return ICE;
		}else if("かくとう".equals(typeName)){
			return FIGHTING;
		}else if("どく".equals(typeName)){
			return POINSON;
		}else if("じめん".equals(typeName)){
			return GROUND;
		}else if("ひこう".equals(typeName)){
			return FLYNG;
		}else if("エスパー".equals(typeName)){
			return PSYCHIC;
		}else if("むし".equals(typeName)){
			return BUG;
		}else if("いわ".equals(typeName)){
			return ROCK;
		}else if("ゴースト".equals(typeName)){
			return GHOST;
		}else if("ドラゴン".equals(typeName)){
			return DRAGON;
		}else if("あく".equals(typeName)){
			return DARK;
		}else if("はがね".equals(typeName)){
			return STEEL;
		}else if("フェアリー".equals(typeName)){
			return FEARY;
		}else{
			return -1;
		}
	}
	public static String convertNoToTypeName(int typeNo){
		switch (typeNo) {
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
	
	public static float calcurateAffinity(int attackType, Pokemon p){
		return AFFINITY_TABLE[attackType][p.getType1()] * AFFINITY_TABLE[attackType][p.getType2()];
	}
}
