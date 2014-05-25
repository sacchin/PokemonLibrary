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
	public void testConvertSkillNameToNo() {
		assertEquals(Type.FIRE, Type.convertSkillNameToNo("ほのお"), 1);
		assertEquals(Type.FLYNG, Type.convertSkillNameToNo("ひこう"), 1);
		assertEquals(-1, Type.convertSkillNameToNo("テスト"), 1);
	}
}
