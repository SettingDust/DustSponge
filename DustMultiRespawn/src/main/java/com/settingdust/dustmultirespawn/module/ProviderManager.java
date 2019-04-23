package com.settingdust.dustmultirespawn.module;

import com.google.common.collect.Sets;
import com.settingdust.dustcore.api.ConfigProvider;
import com.settingdust.dustmultirespawn.DustMultiRespawn;
import com.settingdust.dustmultirespawn.module.main.MainProvider;
import com.settingdust.dustmultirespawn.module.spawns.SpawnsProvider;
import lombok.Getter;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ProviderManager {
    private Set<ConfigProvider> providers;

    @Getter
    private MainProvider mainProvider;

    @Getter
    private SpawnsProvider spawnsProvider;

    public ProviderManager() {
        load();
    }

    private void save() {
        for (ConfigProvider provider : providers) {
            provider.save(provider.get());
        }
    }

    private void load() {
        providers = Sets.newHashSet();
        mainProvider = new MainProvider();
        spawnsProvider = new SpawnsProvider(this);

        providers.add(mainProvider);
        providers.add(spawnsProvider);
    }

    @Listener
    public void onStopping(GameStoppingServerEvent event) {
        this.save();
    }
}
