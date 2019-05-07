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



    public static ItemStack MakeMosaicPaper(int Level,String Type){
        ItemStack itemStack = new ItemStack(Material.BARRIER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("镶嵌符等级:"+Transformation.a2r(Level)+"镶嵌符:"+Type);
        itemMeta.setLore(Arrays.asList("这是打孔符","这是他的lore"));
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
