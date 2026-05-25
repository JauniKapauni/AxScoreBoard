package de.jaunikapauni.axscoreboard;

import de.jaunikapauni.axscoreboard.listener.PlayerJoinListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class AxScoreBoard extends JavaPlugin {

    private File langFile;
    private FileConfiguration langConfig;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        createLangFile();
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
}
