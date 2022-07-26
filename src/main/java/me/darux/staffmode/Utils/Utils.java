package me.darux.staffmode.Utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {
    public static String translate(String text){
        return ChatColor.translateAlternateColorCodes('&',text);
    }

    public static boolean isStaff(Player p){
        if(p.hasPermission("ocean.staff")){
            return true;
        }else return false;
    }
}
