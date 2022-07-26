package me.darux.staffmode.SS;

import me.darux.staffmode.File.FileCreator;
import me.darux.staffmode.Main;
import me.darux.staffmode.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;

public class SSUtils {
    private Main plugin;


    public SSUtils(Main plugin) {
        this.plugin = plugin;
    }

    public static void FreezePlayer(Player p, Main plugin, Player freezer) {
        if(plugin.getFreezedPlayers().contains(p.getName())){
            List<String> freezedPlayers=plugin.getFreezedPlayers();
            freezedPlayers.remove(p.getName());
            plugin.setFreezedPlayers(freezedPlayers);
            List<String> mensaje=plugin.getConfig().getStringList("Messages.unfreeze");
            for(String a: mensaje){
                p.sendMessage(Utils.translate(a).replaceAll("%staff%",freezer.getName()));
            }
            freezer.sendMessage("");
            freezer.sendMessage(Utils.translate("&5&lCONGELACIONES &8► &dHas descongelado a el usuario: &f"+p.getName()));
            freezer.sendMessage("");
        }else{
        plugin.getFreezedPlayers().add(p.getName());
            List<String> mensaje=plugin.getConfig().getStringList("Messages.freeze");
            for(String a: mensaje){
                p.sendMessage(Utils.translate(a.replaceAll("%staff%",freezer.getName())));
            }
            freezer.sendMessage("");
            freezer.sendMessage(Utils.translate("&5&lCONGELACIONES &8► &dHas congelado a el usuario: &f"+p.getName()));
            freezer.sendMessage("");
    }}

    public void sendMessage(){
        FileCreator config=plugin.getConfig();
        List<String> mensaje=config.getStringList("Freeze.Message.message");
        List<String> freezed=plugin.getFreezedPlayers();
        int TaskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                for(String player : freezed){

                    Player freezed=Bukkit.getPlayer(player);
                    freezed.sendTitle(Utils.translate(plugin.getConfig().getString("Titulos.Freezeado.title")),Utils.translate(plugin.getConfig().getString("Titulos.Freezeado.subtitle")),10,100,10);

                    for(String a : mensaje){
                        Bukkit.getPlayer(player).sendMessage(a);
                    }
                }
            }
        },0,100);
    }
}
