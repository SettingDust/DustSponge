package com.settingdust.dustcore.utils;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.plugin.PluginContainer;

import java.util.Locale;

public class PlaceholderUtils {
    public static String forPluginInfo(String s, String id) {
        return forPluginInfo(s, Sponge.getPluginManager().getPlugin(id).get());
    }

    public static String forPluginInfo(String s, PluginContainer pluginContainer) {
        return s
                .replaceAll("%plugin_name%", pluginContainer.getName())
                .replaceAll("%plugin_id%", pluginContainer.getId())
                .replaceAll("%plugin_version%", pluginContainer.getVersion().orElse("null"));
    }

    public static String forLanguage(String s, Locale locale) {
        return s
                .replaceAll("%language_name%", locale.getLanguage() + "_" + locale.getCountry())
                .replaceAll("%language_display_language%", locale.getDisplayName(locale));
    }
}
