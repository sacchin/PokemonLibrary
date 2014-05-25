package com.gmail.sacchin.pokemonlibrary.entity;


public class Type {
	public static final int NORMAL = 0;	//�m�[�}��
	public static final int FIRE = 1;	//�ق̂�
	public static final int WATER = 2;	//�݂�
	public static final int ELECTRIC = 3;	//�ł�
	public static final int GRASS = 4;	//����
	public static final int ICE = 5;	//������
	public static final int FIGHTING = 6;	//�����Ƃ�
	public static final int POINSON = 7;	//�ǂ�
	public static final int GROUND = 8;	//���߂�	
	public static final int FLYNG = 9;	//�Ђ���
	public static final int PSYCHIC = 10;	//�G�X�p�[
	public static final int BUG = 11;	//�ނ�	
	public static final int ROCK = 12;	//����
	public static final int GHOST = 13;	//�S�[�X�g
	public static final int DRAGON = 14;	//�h���S��
	public static final int DARK = 15;	//����
	public static final int STEEL = 16;	//�͂���
	public static final int FEARY = 17;	//�t�F�A���[

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



	public static int convertSkillNameToNo(String typeName){
		if("�m�[�}��".equals(typeName)){
			return NORMAL;
		}else if("�ق̂�".equals(typeName)){
			return FIRE;
		}else if("�݂�".equals(typeName)){
			return WATER;
		}else if("�ł�".equals(typeName)){
			return ELECTRIC;
		}else if("����".equals(typeName)){
			return GRASS;
		}else if("������".equals(typeName)){
			return ICE;
		}else if("�����Ƃ�".equals(typeName)){
			return FIGHTING;
		}else if("�ǂ�".equals(typeName)){
			return POINSON;
		}else if("���߂�".equals(typeName)){
			return GROUND;
		}else if("�Ђ���".equals(typeName)){
			return FLYNG;
		}else if("�G�X�p�[".equals(typeName)){
			return PSYCHIC;
		}else if("�ނ�".equals(typeName)){
			return BUG;
		}else if("����".equals(typeName)){
			return ROCK;
		}else if("�S�[�X�g".equals(typeName)){
			return GHOST;
		}else if("�h���S��".equals(typeName)){
			return DRAGON;
		}else if("����".equals(typeName)){
			return DARK;
		}else if("�͂���".equals(typeName)){
			return STEEL;
		}else if("�t�F�A���[".equals(typeName)){
			return FEARY;
		}else{
			return -1;
		}

	}

	public static float calcurateAffinity(int attackType, Pokemon p){
		return AFFINITY_TABLE[attackType][p.getType1()] * AFFINITY_TABLE[attackType][p.getType2()];
	}

}
