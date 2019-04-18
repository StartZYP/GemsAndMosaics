package com.qq44920040.Minecraft.GemsAndMosaics.Entity;

import com.qq44920040.Minecraft.GemsAndMosaics.Main;
import com.qq44920040.Minecraft.GemsAndMosaics.Util.ContsNumber;
import com.qq44920040.Minecraft.GemsAndMosaics.Util.Transformation;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.regex.Pattern;

import static com.qq44920040.Minecraft.GemsAndMosaics.Util.Transformation.a2r;
import static com.qq44920040.Minecraft.GemsAndMosaics.Util.Transformation.r2a;

public class DecomposePaper {
    public static boolean IsDecomposePaper(ItemStack itemStack){
        if (itemStack!=null){
                if (itemStack.getAmount()==1&&itemStack.hasItemMeta()){
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta.hasDisplayName()){
                        return itemMeta.getDisplayName().contains(Main.decomposePaperKey);
                    }
                }
        }
        return false;
    }

    public static ItemStack MakeDecomposePaper(){
        ItemStack itemStack = new ItemStack(Material.BLAZE_ROD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("分解符");
        itemMeta.setLore(Arrays.asList("打dsds孔符","很牛逼的打"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
