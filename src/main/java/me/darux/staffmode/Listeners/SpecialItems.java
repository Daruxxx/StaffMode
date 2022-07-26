package me.darux.staffmode.Listeners;

import me.darux.staffmode.Cooldowns.FreezeFix;
import me.darux.staffmode.Main;
import me.darux.staffmode.SS.SSUtils;
import me.darux.staffmode.Utils.StaffModeUtils;
import me.darux.staffmode.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class SpecialItems implements Listener {
    private Main plugin;

    public SpecialItems(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRightCLick(PlayerInteractEvent e){
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            Player p = e.getPlayer();
            StaffModeUtils staffModeUtils=new StaffModeUtils(plugin);
            if(staffModeUtils.isInStaffMode(p)){
                if(p.getItemInHand().getType().equals(Material.LIME_DYE)){
                    plugin.showPlayer(p);
                    p.sendMessage(Utils.translate("&cHas desactivado el vanish"));


                    ItemStack item=new ItemStack(Material.LIGHT_GRAY_DYE,1);
                    ItemMeta meta=item.getItemMeta();
                    List<String> lore=new ArrayList<>();
                    meta.setDisplayName(Utils.translate("&7Vanish"));
                    lore.add(Utils.translate("&7Click dereho para"));
                    lore.add(Utils.translate("&7activar el vanish."));
                    meta.setLore(lore);
                    item.setItemMeta(meta);
                    p.getInventory().setItem(8,item);

                }else if(p.getItemInHand().getType().equals(Material.LIGHT_GRAY_DYE)){


                plugin.vanishPlayer(p);
                p.sendMessage(Utils.translate("&aHas activado el vanish"));

                    ItemStack item=new ItemStack(Material.LIME_DYE,1);
                    ItemMeta meta=item.getItemMeta();
                    List<String> lore=new ArrayList<>();
                    meta.setDisplayName(Utils.translate("&aVanish"));
                    lore.add(Utils.translate("&7Click dereho para"));
                    lore.add(Utils.translate("&7desactivar el vanish."));
                    meta.setLore(lore);
                    item.setItemMeta(meta);
                    p.getInventory().setItem(8,item);
                }
            }
        }




    }

    @EventHandler
    public void a(PlayerInteractAtEntityEvent e){
        if(e.getRightClicked().getType().equals(EntityType.PLAYER)){
            Player p=e.getPlayer();
            StaffModeUtils staffModeUtils=new StaffModeUtils(plugin);
            if(staffModeUtils.isInStaffMode(p)){
                if(p.getItemInHand().getType().equals(Material.ICE)){
                    Player freezed=(Player)e.getRightClicked();
                    FreezeFix cooldownManager=new FreezeFix(plugin);
                    if(cooldownManager.getCooldown(p).equals("-1")){
                        cooldownManager.crearCooldown(p);
                        SSUtils.FreezePlayer(freezed,plugin,p);
                    }
                }
            }

        }
    }

    @EventHandler
    public void interact(PlayerInteractEvent e){
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals( Action.RIGHT_CLICK_BLOCK)){
            if(plugin.getStaffmode().contains(e.getPlayer().getName())){
                if(e.getPlayer().getItemInHand().getType().equals(Material.MUSIC_DISC_13)){
                    FreezeFix freezeFix=new FreezeFix(plugin);
                    if(freezeFix.getCooldown(e.getPlayer()).equals("-1")){
                        freezeFix.crearCooldown(e.getPlayer());
                        int online=plugin.getServer().getOnlinePlayers().size();
                        int random=(int)Math.floor(Math.random()*online);

                        Player[] onlines= plugin.getServer().getOnlinePlayers().toArray(new Player[0]);
                        Player tp=onlines[random];
                        e.getPlayer().teleport(tp);
                        e.getPlayer().sendMessage(Utils.translate("&bHas sido teletransportado hasta &7"+onlines[random].getName()));
                    }

                }
            }
        }
    }



    @EventHandler
    public void rightclick(PlayerInteractEvent e){
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) | e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(plugin.getStaffmode().contains(e.getPlayer().getName())){
                Player p=e.getPlayer();
                if(p.getItemInHand().getType().equals(Material.PLAYER_HEAD)){
                    Inventory inv= Bukkit.createInventory(null,54,Utils.translate("&7Staff Online"));
                    List<Player> staffonline=new ArrayList<>();
                    ItemStack item=new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE,1);

                    inv.setItem(0,item);
                    inv.setItem(1,item);
                    inv.setItem(9,item);
                    inv.setItem(8,item);
                    inv.setItem(7,item);
                    inv.setItem(17,item);
                    inv.setItem(36,item);
                    inv.setItem(45,item);
                    inv.setItem(46,item);
                    inv.setItem(52,item);
                    inv.setItem(53,item);
                    inv.setItem(44,item);
                    item=new ItemStack(Material.WHITE_STAINED_GLASS_PANE,1);
                    for(int i=2;i<7;i++){
                        inv.setItem(i,item);
                    }
                    for(int i=47;i<52;i++){
                        inv.setItem(i,item);
                    }
                    inv.setItem(18,item);
                    inv.setItem(27,item);
                    inv.setItem(26,item);
                    inv.setItem(35,item);



                    for(Player player : Bukkit.getOnlinePlayers()){
                        if(player.hasPermission("ocean.staff")){
                            staffonline.add(player);
                        }
                    }
                    for(Player player : staffonline){
                         item=new ItemStack(Material.PLAYER_HEAD,1);
                        SkullMeta meta= (SkullMeta) item.getItemMeta();

                        meta.setDisplayName(Utils.translate("&9"+player.getName()));
                        List<String> lore=new ArrayList<>();
                        lore.add(" ");
                        if(plugin.getStaffmode().contains(player.getName())){
                            lore.add(Utils.translate(" &7Staff mode: &aactivado"));
                        }else {
                            lore.add(Utils.translate(" &7Staff mode: &cdesactivado"));
                        }
                        if(plugin.isVanished(player)){
                            lore.add(Utils.translate(" &7Vanish: &aactivado"));
                        }else{
                            lore.add(Utils.translate(" &7Vanish: &cdesactivado"));
                        }
                        lore.add(" ");
                        meta.setLore(lore);
                        meta.setOwner(player.getName());
                        item.setItemMeta(meta);
                        inv.addItem(item);
                    }


                    p.openInventory(inv);
                }
            }
        }
    }

    @EventHandler
    public void click(InventoryClickEvent e){
        if(plugin.getStaffmode().contains(e.getWhoClicked().getName())){
            if(e.getClickedInventory().getItem(0).getType().equals(Material.PLAYER_HEAD)){
                ItemStack item=e.getCurrentItem();
                Bukkit.dispatchCommand(e.getWhoClicked(),"tp "+ ChatColor.stripColor(item.getItemMeta().getDisplayName()));
            }
        }
    }


}
