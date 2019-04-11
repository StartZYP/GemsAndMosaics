package com.qq44920040.Minecraft.GemsAndMosaics;

import com.qq44920040.Minecraft.GemsAndMosaics.Entity.DecomposePaper;
import com.qq44920040.Minecraft.GemsAndMosaics.Entity.MosaicPaper;
import com.qq44920040.Minecraft.GemsAndMosaics.Listener.ListenerMain;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Main extends JavaPlugin {
    public static List<MosaicPaper> MosaicList=new ArrayList<>();
    public static DecomposePaper decomposePaper;
    public static List<String> GemsLevelInfo = new ArrayList<>();
    public static List<String> attribute = new ArrayList<>();
    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new ListenerMain(),this);
        ReloadConfig();
        super.onEnable();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return super.onCommand(sender, command, label, args);
    }



    private void ReloadConfig() {
        reloadConfig();
        MosaicList.add(new MosaicPaper(getConfig().getString("GemsAndMosaics.PaperItem.MosaicPaper.DarkKey")));
        MosaicList.add(new MosaicPaper(getConfig().getString("GemsAndMosaics.PaperItem.MosaicPaper.LightKey")));
        MosaicList.add(new MosaicPaper(getConfig().getString("GemsAndMosaics.PaperItem.MosaicPaper.Balance")));
        decomposePaper = new DecomposePaper(getConfig().getString("GemsAndMosaics.PaperItem.DecomposePaper"));
        GemsLevelInfo = getConfig().getStringList("GemsAndMosaics.Gems.Quality");
        attribute = getConfig().getStringList("GemsAndMosaics.Gems.attribute");
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
