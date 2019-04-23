package com.settingdust.dustcore.utils;

import org.spongepowered.api.plugin.PluginContainer;

import java.io.InputStream;
import java.util.Optional;

public class FileUtils {
    /**
     * 获取插件资源文件
     *
     * @param pluginContainer 插件
     * @param filename        文件名
     * @return {@link InputStream}
     */
    public static Optional<InputStream> getResource(PluginContainer pluginContainer, String filename) {
        return Optional.ofNullable(pluginContainer.getInstance().get().getClass().getClassLoader().getResourceAsStream(filename));
    }
}
