package com.settingdust.dustmultirespawn;

import com.google.inject.Inject;
import com.settingdust.dustcore.DustCore;
import com.settingdust.dustcore.api.DLocale;
import com.settingdust.dustmultirespawn.module.ProviderManager;
import io.github.nucleuspowered.nucleus.api.NucleusAPI;
import io.github.nucleuspowered.nucleus.api.exceptions.ModulesLoadedException;
import io.github.nucleuspowered.nucleus.api.exceptions.NoModuleException;
import io.github.nucleuspowered.nucleus.api.exceptions.UnremovableModuleException;
import lombok.Getter;
import ninja.leaping.configurate.ConfigurationNode;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.*;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginManager;

import java.nio.file.Path;

@Plugin(
        id = DustMultiRespawn.ID,
        name = "DustMultiRespawn",
        version = "1.1",
        authors = {
                "SettingDust"
        },
        description = "Respawn at the closest point",
        dependencies = {
                @Dependency(id = "dustcore"),
                @Dependency(id = DustMultiRespawn.NUCLEUS_ID, optional = true)
        }
)
public class DustMultiRespawn {
    public static final String ID = "dustmultirespawn";

    @Getter
    private static DustMultiRespawn instance;

    @Getter
    @Inject
    private Logger logger;

    @Getter
    private ProviderManager providerManager;

    @Inject
    @ConfigDir(sharedRoot = false)
    @Getter
    private Path configDir;

    @Inject
    private PluginManager pluginManager;

    public static final String NUCLEUS_ID = "nucleus";
    @Getter
    private boolean isNucleusLoaded;

    private DLocale locale;

    @Listener
    public void onPreInit(GamePreInitializationEvent event) {
        instance = this;
    }

    @Listener
    public void onInit(GameInitializationEvent event) {
        locale = DustCore.getInstance().getLocaleManager().getLocale(ID);

        isNucleusLoaded = pluginManager.isLoaded(NUCLEUS_ID);

        // Disable Nucleus spawn module
        if (isNucleusLoaded) {
            try {
                NucleusAPI.getModuleService().removeModule("spawn", this);
            } catch (ModulesLoadedException | NoModuleException | UnremovableModuleException e) {
                e.printStackTrace();
            }
        }
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
