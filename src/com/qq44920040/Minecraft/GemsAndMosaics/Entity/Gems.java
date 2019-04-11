package com.qq44920040.Minecraft.GemsAndMosaics.Entity;

import com.qq44920040.Minecraft.GemsAndMosaics.Main;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Gems {
    @Override
    public boolean equals(Object object){
        if (object!=null){
            if (object instanceof ItemStack){
                ItemStack item = (ItemStack) object;
                if (item.hasItemMeta()){
                    ItemMeta itemMeta = item.getItemMeta();
                    if (itemMeta.hasDisplayName()){
                        String str = itemMeta.getDisplayName();
                        Main.GemsLevelInfo.stream().filter(str::contains).count();
                        return itemMeta.getDisplayName().contains(MosaicPaperLoreKey);
                    }
                }
            }
        }
        return false;
    }
}
