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
		assertEquals("エラー", Type.convertTypeCodeToName(null));
	}
	
	@Test
	public void testConvertTypeCodeToNo() {
		assertEquals(6, Type.convertTypeCodeToNo(TypeCode.FIGHTING));
		assertEquals(14, Type.convertTypeCodeToNo(TypeCode.DRAGON));
		assertEquals(-1, Type.convertTypeCodeToNo(null));
	}
	
	@Test
	public void testConvertNoToTypeCode() {
		assertEquals(TypeCode.BUG, Type.convertNoToTypeCode(11));
		assertEquals(TypeCode.DARK, Type.convertNoToTypeCode(15));
		assertNull(Type.convertNoToTypeCode(100));
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
				"", "", "", 16, 17, 50){
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
		float result = Type.calcurateAffinity(TypeCode.FIRE, cuheat);
		assertEquals(2f, result, 0.1f);
		result = Type.calcurateAffinity(TypeCode.DRAGON, cuheat);
		assertEquals(0f, result, 0.1f);
		result = Type.calcurateAffinity(TypeCode.FIGHTING, cuheat);
		assertEquals(1f, result, 0.1f);
		result = Type.calcurateAffinity(null, cuheat);
		assertEquals(-1f, result, 0.1f);
		
		Pokemon torimian = new Pokemon("676-01", "トリミアン", "", 50, 80, 80, 55, 55, 50, 
				"", "", "", 0, -1, 50){
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
		
		result = Type.calcurateAffinity(TypeCode.FIGHTING, torimian);
		assertEquals(2f, result, 0.1f);
		result = Type.calcurateAffinity(TypeCode.DRAGON, torimian);
		assertEquals(1f, result, 0.1f);
		result = Type.calcurateAffinity(null, torimian);
		assertEquals(-1f, result, 0.1f);
	}
}
