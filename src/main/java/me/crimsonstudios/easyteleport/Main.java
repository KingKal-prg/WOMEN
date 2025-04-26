package me.crimsonstudios.easyteleport;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("EasyTeleport plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("EasyTeleport plugin disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("tpme")) {
                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        player.teleport(target.getLocation());
                        player.sendMessage("Teleported to " + target.getName() + "!");
                    } else {
                        player.sendMessage("Player not found!");
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
