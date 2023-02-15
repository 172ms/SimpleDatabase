package me.api.command;

import ru.fakeduck_king.register.commands.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class HealCommand extends SexyCommand {
	
	public HealCommand() {
		super("heal",
		"§cИспользуйте правильно команду: /heal §8[§cИгрок§8]",
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
				if (!player.hasPermission("fluxmber.heal")) {
					SexyMessage.send(player, this.permissionMessage);
					return true;
				}
				player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));
				player.setHealth(20);
				player.setFireTicks(0);
				player.setFoodLevel(20);
				player.setSaturation(20);
				SexyMessage.send(player, "Вы исцелились!");
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
				target.getActivePotionEffects().forEach(effect -> target.removePotionEffect(effect.getType()));
				target.setHealth(20);
				player.setFireTicks(0);
				target.setFoodLevel(20);
				target.setSaturation(20);
				SexyMessage.send(target, "Игрок &a" + player.getName() + " &fВас исцелил!");
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