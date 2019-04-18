package com.qq44920040.Minecraft.GemsAndMosaics.Entity;

import com.qq44920040.Minecraft.GemsAndMosaics.Main;
import com.qq44920040.Minecraft.GemsAndMosaics.Util.NbtGetSet;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class DismantlePaper {

    public static boolean IsProtectPaper(ItemStack itemStack){
        if (itemStack!=null){
            if (itemStack.getAmount()== 1&&itemStack.hasItemMeta()){
                ItemMeta itemMeta = itemStack.getItemMeta();
                if (itemMeta.hasDisplayName()){
                    return itemMeta.getDisplayName().contains(Main.DismantlePaperKey);
                }
            }
        }
        return false;
    }

    public static ItemStack MakeDismantlePaper(){
        ItemStack itemStack = new ItemStack(Material.BLAZE_ROD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("拆卸符类型:");
        itemMeta.setLore(Arrays.asList("拆卸符","很牛逼的打"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
