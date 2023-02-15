package ru.fakeduck_king.timer;

import ru.fakeduck_king.messages.SexyActionBar;
import org.bukkit.scheduler.BukkitRunnable;
import ru.fakeduck_king.messages.SexyTitle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.Location;

public abstract class SexyTimer {
	private Player player;
	private int second;
	private int time;
	
	public SexyTimer(int second) {
		this.second = second;
	}
	
	public SexyTimer(Player player, int second) {
		this.player = player;
		this.second = second;
	}
	
	public int getTime() {
		return this.time;
	}
	
	public void run(Plugin plugin) {
		this.time = this.second;
		SexyTitle.send(player, "&aТелепортируем...", "стойте на месте");
		new BukkitRunnable() {
			Location location = player.getLocation();
			private double getLocation(Location location) { return location.getX() + location.getY() + location.getZ(); }
			@Override
			public void run() {
				if (time != 0) {
					SexyActionBar.send(player, "осталось: &a" + time);
				}
				if (!(getLocation(player.getLocation()) == getLocation(location))) {
					SexyTitle.send(player, "&cПРОВАЛ", "&fстой на месте!");
					this.cancel();
					return;
				}
				if (time <= 0) {
					SexyTitle.send(player, "&aУСПЕШНО", "вы были телепортированы");
					handle();
					this.cancel();
				}
				time--;
			}
		}.runTaskTimer(plugin, 0L, 20L);
	}
	
	protected abstract void handle();
}