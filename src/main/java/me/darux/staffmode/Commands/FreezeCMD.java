package me.darux.staffmode.Commands;

import me.darux.staffmode.Main;
import me.darux.staffmode.SS.SSUtils;
import me.darux.staffmode.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class FreezeCMD implements CommandExecutor {
    private Main plugin;

    public FreezeCMD(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p=(Player) sender;
            if(Utils.isStaff(p)){
                if(args.length<1){
                    p.sendMessage(Utils.translate("&c/ss <jugador>"));
                }else{
                    String nick=args[0];
                    for(Player player : Bukkit.getOnlinePlayers()){
                        if(player.getName().equalsIgnoreCase(nick)){
                            if(plugin.getFreezedPlayers().contains(nick)){
                                plugin.getFreezedPlayers().remove(nick);

                                SSUtils.FreezePlayer(player,plugin,p);
                            }else{
                                SSUtils.FreezePlayer(player,plugin,p);
                                plugin.getFreezedPlayers().add(nick);

                            }

                            return false;
                        }
                    }
                    p.sendMessage(Utils.translate("&cNo hay ningún jugador conectado con ese nombre"));
                }
            }
        }
        return false;
    }
}
