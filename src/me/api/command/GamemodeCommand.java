package me.api.command;

import ru.fakeduck_king.register.commands.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;

public class GamemodeCommand extends SexyCommand {
	
	public GamemodeCommand() {
		super("gamemode",
		"§cИспользуйте правильно команду: /gamemode §8[§cРежим§8] §8[§cИгрок§8]",
		"",
		Prefix.ERROR_PERMISSIONS,
		Arrays.asList("gm"));
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You not player!");
			return true;
		}
		Player player = (Player)sender;
		switch (args.length) {
			case 1: {
				if (!player.hasPermission("fluxmber.gamemode")) {
					SexyMessage.send(player, this.permissionMessage);
					return true;
				}
				String string = args[0];
				switch (string) {
					case "survival":
					case "0": {
						player.setGameMode(GameMode.SURVIVAL);
						SexyMessage.send(player, "Вы установили режим &aвыживание");
						return true;
					}
					case "creative":
					case "1": {
						player.setGameMode(GameMode.CREATIVE);
						SexyMessage.send(player, "Вы установили режим &aкреатива");
						return true;
					}
					case "adventure":
					case "2": {
						player.setGameMode(GameMode.ADVENTURE);
						SexyMessage.send(player, "Вы установили режим &aприключение");
						return true;
					}
					case "spectator":
					case "3": {
						player.setGameMode(GameMode.SPECTATOR);
						SexyMessage.send(player, "Вы установили режим &aнаблюдателя");
						return true;
					}
				}
				SexyMessage.send(player, "&cАргумент был не найден! Используйте аргументы: &8[&csurvival/creative/adventure/spectator&8]");
				return true;
			}
			case 2: {
				if (!player.hasPermission("fluxmber.admin")) {
					SexyMessage.send(player, this.permissionMessage);
					return true;
				}
				Player target = Bukkit.getPlayer(args[1]);
				if (target == null) {
					SexyMessage.send(player, "&cИгрок " + args[1] + " не найден!");
					return true;
				}
				String string = args[0];
				switch (string) {
					case "survival":
					case "0": {
						player.setGameMode(GameMode.SURVIVAL);
						SexyMessage.send(target, "Вам установили режим &aвыживание &fигрок &a" + player.getName());
						return true;
					}
					case "creative":
					case "1": {
						player.setGameMode(GameMode.CREATIVE);
						SexyMessage.send(target, "Вам установили режим &aкреатива &fигрок &a" + player.getName());
						return true;
					}
					case "adventure":
					case "2": {
						player.setGameMode(GameMode.ADVENTURE);
						SexyMessage.send(target, "Вам установили режим &aприключение &fигрок &a" + player.getName());
						return true;
					}
					case "spectator":
					case "3": {
						player.setGameMode(GameMode.SPECTATOR);
						SexyMessage.send(target, "Вам установили режим &aнаблюдателя &fигрок &a" + player.getName());
						return true;
					}
				}
				SexyMessage.send(player, "&cАргумент был не найден! Используйте аргументы: &8[&csurvival/creative/adventure/spectator&8]");
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