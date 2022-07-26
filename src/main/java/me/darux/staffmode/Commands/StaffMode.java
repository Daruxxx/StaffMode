package me.darux.staffmode.Commands;

import me.darux.staffmode.Main;
import me.darux.staffmode.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StaffMode implements CommandExecutor {
    private Main plugin;

    public StaffMode(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.isOp()){
            plugin.getConfig().reload();
            sender.sendMessage(Utils.translate("&aLa configuración ha sido recargada"));
        }
        return false;
    }
}
