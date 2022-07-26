package me.darux.staffmode.Listeners;

import me.darux.staffmode.Main;
import me.darux.staffmode.Utils.StaffModeUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class Seguridad implements Listener {
    private Main plugin;

    public Seguridad(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onLogout(PlayerQuitEvent e){
        List<String> staffmode=plugin.getStaffmode();
        staffmode.remove(e.getPlayer().getName());
        StaffModeUtils staffModeUtils=new StaffModeUtils(plugin);
        staffModeUtils.disableStaffMode(e.getPlayer());
    }


    public void alApagar(){
        List<String> staffmode=plugin.getStaffmode();
        for(String player : staffmode){
            StaffModeUtils staffModeUtils=new StaffModeUtils(plugin);
            staffModeUtils.disableStaffMode(Bukkit.getPlayer(player));
            staffmode.clear();
        }
    }

}
