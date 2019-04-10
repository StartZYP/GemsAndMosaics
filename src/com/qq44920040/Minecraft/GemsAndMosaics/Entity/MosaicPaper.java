package com.qq44920040.Minecraft.GemsAndMosaics.Entity;


import net.minecraft.server.v1_12_R1.ItemMapEmpty;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MosaicPaper {
    public String MosaicPaperLoreKey;
    @Override
    public boolean equals(Object object){
        if (object!=null){
            if (object instanceof ItemStack){
                ItemStack item = (ItemStack) object;
                if (item.hasItemMeta()){
                    ItemMeta itemMeta = item.getItemMeta();
                    if (itemMeta.hasDisplayName()){
                        return itemMeta.getDisplayName().contains(MosaicPaperLoreKey);
                    }
                }
            }
        }
        return false;
    }

    public ItemStack UpMosaicPaper(ItemStack itemStack){
        ItemMeta itemMeta = itemStack.getItemMeta();
        String DisPlayName = itemMeta.getDisplayName();


        return itemStack;
    }
}
