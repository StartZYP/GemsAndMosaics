package com.qq44920040.Minecraft.GemsAndMosaics;

import com.qq44920040.Minecraft.GemsAndMosaics.Entity.DecomposePaper;
import com.qq44920040.Minecraft.GemsAndMosaics.Entity.Gems;
import com.qq44920040.Minecraft.GemsAndMosaics.Entity.MosaicPaper;
import com.qq44920040.Minecraft.GemsAndMosaics.Listener.ListenerMain;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Main extends JavaPlugin {
    public static String[] MosaicArrayKey;
    public static Gems gems = new Gems();
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
        Bukkit.getServer().getPluginManager().registerEvents(new ListenerMain(),this);
        ReloadConfig();
        super.onEnable();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player &&label.equalsIgnoreCase("GAMS")){
            if (args.length==5&&args[0].equalsIgnoreCase("give")){
                if (args[1].equalsIgnoreCase("gems")&&args[2].matches("^(10|11|12|13|[1-9])$")&&Main.GemsLevelQuality.contains(args[3])&&Main.Gemsattribute.containsKey(args[4])){
                    //生成宝石
                    ((Player) sender).getInventory().addItem(Gems.MakeGems(Integer.valueOf(args[2]),args[3],args[4]));
                }else {
                    sender.sendMessage("参数不正确");
                }
            }else if (args.length==3&&args[0].equalsIgnoreCase("give")){
                if (args[1].equalsIgnoreCase("Mosaic")&&args[2].matches("^(10|11|12|13|[1-9])$")){
                    //给一个打孔符
                    ((Player) sender).getInventory().addItem(MosaicPaper.MakeMosaicPaper(Integer.valueOf(args[2])));
                }else {
                    sender.sendMessage("参数不正确");
                }
            }else if (args.length==2&&args[0].equalsIgnoreCase("give")&&args[1].equalsIgnoreCase("Punch")){
                
            }
        }
        return super.onCommand(sender, command, label, args);
    }



    private void ReloadConfig() {
        reloadConfig();
        MosaicArrayKey = new String[]{getConfig().getString("GemsAndMosaics.PaperItem.MosaicPaper.DarkKey"),getConfig().getString("GemsAndMosaics.PaperItem.MosaicPaper.LightKey"),getConfig().getString("GemsAndMosaics.PaperItem.MosaicPaper.Balance")};
        PunchPaperKey = getConfig().getString("GemsAndMosaics.PaperItem.PerforatedPaper");
        decomposePaperKey =getConfig().getString("GemsAndMosaics.PaperItem.DecomposePaper");
        ProtectPaperKey = getConfig().getString("GemsAndMosaics.PaperItem.ProtectPaper");
        DismantlePaperKey = getConfig().getString("GemsAndMosaics.PaperItem.DismantlePaper");
        GemsLevelQuality = getConfig().getStringList("GemsAndMosaics.Gems.Quality");
        List<String> attributesmap = getConfig().getStringList("GemsAndMosaics.Gems.attribute");
        for (String temp : attributesmap) {
            String[] arraystr = temp.split("\\|");
            Gemsattribute.put(arraystr[0],arraystr[1]);
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
