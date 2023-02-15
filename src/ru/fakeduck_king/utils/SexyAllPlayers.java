package ru.fakeduck_king.utils;

import com.google.common.collect.Sets;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import java.util.Set;

public class SexyAllPlayers {
	public static Set<Player> get() {
		return Sets.newHashSet(Bukkit.getOnlinePlayers());
	}
}