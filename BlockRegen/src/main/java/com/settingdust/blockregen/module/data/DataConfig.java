package com.settingdust.blockregen.module.data;

import com.google.common.reflect.TypeToken;
import com.settingdust.blockregen.BlockRegen;
import com.settingdust.dustcore.api.Config;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializerCollection;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class DataConfig extends Config {
    public DataConfig() {
        super(Paths.get("data.conf"),
                BlockRegen.getInstance().getConfigDir(),
                TypeToken.of(DataEntity.class)
        );
    }

    @Override
    public void load(Path configPath) {
        // Register serializers
        TypeSerializerCollection serializers = this.getOptions().getSerializers();
        serializers.registerType(TypeToken.of(Date.class), new DateSerializer());
        this.getOptions().setSerializers(serializers);

        super.load(configPath);
    }
}
