package com.settingdust.dustcore.module.main;

import com.google.common.reflect.TypeToken;
import com.settingdust.dustcore.DustCore;
import com.settingdust.dustcore.api.Config;

import java.nio.file.Paths;

public class MainConfig extends Config {
    MainConfig() {
        super(
                Paths.get("module.conf"),
                DustCore.getInstance().getConfigDir(),
                TypeToken.of(MainEntity.class)
        );
    }
}
