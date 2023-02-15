package me.api.command;

import ru.fakeduck_king.register.commands.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class ExtCommand extends SexyCommand {
	
	public ExtCommand() {
		super("ext",
		"§cИспользуйте правильно команду: /ext §8[§cИгрок§8]",
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
		switch (args.length) {
			case 0: {
				if (!player.hasPermission("fluxmber.ext")) {
					SexyMessage.send(player, this.permissionMessage);
					return true;
				}
				player.setFireTicks(0);
				SexyMessage.send(player, "Вы потушили себя!");
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
				target.setFireTicks(0);
				SexyMessage.send(target, "Игрок &a" + player.getName() + " &fВас потушил!");
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