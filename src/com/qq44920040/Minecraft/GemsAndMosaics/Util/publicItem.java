package com.qq44920040.Minecraft.GemsAndMosaics.Util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.regex.Pattern;

public class publicItem {

    public static void TakeComposeItem(Player player, ItemStack itemStack,int Num){
        int Amount = itemStack.getAmount();
        if (Amount==Num){
            player.getInventory().remove(itemStack);
        }else {
            player.getInventory().remove(itemStack);
            itemStack.setAmount(Amount-Num);
            player.getInventory().addItem(itemStack);
        }
    }

    public static boolean ItemCanUporDownLevel(ItemStack itemStack){
        String DisPlayName = itemStack.getItemMeta().getDisplayName();
        if (!Pattern.compile("(l?x{0,3}|x[lc])(v?i{0,3}|i[vx])$").matcher(DisPlayName).find()){
            return false;
        }
        String Level = Pattern.compile("(l?x{0,3}|x[lc])(v?i{0,3}|i[vx])$").matcher(DisPlayName).group(0);
        if (Level!=null){
            int Levenumber = Transformation.r2a(Level);
            return Levenumber<ContsNumber.MaxGemsComposeLevel&&Levenumber>ContsNumber.MinGemsComposeLevel;
        }
        return false;
    }

    public static boolean EquipCanMosaic(ItemStack itemStack){

        return false;
    }
}
