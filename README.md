<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ShopPlugin</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f4f4f4;
        }
        h1, h2, h3, h4 {
            color: #333;
        }
        pre {
            background-color: #eaeaea;
            padding: 10px;
            border-radius: 5px;
            overflow-x: auto;
        }
        code {
            background-color: #eaeaea;
            padding: 2px 4px;
            border-radius: 3px;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .config {
            background-color: #eaeaea;
            padding: 10px;
            border-radius: 5px;
            overflow-x: auto;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>ShopPlugin</h1>
        <h2>Overview</h2>
        <p>ShopPlugin is a Minecraft plugin that allows players to interact with a fully configurable in-game shop through a graphical user interface (GUI). It integrates with Vault for economy management and supports commands for checking balances and managing player funds.</p>
        
        <h2>Features</h2>
        <ul>
            <li>Customizable shop GUI with category-based item browsing.</li>
            <li>Integration with Vault for economy management.</li>
            <li>Configurable messages and commands through <code>config.yml</code>.</li>
            <li>Commands for checking player balances and managing funds (<code>/balance</code>, <code>/addmoney</code>, <code>/removemoney</code>).</li>
        </ul>
        
        <h2>Installation</h2>
        <ol>
            <li><strong>Download the Plugin:</strong> Download the latest version of the ShopPlugin JAR file from the <a href="https://github.com/yourusername/ShopPlugin/releases">Releases</a> page.</li>
            <li><strong>Install Vault:</strong> Ensure Vault is installed and working on your server. You can download Vault from the <a href="https://www.spigotmc.org/resources/vault.34315/">SpigotMC resource page</a>.</li>
            <li><strong>Place the JAR Files:</strong> Place both the Vault and ShopPlugin JAR files into your server's <code>/plugins</code> directory.</li>
            <li><strong>Start the Server:</strong> Start your Minecraft server. The plugin will generate a default <code>config.yml</code> file in the <code>/plugins/ShopPlugin</code> directory.</li>
        </ol>
        
        <h2>Configuration</h2>
        <p>The <code>config.yml</code> file allows you to customize the shop GUI, commands, and messages. Here is an example configuration:</p>
        <pre class="config">
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
        </pre>
        
        <h2>Commands</h2>
        <h3>Shop Commands</h3>
        <ul>
            <li><code>/shop</code> - Opens the main shop GUI.</li>
        </ul>
        <h3>Economy Commands</h3>
        <ul>
            <li><code>/balance</code> - Check your balance.</li>
            <li><code>/addmoney &lt;player&gt; &lt;amount&gt;</code> - Add money to a player's balance.</li>
            <li><code>/removemoney &lt;player&gt; &lt;amount&gt;</code> - Remove money from a player's balance.</li>
        </ul>
        
        <h2>Permissions</h2>
        <ul>
            <li><code>shopplugin.use</code> - Allows access to shop commands.</li>
            <li><code>shopplugin.admin</code> - Allows access to admin commands (<code>addmoney</code>, <code>removemoney</code>).</li>
        </ul>
        
        <h2>Development</h2>
        <h3>Prerequisites</h3>
        <ul>
            <li><a href="https://www.oracle.com/java/technologies/javase-jdk8-downloads.html">Java Development Kit (JDK) 8 or higher</a></li>
            <li><a href="https://maven.apache.org/">Apache Maven</a></li>
            <li><a href="https://www.spigotmc.org/wiki/spigot-maven/">Spigot API</a></li>
        </ul>
        
        <h3>Building from Source</h3>
        <ol>
            <li>Clone the repository:
                <pre class="config">git clone https://github.com/yourusername/ShopPlugin.git
cd ShopPlugin</pre>
            </li>
            <li>Build the plugin using Maven:
                <pre class="config">mvn clean package</pre>
            </li>
            <li>The compiled JAR file will be located in the <code>target</code> directory.</li>
        </ol>
        
        <h2>Contributing</h2>
        <p>Contributions are welcome! Please fork the repository and submit a pull request for any changes.</p>
        
        <h2>License</h2>
        <p>This project is licensed under the MIT License. See the <a href="LICENSE">LICENSE</a> file for details.</p>
    </div>
</body>
</html>
