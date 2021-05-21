package cc.eumc.eusterminal.command;

import cc.eumc.eusterminal.EusTerminal;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.BufferedOutputStream;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadExecutor implements CommandExecutor {
    EusTerminal plugin;

    public DownloadExecutor(EusTerminal instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("eusterminal.download")) {
            sender.sendMessage("[EusTerminal] §eSorry.");
        }

        if (args.length == 1 || args.length == 2) {
            String fileName = args[0].substring(args[0].lastIndexOf("/") + 1);
            File outputFile;
            if (args.length == 1) {
                outputFile = new File(".", fileName);
            }
            else {
                outputFile = new File(args[1]);
            }
            sender.sendMessage("§lDownloading §r§n" + args[0] + "§r §lto §r§n" + outputFile.getAbsolutePath());

            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
                try {
                    URL url = new URL(args[0]);
                    HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());
                    long completeFileSize = httpConnection.getContentLength();

                    java.io.BufferedInputStream in = new java.io.BufferedInputStream(httpConnection.getInputStream());
                    java.io.FileOutputStream fos = new java.io.FileOutputStream(outputFile);
                    java.io.BufferedOutputStream bout = new BufferedOutputStream(
                            fos, 1024);
                    byte[] data = new byte[1024];
                    long downloadedFileSize = 0;
                    int x = 0;
                    int lastProgress = 0;
                    while ((x = in.read(data, 0, 1024)) >= 0) {
                        downloadedFileSize += x;

                        // calculate progress
                        final int currentProgress = (int) ((((double)downloadedFileSize) / ((double)completeFileSize)) * 10000d) / 100;

                        // update progress bar

                        if (lastProgress != currentProgress) {
                            lastProgress = currentProgress;
                            sender.sendMessage("§7-> " + currentProgress + "%");
                        }
                        bout.write(data, 0, x);
                    }
                    bout.close();
                    in.close();
                    sender.sendMessage("§aDownloaded and saved: " + fileName + " > " + outputFile.getAbsolutePath());
                } catch (Exception e) {
                    sender.sendMessage("§cError: " + e.getLocalizedMessage());
                    e.printStackTrace();
                }
            });
        }
        else {
            sender.sendMessage("§eUsage: /download <URL> [Destination]");
        }

        return true;
    }

}
