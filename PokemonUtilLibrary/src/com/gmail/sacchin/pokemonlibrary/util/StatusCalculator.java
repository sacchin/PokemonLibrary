package com.gmail.sacchin.pokemonlibrary.util;

public class StatusCalculator {
	public static int calcHitPoint(int h, int effortValues){
		return (((h * 2) + 31 + (effortValues / 4)) / 2) + 10 + 50;
	}
	
	public static int calcAttack(int a, int effortValues){
		return (((a * 2) + 31 + (effortValues / 4)) / 2) + 5;
	}
	
	public static int calcBlock(int b, int effortValues){
		return (((b * 2) + 31 + (effortValues / 4)) / 2) + 5;
	}
	
	public static int calcConcentration(int c, int effortValues){
		return (((c * 2) + 31 + (effortValues / 4)) / 2) + 5;
	}
	
	public static int calcDefence(int d, int effortValues){
		return (((d * 2) + 31 + (effortValues / 4)) / 2) + 5;
	}
	
	public static int calcSpeed(int s, int effortValues){
		return (((s * 2) + 31 + (effortValues / 4)) / 2) + 5;
	}
}
