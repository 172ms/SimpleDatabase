package me.api.runnable;

import org.bukkit.scheduler.*;
import org.bukkit.*;

public class TabList extends BukkitRunnable {
	
	@Override
	public void run() {
		if (!Bukkit.getOnlinePlayers().isEmpty()) {
			Bukkit.getOnlinePlayers().forEach(player -> {
				player.setPlayerListHeader("Игроков онлайн: §a" + Bukkit.getOnlinePlayers().size() + " §fиз §a" + Bukkit.getMaxPlayers());
				player.setPlayerListFooter("§e§lFLUXMBER");
			});
		}
	}
}