package com.qq44920040.Minecraft.GemsAndMosaics.View;

import com.qq44920040.Minecraft.GemsAndMosaics.Util.ContsNumber;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerUi {
    public static Inventory PlayerOpenMosaicGui(){
        Inventory inventoryView = Bukkit.createInventory(null,54, ContsNumber.MosaicGuiTitle);
        ItemStack border = new ItemStack(Material.BREAD);
        ItemStack addblock = new ItemStack(Material.STAINED_GLASS_PANE);
        for (int i=0;i<54;i++){
            if (i<=9){
                inventoryView.setItem(i,border);
            }else if (i<=13){
                inventoryView.setItem(i,addblock);
            }else if (i==15){
                inventoryView.setItem(i,addblock);
            } else if (i==14||i==16){
                inventoryView.setItem(i,border);
            }else if (i==19||i==21){
                inventoryView.setItem(i,addblock);
            }else if (i==26||i==27){
                inventoryView.setItem(i,border);
            }else if (i>=28&&i<=30){
                inventoryView.setItem(i,addblock);
            }else if (i>=32&&i<=34){
                inventoryView.setItem(i,addblock);
            }else if (i==35||i==36){
                inventoryView.setItem(i,border);
            }else if (i>=37&&i<=43){
                inventoryView.setItem(i,addblock);
            }else if (i>=44){
                inventoryView.setItem(i,border);
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

    public static Inventory PlayerOpenPunch(){
        Inventory inventorView = Bukkit.createInventory(null,27,ContsNumber.PunchGuiTitle);
        ItemStack border = new ItemStack(Material.BREAD);
        ItemStack addblock = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemStack button = new ItemStack(Material.ARROW);
        for (int i=0;i<=26;i++){
            if (i<=9){
                inventorView.setItem(i,border);
            }else if (i>=11&&i<=13){
                inventorView.setItem(i,addblock);
            }else if (i==15){
                inventorView.setItem(i,border);
            }else if (i==16){
                inventorView.setItem(i,button);
            }else if (i>=17){
                inventorView.setItem(i,border);
            }
        }
        return inventorView;
    }
}
