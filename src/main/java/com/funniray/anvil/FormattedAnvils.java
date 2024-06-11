package com.funniray.anvil;

import org.bukkit.plugin.java.JavaPlugin;

public final class FormattedAnvils extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new AnvilListener(), this);
    }

    @Override
    public void onDisable() {
    }
}
