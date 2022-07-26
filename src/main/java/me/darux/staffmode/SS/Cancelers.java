package me.darux.staffmode.SS;

import me.darux.staffmode.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.List;

public class Cancelers implements Listener {
    private Main plugin;

    public Cancelers(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        List<String> staffmode=plugin.getFreezedPlayers();
        if(staffmode.contains(e.getPlayer().getName())){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void drop(PlayerDropItemEvent e){
        List<String> staffmode=plugin.getFreezedPlayers();
        if(staffmode.contains(e.getPlayer().getName())){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void health(FoodLevelChangeEvent e){
        List<String> staffmode=plugin.getFreezedPlayers();
        if(staffmode.contains(e.getEntity().getName())){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void health(EntityDamageEvent e){
        List<String> staffmode=plugin.getFreezedPlayers();
        if(staffmode.contains(e.getEntity().getName())){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(EntityDamageByEntityEvent e){
        List<String> staffmode=plugin.getFreezedPlayers();
        if(staffmode.contains(e.getDamager().getName())){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onBreak(BlockPlaceEvent e){
        List<String> staffmode=plugin.getFreezedPlayers();
        if(staffmode.contains(e.getPlayer().getName())){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(InventoryClickEvent e){
        List<String> staffmode=plugin.getFreezedPlayers();
        if(staffmode.contains(e.getWhoClicked().getName())){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void Playermove(PlayerMoveEvent e){
        List<String> staffmode=plugin.getFreezedPlayers();
        if(staffmode.contains(e.getPlayer().getName())){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e){
        if(e.getMessage().startsWith("/msg") || e.getMessage().startsWith("/r") || e.getMessage().startsWith("/ss")) return;
        List<String> staffmode=plugin.getFreezedPlayers();
        if(staffmode.contains(e.getPlayer().getName())){
            e.setCancelled(true);
        }
    }
}
