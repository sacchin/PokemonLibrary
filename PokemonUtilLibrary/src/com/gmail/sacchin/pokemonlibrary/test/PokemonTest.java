package com.gmail.sacchin.pokemonlibrary.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gmail.sacchin.pokemonlibrary.entity.Pokemon;
import com.gmail.sacchin.pokemonlibrary.entity.Type;

public class PokemonTest {
	@Test
	public void testGetHPValue() {
		Pokemon kucheat = new Pokemon("303", "クチート", "Mawile", 50, 85, 85, 55, 55, 50, 
				"かいりきバサミ", "いかく", "ちからずく", Type.convertTypeCodeToNo(Type.TypeCode.STEEL), Type.convertTypeCodeToNo(Type.TypeCode.FEARY), 23.5f){
			@Override
			public float getAttackRevision(){return 1.0f;}
			@Override
			public float getDeffenceRevision(){return 1.1f;}
			@Override
			public float getSpecialAttackRevision(){return 0.9f;}
			@Override
			public float getSpecialDeffenceRevision(){return 1.0f;}
			@Override
			public float getSpeedRevision(){return 1.0f;}
		};
		assertEquals(157, kucheat.getHPValue(31, 252));
		assertEquals(141, kucheat.getHPValue(0, 252));
		assertEquals(125, kucheat.getHPValue(31, 0));
		assertEquals(110, kucheat.getHPValue(0, 0));
	}
	
	@Test
	public void testGetAttackValue() {
		Pokemon kucheat = new Pokemon("303", "クチート", "Mawile", 50, 85, 85, 55, 55, 50, 
				"かいりきバサミ", "いかく", "ちからずく", Type.convertTypeCodeToNo(Type.TypeCode.STEEL), Type.convertTypeCodeToNo(Type.TypeCode.FEARY), 23.5f){
			@Override
			public float getAttackRevision(){return 0.9f;}
			@Override
			public float getDeffenceRevision(){return 1.0f;}
			@Override
			public float getSpecialAttackRevision(){return 1.0f;}
			@Override
			public float getSpecialDeffenceRevision(){return 1.0f;}
			@Override
			public float getSpeedRevision(){return 1.0f;}
		};
		assertEquals(123, kucheat.getAttackValue(31, 252));
		assertEquals(108, kucheat.getAttackValue(0, 252));
		assertEquals(94, kucheat.getAttackValue(31, 0));
		assertEquals(81, kucheat.getAttackValue(0, 0));
	}
	
	@Test
	public void testGetDeffenceValue() {
		Pokemon kucheat = new Pokemon("303", "クチート", "Mawile", 50, 85, 85, 55, 55, 50, 
				"かいりきバサミ", "いかく", "ちからずく", Type.convertTypeCodeToNo(Type.TypeCode.STEEL), Type.convertTypeCodeToNo(Type.TypeCode.FEARY), 23.5f){
			@Override
			public float getAttackRevision(){return 1.0f;}
			@Override
			public float getDeffenceRevision(){return 1.1f;}
			@Override
			public float getSpecialAttackRevision(){return 1.0f;}
			@Override
			public float getSpecialDeffenceRevision(){return 1.0f;}
			@Override
			public float getSpeedRevision(){return 1.0f;}
		};
		assertEquals(150, kucheat.getDeffenceValue(31, 252));
		assertEquals(133, kucheat.getDeffenceValue(0, 252));
		assertEquals(115, kucheat.getDeffenceValue(31, 0));
		assertEquals(99, kucheat.getDeffenceValue(0, 0));
	}
	
	@Test
	public void testGetSecialAttackValue() {
		Pokemon kucheat = new Pokemon("303", "クチート", "Mawile", 50, 85, 85, 55, 55, 50, 
				"かいりきバサミ", "いかく", "ちからずく", Type.convertTypeCodeToNo(Type.TypeCode.STEEL), Type.convertTypeCodeToNo(Type.TypeCode.FEARY), 23.5f){
			@Override
			public float getAttackRevision(){return 1.0f;}
			@Override
			public float getDeffenceRevision(){return 1.0f;}
			@Override
			public float getSpecialAttackRevision(){return 0.9f;}
			@Override
			public float getSpecialDeffenceRevision(){return 1.0f;}
			@Override
			public float getSpeedRevision(){return 1.0f;}
		};
		assertEquals(96, kucheat.getSpecialAttackValue(31, 252));
		assertEquals(81, kucheat.getSpecialAttackValue(0, 252));
		assertEquals(67, kucheat.getSpecialAttackValue(31, 0));
		assertEquals(54, kucheat.getSpecialAttackValue(0, 0));
	}
	
	@Test
	public void testGetSpecialDeffenceValue() {
		Pokemon kucheat = new Pokemon("303", "クチート", "Mawile", 50, 85, 85, 55, 55, 50, 
				"かいりきバサミ", "いかく", "ちからずく", Type.convertTypeCodeToNo(Type.TypeCode.STEEL), Type.convertTypeCodeToNo(Type.TypeCode.FEARY), 23.5f){
			@Override
			public float getAttackRevision(){return 1.0f;}
			@Override
			public float getDeffenceRevision(){return 1.0f;}
			@Override
			public float getSpecialAttackRevision(){return 1.0f;}
			@Override
			public float getSpecialDeffenceRevision(){return 1.1f;}
			@Override
			public float getSpeedRevision(){return 1.0f;}
		};
		assertEquals(117, kucheat.getSpecialDeffenceValue(31, 252));
		assertEquals(100, kucheat.getSpecialDeffenceValue(0, 252));
		assertEquals(82, kucheat.getSpecialDeffenceValue(31, 0));
		assertEquals(66, kucheat.getSpecialDeffenceValue(0, 0));
	}
	
	@Test
	public void testGetSpeedValue() {
		Pokemon kucheat = new Pokemon("303", "クチート", "Mawile", 50, 85, 85, 55, 55, 50, 
				"かいりきバサミ", "いかく", "ちからずく", Type.convertTypeCodeToNo(Type.TypeCode.STEEL), Type.convertTypeCodeToNo(Type.TypeCode.FEARY), 23.5f){
			@Override
			public float getAttackRevision(){return 1.0f;}
			@Override
			public float getDeffenceRevision(){return 1.0f;}
			@Override
			public float getSpecialAttackRevision(){return 1.0f;}
			@Override
			public float getSpecialDeffenceRevision(){return 1.0f;}
			@Override
			public float getSpeedRevision(){return 1.0f;}
		};
		assertEquals(107, kucheat.getSpecialDeffenceValue(31, 252));
		assertEquals(91, kucheat.getSpecialDeffenceValue(0, 252));
		assertEquals(75, kucheat.getSpecialDeffenceValue(31, 0));
		assertEquals(60, kucheat.getSpecialDeffenceValue(0, 0));
	}
}
