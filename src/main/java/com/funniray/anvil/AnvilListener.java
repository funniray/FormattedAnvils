package com.funniray.anvil;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AnvilListener implements Listener {

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent e) {
        HumanEntity player = e.getView().getPlayer();
        if (!player.hasPermission("anvilformat.format")) {
            return;
        }

        ItemStack item = e.getResult();
        if (item == null) {
            return;
        }

        Component formatted = Formatter.formatName(e.getInventory().getRenameText(), player);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(formatted);
        item.setItemMeta(meta);
    }
}
