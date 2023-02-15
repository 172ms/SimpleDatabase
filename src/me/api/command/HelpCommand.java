package me.api.command;

import ru.fakeduck_king.register.commands.*;
import ru.fakeduck_king.utils.player.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import api.*;

public class HelpCommand extends SexyCommand {
	
	public HelpCommand() {
		super("help",
		"",
		"",
		Prefix.ERROR_PERMISSIONS);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You not player!");
			return true;
		}
		Player player = (Player)sender;
		if (!PlayerSet.getPlayerSet().has(player)) {
			PlayerSet.getPlayerSet().add(player);
			String[] messages = {
				"Основные команды:",
				"&a/online - &fузнать текущий онлайн на сервере",
				"&a/ping - &fпроверить пинг"
			};
			SexyMessage.send(API.getInstance(), player, messages);
			return true;
		}
		return true;
	}
}