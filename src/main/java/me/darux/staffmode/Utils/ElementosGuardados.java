package me.darux.staffmode.Utils;

import org.bukkit.GameMode;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ElementosGuardados {
    private GameMode gamemode;
    private ItemStack[] inventory;
    private String owner;

    public ElementosGuardados(GameMode gamemode, ItemStack[] inventory, String owner) {
        this.gamemode = gamemode;
        this.inventory = inventory;
        this.owner = owner;
    }

    public GameMode getGamemode() {
        return gamemode;
    }

    public void setGamemode(GameMode gamemode) {
        this.gamemode = gamemode;
    }

    public ItemStack[] getInventory() {
        return inventory;
    }

    public void setInventory(ItemStack[] inventory) {
        this.inventory = inventory;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
