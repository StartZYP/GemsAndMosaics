package com.qq44920040.Minecraft.GemsAndMosaics.Entity;


import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.regex.Pattern;

import com.qq44920040.Minecraft.GemsAndMosaics.Util.ContsNumber;
import com.qq44920040.Minecraft.GemsAndMosaics.Util.Transformation;

public class MosaicPaper {
    public MosaicPaper(String mosaicPaperLoreKey) {
        MosaicPaperLoreKey = mosaicPaperLoreKey;
    }

    String MosaicPaperLoreKey;

    @Override
    public boolean equals(Object object){
        if (object!=null){
            if (object instanceof ItemStack){
                ItemStack item = (ItemStack) object;
                if (item.getAmount()>=ContsNumber.MosaicPaperLevelUpNeedNum&&item.hasItemMeta()){
                    ItemMeta itemMeta = item.getItemMeta();
                    if (itemMeta.hasDisplayName()){
                        return itemMeta.getDisplayName().contains(MosaicPaperLoreKey);
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
