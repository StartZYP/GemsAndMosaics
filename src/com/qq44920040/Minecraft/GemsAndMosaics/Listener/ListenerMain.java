package com.qq44920040.Minecraft.GemsAndMosaics.Listener;


import com.qq44920040.Minecraft.GemsAndMosaics.Entity.Gems;
import com.qq44920040.Minecraft.GemsAndMosaics.Entity.MosaicPaper;
import com.qq44920040.Minecraft.GemsAndMosaics.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.qq44920040.Minecraft.GemsAndMosaics.Util.publicItem;
import com.qq44920040.Minecraft.GemsAndMosaics.Util.ContsNumber;

import java.util.ArrayList;
import java.util.List;

public class ListenerMain implements Listener {
    @EventHandler
    public void PlayerInteractItem(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        if (itemStack!=null&&itemStack.getType()!= Material.AIR){
                if (MosaicPaper.IsMosaicPaper(itemStack)){
                    if (publicItem.ItemCanUporDownLevel(itemStack)){
                        publicItem.TakeComposeItem(player,itemStack,ContsNumber.MosaicPaperLevelUpNeedNum);
                        player.getInventory().addItem(MosaicPaper.UpMosaicPaper(itemStack));
                        player.sendMessage("合成镶嵌符成功");
                    }else {
                        player.sendMessage("你的物品等级貌似超出或小了");
                    }
                }
        }
    }

    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent event){
        Inventory Inventory = event.getClickedInventory();
        String invtitle = Inventory.getTitle();
        Player player = (Player) event.getWhoClicked();
        if (invtitle.equalsIgnoreCase(ContsNumber.GemsComposeGuiTitle)){
            int slot = event.getSlot();
            if (!(slot>=10&&slot<=14)){
                event.setCancelled(true);
            }
            if (slot==15){
                List<ItemStack> itemStackList = new ArrayList<>();
                for (int i=10;i<14;i++){
                    ItemStack itemStack = Inventory.getItem(i);
                    if (itemStack!=null){
                        itemStackList.add(itemStack);
                    }
                }
                if (itemStackList.size()==ContsNumber.GemsNumberCompose){
                    if (itemStackList.stream().filter(Gems::IsGems).count()==ContsNumber.GemsNumberCompose){
                        int level = publicItem.GetItemLevel(itemStackList.get(0));
                        if (itemStackList.stream().filter(o->publicItem.GetItemLevel(o)==level).count()==ContsNumber.GemsNumberCompose){
                            String GemsLevelQuality = publicItem.GetItemGemsLevelQuality(itemStackList.get(0));
                            if (itemStackList.stream().filter(item->GemsLevelQuality.equalsIgnoreCase(item.getItemMeta().getDisplayName())).count()==ContsNumber.GemsNumberCompose){
                                //这是宝石都是统一品质等级
                                Gems.GemsUpLevelOrTakeLevel(itemStackList.get(0),level,true);
                            }else {
                                Gems.GemsUpLevelOrTakeLevel(itemStackList.get(0),level,false);
                                //这是宝石有一种是非同一品质丢
                            }
                        }else {
                            player.sendMessage("你放置丢宝石等级不统一");
                        }
                    }else {
                        player.sendMessage("你放置的宝石有类型不对");
                    }
                }else {
                    player.sendMessage("你放置的宝石貌似不够5个");
                }
            }
            //TODU
        }else if (invtitle.equalsIgnoreCase(ContsNumber.MosaicGuiTitle)){

        }

    }
}
