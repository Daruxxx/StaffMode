package me.darux.staffmode.Utils;

import me.darux.staffmode.File.FileCreator;
import me.darux.staffmode.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class StaffModeUtils {
    private Main plugin;

    public StaffModeUtils(Main plugin) {
        this.plugin = plugin;
    }

    public void enableStaffMode(Player p){

        List<String> staffmode=plugin.getStaffmode();
        staffmode.add(p.getName());
        plugin.setStaffmode(staffmode);
        p.sendMessage(Utils.translate("&2&lSTAFF &8► &fTe has activado el Staff Mode."));

        ElementosGuardados elementosGuardados=new ElementosGuardados(p.getGameMode(),p.getInventory().getContents(),p.getName());
        plugin.getElementosGuardados().add(elementosGuardados);
        p.setGameMode(GameMode.CREATIVE);
        p.getInventory().clear();



        //
        ItemStack item=new ItemStack(Material.COMPASS,1);
        ItemMeta meta=item.getItemMeta();
        meta.setDisplayName(Utils.translate("&cCompass"));
        List<String> lore=new ArrayList<>();

        lore.add(Utils.translate("&7Usa este item para"));
        lore.add(Utils.translate("&7Moverte más rápido"));
        lore.add(Utils.translate("&7Por el mapa"));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);
        item.setItemMeta(meta);
        p.getInventory().setItem(0,item);


        ItemStack ice=new ItemStack(Material.ICE,1);
        ItemMeta i=ice.getItemMeta();
        i.setDisplayName(Utils.translate("&eFreeze"));
        lore.clear();
        lore.add(Utils.translate("&7Click derecho"));
        lore.add(Utils.translate("&7a un jugador"));
        lore.add(Utils.translate("&7para congelarlo."));
        i.setLore(lore);
        ice.setItemMeta(i);
        p.getInventory().setItem(1,ice);


        ItemStack slime=new ItemStack(Material.MUSIC_DISC_13);
        ItemMeta meta2=slime.getItemMeta();
        meta2.setDisplayName(Utils.translate("&8Random tp"));
        slime.setItemMeta(meta2);
       p.getInventory().setItem(7,slime);

       ItemStack item2=new ItemStack(Material.LIME_DYE,1);
        ItemMeta meta4=item2.getItemMeta();
        item.setType(Material.LIME_DYE);
        meta4.setDisplayName(Utils.translate("&aVanish"));
        lore.clear();
        lore.add(Utils.translate("&7Click dereho para"));
        lore.add(Utils.translate("&7desactivar el vanish."));
        meta4.setLore(lore);
        item2.setItemMeta(meta4);
        p.getInventory().setItem(8,item2);

        ItemStack cabeza=new ItemStack(Material.PLAYER_HEAD,1);
        SkullMeta sm=(SkullMeta) cabeza.getItemMeta();
        sm.setOwningPlayer(p);
        sm.setDisplayName(Utils.translate("&9Staff Online"));
        lore.clear();
        lore.add(Utils.translate("&7Click derecho"));
        lore.add(Utils.translate("&7para ver a los"));
        lore.add(Utils.translate("&7staffs conectados"));
        sm.setLore(lore);
        cabeza.setItemMeta(sm);
        p.getInventory().setItem(4,cabeza);






        //


        FileCreator config=plugin.getConfig();
        if(config.getBoolean("ModMode.Message-on-Enable")){
            for(Player player : Bukkit.getOnlinePlayers()){
                if(player.hasPermission("ocean.staff")){
                    player.sendMessage(config.getString("ModMode.Message").replaceAll("%player%",p.getName()));
                }
            }
        }

        plugin.vanishPlayer(p);
    }

    public void disableStaffMode(Player p){

        List<String> staffmode=plugin.getStaffmode();
        staffmode.remove(p.getName());
        plugin.setStaffmode(staffmode);
        p.sendMessage(Utils.translate("&4&lSTAFF &8► &fTe has desactivado el Staff Mode."));
        List<ElementosGuardados> elementosGuardados=plugin.getElementosGuardados();
        for(int i=0;i<elementosGuardados.size();i++){
            if(elementosGuardados.get(i).getOwner().equalsIgnoreCase(p.getName())){
                p.setGameMode(elementosGuardados.get(i).getGamemode());
                p.getInventory().setContents(elementosGuardados.get(i).getInventory());
                elementosGuardados.remove(i);
                return;
            }
        }
      //  Bukkit.getConsoleSender().sendMessage(Utils.translate("&cHa ocurrido un error devolviendole los items del inventario a "+p.getName()));

        for(Player player : Bukkit.getOnlinePlayers()){
            player.showPlayer(plugin,p);
        }


    }

    public boolean isInStaffMode(Player p){
        if(plugin.getStaffmode().contains(p.getName())){
            return true;
        }else return false;
    }

}
