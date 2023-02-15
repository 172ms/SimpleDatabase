package me.api.command;

import ru.fakeduck_king.register.commands.*;
import ru.fakeduck_king.messages.*;
import ru.fakeduck_king.utils.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import me.api.data.*;
import org.bukkit.*;
import java.util.*;

public class RUBCommand extends SexyCommand {
	
	public RUBCommand() {
		super("rub",
		"§cИспользуйте правильно команду: /rub §8[§cИгрок§8]",
		"",
		Prefix.ERROR_PERMISSIONS,
		Arrays.asList("balance", "bal", "money"));
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You not player!");
			return true;
		}
		Player player = (Player)sender;
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(player);
		String moneyPlayer = Format.formatDouble(playerAPI.getRUB());
		switch (args.length) {
			case 0: {
				SexyMessage.send(player, "Ваш баланс: &a" + moneyPlayer + " RUB");
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
				PlayerAPI targetAPI = PlayerAPI.getPlayerAPI(target);
				String moneyTarget = Format.formatDouble(targetAPI.getRUB());
				SexyMessage.send(player, "Баланс игрока &a" + target.getName() + " " + moneyTarget + " RUB");
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