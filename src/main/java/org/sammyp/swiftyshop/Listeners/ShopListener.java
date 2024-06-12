package org.sammyp.swiftyshop.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.sammyp.swiftyshop.SwiftyShop;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ShopListener implements Listener {

    private final SwiftyShop plugin;

    public ShopListener(SwiftyShop plugin) {
        this.plugin = plugin;
    }

    public void openMainShop(Player player) {
        FileConfiguration shopConfig = plugin.getConfig();
        Set<String> categories = shopConfig.getConfigurationSection("shop.categories").getKeys(false);

        int guiSize = shopConfig.getInt("shop.gui_size", 54); // Default to 54 if not specified or invalid
        if (guiSize > 54) {
            guiSize = 54; // Limit to maximum inventory size of 54 slots
        }

        Inventory mainShop = Bukkit.createInventory(null, guiSize, ChatColor.translateAlternateColorCodes('&', shopConfig.getString("shop.messages.shop_title")));

        int slot = 0;
        for (String category : categories) {
            ItemStack item = new ItemStack(Material.CHEST); // Placeholder item for category
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.GREEN + category);
            item.setItemMeta(meta);
            mainShop.setItem(slot++, item);
        }

        player.openInventory(mainShop);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        String title = event.getView().getTitle();
        FileConfiguration shopConfig = plugin.getConfig();

        if (title.equals(ChatColor.translateAlternateColorCodes('&', shopConfig.getString("shop.messages.shop_title")))) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem == null || !clickedItem.hasItemMeta()) return;

            String categoryName = ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName());
            openCategoryShop(player, categoryName);

        } else if (title.startsWith(ChatColor.GREEN.toString())) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem == null || !clickedItem.hasItemMeta()) return;

            String categoryName = ChatColor.stripColor(title.substring(ChatColor.GREEN.toString().length()));
            String itemName = ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName());

            List<Map<?, ?>> items = shopConfig.getMapList("shop.categories." + categoryName);

            for (Map<?, ?> itemData : items) {
                String name = (String) itemData.get("name");
                double buyPrice = itemData.get("buy_price") instanceof Number ? ((Number) itemData.get("buy_price")).doubleValue() : -1;
                double sellPrice = itemData.get("sell_price") instanceof Number ? ((Number) itemData.get("sell_price")).doubleValue() : -1;

                if (name.equals(itemName)) {
                    if (buyPrice > 0) {
                        handleBuy(player, clickedItem.getType(), buyPrice);
                    } else if (sellPrice > 0) {
                        handleSell(player, clickedItem.getType(), sellPrice);
                    }
                    break;
                }
            }
        }
    }

    private void openCategoryShop(Player player, String category) {
        FileConfiguration shopConfig = plugin.getConfig();
        List<Map<?, ?>> items = shopConfig.getMapList("shop.categories." + category);

        int guiSize = shopConfig.getInt("shop.gui_size", 54); // Default to 54 if not specified or invalid
        if (guiSize > 54) {
            guiSize = 54; // Limit to maximum inventory size of 54 slots
        }

        Inventory categoryShop = Bukkit.createInventory(null, guiSize, ChatColor.GREEN + category);

        for (Map<?, ?> itemData : items) {
            String materialName = (String) itemData.get("material");
            String displayName = (String) itemData.get("name");
            double buyPrice = itemData.get("buy_price") instanceof Number ? ((Number) itemData.get("buy_price")).doubleValue() : -1;
            double sellPrice = itemData.get("sell_price") instanceof Number ? ((Number) itemData.get("sell_price")).doubleValue() : -1;

            Material material = Material.getMaterial(materialName);
            if (material == null) {
                Bukkit.getLogger().warning("Invalid material: " + materialName);
                continue;
            }

            ItemStack item = new ItemStack(material);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.GREEN + displayName);
            item.setItemMeta(meta);

            categoryShop.addItem(item);
        }

        player.openInventory(categoryShop);
    }

    private void handleBuy(Player player, Material material, double price) {
        FileConfiguration shopConfig = plugin.getConfig();
        if (plugin.getEconomy().getBalance(player) >= price) {
            plugin.getEconomy().withdrawPlayer(player, price);
            player.getInventory().addItem(new ItemStack(material));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', shopConfig.getString("shop.messages.item_bought"))
                    .replace("{item}", material.name()).replace("{price}", String.valueOf(price)));
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', shopConfig.getString("shop.messages.not_enough_money")));
        }
    }

    private void handleSell(Player player, Material material, double price) {
        FileConfiguration shopConfig = plugin.getConfig();
        ItemStack item = null;
        for (ItemStack i : player.getInventory()) {
            if (i != null && i.getType() == material) {
                item = i;
                break;
            }
        }

        if (item != null) {
            player.getInventory().removeItem(item);
            plugin.getEconomy().depositPlayer(player, price);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', shopConfig.getString("shop.messages.item_sold"))
                    .replace("{item}", material.name()).replace("{price}", String.valueOf(price)));
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', shopConfig.getString("shop.messages.not_enough_money")));
        }
    }
}