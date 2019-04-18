package com.qq44920040.Minecraft.GemsAndMosaics.Entity;

import com.qq44920040.Minecraft.GemsAndMosaics.Main;
import com.qq44920040.Minecraft.GemsAndMosaics.Util.ContsNumber;
import com.qq44920040.Minecraft.GemsAndMosaics.Util.Transformation;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.regex.Pattern;

public class Gems {

    public static Boolean IsGems(ItemStack itemStack) {
        if (itemStack != null) {
            if (itemStack.getAmount() >= 1 && itemStack.hasItemMeta()) {
                ItemMeta itemMeta = itemStack.getItemMeta();
                if (itemMeta.hasLore() && itemMeta.hasDisplayName()) {
                    String str = itemMeta.getDisplayName();
                    String lores = itemMeta.getLore().toString();
                    if (Main.GemsLevelQuality.stream().filter(str::contains).count() == 1) {
                        if (Main.Gemsattribute.stream().filter(lores::contains).count() == 1) {
                            return Pattern.compile(ContsNumber.RexNumber).matcher(str).find();
                        }
                    }
                }
            }
        }
        return false;
    }

    public static ItemStack GemsUpLevel(ItemStack itemStack,int GemsLevel,Boolean IsLikeLevel){
        String strlevel = Transformation.a2r(GemsLevel);
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lores = itemMeta.getLore();
        String displayer = itemMeta.getDisplayName();
        displayer = displayer.replace(strlevel,Transformation.a2r(GemsLevel+1));
        String GemsAttribute = lores.get(ContsNumber.GemsAttributeLine);
        if (GemsAttribute.contains("%")){
            //宝石有百分比
        }else {
            //宝石无百分比
        }
        return itemStack;
    }
}
