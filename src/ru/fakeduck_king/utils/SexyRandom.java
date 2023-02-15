package ru.fakeduck_king.utils;

public class SexyRandom {
	public static int random(int min, int max) {
		return (int)Math.floor(Math.random() * (max - min + 1) + min);
	}
}