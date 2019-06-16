package com.qq44920040.Minecraft.GemsAndMosaics.Entity;


import com.qq44920040.Minecraft.GemsAndMosaics.Main;
import com.qq44920040.Minecraft.GemsAndMosaics.Util.NbtGetSet;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.security.Key;
import java.util.Arrays;
import java.util.regex.Pattern;

import com.qq44920040.Minecraft.GemsAndMosaics.Util.ContsNumber;
import com.qq44920040.Minecraft.GemsAndMosaics.Util.Transformation;

public class MosaicPaper {

    public static Boolean IsMosaicPaper(ItemStack itemStack){
        if (NbtGetSet.GetItemDate("MosaicType",itemStack)!=null&&NbtGetSet.GetItemDate("MosaicLevel",itemStack)!=null){
            if (!NbtGetSet.GetItemDate("MosaicType",itemStack).equalsIgnoreCase("")){
                return true;
            }
        }
        return false;
    }


    public static ItemStack MakeMosaicPaper(int Level,String Type){
        ItemStack itemStack = new ItemStack(Material.BARRIER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§8§l[§b§l►§8§l]§8§l"+Transformation.a2r(Level)+"级-镶嵌符-"+Type+"§8§l[§b§l◄§8§l]");
        itemMeta.setLore(Arrays.asList("§8§m--]§f§m---------§f[§b道具信息§f]§f§m---------§8§m[--","§8§l[§d§l☣§8§l]道具类型:符咒","§8§l[§d§l☣§8§l]道具等级:"+Transformation.a2r(Level),"§8§l[§d§li§8§l]ITEMUUID:0205"));
        itemStack.setItemMeta(itemMeta);
        itemStack.setAmount(1);
        itemStack = NbtGetSet.SetItemData("MosaicLevel",String.valueOf(Level),itemStack);
        return NbtGetSet.SetItemData("MosaicType",String.valueOf(Type),itemStack);
    }

    public static boolean MosaicIsRange(ItemStack itemStack){
        int LevelNumber = Integer.parseInt(NbtGetSet.GetItemDate("MosaicLevel",itemStack));
        return LevelNumber>=1&&LevelNumber<=12;
    }


}
