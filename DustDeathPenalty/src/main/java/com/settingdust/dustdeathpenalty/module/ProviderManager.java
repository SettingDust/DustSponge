package com.settingdust.dustdeathpenalty.module;

import com.google.common.collect.Sets;
import com.settingdust.dustcore.api.AbstractProviderManager;
import com.settingdust.dustdeathpenalty.module.main.MainProvider;
import lombok.Getter;

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
