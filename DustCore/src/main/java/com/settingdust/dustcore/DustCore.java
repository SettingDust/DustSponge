package com.settingdust.dustcore;

import com.google.inject.Inject;
import com.settingdust.dustcore.api.IDustCore;
import com.settingdust.dustcore.locale.LocaleManager;
import com.settingdust.dustcore.module.ProviderManager;
import lombok.Getter;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.*;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

import java.nio.file.Path;

/**
 * @author SettingDust
 */
@Plugin(
        id = DustCore.ID,
        name = DustCore.NAME,
        version = "1.3",
        authors = {
                "SettingDust"
        }
)
public class DustCore implements IDustCore {
    public static final String ID = "dustcore";
    public static final String NAME = "DustCore";

    @Inject
    @Getter
    private Logger logger;

    @Getter
    private static DustCore instance;

    @Inject
    @ConfigDir(sharedRoot = false)
    @Getter
    private Path configDir;

    @Inject
    @Getter
    private PluginContainer pluginContainer;

    @Getter
    private ProviderManager providerManager;

    @Getter
    private LocaleManager localeManager;

    @Listener
    public void onPreInit(GamePreInitializationEvent event) {
        instance = this;
        providerManager = new ProviderManager();
        localeManager = new LocaleManager(providerManager.getMainProvider().getLocale());
    }

    @Listener
    public void onInit(GameInitializationEvent event) {
        Sponge.getEventManager().registerListeners(this, providerManager);
        Sponge.getEventManager().registerListeners(this, localeManager);
    }
}
