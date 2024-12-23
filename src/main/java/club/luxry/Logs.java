package club.luxry;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.WebhookClientBuilder;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Logs extends JavaPlugin implements CommandExecutor {
    private WebhookClient client;
    @Getter
    private static Logs plugin;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        String asciiArt =
                "                                         \n"+
                        "                                         \n"+
                        "                                         \n"+
                        "                  &5 |  \\                                        \n" +
                        "                  &5 | $$ __    __  __    __   ______   __    __ \n" +
                        "                  &5 | $$|  \\  |  \\|  \\  /  \\ /      \\ |  \\  |  \\\n" +
                        "                  &5 | $$| $$  | $$ \\$$\\/  $$|  $$$$$$\\| $$  | $$\n" +
                        "                  &5 | $$| $$  | $$  >$$  $$ | $$   \\$$| $$  | $$\n" +
                        "                  &5 | $$| $$__/ $$ /  $$$$\\ | $$      | $$__/ $$\n" +
                        "                  &5 | $$ \\$$    $$|  $$ \\$$\\| $$       \\$$    $$\n" +
                        "                  &5  \\$$  \\$$$$$$  \\$$   \\$$ \\$$       _\\$$$$$$$\n" +
                        "                  &5                                   |  \\__| $$\n" +
                        "                  &5                                    \\$$    $$\n" +
                        "                  &5                                     \\$$$$$$ \n" +
                        "                                         \n"+
                        "                      &bWebHookLogs is running on Spigot - "+((JavaPlugin)this).getDescription().getVersion()+"\n"+
                        "                        &6Check more resources at luxry.club  \n"+
                        "                                         ";

                        String[] lines = asciiArt.split("\n");
        for (String line : lines) {
            getServer().getConsoleSender().sendMessage(MessagesUtils.format("&5" + line));
        }
        getServer().getConsoleSender().sendMessage(MessagesUtils.format("                        &eAuthorization to Discord webhook...."));
        WebhookClientBuilder builder;
        try {
            builder = new WebhookClientBuilder(getConfig().getString("webhook.url"));
        }catch (Exception e){
            getServer().getConsoleSender().sendMessage(MessagesUtils.format("                &cThe plugin has failed to connect to the Discord webhook."));
            return;
        }
            builder.setThreadFactory((job) -> {
                Thread thread = new Thread(job);
                thread.setName(getConfig().getString("webhook.name"));
                thread.setDaemon(true);
                return thread;
            });
            builder.setWait(true);
            client = builder.build();
            getCommand("logs").setExecutor(this);
            getCommand("reloadlogs").setExecutor(new ReloadCommand());
            getServer().getConsoleSender().sendMessage(MessagesUtils.format("                   &aThe plugin has connected to the Discord webhook."));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length > 0) {
            String message = String.join(" ", args);
            WebhookMessageBuilder builder = new WebhookMessageBuilder();
            builder.setUsername(getConfig().getString("webhook.name"));
            builder.addEmbeds(DiscordEmbedLoader.loadEmbed(getConfig(),message));
            client.send(builder.build());
            sender.sendMessage(ChatColor.GREEN+"The message was logged successfully.");
            return true;
        } else {
            sender.sendMessage("Usage: /log <message>");
            return false;
        }
    }
    private class ReloadCommand implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
            reloadConfig();
            sender.sendMessage(MessagesUtils.format("The config was reloaded successfully."));
            return true;
        }
    }
}
