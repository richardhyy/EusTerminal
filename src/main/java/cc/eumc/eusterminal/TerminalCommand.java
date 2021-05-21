package cc.eumc.eusterminal;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

import java.io.IOException;
import java.io.OutputStream;

public class TerminalCommand extends Command {
    final Runtime rt = Runtime.getRuntime();
    public TerminalCommand() {
        super("exec", "eusterminal", "e");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder(args);
            pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            pb.redirectError(ProcessBuilder.Redirect.INHERIT);
            Process p = pb.start();
        } catch (IOException e) {
            sender.sendMessage(e.getStackTrace().toString());
            sender.sendMessage("§cError: " + e.getLocalizedMessage());
        }
    }
}
