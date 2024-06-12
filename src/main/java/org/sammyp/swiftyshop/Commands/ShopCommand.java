package org.sammyp.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sammyp.swiftyshop.Listeners.ShopListener;
import org.sammyp.swiftyshop.SwiftyShop;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShopCommand implements CommandExecutor {

    private final SwiftyShop plugin;
    private final ShopListener shopListener;

    public ShopCommand(SwiftyShop plugin, ShopListener shopListener) {
        this.plugin = plugin;
        this.shopListener =  shopListener;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("shop.messages.player_only_command")));
            return true;
        }

        Player player = (Player) sender;
        shopListener.openMainShop(player);

        return true;
    }
}