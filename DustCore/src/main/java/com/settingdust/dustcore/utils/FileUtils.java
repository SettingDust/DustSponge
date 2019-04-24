package com.settingdust.dustcore.utils;

import org.spongepowered.api.plugin.PluginContainer;

import java.io.InputStream;
import java.util.Optional;

public class FileUtils {
    public static Optional<InputStream> getResource(PluginContainer pluginContainer, String filename) {
        return Optional.ofNullable(pluginContainer.getInstance().get().getClass().getClassLoader().getResourceAsStream(filename));
    }
}
