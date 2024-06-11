package com.funniray.anvil;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import org.bukkit.entity.HumanEntity;
import org.jetbrains.annotations.NotNull;

public class Formatter {

    public static @NotNull Component formatName(String input, HumanEntity player) {
        MiniMessage formatter;

        if (player.hasPermission("anvilformat.tag.any")) {
            formatter = MiniMessage.miniMessage();
        } else {
            TagResolver.Builder resolver = TagResolver.builder()
                    .resolver(StandardTags.reset());

            if (player.hasPermission("anvilformat.tag.color")) {
                resolver.resolver(StandardTags.color());
            }
            if (player.hasPermission("anvilformat.tag.gradient")) {
                resolver.resolver(StandardTags.gradient());
                resolver.resolver(StandardTags.rainbow());
            }
            if (player.hasPermission("anvilformat.tag.decorations")) {
                resolver.resolver(StandardTags.decorations());
            }
            if (player.hasPermission("anvilformat.tag.newline")) {
                resolver.resolver(StandardTags.newline());
            }

            formatter = MiniMessage.builder().tags(resolver.build()).build();
        }

        Component formatted = formatter.deserialize(input);

        if (player.hasPermission("anvilformat.noitalics")) {
            return formatted.decoration(TextDecoration.ITALIC, false);
        }

        return formatted;
    }

}
