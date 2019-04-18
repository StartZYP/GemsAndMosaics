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
        itemStack = NbtGetSet.SetItemData("MosaicLevel",String.valueOf(Level),itemStack);
        return NbtGetSet.SetItemData("MosaicType",String.valueOf(Level),itemStack);
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
