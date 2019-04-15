package com.qq44920040.Minecraft.GemsAndMosaics.Entity;

import com.qq44920040.Minecraft.GemsAndMosaics.Main;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
}
