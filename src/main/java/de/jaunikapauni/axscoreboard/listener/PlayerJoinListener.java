package de.jaunikapauni.axscoreboard.listener;

import de.jaunikapauni.axscoreboard.AxScoreBoard;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class PlayerJoinListener implements Listener {
    AxScoreBoard reference;
    public PlayerJoinListener(AxScoreBoard reference){
        this.reference = reference;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();

        Objective objective = board.registerNewObjective("sidebar", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(PlaceholderAPI.setPlaceholders(p, reference.getValue("scoreboard.title.value")));
        for (int i = 1; i < 15; i++) {
            objective.getScore(PlaceholderAPI.setPlaceholders(p, reference.getValue("scoreboard" + "." + "line" + i + "." + "value"))).setScore(reference.getScore("scoreboard" + "." + "line" + i + "." + "score"));
        }

        p.setScoreboard(board);
    }
}
