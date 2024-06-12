package org.sammyp.Commands;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.sammyp.swiftyshop.SwiftyShop;

public class EconomyCommands implements CommandExecutor {

    private final SwiftyShop plugin;
    private final Economy economy;
    private final FileConfiguration config;

    public EconomyCommands(SwiftyShop plugin) {
        this.plugin = plugin;
        this.economy = plugin.getEconomy();
        this.config = plugin.getConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("balance")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                double balance = economy.getBalance(player);
                String message = ChatColor.translateAlternateColorCodes('&',
                                config.getString("shop.messages.balance"))
                        .replace("{balance}", String.valueOf(balance));
                player.sendMessage(message);
                return true;
            } else {
                sender.sendMessage(config.getString("shop.messages.player_only_command"));
                return true;
            }
        } else if (command.getName().equalsIgnoreCase("addmoney")) {
            if (args.length != 2) {
                sender.sendMessage(ChatColor.RED + "Usage: /addmoney <player> <amount>");
                return false;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("shop.messages.player_not_found")));
                return true;
            }

            try {
                double amount = Double.parseDouble(args[1]);
                economy.depositPlayer(target, amount);
                String senderMessage = ChatColor.translateAlternateColorCodes('&',
                                config.getString("shop.messages.added_money"))
                        .replace("{amount}", String.valueOf(amount))
                        .replace("{player}", target.getName());
                String targetMessage = ChatColor.translateAlternateColorCodes('&',
                                config.getString("shop.messages.received_money"))
                        .replace("{amount}", String.valueOf(amount))
                        .replace("{sender}", sender.getName());
                sender.sendMessage(senderMessage);
                target.sendMessage(targetMessage);
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("shop.messages.amount_must_be_number")));
            }
            return true;
        } else if (command.getName().equalsIgnoreCase("removemoney")) {
            if (args.length != 2) {
                sender.sendMessage(ChatColor.RED + "Usage: /removemoney <player> <amount>");
                return false;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("shop.messages.player_not_found")));
                return true;
            }

            try {
                double amount = Double.parseDouble(args[1]);
                economy.withdrawPlayer(target, amount);
                String senderMessage = ChatColor.translateAlternateColorCodes('&',
                                config.getString("shop.messages.removed_money"))
                        .replace("{amount}", String.valueOf(amount))
                        .replace("{player}", target.getName());
                String targetMessage = ChatColor.translateAlternateColorCodes('&',
                                config.getString("shop.messages.lost_money"))
                        .replace("{amount}", String.valueOf(amount))
                        .replace("{sender}", sender.getName());
                sender.sendMessage(senderMessage);
                target.sendMessage(targetMessage);
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("shop.messages.amount_must_be_number")));
            }
            return true;
        }
        return false;
    }
}