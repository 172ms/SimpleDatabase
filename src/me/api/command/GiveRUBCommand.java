package me.api.command;

import ru.fakeduck_king.register.commands.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import me.api.data.*;
import org.bukkit.*;

public class GiveRUBCommand extends SexyCommand {
	
	public GiveRUBCommand() {
		super("giverub",
		"§cИспользуйте правильно команду: /giverub §8[§cИгрок§8] §8[§cset/add/remove§8] §8[§cСумма§8]",
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
		if (!player.hasPermission("fluxmber.admin")) {
			SexyMessage.send(player, this.permissionMessage);
			return true;
		}
		switch (args.length) {
			case 3: {
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					SexyMessage.send(player, "&cИгрок " + args[0] + " не найден!");
					return true;
				}
				int amount;
				try {
					amount = Integer.valueOf(args[2]);
				}
				catch (NumberFormatException e) {
					SexyMessage.send(player, "&cНе удалось распознать число!");
					return true;
				}
				String string = args[1];
				PlayerAPI targetAPI = PlayerAPI.getPlayerAPI(target);
				switch (string) {
					case "set":
					case "s": {
						targetAPI.setRUB(amount);
						DatabaseManager.getDatabaseManager().save(targetAPI);
						SexyMessage.send(target, "Вам установили &a" + amount + " RUB &fигрок &a" + player.getName());
						return true;
					}
					case "add":
					case "a": {
						targetAPI.addRUB(amount);
						DatabaseManager.getDatabaseManager().save(targetAPI);
						SexyMessage.send(target, "Вам выдал &a" + amount + " RUB &fигрок &a" + player.getName());
						return true;
					}
					case "remove":
					case "r": {
						targetAPI.removeRUB(amount);
						DatabaseManager.getDatabaseManager().save(targetAPI);
						SexyMessage.send(target, "У Вас забрали &c" + amount + " RUB &fигрок &a" + player.getName());
						return true;
					}
				}
				SexyMessage.send(player, "&cАргумент был не найден! Используйте аргументы: [set/add/remove]");
				return true;
			}
			default: {
				SexyMessage.send(player, this.usage);
				return true;
			}
		}
	}
}