package com.qq44920040.Minecraft.GemsAndMosaics.Entity;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DecomposePaper {
    public DecomposePaper(String decomposeKey) {
        DecomposeKey = decomposeKey;
    }
    String DecomposeKey;

    @Override
    public boolean equals(Object object){
        if (object!=null){
            if (object instanceof ItemStack){
                ItemStack item = (ItemStack) object;
                if (item.hasItemMeta()){
                    ItemMeta itemMeta = item.getItemMeta();
                    if (itemMeta.hasDisplayName()){
                        return itemMeta.getDisplayName().contains(DecomposeKey);
                    }
                }
            }
        }
        return false;
    }
}
