package com.funniray.anvil.commands;

import com.funniray.anvil.FormattedAnvils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class FormattedAnvilsReloadCommand implements CommandExecutor {
    FormattedAnvils plugin;

    public FormattedAnvilsReloadCommand(FormattedAnvils plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        plugin.load();
        commandSender.sendMessage("Reload complete.");
        return true;
    }
}
