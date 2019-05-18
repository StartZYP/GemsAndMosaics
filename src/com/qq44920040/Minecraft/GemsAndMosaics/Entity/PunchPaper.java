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
        itemMeta.setDisplayName("&8&l[&b&l►&8&l]&8&l打孔符-"+Type+"&8&l[&b&l◄&8&l]");
        itemMeta.setLore(Arrays.asList("&8&m--]&f&m---------&f[&b道具信息&f]&f&m---------&8&m[--","&8&l[&d&l☣&8&l]道具类型:符咒","&8&l[&d&li&8&l]ITEMUUID:0204"));
        itemStack.setItemMeta(itemMeta);
        return NbtGetSet.SetItemData("PunchType",Type,itemStack);
    }

}
