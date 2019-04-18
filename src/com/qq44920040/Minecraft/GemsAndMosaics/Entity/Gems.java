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
        if (Attribute.contains("%")){
            List<Integer> list = publicItem.GetItemVaultNumber(Main.Gemsattribute.get(Attribute));

        }else {

        }
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


//宝石数值类增幅：
//        取宝石等级配置项: xxxx-xxxxx
//        再取宝石品质: 如优秀
//        最终值 = 宝石等级配置项随机值*递增百分比0.3(因为优秀为第三个)+宝石等级配置项随机值
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
