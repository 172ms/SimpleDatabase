package me.api.command;

import ru.fakeduck_king.register.commands.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import java.util.*;

public class BroadcastCommand extends SexyCommand {
	
	public BroadcastCommand() {
		super("broadcast",
		"",
		"",
		Prefix.ERROR_PERMISSIONS,
		Arrays.asList("bc"));
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You not player!");
			return true;
		}
		Player player = (Player)sender;
		if (!player.hasPermission("fluxmber.broadcast")) {
			SexyMessage.send(player, this.permissionMessage);
			return true;
		}
		if (args.length == 0) {
			SexyMessage.send(player, "&cВвведите сообщение!");
			return true;
		}
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < args.length; ++i) {
			stringBuilder.append(args[i]).append(" ");
		}
		String message = stringBuilder.toString().replace("&", "§");
		SexyBroadcast.send(message);
		return true;
	}
}