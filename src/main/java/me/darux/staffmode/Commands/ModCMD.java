package me.darux.staffmode.Commands;

import me.darux.staffmode.File.FileCreator;
import me.darux.staffmode.Main;
import me.darux.staffmode.Utils.StaffModeUtils;
import me.darux.staffmode.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ModCMD implements CommandExecutor {
    private final Main plugin;

    public ModCMD(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

         if(sender instanceof Player){
            Player p=(Player) sender;
            plugin.showPlayer(p);
            FileCreator config=plugin.getConfig();
            String permission=config.getString("Commands.mod.permission");
            List<String> staffmode=plugin.getStaffmode();
            String nombre=p.getName();
            StaffModeUtils staffModeUtils=new StaffModeUtils(plugin);

            if(p.hasPermission(permission)){

                if(staffmode.contains(nombre)){


                    staffModeUtils.disableStaffMode(p);
                }else{
                    staffModeUtils.enableStaffMode(p);
                }

            }else p.sendMessage(Utils.translate("&cNo tienes los permisos suficientes para usar este comando"));

        }else sender.sendMessage(Utils.translate("&cEste comando solo puede ser ejecutado por un jugador"));
        return false;
    }


}
