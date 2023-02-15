package me.api.scoreboard;

import com.google.common.collect.*;
import org.bukkit.scoreboard.*;
import org.bukkit.scheduler.*;
import org.bukkit.*;
import java.util.*;

public class APIBoard extends BukkitRunnable {
	public static Map<String, APIBoard> playersBoards = Maps.newHashMap();
	
	public static List<String> title = Lists.newArrayList(
		"§e§lFLUXMBER",
		"§e§lFLUXMBER",
		"§f§lF§e§lLUXMBER",
		"§f§lF§e§lLUXMBER",
		"§f§lFLU§e§lXMBER",
		"§f§lFLU§e§lXMBER",
		"§f§lFLUX§e§lMBER",
		"§f§lFLUX§e§lMBER",
		"§f§lFLUXM§e§lBER",
		"§f§lFLUXM§e§lBER",
		"§f§lFLUXMB§e§lER",
		"§f§lFLUXMB§e§lER",
		"§f§lFLUXMBE§e§lR",
		"§f§lFLUXMBE§e§lR",
		"§f§lFLUXMBER",
		"§f§lFLUXMBER",
		"§f§lFLUXMBE§e§lR",
		"§f§lFLUXMBE§e§lR",
		"§f§lFLUXMB§e§lER",
		"§f§lFLUXMB§e§lER",
		"§f§lFLUXM§e§lBER",
		"§f§lFLUXM§e§lBER",
		"§f§lFLUX§e§lMBER",
		"§f§lFLUX§e§lMBER",
		"§f§lFLU§e§lXMBER",
		"§f§lFLU§e§lXMBER",
		"§f§lFL§e§lUXMBER",
		"§f§lFL§e§lUXMBER",
		"§f§lF§e§lLUXMBER",
		"§f§lF§e§lLUXMBER",
		"§e§lFLUXMBER",
		"§e§lFLUXMBER",
		"§f§l^§e§lLUXMBER",
		"§f§l^§e§lLUXMBER",
		"§f§l^%§e§lUXMBER",
		"§f§l^%§e§lUXMBER",
		"§f§l^%$§e§lXMBER",
		"§f§l^%$§e§lXMBER",
		"§f§l^%$@§e§lMBER",
		"§f§l^%$@§e§lMBER",
		"§f§l^%$@*§e§lBER",
		"§f§l^%$@*§e§lBER",
		"§f§l^%$@*&§e§lER",
		"§f§l^%$@*&§e§lER",
		"§f§l^%$@*&#§e§lR",
		"§f§l^%$@*&#§e§lR",
		"§f§l^%$@*&#!",
		"§f§l^%$@*&#!",
		"§f§l^%$@*&#§e§lR",
		"§f§l^%$@*&#§e§lR",
		"§f§l^%$@*&§e§lER",
		"§f§l^%$@*&§e§lER",
		"§f§l^%$@*§e§lBER",
		"§f§l^%$@*§e§lBER",
		"§f§l^%$@§e§lMBER",
		"§f§l^%$@§e§lMBER",
		"§f§l^%$§e§lXMBER",
		"§f§l^%$§e§lXMBER",
		"§f§l^%§e§lUXMBER",
		"§f§l^%§e§lUXMBER",
		"§f§l^§e§lLUXMBER",
		"§f§l^§e§lLUXMBER",
		"§e§lFLUXMBER",
		"§e§lFLUXMBER"
	);
	
	private int i = 0;
	private int last = title.size();
	private Scoreboard scoreboard;
	private String string;
	
	public APIBoard(Scoreboard scoreboard, String string) {
		this.scoreboard = scoreboard;
		this.string = string;
		APIBoard.playersBoards.put(string, this);
	}
	
	@Override
	public void run() {
		i++;
		if (i >= last) {
			i = 0;
		}
		if (Bukkit.getPlayer(this.string) == null) {
			this.cancel();
			return;
		}
		this.scoreboard.getObjective(DisplaySlot.SIDEBAR).setDisplayName(APIBoard.title.get(i));
	}
}