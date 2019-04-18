package com.qq44920040.Minecraft.GemsAndMosaics.View;

import com.qq44920040.Minecraft.GemsAndMosaics.Util.ContsNumber;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerUi {
    public static Inventory PlayerOpenMosaicGui(){
        Inventory inventoryView = Bukkit.createInventory(null,53, ContsNumber.MosaicGuiTitle);
        ItemStack border = new ItemStack(Material.BREAD);
        ItemStack addblock = new ItemStack(Material.STAINED_GLASS_PANE);
        for (int i=0;i<54;i++){
            if (i<=9){
                inventoryView.addItem(border);
            }else if (i<=12){
                inventoryView.addItem(addblock);
            }else if (i==17||i==18){
                inventoryView.addItem(border);
            }else if (i==19||i==21){
                inventoryView.addItem(addblock);
            }else if (i==26||i==27){
                inventoryView.addItem(border);
            }else if (i>=28&&i<=30){
                inventoryView.addItem(addblock);
            }else if (i>=32&&i<=34){
                inventoryView.addItem(addblock);
            }else if (i==35||i==36){
                inventoryView.addItem(border);
            }else if (i>=37&&i<=43){
                inventoryView.addItem(addblock);
            }else if (i>=44){
                inventoryView.addItem(border);
            }
        }
        return inventoryView;
    }

    public static Inventory PlayerOpenGamsGui(){
        Inventory inventorView = Bukkit.createInventory(null,27,ContsNumber.GemsComposeGuiTitle);
        ItemStack border = new ItemStack(Material.BREAD);
        ItemStack addblock = new ItemStack(Material.STAINED_GLASS_PANE);
        for (int i=0;i<=26;i++){
            if (i<=9){
                inventorView.setItem(i,border);
            }else if (i==16){
                inventorView.setItem(i,addblock);
            }else if (i==15){
                inventorView.setItem(i,border);
            }else if (i>=17){
                inventorView.setItem(i,border);
            }
        }
        return inventorView;
    }
}
