package de.jaunikapauni.axscoreboard.command;

import de.jaunikapauni.axscoreboard.AxScoreBoard;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {

    AxScoreBoard reference;
    public ReloadCommand(AxScoreBoard reference){
        this.reference = reference;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Only players can run this command!");
            return true;
        }
        Player p = (Player) sender;
        if(!p.hasPermission("axscoreboard.reload")){
            p.sendMessage("You don't have the permission! [axscoreboard.reload]");
            return true;
        }
        reference.reloadLangFile();
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            reference.setScoreboard(onlinePlayer);
        }
        sender.sendMessage("lang.yml reloaded!");
        return true;
    }
}
