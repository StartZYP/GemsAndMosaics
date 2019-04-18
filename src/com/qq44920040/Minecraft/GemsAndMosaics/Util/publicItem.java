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
    //物品减少函数
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
    //获取物品等级
    public static int GetItemLevel(ItemStack itemStack){
        String DisPlayName = itemStack.getItemMeta().getDisplayName();
        String Level = Pattern.compile(ContsNumber.RexNumber).matcher(DisPlayName).group(0);
        return Transformation.r2a(Level);
    }
    //获取物品品质
    public static String GetItemGemsLevelQuality(ItemStack itemStack){
        String keystr = itemStack.getItemMeta().getDisplayName();
        for (String str:Main.GemsLevelQuality){
            if (keystr.contains(str)){
                return str;
            }
        }
        return "";
    }
    //物品能继续升级
    public static boolean ItemCanUporDownLevel(ItemStack itemStack){
        String DisPlayName = itemStack.getItemMeta().getDisplayName();
        if (!Pattern.compile(ContsNumber.RexNumber).matcher(DisPlayName).find()){
            return false;
        }
        String Level = Pattern.compile(ContsNumber.RexNumber).matcher(DisPlayName).group(0);
        if (Level!=null){
            int Levenumber = Transformation.r2a(Level);
            return Levenumber<ContsNumber.MaxGemsComposeLevel&&Levenumber>ContsNumber.MinGemsComposeLevel;
        }
        return false;
    }
    //取装备物品lore上的属性数值
    public static List<Integer> GetItemVaultNumber(String itemattbut){
        Pattern p = Pattern.compile("\\d*");
        Matcher m = p.matcher(itemattbut);
        List<Integer> list = new ArrayList<>();
        while (m.find()){
            list.add(Integer.parseInt(m.group()));
        }
        return list;
    }
    //判断一个装备是否符合
    public static boolean EquipCanMosaic(List<String> itemStacklore) {
        return itemStacklore.stream().filter(o -> o.contains(Main.EndLine) || o.contains(Main.StartLine)).count() == 2;
    }
    //得到装备的开始行数和结束行数
    public static int EquipStartLineNumber(List<String> itemStacklore,Boolean IsStart){
       return IsStart?itemStacklore.indexOf(Main.StartLine):itemStacklore.indexOf(Main.EndLine);
    }
    //得到装备Lore行的List集合
    public static List<String> GetEquipLoreList(int StartLine,int Endline,List<String> lorelist){
        List<String> MosaicLore = new ArrayList<>();
        for (int i=StartLine;i<=Endline;i++){
            MosaicLore.add(lorelist.get(i));
        }
        return MosaicLore;
    }
    //获得宝石属性
    public static String GetGemsattribute(List<String> GemLore){
        return GemLore.get(1);
    }
    //给物品添加镶嵌上下格式
    public static ItemStack AddItemUpDownLine(ItemStack itemStack){
        if (itemStack.hasItemMeta()){
            ItemMeta itemMeta = itemStack.getItemMeta();
            if (itemMeta.hasLore()){
                List<String> lorelist = itemMeta.getLore();
                lorelist.add(Main.StartLine);
                lorelist.add(Main.CheckSlotLore);
                lorelist.add(Main.CheckSlotLore);
                lorelist.add(Main.CheckSlotLore);
                lorelist.add(Main.EndLine);
                itemMeta.setLore(lorelist);
            }else {
                List<String> lorelist = Arrays.asList(Main.StartLine,Main.CheckSlotLore,Main.CheckSlotLore,Main.EndLine);
                itemMeta.setLore(lorelist);
            }
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
        return itemStack;
    }
    //给物品添加各种孔
    public static ItemStack AddPunch(ItemStack itemStack,int RepalceLine,String PunchLore){
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lores = itemMeta.getLore();
        lores.set(RepalceLine,PunchLore);
        itemMeta.setLore(lores);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    //给物品添加孔位镶嵌宝石丢NBT属性
    public static ItemStack AddItemGemsattributeNBT(ItemStack itemStack,int GemsLevel,String Quality,String Attribute){
        return NbtGetSet.SetItemData(Attribute,GemsLevel+":"+Quality,itemStack);
    }

}
