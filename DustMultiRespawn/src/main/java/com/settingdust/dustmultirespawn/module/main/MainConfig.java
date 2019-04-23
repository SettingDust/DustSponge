package com.settingdust.dustmultirespawn.module.main;

import com.google.common.reflect.TypeToken;
import com.settingdust.dustcore.api.Config;
import com.settingdust.dustmultirespawn.DustMultiRespawn;
import com.settingdust.dustmultirespawn.module.main.entity.MainEntity;

import java.nio.file.Paths;

class MainConfig extends Config {
    MainConfig() {
        super(
                Paths.get("config.conf"),
                DustMultiRespawn.getInstance().getConfigDir(),
                TypeToken.of(MainEntity.class)
        );
    }
}
