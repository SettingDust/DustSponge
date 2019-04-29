package com.settingdust.blockregen.module;

import com.settingdust.blockregen.module.main.MainProvider;
import com.settingdust.dustcore.api.AbstractProviderManager;
import lombok.Getter;

public class ProviderManager extends AbstractProviderManager {
    @Getter
    private MainProvider mainProvider;

    @Override
    protected void load() {
        this.mainProvider = new MainProvider();

        this.providers.add(mainProvider);
    }
}
