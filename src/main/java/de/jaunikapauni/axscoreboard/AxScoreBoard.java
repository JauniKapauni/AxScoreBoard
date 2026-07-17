package de.jaunikapauni.axscoreboard;

import de.jaunikapauni.axscoreboard.command.ReloadCommand;
import de.jaunikapauni.axscoreboard.listener.PlayerJoinListener;
import de.jaunikapauni.axscoreboard.listener.PlayerQuitListener;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class AxScoreBoard extends JavaPlugin {

    private File langFile;
    private FileConfiguration langConfig;
    Map<UUID, Scoreboard> scoreboards = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
        createLangFile();
        getCommand("reload").setExecutor(new ReloadCommand(this));
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for(Player p : Bukkit.getOnlinePlayers()){
                updateScoreboard(p);
            }
        }, 100L, 100L);
        getLogger().info("");
        getLogger().info("----------------------------------------");
        getLogger().info("Name: " + getName());
        getLogger().info("Version: " + getDescription().getVersion());
        getLogger().info(String.join("Authors: " + ", ", getDescription().getAuthors()));
        getLogger().info("----------------------------------------");
        getLogger().info("");
    }

    public void updateScoreboard(Player p) {
        Scoreboard scoreboard = scoreboards.get(p.getUniqueId());
        if(scoreboard == null){
            setScoreboard(p);
            return;
        }
        Objective objective = scoreboard.getObjective("sidebar");
        if(objective == null){
            return;
        }
        objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(p, getValue("scoreboard.title.value"))));
        for(int i = 1; i < 15; i++){
            String text = ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(p, getValue("scoreboard.line" + i + ".value")));
            objective.getScore(text).setScore(getScore("scoreboard.line" + i + ".score"));
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void createLangFile() {
        langFile = new File(getDataFolder(), "lang.yml");
        if (!langFile.exists()) {
            saveResource("lang.yml", false);
        }
        langConfig = YamlConfiguration.loadConfiguration(langFile);
    }

    public String getValue(String path) {
        return langConfig.getString(path);
    }

    public Integer getScore(String path) {
        return langConfig.getInt(path);
    }

    public void setScoreboard(Player p){
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("sidebar", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(PlaceholderAPI.setPlaceholders(p, getValue("scoreboard.title.value")));
        for(int i = 1; i < 15; i++){
            String text = PlaceholderAPI.setPlaceholders(p, getValue("scoreboard.line" + i + ".value"));
            objective.getScore(ChatColor.translateAlternateColorCodes('&', text)).setScore(getScore("scoreboard.line" + i + ".score"));
        }
        scoreboards.put(p.getUniqueId(), scoreboard);
        p.setScoreboard(scoreboard);
    }

    public void reloadLangFile(){
        langConfig = YamlConfiguration.loadConfiguration(langFile);
    }

    public void removeScoreboard(@NotNull Player player) {
        scoreboards.remove(player.getUniqueId());
    }
}
