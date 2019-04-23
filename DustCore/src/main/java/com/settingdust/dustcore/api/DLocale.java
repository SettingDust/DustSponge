package com.settingdust.dustcore.api;

import com.settingdust.dustcore.DustCore;
import com.settingdust.dustcore.api.Config;
import com.settingdust.dustcore.api.ILocale;
import com.settingdust.dustcore.utils.FileUtils;
import com.settingdust.dustcore.utils.PlaceholderUtils;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigManager;
import org.spongepowered.api.config.ConfigRoot;
import org.spongepowered.api.plugin.PluginContainer;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Optional;

public class DLocale implements ILocale {
    private Config config;

    public DLocale(Locale locale, PluginContainer pluginContainer) {
        ConfigManager configManager = Sponge.getConfigManager();
        ConfigRoot configRoot = configManager.getPluginConfig(pluginContainer.getInstance().get());
        String language = PlaceholderUtils.forLanguage("%language_name%", locale);
        String id = pluginContainer.getId();
        Path localePath = Paths.get("assets", id, "lang", language + ".lang");
        File file = new File(configRoot.getDirectory().toFile(), localePath.toString());
        if (!file.exists()) {
            Optional<InputStream> streamOptional = FileUtils.getResource(pluginContainer, "assets/" + id + "/lang/" + language + ".lang");
            try {
                if (streamOptional.isPresent()) {
                    InputStream inputStream = streamOptional.get();
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                    FileOutputStream outputStream = new FileOutputStream(file);
                    int len;
                    byte[] b = new byte[1024];
                    while ((len = inputStream.read(b)) != -1) {
                        outputStream.write(b, 0, len);
                    }
                    inputStream.close();
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = new Config(localePath, configRoot.getDirectory());
    }

    @Override
    public ConfigurationNode getRoot() {
        return config.getRoot();
    }
}
