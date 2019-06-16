package com.qq44920040.Minecraft.GemsAndMosaics.Util;

import com.qq44920040.Minecraft.GemsAndMosaics.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class publicItem {
    //物品能继续升级
    public static ItemStack MosaicEquip(ItemStack itemStack,String GemsLevel,String GemsQuality,String Attribute,String AttributeTogether,int MosaicTypeLine){
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = itemMeta.getLore();
        int i = EquipStartLineNumber(lore, true)+MosaicTypeLine;
        String ComposeStr = GemsLevel+":"+GemsQuality+":"+Attribute;
        lore.set(i,AttributeTogether);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        if (MosaicTypeLine==1){
            return NbtGetSet.SetItemData("yin",ComposeStr,itemStack);
        }else if (MosaicTypeLine==2){
            return NbtGetSet.SetItemData("yang",ComposeStr,itemStack);
        }else if (MosaicTypeLine==3){
            return NbtGetSet.SetItemData("jun",ComposeStr,itemStack);
        }
        return itemStack;
    }
    //取装备物品lore上的属性数值
    public static List<Integer> GetItemVaultNumber(String itemattbut){
        List<Integer> ss = new ArrayList<>();
        for(String sss:itemattbut.replaceAll("[^0-9]", ",").split(",")){
            if (sss.length()>0)
                ss.add(Integer.parseInt(sss));
        }
        return ss;
    }
    //判断一个装备是否符合
    public static boolean EquipCanMosaic(List<String> itemStacklore) {
        return itemStacklore.stream().filter(o -> o.contains(Main.EndLine) || o.contains(Main.StartLine)).count() == 2;
    }
    //得到装备的开始行数
    public static int EquipStartLineNumber(List<String> itemStacklore,Boolean IsStart){
       return IsStart?itemStacklore.indexOf(Main.StartLine):itemStacklore.indexOf(Main.EndLine);
    }
    //判断一个装备是否有是未打孔状态
    //获得宝石属性
    public static String GetGemsattribute(List<String> GemLore){
        return GemLore.get(1);
    }
    //给物品添加镶嵌上下格式
    public static ItemStack AddItemUpDownLine(ItemStack itemStack){
            ItemMeta itemMeta = itemStack.getItemMeta();
            if (itemMeta.hasLore()){
                List<String> lorelist = itemMeta.getLore();
                lorelist.add(Main.StartLine);
                lorelist.add(Main.CheckSlotLore);
                lorelist.add(Main.CheckSlotLore);
                lorelist.add(Main.SlotLore[2]);
                lorelist.add(Main.EndLine);
                itemMeta.setLore(lorelist);
            }else {
                List<String> lorelist = Arrays.asList(Main.StartLine,Main.CheckSlotLore,Main.CheckSlotLore,Main.SlotLore[2],Main.EndLine);
                itemMeta.setLore(lorelist);
            }
            itemStack.setItemMeta(itemMeta);
            return itemStack;
    }

    //拆卸装备并且清理lore
    public static ItemStack CleanGemsLore(ItemStack itemStack){
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = itemMeta.getLore();
        int i = EquipStartLineNumber(lore, true);
        lore.set(i+1,Main.CheckSlotLore);
        lore.set(i+2,Main.CheckSlotLore);
        lore.set(i+3,Main.SlotLore[3]);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
