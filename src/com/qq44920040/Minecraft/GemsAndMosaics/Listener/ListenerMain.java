package com.qq44920040.Minecraft.GemsAndMosaics.Listener;


import com.qq44920040.Minecraft.GemsAndMosaics.Entity.DecomposePaper;
import com.qq44920040.Minecraft.GemsAndMosaics.Entity.Gems;
import com.qq44920040.Minecraft.GemsAndMosaics.Entity.MosaicPaper;
import com.qq44920040.Minecraft.GemsAndMosaics.Main;
import com.qq44920040.Minecraft.GemsAndMosaics.Util.NbtGetSet;
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
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ListenerMain implements Listener {
    @EventHandler
    public void PlayerInteractItem(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        if (itemStack!=null&&itemStack.getType()!= Material.AIR){
                if (NbtGetSet.GetItemDate("MosaicType",itemStack)!=null){
                    if (publicItem.ItemCanUporDownLevel(itemStack)){
                        publicItem.TakeComposeItem(player,itemStack,ContsNumber.MosaicPaperLevelUpNeedNum);
                        player.getInventory().addItem(MosaicPaper.UpMosaicPaper(itemStack));
                        player.sendMessage("合成镶嵌符成功");
                    }else {
                        player.sendMessage("你的物品等级貌似超出或小了");
                    }
                }else if (DecomposePaper.IsDecomposePaper(itemStack)&&Gems.GemsIsRange(itemStack)){
                    Inventory inv = player.getInventory();
                    ItemStack itemGem = inv.getItem(1);
                    if (Gems.IsGems(itemGem)){
                        String Level = NbtGetSet.GetItemDate("GemLevel",itemGem);
                        int Levelnumbner = Integer.parseInt(Level)-1;
                        String Attribute = NbtGetSet.GetItemDate("Attribute",itemGem);
                        for (int i=0;i<=3;i++){
                            inv.addItem(Gems.MakeGems(Levelnumbner,Main.GemsLevelQuality.get(new Random().nextInt(Main.GemsLevelQuality.size()-1)),Attribute));
                        }
                        player.setItemInHand(null);
                    }else {
                        player.sendMessage("请将1号位放上宝石，再使用分解符");
                    }
                }
        }
    }

    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent event){
        Inventory Inventory = event.getClickedInventory();
        String invtitle = "";
        try{
            invtitle = Inventory.getTitle();
        }catch (NullPointerException e){
        }
        Player player = (Player) event.getWhoClicked();
        if (invtitle.equalsIgnoreCase(ContsNumber.GemsComposeGuiTitle)){
            int slot = event.getSlot();
            if (slot>=0&&slot<=9||slot>=15&&slot<=26){
                event.setCancelled(true);
            }
            if (slot==16){
                List<ItemStack> itemStackList = new ArrayList<>();
                for (int i=10;i<=14;i++){
                    ItemStack itemStack = Inventory.getItem(i);
                    if (itemStack!=null){
                        itemStackList.add(itemStack);
                    }
                }
                if (itemStackList.size()==ContsNumber.GemsNumberCompose){
                    if (itemStackList.stream().filter(Gems::IsGems).count()==ContsNumber.GemsNumberCompose){
                        String level = NbtGetSet.GetItemDate("GemLevel",itemStackList.get(0));
                        if (itemStackList.stream().filter(o->NbtGetSet.GetItemDate("GemLevel",o).equalsIgnoreCase(level)&&Gems.GemsIsRange(o)).count()==ContsNumber.GemsNumberCompose){
                            String GemQuality = NbtGetSet.GetItemDate("GemQuality",itemStackList.get(0));
                            if (itemStackList.stream().filter(o->NbtGetSet.GetItemDate("GemQuality",o).equalsIgnoreCase(GemQuality)).count()==ContsNumber.GemsNumberCompose){
                                //这是宝石都是统一品质等级
                                event.getWhoClicked().getInventory().addItem(Gems.MakeGems(Integer.parseInt(level)+1, Main.GemsLevelQuality.get(Main.GemsLevelQuality.indexOf(GemQuality)+1),NbtGetSet.GetItemDate("Attribute",itemStackList.get(0))));
                                for (int i=10;i<=14;i++){
                                    Inventory.setItem(i,null);
                                }
                            }else {
                                //这是宝石有一种是非同一品质丢
                                event.getWhoClicked().getInventory().addItem(Gems.MakeGems(Integer.parseInt(level)+1, Main.GemsLevelQuality.get(new Random().nextInt(Main.GemsLevelQuality.size())),NbtGetSet.GetItemDate("Attribute",itemStackList.get(0))));
                                for (int i=10;i<=14;i++){
                                    Inventory.setItem(i,null);
                                }
                            }
                        }else {
                            player.sendMessage("你放置丢宝石等级不统一");
                        }
                    }else {
                        player.sendMessage("你放置的宝石貌似不够5个");
                    }
                }
            }
            //TODU
        }else if (invtitle.equalsIgnoreCase(ContsNumber.MosaicGuiTitle)){
            int slot = event.getSlot();
            if (slot==34){
                ItemStack itemStack = Inventory.getItem(20);
                if (itemStack==null){
                    return;
                }
                if (!itemStack.hasItemMeta()&&!itemStack.getItemMeta().hasLore()) {
                    return;
                    //无lore
                }
                List<String> lore = itemStack.getItemMeta().getLore();
                if (!publicItem.EquipCanMosaic(lore)){
                    //无不符合强化装备条件
                    return;
                }
                //此处可以开始操作
                //先判断放物品1号什么位置
            }
        }

    }
}
