package com.qq44920040.Minecraft.GemsAndMosaics.Entity;

import com.qq44920040.Minecraft.GemsAndMosaics.Main;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.regex.Pattern;

public class Gems {
    public final int ComposeNum = 5;
    @Override
    public boolean equals(Object object){
        if (object!=null){
            if (object instanceof ItemStack){
                ItemStack item = (ItemStack) object;
                if (item.hasItemMeta()){
                    ItemMeta itemMeta = item.getItemMeta();
                    if (itemMeta.hasLore()&&itemMeta.hasDisplayName()){
                        String str = itemMeta.getDisplayName();
                        String lores = itemMeta.getLore().toString();
                        if (Main.GemsLevelInfo.stream().filter(str::contains).count()==1){
                            if (Main.attribute.stream().filter(lores::contains).count()==1){
                                return Pattern.compile("(l?x{0,3}|x[lc])(v?i{0,3}|i[vx])$").matcher(str).find();
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
