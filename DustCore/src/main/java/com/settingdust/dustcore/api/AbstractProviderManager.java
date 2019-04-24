package com.settingdust.dustcore.api;

import com.google.common.collect.Sets;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.GameReloadEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;

import java.util.Set;

public abstract class AbstractProviderManager {
    protected Set<ConfigProvider> providers;

    public AbstractProviderManager() {
        providers = Sets.newHashSet();
        this.load();
    }

    private void save() {
        for (ConfigProvider provider : providers) {
            provider.save(provider.get());
        }
    }

    protected abstract void load();

    @Listener
    public void onStopping(GameStoppingServerEvent event) {
        this.save();
    }

    @Listener
    public void onReload(GameReloadEvent event) {
        this.load();
    }
}
