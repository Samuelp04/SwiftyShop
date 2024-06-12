package org.sammyp.swiftyshop;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.sammyp.swiftyshop.Commands.EconomyCommands;
import org.sammyp.swiftyshop.Commands.ShopCommand;
import org.sammyp.swiftyshop.Listeners.ShopListener;

import java.io.File;

public final class SwiftyShop extends JavaPlugin {

    private Economy economy;
    private ShopListener shopListener;
    private static final String SHOP_TITLE = ChatColor.GREEN + "Shop";
    private FileConfiguration shopConfig;

    @Override
    public void onEnable() {
        // Save default configuration if it doesn't exist
        saveDefaultConfig();
        setupEconomy();
        loadShopConfig();

        // Initialize the ShopListener
        shopListener = new ShopListener(this);

        // Register the ShopListener
        getServer().getPluginManager().registerEvents(shopListener, this);

        // Register the shop command
        getCommand("shop").setExecutor(new ShopCommand(this, shopListener));
        getCommand("balance").setExecutor(new EconomyCommands(this));
        getCommand("addmoney").setExecutor(new EconomyCommands(this));
        getCommand("removemoney").setExecutor(new EconomyCommands(this));

        getLogger().info("ShopPlugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("ShopPlugin disabled!");
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    public Economy getEconomy() {
        return economy;
    }

    private void loadShopConfig() {
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            saveResource("config.yml", false);
        }
        shopConfig = YamlConfiguration.loadConfiguration(configFile);
    }

    public FileConfiguration getShopConfig() {
        return shopConfig;
    }


}