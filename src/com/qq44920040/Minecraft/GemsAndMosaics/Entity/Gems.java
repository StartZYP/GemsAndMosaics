package com.qq44920040.Minecraft.GemsAndMosaics.Entity;

import com.qq44920040.Minecraft.GemsAndMosaics.Main;
import com.qq44920040.Minecraft.GemsAndMosaics.Util.NbtGetSet;
import com.qq44920040.Minecraft.GemsAndMosaics.Util.Transformation;
import com.qq44920040.Minecraft.GemsAndMosaics.Util.publicItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class Gems {

    public static Boolean IsGems(ItemStack itemStack) {
        if (itemStack != null) {
            if (itemStack.getAmount() >= 1 && itemStack.hasItemMeta()) {
               return NbtGetSet.GetItemDate("GemLevel",itemStack)!=null&&NbtGetSet.GetItemDate("GemQuality",itemStack)!=null&&NbtGetSet.GetItemDate("Attribute",itemStack)!=null;
            }
        }
        return false;
    }

    public static boolean GemsIsRange(ItemStack itemStack){
        int LevelNumber = Integer.parseInt(NbtGetSet.GetItemDate("GemLevel",itemStack));
        return LevelNumber>=1&&LevelNumber<=12;
    }

    public static ItemStack MakeGems(int Level,String Quality,String Attribute){
        String Attributevalue = Main.Gemsattribute.get(Attribute);
        if (Attributevalue.contains("%")){
            List<Integer> list = publicItem.GetItemVaultNumber(Attributevalue);
            double value = (double) Main.GemsLevelQuality.indexOf(Quality) + (double)Level;
            for (int s:list){
                Attributevalue = Attributevalue.replace(String.valueOf(s),String.valueOf((int)value+s));
            }
        }else {
            List<Integer> list = publicItem.GetItemVaultNumber(Attributevalue);
            double valuequality = (double) Main.GemsLevelQuality.indexOf(Quality)/10;
            double valuelevel = (double)Level/10;
            for (int s:list){
                Attributevalue = Attributevalue.replace(String.valueOf(s),String.valueOf((int)(s+(s*valuelevel)+(valuequality*s))));
            }
        }
        ItemStack itemGems = new ItemStack(Material.BREAD);
        ItemMeta itemMeta = itemGems.getItemMeta();
        itemMeta.setDisplayName("宝石等级:"+Transformation.a2r(Level)+"宝石品质:"+Quality);
        itemMeta.setLore(Arrays.asList("这是宝石","宝石属性为:"+Attribute+Attributevalue,"从天上而来"));
        itemGems.setItemMeta(itemMeta);
        itemGems =  NbtGetSet.SetItemData("GemLevel",String.valueOf(Level),itemGems);
        itemGems = NbtGetSet.SetItemData("GemQuality",Quality,itemGems);
        itemGems = NbtGetSet.SetItemData("Attribute",Attribute,itemGems);
        return itemGems;
    }
}


//宝石数值类增幅：
//        取宝石等级配置项: xxxx-xxxxx
//        再取宝石品质: 如优秀
//        最终值 = 宝石等级配置项基础随机值+递增百分比0.3(因为优秀为第三个)*基础值*宝石等级配置项随机值/0.level*基础值
//        如果属性为范围属性：
//        则取两次最终值小在前大在后
//        如果属性为单值：
//        则取一次
//        宝石百分比值增幅:
//        取宝石等级配置项: xx%
//        再取宝石品质: 如优秀
//        最终值 = 宝石等级配置项值+宝石品质
//
//        自定义宝石：
//        可以按照规范写即可
//
//
//        镶嵌符有类型 阴钥 10张阴钥 才能升级更高
//        II级镶嵌符可以镶嵌I II级宝石
//        镶嵌的时候 宝石等级必须和镶嵌符等级一致才可以使用
