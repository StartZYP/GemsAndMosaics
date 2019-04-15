package com.qq44920040.Minecraft.GemsAndMosaics.Util;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagLong;
import net.minecraft.server.v1_12_R1.NBTTagString;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.Date;

public class NbtGetSet {

    public static ItemStack SetItemData(String Key,String Vaule, ItemStack itemStack){
        net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound compound = (nmsItem.hasTag()) ? nmsItem.getTag() : new NBTTagCompound();
        compound.set(Key, new NBTTagString(Vaule));
        nmsItem.setTag(compound);
        ItemStack hasNBTItem = CraftItemStack.asBukkitCopy(nmsItem);
        return hasNBTItem;
    }

    public static String GetItemDate(String key,ItemStack itemStack){
        net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        if (nmsItem.hasTag()){
            NBTTagCompound compound = nmsItem.getTag();
            return compound.getString(key);
        }
        return "";
    }

}
