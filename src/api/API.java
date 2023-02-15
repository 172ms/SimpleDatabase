package api;

import ru.fakeduck_king.utils.player.*;
import org.bukkit.plugin.java.*;
import me.api.scoreboard.*;
import me.api.listener.*;
import me.api.runnable.*;
import me.api.command.*;
import me.api.data.*;
import org.bukkit.*;
import java.text.*;

public class API extends JavaPlugin {
	private void registerCommands() {
		new BroadcastCommand().register();
		new ClearCommand().register();
		new EnderchestCommand().register();
		new ExtCommand().register();
		new FeedCommand().register();
		new FlyCommand().register();
		new GamemodeCommand().register();
		new GiveRUBCommand().register();
		new HatCommand().register();
		new HealCommand().register();
		new HelpCommand().register();
		new OnlineCommand().register();
		new PingCommand().register();
		new RUBCommand().register();
		new StatusCommand().register();
		new TeleportCommand().register();
		new TeleportHereCommand().register();
		new TimeCommand().register();
	}
	
	private void registerListeners() {
		new Handlers(getInstance());
	}
	
	@Override
	public void onEnable() {
		//DATABASEMANAGER
		DatabaseManager.getDatabaseManager().setup();
		
		Bukkit.getServer().getConsoleSender().sendMessage("ANALYSIS"
		+ "§c\n"
		+ "⠄⠄⠄⢰⣧⣼⣯⠄⣸⣠⣶⣶⣦⣾⠄⠄⠄⠄⡀⠄⢀⣿⣿⠄⠄⠄⢸⡇⠄⠄\n"
		+ "⠄⠄⠄⣾⣿⠿⠿⠶⠿⢿⣿⣿⣿⣿⣦⣤⣄⢀⡅⢠⣾⣛⡉⠄⠄⠄⠸⢀⣿⠄\n"
		+ "⠄⠄⢀⡋⣡⣴⣶⣶⡀⠄⠄⠙⢿⣿⣿⣿⣿⣿⣴⣿⣿⣿⢃⣤⣄⣀⣥⣿⣿⠄\n"
		+ "⠄⠄⢸⣇⠻⣿⣿⣿⣧⣀⢀⣠⡌⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠿⠿⣿⣿⣿⠄\n"
		+ "⠄⢀⢸⣿⣷⣤⣤⣤⣬⣙⣛⢿⣿⣿⣿⣿⣿⣿⡿⣿⣿⡍⠄⠄⢀⣤⣄⠉⠋⣰\n"
		+ "⠄⣼⣖⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⣿⣿⣿⣿⢇⣿⣿⡷⠶⠶⢿⣿⣿⠇⢀⣤\n"
		+ "⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣽⣿⣿⣿⡇⣿⣿⣿⣿⣿⣿⣷⣶⣥⣴⣿⡗\n"
		+ "⢀⠈⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠄\n"
		+ "⢸⣿⣦⣌⣛⣻⣿⣿⣧⠙⠛⠛⡭⠅⠒⠦⠭⣭⡻⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃⠄\n"
		+ "⠘⣿⣿⣿⣿⣿⣿⣿⣿⡆⠄⠄⠄⠄⠄⠄⠄⠄⠹⠈⢋⣽⣿⣿⣿⣿⣵⣾⠃⠄\n"
		+ "⠄⠘⣿⣿⣿⣿⣿⣿⣿⣿⠄⣴⣿⣶⣄⠄⣴⣶⠄⢀⣾⣿⣿⣿⣿⣿⣿⠃⠄⠄\n"
		+ "⠄⠄⠈⠻⣿⣿⣿⣿⣿⣿⡄⢻⣿⣿⣿⠄⣿⣿⡀⣾⣿⣿⣿⣿⣛⠛⠁⠄⠄⠄\n"
		+ "⠄⠄⠄⠄⠈⠛⢿⣿⣿⣿⠁⠞⢿⣿⣿⡄⢿⣿⡇⣸⣿⣿⠿⠛⠁⠄⠄⠄⠄⠄\n"
		+ "⠄⠄⠄⠄⠄⠄⠄⠉⠻⣿⣿⣾⣦⡙⠻⣷⣾⣿⠃⠿⠋⠁⠄⠄⠄⠄⠄⢀⣠⣴\n"
		+ "⣿⣿⣿⣶⣶⣮⣥⣒⠲⢮⣝⡿⣿⣿⡆⣿⡿⠃⠄⠄⠄⠄⠄⠄⠄⣠⣴⣿⣿⣿");
		Bukkit.getServer().getConsoleSender().sendMessage("§cDEVELOPED BY vk.com/fakeduck_king");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		Bukkit.getServer().getConsoleSender().sendMessage("§cCurrent date and time: " + dateFormat.format(date));

		//REGISTER
		registerCommands();
		registerListeners();
		
		//RUNNABLE
		new AutoRespawn().runTaskTimer(this, 0L, 20L);
		new TabList().runTaskTimer(this, 0L, 20L);
		new TablistTag().runTaskTimer(this, 0L, 100L);
		
		//CREATE BOARD
		Bukkit.getOnlinePlayers().forEach(player -> BoardManager.createBoard(player));
		
		//COOLDOWN
		PlayerCooldown.getPlayerCooldown().setup();
		
		//PLAYERSET
		PlayerSet.getPlayerSet().setup();
	}
	
	@Override
	public void onDisable() {
		//REMOVE BOARD
		Bukkit.getOnlinePlayers().forEach(player -> BoardManager.scoreboard.remove(player));
	}
	
	public static API getInstance() {
		return API.getPlugin(API.class);
	}
}