package me.api.command;

import ru.fakeduck_king.register.commands.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class FlyCommand extends SexyCommand {
	
	public FlyCommand() {
		super("fly",
		"§cИспользуйте правильно команду: /fly §8[§cИгрок§8]",
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
				if (!player.hasPermission("fluxmber.fly")) {
					SexyMessage.send(player, this.permissionMessage);
					return true;
				}
				if (!player.getAllowFlight()) {
					player.setAllowFlight(true);
					SexyMessage.send(player, "Вы &aвключили &fрежим полёта");
					return true;
				}
				else {
					player.setAllowFlight(false);
					SexyMessage.send(player, "Вы &cвыключили &fрежим полёта");
					return true;
				}
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
				if (!target.getAllowFlight()) {
					target.setAllowFlight(true);
					SexyMessage.send(target, "Вам &aвключили &fрежим полёта игрок &a" + player.getName());
					return true;
				}
				else {
					target.setAllowFlight(false);
					SexyMessage.send(target, "Вам &cвыключили &fрежим полёта игрок &a" + player.getName());
					return true;
				}
			}
			default: {
				if (!player.hasPermission("fluxmber.admin")) { return true; }
				SexyMessage.send(player, this.usage);
				return true;
			}
		}
	}
}