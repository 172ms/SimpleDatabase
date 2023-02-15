package ru.fakeduck_king.utils;

import java.lang.reflect.*;
import org.bukkit.*;

public class SexyTPS {
	public static String get() {
		String name = Bukkit.getServer().getClass().getPackage().getName();
		String version = name.substring(name.lastIndexOf('.') + 1);
		Class<?> NMSClass = null;
		Object object = null;
		Field field = null;
		try {
			NMSClass = Class.forName("net.minecraft.server." + version + "." + "MinecraftServer");
			object = NMSClass.getMethod("getServer").invoke(null);
			field = object.getClass().getField("recentTps");
		}
		catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException e) {
			e.printStackTrace();
		}
		double[] tps = null;
		try {
			tps = (double[])field.get(object);
		}
		catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return SexyTPS.format(tps[0]);
	}
	
	private static String format(double tps) {
		return ((tps > 18.0) ? "§a" : ((tps > 16.0) ? "§e" : "§c")) + ((tps > 20.0) ? "" : "") + Math.min(Math.round(tps * 100.0) / 100.0, 20.0);
	}
}