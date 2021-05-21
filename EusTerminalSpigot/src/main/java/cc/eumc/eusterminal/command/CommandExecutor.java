package cc.eumc.eusterminal.command;

import cc.eumc.eusterminal.EusTerminal;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.io.IOException;

public class CommandExecutor implements org.bukkit.command.CommandExecutor {
    EusTerminal plugin;

    public CommandExecutor(EusTerminal instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("eusterminal.exec")) {
            sender.sendMessage("[EusTerminal] §eSorry.");
        }

        try {
            ProcessBuilder pb = new ProcessBuilder(args);
            pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            pb.redirectError(ProcessBuilder.Redirect.INHERIT);
            Process p = pb.start();
            sender.sendMessage("§aCommand executed.");
        } catch (IOException e) {
            sender.sendMessage(e.getStackTrace().toString());
            sender.sendMessage("§cError: " + e.getLocalizedMessage());
        }

        return true;
    }

}
