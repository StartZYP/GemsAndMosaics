package com.qq44920040.Minecraft.GemsAndMosaics.Entity;

import com.qq44920040.Minecraft.GemsAndMosaics.Main;
import com.qq44920040.Minecraft.GemsAndMosaics.Util.ContsNumber;
import com.qq44920040.Minecraft.GemsAndMosaics.Util.NbtGetSet;
import com.qq44920040.Minecraft.GemsAndMosaics.Util.Transformation;
import com.qq44920040.Minecraft.GemsAndMosaics.Util.publicItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class Gems {

    public static Boolean IsGems(ItemStack itemStack) {
        if (itemStack != null) {
            if (itemStack.getAmount() >= 1 && itemStack.hasItemMeta()) {
                if (NbtGetSet.GetItemDate("GemLevel",itemStack)!=null&&NbtGetSet.GetItemDate("GemQuality",itemStack)!=null&&NbtGetSet.GetItemDate("Attribute",itemStack)!=null){
                    return true;
                }
            }
        }
        return false;
    }
    public static ItemStack MakeGems(int Level,String Quality,String Attribute){
        ItemStack itemGems = new ItemStack(Material.BREAD);
        ItemMeta itemMeta = itemGems.getItemMeta();
        itemMeta.setDisplayName("宝石等级:"+Transformation.a2r(Level)+"宝石品质:"+Quality);
        itemMeta.setLore(Arrays.asList("这是宝石","宝石属性为:"+Attribute+Main.Gemsattribute.get(Attribute),"从天上而来"));
        itemGems.setItemMeta(itemMeta);
        itemGems =  NbtGetSet.SetItemData("GemLevel",String.valueOf(Level),itemGems);
        itemGems = NbtGetSet.SetItemData("GemQuality",Quality,itemGems);
        itemGems = NbtGetSet.SetItemData("Attribute",Attribute,itemGems);
        return itemGems;
    }

    public static List<Integer> GemsCalculateVaultAddOrTake(List<Integer> Value,int Level,Boolean IsAdd){

        return Value;
    }

    public static ItemStack GemsUpLevelOrTakeLevel(ItemStack itemStack,int GemsLevel,Boolean IsLikeQuality,Boolean IsLevelUp){
        String strlevel = Transformation.a2r(GemsLevel);
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lores = itemMeta.getLore();
        String displayer = itemMeta.getDisplayName();
        displayer = displayer.replace(strlevel,Transformation.a2r(GemsLevel+1));
        String GemsAttribute = lores.get(ContsNumber.GemsAttributeLine);
        if (IsLikeQuality){
            //这里写相同品质的宝石
            if (GemsAttribute.contains("%")){
                List<Integer> item = publicItem.GetItemVaultNumber(GemsAttribute);
                //宝石有百分比
            }else {
                List<Integer> item = publicItem.GetItemVaultNumber(GemsAttribute);

                publicItem.GetItemVaultNumber(GemsAttribute);
                //宝石无百分比
            }
        }else {
            //这里写非相同品质宝石随机等级
            if (GemsAttribute.contains("%")){
                List<Integer> item = publicItem.GetItemVaultNumber(GemsAttribute);
                //宝石有百分比
            }else {
                List<Integer> item = publicItem.GetItemVaultNumber(GemsAttribute);

                publicItem.GetItemVaultNumber(GemsAttribute);
                //宝石无百分比
            }
        }

        return itemStack;
    }
}
