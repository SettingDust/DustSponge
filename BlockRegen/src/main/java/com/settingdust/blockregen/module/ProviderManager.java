package com.settingdust.blockregen.module;

import com.settingdust.blockregen.module.data.DataProvider;
import com.settingdust.blockregen.module.main.MainProvider;
import com.settingdust.dustcore.api.AbstractProviderManager;
import lombok.Getter;

public class ProviderManager extends AbstractProviderManager {
    @Getter
    private MainProvider mainProvider;

    @Getter
    private DataProvider dataProvider;

    @Override
    protected void load() {
        this.mainProvider = new MainProvider(this);
        this.providers.add(mainProvider);

        if (mainProvider.get().isSaveData()) {
            this.dataProvider = new DataProvider(this);
            this.providers.add(dataProvider);
        }
    }
}
