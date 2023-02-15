package me.api.data;

import org.bukkit.entity.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class PlayerAPI {
	private String UUID;
	private int RUB;
	private int blocks;
	private int kills;
	private int deaths;
	private String lastJoin;
	private String firstJoin;
	
	public PlayerAPI(String UUID, int RUB, int blocks, int kills, int deaths, String lastJoin, String firstJoin) {
		this.UUID = UUID;
		this.RUB = RUB;
		this.blocks = blocks;
		this.kills = kills;
		this.deaths = deaths;
		this.lastJoin = lastJoin;
		this.firstJoin = firstJoin;
	}
	
	//getPlayerAPI
	public static PlayerAPI getPlayerAPI(Player player) {
		PlayerAPI playerAPI = DatabaseManager.getDatabaseManager().load(player.getUniqueId().toString());
		if (playerAPI == null) {
			playerAPI = new PlayerAPI(player.getUniqueId().toString(), 0, 0, 0, 0, null, null);
			DatabaseManager.getDatabaseManager().create(playerAPI);
		}
		return playerAPI;
	}
	
	//UUID
	public String getUUID() {
		return UUID;
	}
	
	//RUB
	public int getRUB() {
		return this.RUB;
	}
	
	public void setRUB(int RUB) {
		if (RUB < 0) {
			return;
		}
		if (RUB > 1000000) {
			return;
		}
		this.RUB = RUB;
	}
	
	public void addRUB(int RUB) {
		if (RUB < 0) {
			return;
		}
		if (this.getRUB() + RUB > 1000000) {
			return;
		}
		this.RUB += RUB;
	}
	
	public void removeRUB(int RUB) {
		if (RUB < 0) {
			return;
		}
		if (this.getRUB() - RUB < 0) {
			return;
		}
		this.RUB -= RUB;
	}
	
	//blocks
	public int getBlocks() {
		return this.blocks;
	}
	
	public void setBlocks(int blocks) {
		if (blocks < 0) {
			return;
		}
		if (blocks > 1000000) {
			return;
		}
		this.blocks = blocks;
	}
	
	public void addBlocks(int blocks) {
		if (blocks < 0) {
			return;
		}
		if (this.getBlocks() + blocks > 1000000) {
			return;
		}
		this.blocks += blocks;
	}
	
	public void removeBlocks(int blocks) {
		if (blocks < 0) {
			return;
		}
		if (this.getBlocks() - blocks < 0) {
			return;
		}
		this.blocks -= blocks;
	}
	
	//kills
	public int getKills() {
		return this.kills;
	}
	
	public void setKills(int kills) {
		this.kills = kills;
	}
	
	public void addKills(int kills) {
		this.kills += kills;
	}
	
	//deaths
	public int getDeaths() {
		return this.deaths;
	}
	
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}
	
	public void addDeaths(int deaths) {
		this.deaths += deaths;
	}
	
	//lastJoin
	public String getLastJoin() {
		return this.lastJoin;
	}
	
	public void setLastJoin(Date lastJoin) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.lastJoin = dateFormat.format(lastJoin);
	}
	
	//firstJoin
	public String getFirstJoin() {
		return this.firstJoin;
	}
	
	public void setFirstJoin(Date firstJoin) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.firstJoin = dateFormat.format(firstJoin);
	}
}