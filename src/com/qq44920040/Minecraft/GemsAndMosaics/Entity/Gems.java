package com.qq44920040.Minecraft.GemsAndMosaics.Entity;

import com.qq44920040.Minecraft.GemsAndMosaics.Main;
import com.qq44920040.Minecraft.GemsAndMosaics.Util.ContsNumber;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.regex.Pattern;

public class Gems {

    public static Boolean IsGems(ItemStack itemStack){
        if (itemStack!=null){
                if (itemStack.getAmount()>=1&&itemStack.hasItemMeta()){
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta.hasLore()&&itemMeta.hasDisplayName()){
                        String str = itemMeta.getDisplayName();
                        String lores = itemMeta.getLore().toString();
                        if (Main.GemsLevelInfo.stream().filter(str::contains).count()==1){
                            if (Main.attribute.stream().filter(lores::contains).count()==1){
                                return Pattern.compile(ContsNumber.RexNumber).matcher(str).find();
                            }
                        }
                    }
                }
        }
        return false;
    }

    public ItemStack GemsUpLevel(ItemStack itemStack){

        return itemStack;
    }
}
