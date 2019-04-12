package com.qq44920040.Minecraft.GemsAndMosaics.Listener;


import com.qq44920040.Minecraft.GemsAndMosaics.Entity.MosaicPaper;
import com.qq44920040.Minecraft.GemsAndMosaics.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.qq44920040.Minecraft.GemsAndMosaics.Util.publicItem;
import com.qq44920040.Minecraft.GemsAndMosaics.Util.ContsNumber;

public class ListenerMain implements Listener {
    @EventHandler
    public void PlayerInteractItem(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        if (itemStack!=null&&itemStack.getType()!= Material.AIR){
            for (MosaicPaper mosaicPaper: Main.MosaicList){
                if (mosaicPaper.equals(itemStack)){
                    if (publicItem.ItemCanUporDownLevel(itemStack)){
                        publicItem.TakeComposeItem(player,itemStack,ContsNumber.MosaicPaperLevelUpNeedNum);
                        player.getInventory().addItem(MosaicPaper.UpMosaicPaper(itemStack));
                        player.sendMessage("合成镶嵌符成功");
                    }else {
                        player.sendMessage("你的物品等级貌似超出或小了");
                    }
                }
            }
            if (Main.gems.equals(itemStack)){

            }
        }
    }
}
