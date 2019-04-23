package com.settingdust.dustcore.utils;

import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.plugin.meta.PluginDependency;

public class PluginUtils {
    public static boolean isDepend(PluginContainer pluginContainer, String id) {
        boolean isDepend = false;
        for (PluginDependency dependency : pluginContainer.getDependencies()) {
            if (dependency.getId().equalsIgnoreCase(id)) {
                isDepend = true;
            }
        }
        return isDepend;
    }
}
