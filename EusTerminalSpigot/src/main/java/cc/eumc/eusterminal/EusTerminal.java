package cc.eumc.eusterminal;

import cc.eumc.eusterminal.command.CommandExecutor;
import cc.eumc.eusterminal.command.DownloadExecutor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class EusTerminal extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("§e<!> EusTerminal is made for local debugging purposes, use at your own risk!");
        Bukkit.getPluginCommand("_exec").setExecutor(new CommandExecutor(this));
        Bukkit.getPluginCommand("_download").setExecutor(new DownloadExecutor(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
