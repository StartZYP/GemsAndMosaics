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
    public static List<String> attribute = new ArrayList<>();
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
        if (sender instanceof Player &&label.equalsIgnoreCase("gams")){

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
        attribute = getConfig().getStringList("GemsAndMosaics.Gems.attribute");
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
