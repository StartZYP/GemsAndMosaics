package com.qq44920040.Minecraft.GemsAndMosaics.Util;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.regex.Pattern;

import static com.qq44920040.Minecraft.GemsAndMosaics.Util.Transformation.a2r;
import static com.qq44920040.Minecraft.GemsAndMosaics.Util.Transformation.r2a;

public class PublicItemStack {
    public static ItemStack UpMosaicPaper(ItemStack itemStack){
        ItemMeta itemMeta = itemStack.getItemMeta();
        String DisPlayName = itemMeta.getDisplayName();
        String Level = Pattern.compile("(l?x{0,3}|x[lc])(v?i{0,3}|i[vx])$").matcher(DisPlayName).group(0);
        String RePlaceLevel = a2r(r2a(Level)+1);
        String NowDisPlayerName = DisPlayName.replace(Level,RePlaceLevel);
        itemMeta.setDisplayName(NowDisPlayerName);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
