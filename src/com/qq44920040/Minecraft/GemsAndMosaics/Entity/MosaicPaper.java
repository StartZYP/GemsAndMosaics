package com.qq44920040.Minecraft.GemsAndMosaics.Entity;


import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.regex.Pattern;

import static com.qq44920040.Minecraft.GemsAndMosaics.Util.Transformation.a2r;
import static com.qq44920040.Minecraft.GemsAndMosaics.Util.Transformation.r2a;

public class MosaicPaper {
    public MosaicPaper(String mosaicPaperLoreKey) {
        MosaicPaperLoreKey = mosaicPaperLoreKey;
    }

    String MosaicPaperLoreKey;
    static final int LevelUpNeedNum =10;
    @Override
    public boolean equals(Object object){
        if (object!=null){
            if (object instanceof ItemStack){
                ItemStack item = (ItemStack) object;
                if (item.getAmount()>=LevelUpNeedNum&&item.hasItemMeta()){
                    ItemMeta itemMeta = item.getItemMeta();
                    if (itemMeta.hasDisplayName()){
                        return itemMeta.getDisplayName().contains(MosaicPaperLoreKey);
                    }
                }
            }
        }
        return false;
    }
    public static void TakeMosaicPaper(Player player,ItemStack itemStack){
        int Amount = itemStack.getAmount();
        if (Amount==LevelUpNeedNum){
            player.getInventory().remove(itemStack);
        }else {
            player.getInventory().remove(itemStack);
            itemStack.setAmount(Amount-LevelUpNeedNum);
            player.getInventory().addItem(itemStack);
        }
    }

    public static ItemStack UpMosaicPaper(ItemStack itemStack){
        itemStack.setAmount(1);
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
