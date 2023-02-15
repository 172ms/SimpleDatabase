package ru.fakeduck_king.messages;

import ru.fakeduck_king.utils.player.PlayerSet;
import org.bukkit.scheduler.BukkitRunnable;
import com.google.common.collect.Lists;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import java.util.Collections;
import org.bukkit.ChatColor;
import java.util.List;

public class SexyMessage {
	public static void send(Plugin plugin, Player player, String[] messages) {
		List<String> list = Lists.newArrayList();
		Collections.addAll(list, messages);
		int[] array = { 0 };
		int i = 0;
		new BukkitRunnable() {
			@Override
			public void run() {
				SexyMessage.send(player, list.get(array[0]));
				++array[i];
				if (array[0] == list.size()) {
					this.cancel();
					if (PlayerSet.getPlayerSet().has(player)) {
						PlayerSet.getPlayerSet().remove(player);
					}
				}
			}
		}.runTaskTimer(plugin, 0L, 20L);
	}
	
	public static void send(Player player, String message) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}
}