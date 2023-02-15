package me.api.listener;

import ru.fakeduck_king.register.listeners.*;
import ru.fakeduck_king.utils.permissions.*;
import ru.fakeduck_king.utils.player.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import ru.fakeduck_king.utils.*;
import org.bukkit.entity.*;
import me.api.scoreboard.*;
import org.bukkit.plugin.*;
import org.bukkit.event.*;
import me.api.data.*;
import org.bukkit.*;
import java.util.*;

public class Handlers extends SexyEvent {
	
	public Handlers(Plugin plugin) {
		super(plugin);
	}
	
	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
		if (event.isCancelled()) {
			return;
		}
		event.setCancelled(true);
		Player player = event.getPlayer();
		String message = event.getMessage();
		Set<Player> notify = SexyAllPlayers.get();
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(player);
		String globalMessage = ChatColor.translateAlternateColorCodes('&', "&8[&6G&8] ");
		String localMessage = ChatColor.translateAlternateColorCodes('&', "&8[&aL&8] ");
		if (notify.size() < 2) {
			SexyMessage.send(player, "&cВас никто не слышит.");
			return;
		}
		if (!(player.hasPermission("chat.bypass"))) {
			if (PlayerCooldown.getPlayerCooldown().has(player)) {
				PlayerCooldown.getPlayerCooldown().set(player, 3);
			}
			else {
				SexyMessage.send(player, "Подождите &c" + PlayerCooldown.getPlayerCooldown().get(player) + "с &fперед отправкой сообщение.");
				return;
			}
		}
		notify.forEach(onlinePlayers -> {
			if (message.startsWith("!")) {
				SexyMessage.send(onlinePlayers, globalMessage + SexyPlayerPrefix.get(player) + " §8[§aRUB " + playerAPI.getRUB() + "§8]§f: " + SexyColorize.colorize(player, message.substring(1)));
			}
			else if (player.getLocation().distance(onlinePlayers.getLocation()) <= 100) {
				SexyMessage.send(onlinePlayers, localMessage + SexyPlayerPrefix.get(player) + " §8[§aRUB " + playerAPI.getRUB() + "§8]§f: " + SexyColorize.colorize(player, message));
			}
		});
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(player);
		if (!player.getGameMode().equals(GameMode.CREATIVE)) {
			if (player.getInventory().firstEmpty() == -1) {
				SexyMessage.send(player, "&cУ вас переполнен инвентарь! :(");
				event.setCancelled(true);
				return;
			}
			playerAPI.addBlocks(1);
			playerAPI.addRUB(1);
			DatabaseManager.getDatabaseManager().save(playerAPI);
		}
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		event.setDeathMessage(null);
		Player player = event.getEntity();
		int RUB = SexyRandom.random(10, 100);
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(player);
		if (player.getKiller() instanceof Player) {
			Player killer = player.getKiller();
			PlayerAPI killerAPI = PlayerAPI.getPlayerAPI(killer);
			if (player != killer) {
				if (playerAPI.getRUB() - RUB < 0) {
					playerAPI.addDeaths(1);
					killerAPI.addKills(1);
					DatabaseManager.getDatabaseManager().save(playerAPI);
					DatabaseManager.getDatabaseManager().save(killerAPI);
					return;
				}
				if (killerAPI.getRUB() + RUB > 1000000) {
					playerAPI.addDeaths(1);
					killerAPI.addKills(1);
					DatabaseManager.getDatabaseManager().save(playerAPI);
					DatabaseManager.getDatabaseManager().save(killerAPI);
					return;
				}
				playerAPI.removeRUB(RUB);
				killerAPI.addRUB(RUB);
				playerAPI.addDeaths(1);
				killerAPI.addKills(1);
				DatabaseManager.getDatabaseManager().save(playerAPI);
				DatabaseManager.getDatabaseManager().save(killerAPI);
				SexyActionBar.send(player, "&c- " + RUB + " RUB");
				SexyActionBar.send(killer, "&a+ " + RUB + " RUB");
				SexyMessage.send(player, "Вас убил &a" + killer.getName() + "&f. Вы потеряли &c" + RUB + " RUB");
				SexyMessage.send(killer, "Вы убили &a" + player.getName() + " &fи получили &a" + RUB + " RUB");
			}
		}
		if (playerAPI.getRUB() - RUB < 0) {
			playerAPI.addDeaths(1);
			DatabaseManager.getDatabaseManager().save(playerAPI);
			return;
		}
		playerAPI.removeRUB(RUB);
		playerAPI.addDeaths(1);
		DatabaseManager.getDatabaseManager().save(playerAPI);
		SexyActionBar.send(player, "&c- " + RUB + " RUB");
		SexyMessage.send(player, "Вы потеряли &c" + RUB + " RUB");
	}
	
	@EventHandler
	public void onPlayerFish(PlayerFishEvent event) {
		Player player = event.getPlayer();
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(player);
		if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH && event.getCaught().getType() == EntityType.DROPPED_ITEM) {
			event.getCaught().remove();
			int RUB = SexyRandom.random(0, 10);
			if (RUB < 1) {
				SexyMessage.send(player, "&cТебе не повезло, вы ничего не поймали! ╯╸╭ ╮╸╯");
				return;
			}
			playerAPI.addRUB(RUB);
			DatabaseManager.getDatabaseManager().save(playerAPI);
			SexyMessage.send(player, "Вы поймали &a" + RUB + " RUB");
		}
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		event.setJoinMessage(null);
		Player player = event.getPlayer();
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(player);
		if (playerAPI.getLastJoin() == null) {
			playerAPI.setLastJoin(new Date());
		}
		if (playerAPI.getFirstJoin() == null) {
			playerAPI.setFirstJoin(new Date());
		}
		DatabaseManager.getDatabaseManager().save(playerAPI);
		BoardManager.createBoard(player);
	}
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent event) {
		event.setLeaveMessage(null);
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		event.setQuitMessage(null);
		Player player = event.getPlayer();
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(player);
		playerAPI.setLastJoin(new Date());
		DatabaseManager.getDatabaseManager().save(playerAPI);
		BoardManager.scoreboard.remove(player);
	}
}