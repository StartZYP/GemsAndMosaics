package com.qq44920040.Minecraft.GemsAndMosaics.Entity;

import com.qq44920040.Minecraft.GemsAndMosaics.Util.ContsNumber;
import com.qq44920040.Minecraft.GemsAndMosaics.Util.Transformation;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.regex.Pattern;

import static com.qq44920040.Minecraft.GemsAndMosaics.Util.Transformation.a2r;
import static com.qq44920040.Minecraft.GemsAndMosaics.Util.Transformation.r2a;

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
