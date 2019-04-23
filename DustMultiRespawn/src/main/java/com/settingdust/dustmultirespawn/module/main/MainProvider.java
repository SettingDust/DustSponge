package com.settingdust.dustmultirespawn.module.main;

import com.google.common.reflect.TypeToken;
import com.settingdust.dustcore.api.ConfigProvider;
import com.settingdust.dustmultirespawn.module.main.entity.MainEntity;
import com.settingdust.dustmultirespawn.module.main.entity.SyncEntity;
import lombok.Getter;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.io.IOException;
import java.util.Objects;

public class MainProvider extends ConfigProvider<MainEntity> {
    @Getter
    private boolean syncWarp;

    public MainProvider() {
        super(new MainConfig(), new MainEntity());
        this.load();
        syncWarp = entity.getSync().isWarp();
    }

    @Override
    public MainEntity get() {
        return entity;
    }

    @Override
    public void load() {
        this.config = new MainConfig();
        try {
            if (Objects.isNull(config.getRoot().getValue())) {
                this.entity = new MainEntity();
            } else {
                this.entity = config.getRoot().getValue(TypeToken.of(MainEntity.class));
            }
            this.config.save(this.entity);
        } catch (ObjectMappingException | IOException e) {
            e.printStackTrace();
        }
    }

    public SyncEntity getSync() {
        return entity.getSync();
    }
}
