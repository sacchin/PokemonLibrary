package com.gmail.sacchin.pokemonlibrary.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.gmail.sacchin.pokemonlibrary.entity.Pokemon;
import com.gmail.sacchin.pokemonlibrary.entity.Type;
import com.gmail.sacchin.pokemonlibrary.entity.Type.TypeCode;

public class TypeTest {
	@Test
	public void testConvertNameToTypeCode(){
		TypeCode typeCode1 = Type.convertNameToTypeCode("ほのお");
		assertEquals(TypeCode.FIRE, typeCode1);
		TypeCode typeCode2 = Type.convertNameToTypeCode("くさ");
		assertEquals(TypeCode.GRASS, typeCode2);
		TypeCode typeCode3 = Type.convertNameToTypeCode("かぜ");
		assertNull(typeCode3);
	}
	
	@Test
	public void testConvertTypeCodeToName() {
		assertEquals("でんき", Type.convertTypeCodeToName(TypeCode.ELECTRIC));
		assertEquals("ドラゴン", Type.convertTypeCodeToName(TypeCode.DRAGON));
		assertEquals("フェアリー", Type.convertTypeCodeToName(TypeCode.FEARY));
	}
	
	@Test
	public void testConvertTypeCodeToNo() {
		assertEquals(6, Type.convertTypeCodeToNo(TypeCode.FIGHTING));
		assertEquals(14, Type.convertTypeCodeToNo(TypeCode.DRAGON));
		assertEquals(10, Type.convertTypeCodeToNo(TypeCode.PSYCHIC));
	}
	
	@Test
	public void testConvertNoToTypeCode() {
		assertEquals(TypeCode.BUG, Type.convertNoToTypeCode(11));
		assertEquals(TypeCode.DARK, Type.convertNoToTypeCode(15));
		assertEquals(TypeCode.PSYCHIC, Type.convertNoToTypeCode(10));
	}
	
	@Test
	public void testValues() {
		TypeCode[] array = TypeCode.values();
		
		assertNotNull(array);
		assertEquals(18, array.length);
	}
		
	@Test
	public void testCalcurateAffinity() {
		Pokemon cuheat = new Pokemon("303-01", "クチート", "", 50, 80, 80, 55, 55, 50, 
				"", "", "", 16, 17, 50);
		float result = Type.calcurateAffinity(TypeCode.FIRE, cuheat);
		assertEquals(2f, result, 0.1f);
		result = Type.calcurateAffinity(TypeCode.DRAGON, cuheat);
		assertEquals(0f, result, 0.1f);
		result = Type.calcurateAffinity(TypeCode.FIGHTING, cuheat);
		assertEquals(1f, result, 0.1f);
	}
}
