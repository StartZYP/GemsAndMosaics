package com.qq44920040.Minecraft.GemsAndMosaics.Entity;


import com.qq44920040.Minecraft.GemsAndMosaics.Main;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.security.Key;
import java.util.regex.Pattern;

import com.qq44920040.Minecraft.GemsAndMosaics.Util.ContsNumber;
import com.qq44920040.Minecraft.GemsAndMosaics.Util.Transformation;

public class MosaicPaper {


    public static boolean IsMosaicPaper(ItemStack itemStack){
        if (itemStack!=null){
                if (itemStack.getAmount()==1&&itemStack.hasItemMeta()){
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta.hasDisplayName()){
                        for (String key:Main.MosaicArrayKey){
                            if (itemMeta.getDisplayName().contains(key)){
                                return true;
                            }
                        }
                    }
                }
        }
        return false;
    }


    public static ItemStack UpMosaicPaper(ItemStack itemStack){
        itemStack.setAmount(1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        String DisPlayName = itemMeta.getDisplayName();
        String Level = Pattern.compile(ContsNumber.RexNumber).matcher(DisPlayName).group(0);
        String RePlaceLevel = Transformation.a2r(Transformation.r2a(Level)+1);
        String NowDisPlayerName = DisPlayName.replace(Level,RePlaceLevel);
        itemMeta.setDisplayName(NowDisPlayerName);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
