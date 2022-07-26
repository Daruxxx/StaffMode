package me.darux.staffmode.Vanish;


import me.darux.staffmode.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class VanishLoginHandler implements Listener {

    private Main plugin;

    public VanishLoginHandler(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void handleLogin(PlayerLoginEvent event) {
        Player p= event.getPlayer();
if(p.hasPermission("ocean.staff")) return;
BukkitRunnable bukkitRunnable=new BukkitRunnable() {
    @Override
    public void run() {
        for(Player player:Bukkit.getOnlinePlayers()){

            if(plugin.isVanished(player)){
                p.hidePlayer(plugin,player);
            }
        }
    }
};
bukkitRunnable.runTaskLater(plugin,20);
    }

    @EventHandler
    public void handleQuit(PlayerQuitEvent event) {
        if(plugin.isVanished(event.getPlayer())){
            plugin.showPlayer(event.getPlayer());
        }
    }
}