package com.qq44920040.Minecraft.GemsAndMosaics.Listener;


import com.qq44920040.Minecraft.GemsAndMosaics.Entity.DecomposePaper;
import com.qq44920040.Minecraft.GemsAndMosaics.Entity.Gems;
import com.qq44920040.Minecraft.GemsAndMosaics.Entity.MosaicPaper;
import com.qq44920040.Minecraft.GemsAndMosaics.Entity.PunchPaper;
import com.qq44920040.Minecraft.GemsAndMosaics.Main;
import com.qq44920040.Minecraft.GemsAndMosaics.Util.NbtGetSet;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.qq44920040.Minecraft.GemsAndMosaics.Util.publicItem;
import com.qq44920040.Minecraft.GemsAndMosaics.Util.ContsNumber;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.RecursiveTask;

public class ListenerMain implements Listener {
    @EventHandler
    public void PlayerInteractItem(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        if (itemStack!=null&&itemStack.getType()!= Material.AIR){
                if (NbtGetSet.GetItemDate("MosaicType",itemStack)!=null&&NbtGetSet.GetItemDate("MosaicLevel",itemStack)!=null){
                    int MosaicLevel = Integer.parseInt(NbtGetSet.GetItemDate("MosaicLevel",itemStack))+1;
                    String MosaicType = NbtGetSet.GetItemDate("MosaicType",itemStack);
                    if (MosaicPaper.MosaicIsRange(itemStack)&&itemStack.getAmount()==ContsNumber.MosaicPaperLevelUpNeedNum){
                        player.getInventory().addItem(MosaicPaper.MakeMosaicPaper(MosaicLevel,MosaicType));
                       player.setItemInHand(new ItemStack(Material.AIR));
                        player.sendMessage("合成镶嵌符成功");
                    }else {
                        player.sendMessage("你的物品等级貌似超出或小了，或者物品数量不为10");
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
        Inventory inventory = event.getClickedInventory();
        String invtitle = "";
        try{
            invtitle = inventory.getTitle();
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
                    ItemStack itemStack = inventory.getItem(i);
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
                                    inventory.setItem(i,null);
                                }
                            }else {
                                //这是宝石有一种是非同一品质丢
                                event.getWhoClicked().getInventory().addItem(Gems.MakeGems(Integer.parseInt(level)+1, Main.GemsLevelQuality.get(new Random().nextInt(Main.GemsLevelQuality.size())),NbtGetSet.GetItemDate("Attribute",itemStackList.get(0))));
                                for (int i=10;i<=14;i++){
                                    inventory.setItem(i,null);
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
        }else if (invtitle.equalsIgnoreCase(ContsNumber.PunchGuiTitle)){
            int slot = event.getSlot();
            if (slot>=0&&slot<=9||slot>=11&&slot<14||slot>=15){
                event.setCancelled(true);
            }
            if (slot==16){
                ItemStack itemStack = inventory.getItem(10);
                ItemStack itemStack1 = inventory.getItem(14);
                if (itemStack==null&&itemStack1==null){
                    return;
                }
                if (!inventory.getItem(10).hasItemMeta()||!inventory.getItem(10).hasItemMeta()) {
                    return;
                    //无lore
                }
                ItemMeta itemMetaitem = inventory.getItem(10).getItemMeta();
                ItemMeta itemMetaPunch = inventory.getItem(14).getItemMeta();
                if (!itemMetaitem.hasLore()&&!itemMetaPunch.hasLore()){
                    return;
                }
                List<String> loreitem = itemMetaitem.getLore();
                if (!publicItem.EquipCanMosaic(loreitem)&&!PunchPaper.IsPunchPaper(itemStack1)){
                    //无不符合强化装备条件
                    return;
                }
                String PunchType = NbtGetSet.GetItemDate("PunchType",itemStack1);
                int lorelinestart = publicItem.EquipStartLineNumber(loreitem,true);
                if (PunchType!=null){
                    if (loreitem.get(lorelinestart+1).equalsIgnoreCase(Main.CheckSlotLore)){
                        if (Main.TypeArrayKey.get(0).equalsIgnoreCase(PunchType)){
                            //给予对应lore 给消除队友的打孔符//打阴孔
                            loreitem.set(lorelinestart+1,Main.SlotLore[0]);
                            inventory.setItem(14,null);
                            itemMetaitem.setLore(loreitem);
                            inventory.getItem(10).setItemMeta(itemMetaitem);
                            inventory.setItem(10,NbtGetSet.SetItemData("yin","null",inventory.getItem(10)));
                        }else {
                            //孔类型不匹配
                            player.sendMessage("孔类型与打孔符不匹配");
                        }
                    }else if (loreitem.get(lorelinestart+2).equalsIgnoreCase(Main.CheckSlotLore)){
                        if (Main.TypeArrayKey.get(1).equalsIgnoreCase(PunchType)){
                            //给予对应lore 给消除队友的打孔符
                            loreitem.set(lorelinestart+2,Main.SlotLore[1]);
                            inventory.setItem(14,null);
                            itemMetaitem.setLore(loreitem);
                            inventory.getItem(10).setItemMeta(itemMetaitem);
                            inventory.setItem(10,NbtGetSet.SetItemData("yang","null",inventory.getItem(10)));
                        }else {
                            //打孔符不匹配请先开启阴孔
                            player.sendMessage("打孔符不匹配请先开启阴孔");
                        }
                    }else {
                        //提示强化的物品没有
                        player.sendMessage("提示强化的物品没有");
                    }
                }else {
                    //提示物品不是打孔符
                    player.sendMessage("物品不是打孔符");
                }
                //此处可以开始操作
                //先判断放物品1号什么位置
            }
        }else if (invtitle.equalsIgnoreCase(ContsNumber.MosaicGuiTitle)){
            int slot = event.getSlot();
            if (slot!=11&&slot!=15&&slot!=22&&slot<=26){
                event.setCancelled(true);
            }
            //判断22号位置是否有物品
            if (slot==13){
                ItemStack itemStack = inventory.getItem(22);
                ItemStack Gemsitem = inventory.getItem(11);
                ItemStack Mosaicitem = inventory.getItem(15);
                if (itemStack==null||Gemsitem==null||Mosaicitem==null){
                    return;
                }
                //判断三个物品都是正确的
                if (!Gems.IsGems(Gemsitem)||NbtGetSet.GetItemDate("MosaicType",Mosaicitem)==null||NbtGetSet.GetItemDate("yin",itemStack)==null||NbtGetSet.GetItemDate("yang",itemStack)==null){
                    return;
                }
                //
                String GemsLevel = NbtGetSet.GetItemDate("GemLevel",Gemsitem);
                String GemsQuality = NbtGetSet.GetItemDate("GemQuality",Gemsitem);
                String GemsAttribute = NbtGetSet.GetItemDate("Attribute",Gemsitem);



            }

        }

    }
}
