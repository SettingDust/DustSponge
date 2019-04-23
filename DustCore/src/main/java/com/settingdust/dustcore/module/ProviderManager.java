package com.settingdust.dustcore.module;

import com.google.common.collect.Sets;
import com.settingdust.dustcore.api.ConfigProvider;
import com.settingdust.dustcore.module.main.MainProvider;
import lombok.Getter;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.GameReloadEvent;

import java.util.Set;

public class ProviderManager {
    private Set<ConfigProvider> providers;

    @Getter
    private MainProvider mainProvider;

    public ProviderManager() {
        this.load();
    }

    public void save() {
        for (ConfigProvider provider : providers) {
            provider.save(provider.get());
        }
    }

    private void load() {
        providers = Sets.newHashSet();
        mainProvider = new MainProvider();

        providers.add(mainProvider);
    }

    @Listener
    public void onReload(GameReloadEvent event) {
        this.load();
    }
}
