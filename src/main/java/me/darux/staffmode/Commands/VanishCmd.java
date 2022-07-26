package me.darux.staffmode.Commands;

import me.darux.staffmode.Main;
import me.darux.staffmode.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCmd implements CommandExecutor {
    private Main plugin;

    public VanishCmd(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p=(Player) sender;
            if(Utils.isStaff(p)) {

            if(plugin.isVanished(p)){
                plugin.showPlayer(p);
                p.sendMessage(Utils.translate("&cAhora eres visible"));
            }else{
                plugin.vanishPlayer(p);
                p.sendMessage(Utils.translate("&aYa no eres visible"));

            }




            } }
        return false;
    }
}
