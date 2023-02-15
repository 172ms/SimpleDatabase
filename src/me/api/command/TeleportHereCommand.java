package me.api.command;

import ru.fakeduck_king.register.commands.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;

public class TeleportHereCommand extends SexyCommand {
	
	public TeleportHereCommand() {
		super("teleporthere",
		"§cИспользуйте правильно команду: /teleporthere §8[§cИгрок§8]",
		"",
		Prefix.ERROR_PERMISSIONS,
		Arrays.asList("tphere", "s"));
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You not player!");
			return true;
		}
		Player player = (Player)sender;
		if (!player.hasPermission("fluxmber.admin")) {
			SexyMessage.send(player, this.permissionMessage);
			return true;
		}
		switch (args.length) {
			case 1: {
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					SexyMessage.send(player, "&cИгрок " + args[0] + " не найден!");
					return true;
				}
				target.teleport(player.getLocation());
				SexyMessage.send(target, "Игрок &a" + player.getName() + " &fтелепортировал Вас к себе!");
				return true;
			}
			default: {
				SexyMessage.send(player, this.usage);
				return true;
			}
		}
	}
}