package com.qq44920040.Minecraft.GemsAndMosaics;

import com.qq44920040.Minecraft.GemsAndMosaics.Entity.MosaicPaper;
import com.qq44920040.Minecraft.GemsAndMosaics.Listener.ListenerMain;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main extends JavaPlugin {
    public static List<MosaicPaper> MosaicList=new ArrayList<>();
    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new ListenerMain(),this);
        super.onEnable();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return super.onCommand(sender, command, label, args);
    }



    private void ReloadConfig() {
        reloadConfig();
        MosaicList.add(new MosaicPaper(getConfig().getString("GemsAndMosaics.MosaicPaper.DarkKey")));
        MosaicList.add(new MosaicPaper(getConfig().getString("GemsAndMosaics.MosaicPaper.LightKey")));
        MosaicList.add(new MosaicPaper(getConfig().getString("GemsAndMosaics.MosaicPaper.Balance")));

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
