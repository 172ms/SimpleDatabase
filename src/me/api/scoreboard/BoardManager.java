package me.api.scoreboard;

import com.google.common.collect.*;
import ru.fakeduck_king.utils.*;
import org.bukkit.scoreboard.*;
import org.bukkit.scheduler.*;
import org.bukkit.entity.*;
import me.api.data.*;
import org.bukkit.*;
import java.util.*;
import api.*;

public class BoardManager {
	public static Map<Player, Scoreboard> scoreboard = Maps.newHashMap();
	
	public static void createBoard(Player player) {
		ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
		Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
		Objective objective = scoreboard.registerNewObjective("dummy", "dummy", "fluxmber");
		new APIBoard(scoreboard, player.getName()).runTaskTimer(API.getInstance(), 0, 2L);
		setup();
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		player.setScoreboard(scoreboard);
	}
	
	public static void setScore(Scoreboard scoreboard, Objective objective, String name, int index) {
		String string = BoardManager.build(index);
		Team team = scoreboard.getTeam(string);
		if (team == null) {
			team = scoreboard.registerNewTeam(string);
			team.addEntry(string);
			Score score = objective.getScore(string);
			score.setScore(index);
		}
		team.setPrefix(name);
	}

	public static String build(int index) {
		String hex = Integer.toHexString(index);
		StringBuilder stringBuilder = new StringBuilder();
		for (char text : hex.toCharArray()) {
			stringBuilder.append("§").append(text);
		}
		return stringBuilder.toString();
	}
	
	public static void setup() {
		new BukkitRunnable() {
			@Override
			public void run() {
				Bukkit.getOnlinePlayers().forEach(player -> {
					PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(player);
					Scoreboard scoreboard = player.getScoreboard();
					Objective objective = scoreboard.getObjective("dummy");
					BoardManager.setScore(scoreboard, objective, "§fСтатистика:", 9);
					BoardManager.setScore(scoreboard, objective, " §fБаланс §a§l> §a" + Format.formatDouble(playerAPI.getRUB()) + " RUB", 8);
					BoardManager.setScore(scoreboard, objective, "  ", 7);
					BoardManager.setScore(scoreboard, objective, " §fДобыто блоков §a> §a" + Format.formatDouble(playerAPI.getBlocks()), 6);
					BoardManager.setScore(scoreboard, objective, " ", 5);
					BoardManager.setScore(scoreboard, objective, " §fУбийств §a> §a" + playerAPI.getKills(), 4);
					BoardManager.setScore(scoreboard, objective, " §fСмертей §a> §c" + playerAPI.getDeaths(), 3);
					BoardManager.setScore(scoreboard, objective, "", 2);
					BoardManager.setScore(scoreboard, objective, " §fLast Join §a> §a" + playerAPI.getLastJoin(), 1);
					BoardManager.setScore(scoreboard, objective, " §fFirst Join §a> §a" + playerAPI.getFirstJoin(), 0);
				});
			}
		}.runTaskTimer(API.getInstance(), 0, 1L);
	}
}