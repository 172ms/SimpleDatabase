package me.api.runnable;

import ru.fakeduck_king.utils.*;
import org.bukkit.scheduler.*;
import org.bukkit.*;

public class AutoRespawn extends BukkitRunnable {
	
	@Override
	public void run() {
		if (!Bukkit.getOnlinePlayers().isEmpty()) {
			Bukkit.getOnlinePlayers().forEach(player -> {
				if (player.isDead()) {
					SexyAutoRespawn.respawn(player);
				}
			});
		}
	}
}