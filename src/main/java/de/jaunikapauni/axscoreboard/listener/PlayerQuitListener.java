package de.jaunikapauni.axscoreboard.listener;

import de.jaunikapauni.axscoreboard.AxScoreBoard;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    AxScoreBoard reference;
    public PlayerQuitListener(AxScoreBoard reference){
        this.reference = reference;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        reference.removeScoreboard(e.getPlayer());
    }
}
