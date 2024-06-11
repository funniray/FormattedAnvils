package com.funniray.anvil;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AnvilListener implements Listener {
    FormattedAnvils plugin;

    public AnvilListener(FormattedAnvils plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent e) {
        HumanEntity player = e.getView().getPlayer();
        if (!player.hasPermission("anvilformat.format")) {
            return;
        }

        ItemStack item = e.getResult();
        ItemStack inputItem = e.getInventory().getFirstItem();
        if (item == null || inputItem == null || e.getInventory().getRenameText() == null) {
            return;
        }

        // Don't rename the item if they're just trying to repair their tool
        ItemMeta inputMeta = inputItem.getItemMeta();
        if (inputMeta != null && inputMeta.displayName() != null) {
            String plain = PlainTextComponentSerializer.plainText().serialize(inputMeta.displayName());
            if (e.getInventory().getRenameText().equals(plain) && e.getInventory().getSecondItem() != null && !e.getInventory().getSecondItem().isEmpty()) {
                return;
            }
        }

        Component formatted = Formatter.formatName(e.getInventory().getRenameText(), player);

        if (!player.hasPermission("anvilformat.filter.bypass")) {
            if (plugin.getFilter().test(formatted)) {
                e.setResult(ItemStack.empty());
                return;
            };
        }

        ItemMeta meta = item.getItemMeta();
        meta.displayName(formatted);
        item.setItemMeta(meta);
    }
}
