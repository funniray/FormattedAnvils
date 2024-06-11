package com.funniray.anvil;

import com.funniray.anvil.commands.FormattedAnvilsReloadCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class FormattedAnvils extends JavaPlugin {

    private Filter filter;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        load();

        getServer().getPluginManager().registerEvents(new AnvilListener(this), this);
        getServer().getPluginCommand("anvilformatreload").setExecutor(new FormattedAnvilsReloadCommand(this));
    }

    @Override
    public void onDisable() {
    }

    public void load() {
        reloadConfig();
        this.filter = new Filter(this);
    }

    public Filter getFilter() {
        return filter;
    }
}
