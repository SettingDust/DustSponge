package com.settingdust.dustcore.api;

import com.settingdust.dustcore.locale.LocaleManager;
import com.settingdust.dustcore.module.ProviderManager;
import org.slf4j.Logger;
import org.spongepowered.api.plugin.PluginContainer;

import java.nio.file.Path;

public interface IDustCore {
    LocaleManager getLocaleManager();

    PluginContainer getPluginContainer();

    ProviderManager getProviderManager();

    Logger getLogger();

    Path getConfigDir();
}
