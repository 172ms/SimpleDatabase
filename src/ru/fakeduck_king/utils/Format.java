package ru.fakeduck_king.utils;

import java.text.DecimalFormat;
import org.bukkit.Bukkit;

public class Format {
	public static String formatDouble(float value) {
		try {
			String array[] = {"", "K", "M"};
			int index = 0;
			while ((value / 1000) >= 1) {
				value = value / 1000;
				index++;
			}
			DecimalFormat decimalFormat = new DecimalFormat("#.###");
			return String.format("%s%s", decimalFormat.format(value), array[index]).replace(',', '.');
		}
		catch (Exception e) {
			Bukkit.getServer().getConsoleSender().sendMessage("§cFailed to get a number!");
		}
		return null;
	}
	
	public static float fixDouble(float amount, int digits) {
		if (digits == 0) {
			return amount;
		}
		StringBuilder stringBuilder = new StringBuilder("###");
		for (int i = 0; i < digits; i++) {
			if (i == 0) {
				stringBuilder.append(".");
			}
			stringBuilder.append("#");
		}
		return Float.valueOf(new DecimalFormat(stringBuilder.toString()).format(amount).replace(",", "."));
	}
	
	public static float fixDouble(float amount) {
		return fixDouble(amount, 3);
	}
}