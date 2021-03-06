package com.gmail.sacchin.pokemonlibrary.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.gmail.sacchin.pokemonlibrary.entity.Characteristic;

public class CharacteristicTest {
	@Test
	public void testConvertCharacteristicNameToNo() {
		assertEquals(Characteristic.SAMISIGARI, Characteristic.convertCharacteristicNameToNo("さみしがり"), 1);
		assertEquals(Characteristic.IJIPPARI, Characteristic.convertCharacteristicNameToNo("いじっぱり"), 1);
		assertEquals(-1, Characteristic.convertCharacteristicNameToNo("かわいい"), 1);
	}
}
