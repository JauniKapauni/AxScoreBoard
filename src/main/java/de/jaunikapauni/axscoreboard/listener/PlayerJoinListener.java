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
        objective.getScore(PlaceholderAPI.setPlaceholders(p, reference.getValue("scoreboard.line1.value"))).setScore(reference.getScore("scoreboard.line1.score"));
        objective.getScore(PlaceholderAPI.setPlaceholders(p, reference.getValue("scoreboard.line2.value"))).setScore(reference.getScore("scoreboard.line2.score"));
        objective.getScore(PlaceholderAPI.setPlaceholders(p, reference.getValue("scoreboard.line3.value"))).setScore(reference.getScore("scoreboard.line3.score"));
        objective.getScore(PlaceholderAPI.setPlaceholders(p, reference.getValue("scoreboard.line4.value"))).setScore(reference.getScore("scoreboard.line4.score"));
        objective.getScore(PlaceholderAPI.setPlaceholders(p, reference.getValue("scoreboard.line5.value"))).setScore(reference.getScore("scoreboard.line5.score"));
        objective.getScore(PlaceholderAPI.setPlaceholders(p, reference.getValue("scoreboard.line6.value"))).setScore(reference.getScore("scoreboard.line6.score"));
        objective.getScore(PlaceholderAPI.setPlaceholders(p, reference.getValue("scoreboard.line7.value"))).setScore(reference.getScore("scoreboard.line7.score"));
        objective.getScore(PlaceholderAPI.setPlaceholders(p, reference.getValue("scoreboard.line8.value"))).setScore(reference.getScore("scoreboard.line8.score"));
        objective.getScore(PlaceholderAPI.setPlaceholders(p, reference.getValue("scoreboard.line9.value"))).setScore(reference.getScore("scoreboard.line9.score"));
        objective.getScore(PlaceholderAPI.setPlaceholders(p, reference.getValue("scoreboard.line10.value"))).setScore(reference.getScore("scoreboard.line10.score"));
        objective.getScore(PlaceholderAPI.setPlaceholders(p, reference.getValue("scoreboard.line11.value"))).setScore(reference.getScore("scoreboard.line11.score"));
        objective.getScore(PlaceholderAPI.setPlaceholders(p, reference.getValue("scoreboard.line12.value"))).setScore(reference.getScore("scoreboard.line12.score"));
        objective.getScore(PlaceholderAPI.setPlaceholders(p, reference.getValue("scoreboard.line13.value"))).setScore(reference.getScore("scoreboard.line13.score"));
        objective.getScore(PlaceholderAPI.setPlaceholders(p, reference.getValue("scoreboard.line14.value"))).setScore(reference.getScore("scoreboard.line14.score"));
        objective.getScore(PlaceholderAPI.setPlaceholders(p, reference.getValue("scoreboard.line15.value"))).setScore(reference.getScore("scoreboard.line15.score"));

        p.setScoreboard(board);
    }
}
