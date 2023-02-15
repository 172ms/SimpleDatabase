package me.api.command;

import ru.fakeduck_king.register.commands.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;

public class EnderchestCommand extends SexyCommand {
	
	public EnderchestCommand() {
		super("enderchest",
		"§cИспользуйте правильно команду: /enderchest §8[§cИгрок§8]",
		"",
		Prefix.ERROR_PERMISSIONS,
		Arrays.asList("ec"));
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You not player!");
			return true;
		}
		Player player = (Player)sender;
		switch (args.length) {
			case 0: {
				if (!player.hasPermission("fluxmber.enderchest")) {
					SexyMessage.send(player, this.permissionMessage);
					return true;
				}
				player.openInventory(player.getEnderChest());
				return true;
			}
			case 1: {
				if (!player.hasPermission("fluxmber.admin")) {
					SexyMessage.send(player, this.permissionMessage);
					return true;
				}
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					SexyMessage.send(player, "&cИгрок " + args[0] + " не найден!");
					return true;
				}
				player.openInventory(target.getEnderChest());
				return true;
			}
			default: {
				if (!player.hasPermission("fluxmber.admin")) { return true; }
				SexyMessage.send(player, this.usage);
				return true;
			}
		}
	}
}