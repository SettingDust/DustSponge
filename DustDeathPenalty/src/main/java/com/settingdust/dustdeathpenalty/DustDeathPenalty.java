package com.settingdust.dustdeathpenalty;

import com.google.inject.Inject;
import com.settingdust.dustcore.DustCore;
import com.settingdust.dustcore.api.DLocale;
import com.settingdust.dustdeathpenalty.module.ProviderManager;
import lombok.Getter;
import ninja.leaping.configurate.ConfigurationNode;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;

import java.nio.file.Path;

@Plugin(
        id = DustDeathPenalty.ID,
        name = "DustDeathPenalty",
        version = "1.3",
        authors = {
                "SettingDust"
        },
        dependencies = {
                @Dependency(id = "dustcore", version = "1.4")
        }
)
public class DustDeathPenalty {
    public static final String ID = "dustdeathpenalty";
    @Getter
    private static DustDeathPenalty instance;

    @Inject
    @Getter
    private Logger logger;

    @Inject
    @Getter
    @ConfigDir(sharedRoot = false)
    private Path configDir;

    private DLocale locale;
    @Getter
    private ProviderManager providerManager;

    @Listener
    public void onPreInit(GamePreInitializationEvent event) {
        instance = this;
    }

    @Listener
    public void onInit(GameInitializationEvent event) {
        locale = DustCore.getInstance().getLocaleManager().getLocale(ID);
    }

    @Listener
    public void onStarting(GameStartingServerEvent event) {
        providerManager = new ProviderManager();

        Sponge.getEventManager().registerListeners(this, providerManager);
    }

    public ConfigurationNode getLocale() {
        return locale.getRoot();
    }
}
