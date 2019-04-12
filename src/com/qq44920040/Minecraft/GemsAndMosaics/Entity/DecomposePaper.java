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
    public static ItemStack UpMosaicPaper(ItemStack itemStack){
        itemStack.setAmount(1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        String DisPlayName = itemMeta.getDisplayName();
        String Level = Pattern.compile("(l?x{0,3}|x[lc])(v?i{0,3}|i[vx])$").matcher(DisPlayName).group(0);
        String RePlaceLevel = Transformation.a2r(Transformation.r2a(Level)+1);
        String NowDisPlayerName = DisPlayName.replace(Level,RePlaceLevel);
        itemMeta.setDisplayName(NowDisPlayerName);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
