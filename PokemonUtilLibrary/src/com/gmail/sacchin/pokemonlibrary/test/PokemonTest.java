package com.gmail.sacchin.pokemonlibrary.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gmail.sacchin.pokemonlibrary.entity.Pokemon;
import com.gmail.sacchin.pokemonlibrary.entity.Type;

public class PokemonTest {
	@Test
	public void testGetHPValue() {
		Pokemon kucheat = new Pokemon("303", "クチート", "Mawile", 50, 85, 85, 55, 55, 50, 
				"かいりきバサミ", "いかく", "ちからずく", Type.convertTypeCodeToNo(Type.TypeCode.STEEL), Type.convertTypeCodeToNo(Type.TypeCode.FEARY), 23.5f){};
		assertEquals(157, kucheat.getHPValue(31, 252));
		assertEquals(141, kucheat.getHPValue(0, 252));
		assertEquals(125, kucheat.getHPValue(31, 0));
		assertEquals(110, kucheat.getHPValue(0, 0));
	}
	
	@Test
	public void testGetAttackValue() {
		Pokemon kucheat = new Pokemon("303", "クチート", "Mawile", 50, 85, 85, 55, 55, 50, 
				"かいりきバサミ", "いかく", "ちからずく", Type.convertTypeCodeToNo(Type.TypeCode.STEEL), Type.convertTypeCodeToNo(Type.TypeCode.FEARY), 23.5f){};
		assertEquals(137, kucheat.getAttackValue(31, 252));
		assertEquals(121, kucheat.getAttackValue(0, 252));
		assertEquals(105, kucheat.getAttackValue(31, 0));
		assertEquals(90, kucheat.getAttackValue(0, 0));
	}
	
	@Test
	public void testGetDeffenceValue() {
		Pokemon kucheat = new Pokemon("303", "クチート", "Mawile", 50, 85, 85, 55, 55, 50, 
				"かいりきバサミ", "いかく", "ちからずく", Type.convertTypeCodeToNo(Type.TypeCode.STEEL), Type.convertTypeCodeToNo(Type.TypeCode.FEARY), 23.5f){};
		assertEquals(137, kucheat.getDeffenceValue(31, 252));
		assertEquals(121, kucheat.getDeffenceValue(0, 252));
		assertEquals(105, kucheat.getDeffenceValue(31, 0));
		assertEquals(90, kucheat.getDeffenceValue(0, 0));
	}
	
	@Test
	public void testGetSecialAttackValue() {
		Pokemon kucheat = new Pokemon("303", "クチート", "Mawile", 50, 85, 85, 55, 55, 50, 
				"かいりきバサミ", "いかく", "ちからずく", Type.convertTypeCodeToNo(Type.TypeCode.STEEL), Type.convertTypeCodeToNo(Type.TypeCode.FEARY), 23.5f){};
		assertEquals(107, kucheat.getSpecialAttackValue(31, 252));
		assertEquals(91, kucheat.getSpecialAttackValue(0, 252));
		assertEquals(75, kucheat.getSpecialAttackValue(31, 0));
		assertEquals(60, kucheat.getSpecialAttackValue(0, 0));
	}
	
	@Test
	public void testGetSpecialDeffenceValue() {
		Pokemon kucheat = new Pokemon("303", "クチート", "Mawile", 50, 85, 85, 55, 55, 50, 
				"かいりきバサミ", "いかく", "ちからずく", Type.convertTypeCodeToNo(Type.TypeCode.STEEL), Type.convertTypeCodeToNo(Type.TypeCode.FEARY), 23.5f){
		};
		assertEquals(107, kucheat.getSpecialDeffenceValue(31, 252));
		assertEquals(91, kucheat.getSpecialDeffenceValue(0, 252));
		assertEquals(75, kucheat.getSpecialDeffenceValue(31, 0));
		assertEquals(60, kucheat.getSpecialDeffenceValue(0, 0));
	}
	
	@Test
	public void testGetSpeedValue() {
		Pokemon kucheat = new Pokemon("303", "クチート", "Mawile", 50, 85, 85, 55, 55, 50, 
				"かいりきバサミ", "いかく", "ちからずく", Type.convertTypeCodeToNo(Type.TypeCode.STEEL), Type.convertTypeCodeToNo(Type.TypeCode.FEARY), 23.5f){};
		assertEquals(107, kucheat.getSpecialDeffenceValue(31, 252));
		assertEquals(91, kucheat.getSpecialDeffenceValue(0, 252));
		assertEquals(75, kucheat.getSpecialDeffenceValue(31, 0));
		assertEquals(60, kucheat.getSpecialDeffenceValue(0, 0));
	}
}
