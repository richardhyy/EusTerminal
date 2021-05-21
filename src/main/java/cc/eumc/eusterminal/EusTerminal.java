package cc.eumc.eusterminal;

import net.md_5.bungee.api.plugin.Plugin;

public final class EusTerminal extends Plugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("§e<!> EusTerminal is made for local debugging purposes, use at your own risk!");
        getProxy().getPluginManager().registerCommand(this, new TerminalCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
