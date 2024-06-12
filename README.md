# ShopPlugin

## Overview

ShopPlugin is a Minecraft plugin that allows players to interact with a fully configurable in-game shop through a graphical user interface (GUI). It integrates with Vault for economy management and supports commands for checking balances and managing player funds.

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Installation](#installation)
- [Configuration](#configuration)
- [Commands](#commands)
- [Permissions](#permissions)
- [Development](#development)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

---

## Features

<ul>
  <li><b>Customizable Shop GUI:</b> Configure the shop layout and items through a user-friendly interface.</li>
  <li><b>Vault Integration:</b> Seamlessly manage the in-game economy with Vault support.</li>
  <li><b>Dynamic Messaging:</b> Customize all plugin messages in the `config.yml` file.</li>
  <li><b>Balance Commands:</b> Easily check and manage player balances with commands.</li>
</ul>

---

## Installation

To install **ShopPlugin**, follow these steps:

1. **Download the Plugin:**
   - Download the latest version of the ShopPlugin JAR file from the [Releases](https://github.com/yourusername/ShopPlugin/releases) page.

2. **Install Vault:**
   - Ensure Vault is installed and working on your server. You can download Vault from the [SpigotMC resource page](https://www.spigotmc.org/resources/vault.34315/).

3. **Place the JAR Files:**
   - Place both the Vault and ShopPlugin JAR files into your server's `/plugins` directory.

4. **Start the Server:**
   - Start your Minecraft server. The plugin will generate a default `config.yml` file in the `/plugins/ShopPlugin` directory.

---

## Configuration

The `config.yml` file allows you to customize the shop GUI, commands, and messages. Here is an example configuration:

```yaml
shop:
  gui_size: 54  # Default size for the shop GUI (6 rows * 9 slots)
  messages:
    shop_title: "&aShop"
    item_bought: "&aYou bought {item} for {price} coins."
    not_enough_money: "&cYou do not have enough money."
    item_sold: "&aYou sold {item} for {price} coins."
    item_not_found: "&cYou do not have {item} in your inventory."
    player_only_command: "&cThis command can only be executed by a player."
    balance: "&aYour balance: &6{balance}"
    added_money: "&aAdded {amount} to {player}'s balance."
    received_money: "&aYou have received {amount} from {sender}."
    player_not_found: "&cPlayer not found."
    amount_must_be_number: "&cAmount must be a number."
    removed_money: "&aRemoved {amount} from {player}'s balance."
    lost_money: "&cYou have lost {amount} by {sender}."
  categories:
    Weapons:
      - material: DIAMOND_SWORD
        name: "Buy Diamond Sword"
        buy_price: 150
        sell_price: 75
    Armor:
      - material: DIAMOND_CHESTPLATE
        name: "Buy Diamond Chestplate"
        buy_price: 200
        sell_price: 100
    Resources:
      - material: IRON_INGOT
        name: "Buy Iron Ingot"
        buy_price: 50
        sell_price: 25
      - material: GOLD_INGOT
        name: "Buy Gold Ingot"
        buy_price: 100
        sell_price: 50
```
---
## Commands

**Shop Commands**

- /shop - Opens the main shop GUI.
- 
**Economy Commands**

- /balance - Check your balance.
- /addmoney <player> <amount> - Add money to a player's balance.
- /removemoney <player> <amount> - Remove money from a player's balance.

---

**Permissions**

- shopplugin.use - Allows access to shop commands.
- shopplugin.admin - Allows access to admin commands (addmoney, removemoney).

---

**License**

**ShopPlugin** is licensed under the MIT License.

---

**Contact**

For questions, suggestions, or support, please contact us via our [Discord Server](https://discord.gg/5CEWv5pNXA) or open an issue in this repository.
