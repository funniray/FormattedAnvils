package com.funniray.anvil;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class Filter {
    private final List<Pattern> patterns;

    public Filter(FormattedAnvils plugin) {
        if (plugin.getConfig().getBoolean("filter.enabled", false)) {
            List<?> words = plugin.getConfig().getList("filter.patterns");
            patterns = words.stream().map(s->(String) s).map(Pattern::compile).toList();
        } else {
            patterns = Collections.emptyList();
        }
    }

    public boolean test(Component component) {
        String plain = PlainTextComponentSerializer.plainText().serialize(component);
        for (Pattern pattern : patterns) {
            if (pattern.matcher(plain).find()) return true;
        }
        return false;
    }
}
