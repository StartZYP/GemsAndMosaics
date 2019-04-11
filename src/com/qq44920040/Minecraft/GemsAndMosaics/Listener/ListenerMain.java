package com.qq44920040.Minecraft.GemsAndMosaics.Listener;


import com.qq44920040.Minecraft.GemsAndMosaics.Entity.MosaicPaper;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static com.qq44920040.Minecraft.GemsAndMosaics.Main.MosaicList;


public class ListenerMain implements Listener {
    @EventHandler
    public void PlayerInteractItem(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        if (itemStack!=null&&itemStack.getType()!= Material.AIR){
            for (MosaicPaper mosaicPaper:MosaicList){
                if (mosaicPaper.equals(itemStack)){
                    MosaicPaper.TakeMosaicPaper(player,itemStack);
                    player.getInventory().addItem(MosaicPaper.UpMosaicPaper(itemStack));
                    player.sendMessage("合成镶嵌符成功");
                }
            }
        }
    }
}
