package com.qq44920040.Minecraft.GemsAndMosaics.Util;

import com.qq44920040.Minecraft.GemsAndMosaics.Main;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
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

    public static boolean EquipCanMosaic(List<String> itemStacklore) {
        return itemStacklore.stream().filter(o -> o.contains(Main.EndLine) || o.contains(Main.StartLine)).count() == 2;
    }

    public static int EquipStartLineNumber(List<String> itemStacklore,Boolean IsStart){
       return IsStart?itemStacklore.indexOf(Main.StartLine):itemStacklore.indexOf(Main.EndLine);
    }

    public static List<String> GetEquipLoreList(int StartLine,int Endline,List<String> lorelist){
        List<String> MosaicLore = new ArrayList<>();
        for (int i=StartLine;i<=Endline;i++){
            MosaicLore.add(lorelist.get(i));
        }
        return MosaicLore;
    }

    public static String GetGemsattribute(List<String> GemLore){
        return GemLore.get(1);
    }
}
