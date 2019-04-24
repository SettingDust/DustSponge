package com.settingdust.dustmultirespawn.module.spawn;

import com.google.common.reflect.TypeToken;
import com.settingdust.dustcore.api.Config;
import com.settingdust.dustmultirespawn.DustMultiRespawn;

import java.nio.file.Paths;

public class SpawnsConfig extends Config {
    public SpawnsConfig() {
        super(Paths.get("spawns.conf"),
                DustMultiRespawn.getInstance().getConfigDir(),
                TypeToken.of(SpawnsEntity.class));
    }
}
