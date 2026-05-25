package de.jaunikapauni.axscoreboard;

import de.jaunikapauni.axscoreboard.command.ReloadCommand;
import de.jaunikapauni.axscoreboard.listener.PlayerJoinListener;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.io.File;

public final class AxScoreBoard extends JavaPlugin {

    private File langFile;
    private FileConfiguration langConfig;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        createLangFile();
        getCommand("reload").setExecutor(new ReloadCommand(this));
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
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();

        Objective objective = board.registerNewObjective("sidebar", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(PlaceholderAPI.setPlaceholders(p, getValue("scoreboard.title.value")));
        for (int i = 1; i < 15; i++) {
            objective.getScore(PlaceholderAPI.setPlaceholders(p, getValue("scoreboard" + "." + "line" + i + "." + "value"))).setScore(getScore("scoreboard" + "." + "line" + i + "." + "score"));
        }
    }

    public void reloadLangFile(){
        langConfig = YamlConfiguration.loadConfiguration(langFile);
    }
}
