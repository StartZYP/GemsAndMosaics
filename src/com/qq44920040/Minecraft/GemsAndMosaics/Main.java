package com.qq44920040.Minecraft.GemsAndMosaics;

import com.qq44920040.Minecraft.GemsAndMosaics.Entity.*;
import com.qq44920040.Minecraft.GemsAndMosaics.Listener.ListenerMain;
import com.qq44920040.Minecraft.GemsAndMosaics.Util.NbtGetSet;
import com.qq44920040.Minecraft.GemsAndMosaics.Util.publicItem;
import com.qq44920040.Minecraft.GemsAndMosaics.View.PlayerUi;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.*;

public class Main extends JavaPlugin {
    public static List<String> TypeArrayKey;
    public static List<String> GemsLevelQuality = new ArrayList<>();
    public static HashMap<String,String> Gemsattribute = new HashMap<>();
    public static String StartLine;
    public static String EndLine;
    public static String CheckSlotLore;
    public static String[] SlotLore;
    public static String PunchPaperKey;
    public static String decomposePaperKey;
    public static String ProtectPaperKey;
    public static String DismantlePaperKey;
    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        File file = new File(getDataFolder(),"config.yml");
        if (!file.exists()){
            saveDefaultConfig();
        }
        Bukkit.getServer().getPluginManager().registerEvents(new ListenerMain(),this);
        ReloadConfig();
        super.onEnable();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player &&label.equalsIgnoreCase("GAMS")){
            //op命令
            if (sender.isOp()){
                if (args.length==5&&args[0].equalsIgnoreCase("give")){
                    if (args[1].equalsIgnoreCase("gems")&&args[2].matches("^(10|11|12|13|[1-9])$")&&Main.GemsLevelQuality.contains(args[3])&&Main.Gemsattribute.containsKey(args[4])){
                        //生成宝石
                        ((Player) sender).getInventory().addItem(Gems.MakeGems(Integer.valueOf(args[2]),args[3],args[4]));
                    }
                }else if (args.length==4&&args[0].equalsIgnoreCase("give")){
                    if (args[1].equalsIgnoreCase("Mosaic")&&args[2].matches("^(10|11|12|13|[1-9])$")&&TypeArrayKey.contains(args[3])){
                        //给一个镶嵌符
                        ((Player) sender).getInventory().addItem(MosaicPaper.MakeMosaicPaper(Integer.valueOf(args[2]),args[3]));
                    }
                }else if (args.length==3&&args[0].equalsIgnoreCase("give")&&args[1].equalsIgnoreCase("Punch")&&TypeArrayKey.contains(args[2])){
                    ((Player) sender).getInventory().addItem(PunchPaper.MakePunchPaper(args[2]));
                }else if (args.length==2&&args[0].equalsIgnoreCase("give")){
                    if (args[1].equalsIgnoreCase("Decompose")){
                        ((Player) sender).getInventory().addItem(DecomposePaper.MakeDecomposePaper());
                    }else if (args[1].equalsIgnoreCase("Dismantle")){
                        ((Player) sender).getInventory().addItem(DismantlePaper.MakeDismantlePaper());
                    }
                }else if (args.length==1&&args[0].equalsIgnoreCase("set")){
                    ItemStack itemInHand = ((Player) sender).getItemInHand();
                    if (itemInHand!=null){
                        itemInHand = NbtGetSet.SetItemData("yin","null",publicItem.AddItemUpDownLine(itemInHand));
                        itemInHand = NbtGetSet.SetItemData("yang","null",itemInHand);
                        itemInHand = NbtGetSet.SetItemData("jun","null",itemInHand);
                        ((Player) sender).setItemInHand(itemInHand);
                    }else {
                        sender.sendMessage("你手上没有装备物品，无法进行开启");
                    }
                }
                sender.sendMessage("参数不对");
            }
            //玩家命令
            if (args.length==1&&args[0].equalsIgnoreCase("compose")){
                ((Player) sender).openInventory(PlayerUi.PlayerOpenGamsGui());
            }else if (args.length==1&&args[0].equalsIgnoreCase("open")){
                ((Player) sender).openInventory(PlayerUi.PlayerOpenMosaicGui());
            }else if (args.length==1&&args[0].equalsIgnoreCase("punch")){
                ((Player) sender).openInventory(PlayerUi.PlayerOpenPunch());
            }
        }
        return super.onCommand(sender, command, label, args);
    }



    private void ReloadConfig() {
        reloadConfig();
        TypeArrayKey = Arrays.asList(getConfig().getString("GemsAndMosaics.PaperItem.MosaicPaper.DarkKey"),getConfig().getString("GemsAndMosaics.PaperItem.MosaicPaper.LightKey"),getConfig().getString("GemsAndMosaics.PaperItem.MosaicPaper.Balance"));
        PunchPaperKey = getConfig().getString("GemsAndMosaics.PaperItem.PerforatedPaper");
        decomposePaperKey =getConfig().getString("GemsAndMosaics.PaperItem.DecomposePaper");
        ProtectPaperKey = getConfig().getString("GemsAndMosaics.PaperItem.ProtectPaper");
        DismantlePaperKey = getConfig().getString("GemsAndMosaics.PaperItem.DismantlePaper");
        GemsLevelQuality = getConfig().getStringList("GemsAndMosaics.Gems.Quality");
        List<String> attributesmap = getConfig().getStringList("GemsAndMosaics.Gems.Baseattribute");
        for (String temp : attributesmap) {
            Gemsattribute.put(temp.split("\\|")[0],temp.split("\\|")[1]);
        }
        EndLine = getConfig().getString("BaseConfig.CheckEndLine");
        StartLine = getConfig().getString("BaseConfig.CheckStartLine");
        CheckSlotLore = getConfig().getString("BaseConfig.CheckSlotLore");
        SlotLore = new String[]{getConfig().getString("BaseConfig.DarkSlotLore"),getConfig().getString("BaseConfig.LightSlotLore"),getConfig().getString("BaseConfig.BalanceSlotLore")};
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
