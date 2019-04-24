package com.settingdust.dustmultirespawn.module;

import com.settingdust.dustcore.api.AbstractProviderManager;
import com.settingdust.dustmultirespawn.module.main.MainProvider;
import com.settingdust.dustmultirespawn.module.spawn.SpawnsProvider;
import lombok.Getter;

public class ProviderManager extends AbstractProviderManager {

    @Getter
    private MainProvider mainProvider;

    @Getter
    private SpawnsProvider spawnsProvider;

    @Override
    protected void load() {
        mainProvider = new MainProvider();
        spawnsProvider = new SpawnsProvider(this);

        providers.add(mainProvider);
        providers.add(spawnsProvider);
    }
}
