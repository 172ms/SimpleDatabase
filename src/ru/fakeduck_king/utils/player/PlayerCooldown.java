package ru.fakeduck_king.utils.player;

import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.UUID;

public class PlayerCooldown {
	private static PlayerCooldown playerCooldown = new PlayerCooldown();
	private HashMap<UUID, Double> time;
	
	public static PlayerCooldown getPlayerCooldown() { return PlayerCooldown.playerCooldown; }
	
	public void setup() { this.time = new HashMap<UUID, Double>(); }
	
	public boolean has(Player player) {
		if (!this.time.containsKey(player.getUniqueId()) || this.time.get(player.getUniqueId()) <= System.currentTimeMillis()) {
			return true;
		}
		return false;
	}
	
	public int get(Player player) { return Math.toIntExact(Math.round((this.time.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000)); }
	
	public void set(Player player, int seconds) {
		double delay = System.currentTimeMillis() + (seconds * 1000);
		this.time.put(player.getUniqueId(), delay);
	}
}