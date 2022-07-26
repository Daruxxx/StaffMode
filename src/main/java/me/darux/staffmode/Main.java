package me.darux.staffmode;

import me.darux.staffmode.Commands.FreezeCMD;
import me.darux.staffmode.Commands.ModCMD;
import me.darux.staffmode.Commands.StaffMode;
import me.darux.staffmode.Commands.VanishCmd;
import me.darux.staffmode.File.FileCreator;
import me.darux.staffmode.Listeners.CancelEvents;
import me.darux.staffmode.Listeners.Seguridad;
import me.darux.staffmode.Listeners.SpecialItems;
import me.darux.staffmode.SS.Cancelers;
import me.darux.staffmode.SS.SSUtils;
import me.darux.staffmode.Utils.ElementosGuardados;
import me.darux.staffmode.Vanish.VanishLoginHandler;
import me.darux.staffmode.Vanish.VanishPlayerHandler;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    private FileCreator config;
    private List<String> staffmode=new ArrayList<>();
    private List<ElementosGuardados> elementosGuardados=new ArrayList<>();
    private List<String> freezedPlayers=new ArrayList<>();
    private List<Player> vanished=new ArrayList<>();


    @Override
    public void onEnable() {
        registerFiles();
        registerCommandsAndEvents();
        getServer().getPluginManager().registerEvents(new VanishLoginHandler(this), this);
        getServer().getPluginManager().registerEvents(new VanishPlayerHandler(this), this);
        // Plugin startup logic
        SSUtils ssUtils=new SSUtils(this);
        ssUtils.sendMessage();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Seguridad seguridad=new Seguridad(this);
        seguridad.alApagar();
        for (Player p : getServer().getOnlinePlayers()) {
            if (isVanished(p)) {
                showPlayer(p);
            }
        }

        for(Player p : vanished){
            showPlayer(p);
        }
    }

    public void registerFiles(){
        config=new FileCreator(this,"config");

    }

    public void registerCommandsAndEvents(){
        this.getCommand("mod").setExecutor(new ModCMD(this));
        this.getCommand("ss").setExecutor(new FreezeCMD(this));
        this.getCommand("v").setExecutor(new VanishCmd(this));
        this.getCommand("smreload").setExecutor(new StaffMode(this));
        getServer().getPluginManager().registerEvents(new CancelEvents(this),this);
        getServer().getPluginManager().registerEvents(new Seguridad(this),this);
        getServer().getPluginManager().registerEvents(new SpecialItems(this),this);
        getServer().getPluginManager().registerEvents(new Cancelers(this),this);

    }


    @Override
    public FileCreator getConfig() {
        return config;
    }

    public List<String> getStaffmode() {
        return staffmode;
    }

    public void setStaffmode(List<String> staffmode) {
        this.staffmode = staffmode;
    }

    public List<ElementosGuardados> getElementosGuardados() {
        return elementosGuardados;
    }

    public boolean isVanished(Player player) {
        return vanished.contains(player);
    }

    public void vanishPlayer(Player player) {
        if(vanished.contains(player)) return;
        vanished.add(player);
        for(Player p : Bukkit.getOnlinePlayers()){
            if(!p.hasPermission("ocean.staff")) {
                p.hidePlayer(this, player);
            }
        }
    }

    public void showPlayer(Player player) {
        if(vanished.contains(player)){
            vanished.remove(player);
            for(Player p : Bukkit.getOnlinePlayers()){
                p.showPlayer(this,player);
            }
        }
    }

    public List<String> getFreezedPlayers() {
        return freezedPlayers;
    }

    public void setFreezedPlayers(List<String> freezedPlayers) {
        this.freezedPlayers = freezedPlayers;
    }
}
