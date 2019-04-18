package com.qq44920040.Minecraft.GemsAndMosaics.Entity;

import com.qq44920040.Minecraft.GemsAndMosaics.Util.NbtGetSet;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;


public class PunchPaper {
    public static boolean IsPunchPaper(ItemStack itemStack){
        if (itemStack!=null){
                if (itemStack.getAmount()== 1&&itemStack.hasItemMeta()){
                        return NbtGetSet.GetItemDate("PunchType",itemStack)!=null;
                }
        }
        return false;
    }

    public static ItemStack MakePunchPaper(String Type){
        ItemStack itemStack = new ItemStack(Material.BLAZE_ROD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("打孔符类型:"+Type);
        itemMeta.setLore(Arrays.asList("打孔符","很牛逼的打孔符"));
        itemStack.setItemMeta(itemMeta);
        return NbtGetSet.SetItemData("PunchType",Type,itemStack);
    }


}
