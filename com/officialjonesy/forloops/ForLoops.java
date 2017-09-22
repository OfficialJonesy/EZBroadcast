package com.officialjonesy.forloops;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.ChatColor;

@SuppressWarnings("all")
public class ForLoops extends JavaPlugin {
	public void onEnable() {
		saveDefaultConfig();

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("broadcast")) {
			if (!sender.hasPermission("broadcast.use")) {
				sender.sendMessage(tl('&', "&9&lBroadcast > &8You don't have permission!"));
				return true;
			}
			if (args.length == 0) {
				sender.sendMessage(tl('&', "&9&lBroadcast > &8Please specify a message to send!"));
				return true;
			}

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < args.length; i++) {
				sb.append(args[i] + " ");
			}

			// after collecting args!
			String message = sb.toString();
			String name = sender.getName();
			getLogger().info("Raw Broadcast: " + sb.toString());
			for (Player online : Bukkit.getOnlinePlayers()) {
				online.sendMessage(tl('&',
						getConfig().getString("prefix").replace("{server}", getConfig().getString("server-name"))
								.replace("{player}", online.getName()).replace("{sender}", sender.getName()) + "&b " + message));
			}

			return true;
		}

		return true;
	}

	public String tl(char code, String message) {
		return ChatColor.translateAlternateColorCodes(code, message);
	}

}
