package com.settingdust.dustcore.module;

import com.google.common.collect.Sets;
import com.settingdust.dustcore.api.AbstractProviderManager;
import com.settingdust.dustcore.api.ConfigProvider;
import com.settingdust.dustcore.module.main.MainProvider;
import lombok.Getter;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.GameReloadEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;

import java.util.Set;

public class ProviderManager extends AbstractProviderManager {
    @Getter
    private MainProvider mainProvider;

    @Override
    protected void load() {
        providers = Sets.newHashSet();
        mainProvider = new MainProvider();

        providers.add(mainProvider);
    }
}
