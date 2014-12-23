package com.gmail.sacchin.pokemonlibrary.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.gmail.sacchin.pokemonlibrary.entity.Pokemon;
import com.gmail.sacchin.pokemonlibrary.entity.Type;

public class TypeTest {

	@Test
	public void testCalcurateAffinity() {
		Pokemon cuheat = new Pokemon("303-01", "クチート", "", 50, 80, 80, 55, 55, 50, 
				"", "", "", Type.STEEL, Type.FEARY, 50);
		float result = Type.calcurateAffinity(Type.FIRE, cuheat);
		assertEquals(2f, result, 0.1f);
		result = Type.calcurateAffinity(Type.DRAGON, cuheat);
		assertEquals(0f, result, 0.1f);
		result = Type.calcurateAffinity(Type.FIGHTING, cuheat);
		assertEquals(1f, result, 0.1f);
	}
	
	@Test
	public void testConvertTypeNameToNo() {
		assertEquals(Type.FIRE, Type.convertTypeNameToNo("ほのお"), 1);
		assertEquals(Type.FLYNG, Type.convertTypeNameToNo("ひこう"), 1);
		assertEquals(-1, Type.convertTypeNameToNo("かたな"), 1);
	}
	
	@Test
	public void testConvertNoToTypeName() {
		assertEquals("でんき", Type.convertNoToTypeName(Type.ELECTRIC));
		assertEquals("ドラゴン", Type.convertNoToTypeName(Type.DRAGON));
		assertEquals("エラー", Type.convertNoToTypeName(100));
	}
}
